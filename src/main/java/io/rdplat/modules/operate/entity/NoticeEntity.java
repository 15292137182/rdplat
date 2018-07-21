package io.rdplat.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.rdplat.common.base.BaseEntity;

/**
 * 运营_公告
 * 
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018-07-19 11:13:20
 */
@TableName("operate_notice")
public class NoticeEntity extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId
	private Long pId;
	/**
	 * 语言类型
	 */
	private String langType;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private String publicTime;
	/**
	 * 状态
	 */
	private Long status;

	/**
	 * 消息类型
	 */
	private String msgType;

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	/**
	 * 设置：语言类型
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语言类型
	 */
	public String getLangType() {
		return langType;
	}
	/**
	 * 设置：主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 获取：主题
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}
	/**
	 * 获取：发布时间
	 */
	public String getPublicTime() {
		return publicTime;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Long status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Long getStatus() {
		return status;
	}
}
