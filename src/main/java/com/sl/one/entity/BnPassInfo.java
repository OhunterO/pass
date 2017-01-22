package com.sl.one.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Created by shi on 2017/1/7.
 */
@TableName("BN_PASS_INFO")
public class BnPassInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    @TableField(value = "ID")
    private String id;

    /**
     * 名称
     */
    @TableField(value = "PASS_NAME")
    private String passName;

    /**
     * 密码
     */
    @TableField(value = "PASS_INFO")
    private String passInfo;

    /**
     * 盐
     */
    @TableField(value = "SALT")
    private String salt;

    /**
     * 备注
     */
    @TableField(value = "PASS_REMARK")
    private String passRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public String getPassInfo() {
        return passInfo;
    }

    public void setPassInfo(String passInfo) {
        this.passInfo = passInfo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassRemark() {
        return passRemark;
    }

    public void setPassRemark(String passRemark) {
        this.passRemark = passRemark;
    }
}
