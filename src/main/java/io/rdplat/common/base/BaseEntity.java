package io.rdplat.common.base;

import io.rdplat.common.utils.DateUtils;
import io.rdplat.common.utils.ShiroUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018年07月09日 09:53
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 修改时间
     */
    private String modifyTime;

    public void create(){
        Date date = new Date();
        this.createUser = ShiroUtils.getUserEntity().getUsername();
        this.createTime = DateUtils.formatH(date);
        this.modifyUser = ShiroUtils.getUserEntity().getUsername();
        this.modifyTime = DateUtils.formatH(date);
    }

    public void modify(){
        this.modifyUser = ShiroUtils.getUserEntity().getUsername();
        this.modifyTime = DateUtils.formatH(new Date());
    }



    /**
     * 设置：创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * 设置：创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * 获取：创建人
     */
    public String getCreateUser() {
        return createUser;
    }
    /**
     * 设置：修改时间
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    /**
     * 获取：修改时间
     */
    public String getModifyTime() {
        return modifyTime;
    }
    /**
     * 设置：修改人
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    /**
     * 获取：修改人
     */
    public String getModifyUser() {
        return modifyUser;
    }

}
