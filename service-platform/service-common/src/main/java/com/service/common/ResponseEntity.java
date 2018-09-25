package com.service.common;

/**
 * @author yuanghuangd
 * @date 2018年9月21日 下午1:52:13
 */
public enum ResponseEntity {
	// RPC层调用错误码
	API_SERVICE_OK(200, "操作成功"), 
	API_SERVICE_FAILER(2001, "操作失败"), 
	REQUEST_UNAUTH(401, "无权限操作"), 
	DB_SERVICE_DBDAO_ERROR(20104, "返回数据库的具体异常信息"), 
	DB_SERVICE_SPEAKER_NOT_EXISTED(20108, "数据库中没有要查询的speaker"), 
	DB_SERVICE_SPEAKER_HAVE_NOT_VP(20109, "speaker中没有对应的voiceprint"), 
	DB_SERVICE_UNKNOWN_ERROR(20101, "未知异常"), 
	DB_SERVICE_AGENT_ERROR(20102, "DBServiceAgent异常"), 
	DB_SERVICE_NETWORK_ERROR(20103, "网络异常"), 
	DB_SERVICE_INVALID_FUNCTION(20105, "方法名不存在"),
	DB_SERVICE_INVALID_PARAMETER(20106, "方法参数错误"), 
	DB_SERVICE_FUNCTION_NO_ACCESS(20107, "对此方法无访问权限");

	/**
	 * 返回的错误消息
	 */
	private String msg;
	
	/**
	 * 错误码
	 */
	private int code;
	
	/**
	 * 返回的结果
	 */
	private Object data;

	private ResponseEntity(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getCode() {
		return this.code;
	}
	
	/**
	 * 设置：msg
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 获取：data
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * 设置：data
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
