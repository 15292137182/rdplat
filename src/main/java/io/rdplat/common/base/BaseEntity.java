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



    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


}
