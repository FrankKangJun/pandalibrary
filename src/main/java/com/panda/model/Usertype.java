package com.panda.model;

public class Usertype {
	String typeId;
	String typeName;
	Integer maxBorrowNum;
	String isDelete;
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getMaxBorrowNum() {
		return maxBorrowNum;
	}
	public void setMaxBorrowNum(Integer maxBorrowNum) {
		this.maxBorrowNum = maxBorrowNum;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
