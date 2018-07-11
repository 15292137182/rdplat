package io.rdplat.modules.oss.cloud;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import io.rdplat.common.exception.RRException;
import io.rdplat.common.utils.ServletUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 阿里云存储
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 16:22
 */
public class AliyunCloudStorageService extends CloudStorageService {
    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
        } catch (Exception e) {
            throw new RRException("上传文件失败，请检查配置信息", e);
        }

        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public void download(String path) throws Exception {
        String aliyunDomain = config.getAliyunDomain();
        String substring = path.substring(aliyunDomain.length() + 1, path.length());
        OSSObject object = client.getObject(config.getAliyunBucketName(), substring);
        InputStream content = object.getObjectContent();
        downloadFile(substring, content);
    }

    @Override
    public void delete(String path) {
        String aliyunDomain = config.getAliyunDomain();
        String substring = path.substring(aliyunDomain.length() + 1, path.length());
        //删除文件
        client.deleteObject(config.getAliyunBucketName(), substring);
    }


    /**
     * 批量下载文件
     *
     * @param path  路径
     * @param input input 输入流
     * @throws IOException
     */
    protected void downloadBatchFile(String path, InputStream input) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        HttpServletResponse response = ServletUtils.getResponse();
        String sub = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        if (response != null) {
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(sub + ".zip", "UTF-8"));
            OutputStream os = response.getOutputStream();
            String[] split = path.substring(path.lastIndexOf("/"), path.length()).split(",");
            for (String p : split) {
                zip.putNextEntry(new ZipEntry(p));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zip.write(temp);
                }
            }
            byte[] bytes = outputStream.toByteArray();
            os.write(bytes);
            os.close();
        }
    }
}
