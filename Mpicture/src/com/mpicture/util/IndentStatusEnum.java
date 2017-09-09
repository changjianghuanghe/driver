package com.mpicture.util;
/**
 * 订单状态Enum
 * @author admin
 *
 */
public enum IndentStatusEnum {
	ReturnOrder(0,"退回单"),
	NotOrders(1,"未接单"),
	Orders(2,"已接单"),
	WaitConfirm(3,"待确认"),
	MissionSuccess(4,"已结单"),
	UpdateOrder(5,"修改中"),
	Takeeffect(6,"未生效");
	
	private int status;
	private String describe;
	private IndentStatusEnum(int status, String describe) {
		this.status = status;
		this.describe = describe;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 通过传入值获取enum
	 * @param status
	 * @return
	 */
	public static IndentStatusEnum getIndentStatusEnum(Integer status){
		for(IndentStatusEnum Indentenum:IndentStatusEnum.values()){
			if(Indentenum.getStatus()==status){
				return Indentenum;
			}
		}
		return null;
	}
	
}
