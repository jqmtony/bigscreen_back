package com.gochinatv.accelarator.bmapi.constants;



public class AreaConstants {

	public enum Status{
		HAS_GAME(2,"有效"),
		NO_GAME(1,"无效");
		
		private int value;
		private String desc;
		
		public static Status getEnum(int value){
			for(Status orderType:values() ){
				if(orderType.value == value){
					return orderType;
				}
			}
			return null;
		}
		
		private Status(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
