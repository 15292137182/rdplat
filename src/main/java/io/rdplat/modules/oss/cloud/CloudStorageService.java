package io.rdplat.modules.oss.cloud;

import io.rdplat.common.utils.DateUtils;
import io.rdplat.common.utils.ServletUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 14:58
 */
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     */
    CloudStorageConfig config;

    /**
     * 文件路径
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + "/" + path;
        }

        return path + suffix;
    }

    /**
     * 下载文件
     *
     * @param path    路径
     * @param content input 输入流
     * @throws IOException
     */
    protected void downloadFile(String path, InputStream content) throws IOException {
        if (content != null) {
            HttpServletResponse response = ServletUtils.getResponse();
            String sub = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
            if (response != null) {
                //设置响应头和客户端保存文件名
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + URLEncoder.encode(sub + path.substring(path.lastIndexOf("."), path.length()), "UTF-8"));
                OutputStream os = response.getOutputStream();
                //循环写入输出流
                byte[] b = new byte[1024 * 10];
                int length;
                while ((length = content.read()) != -1) {
                    os.write(b, 0, length);
                }
                os.close();
            }
        }
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data   文件字节数组
     * @param suffix 后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param suffix      后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);


    /**
     * 下载文件
     *
     * @param path 需要下载文件的路径
     * @throws IOException
     */
    public abstract void download(String path) throws Exception;


    /**
     * 删除文件
     *
     * @param path 需要删除文件的路径
     */
    public abstract void delete(String path);


}
