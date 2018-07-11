package io.rdplat.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.rdplat.common.base.BaseEntity;

/**
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018年07月09日 09:51
 */

@TableName("sys_data_dict")
public class SysDataDictEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId
    private Long dictId;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 父名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 名称
     */
    private String name;


    /**
     * 字段key
     */
    private String key;

    /**
     * 字段值
     */
    private String value;

    /**
     * 排序
     */
    private String sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    @TableField(exist = false)
    private boolean open;


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
