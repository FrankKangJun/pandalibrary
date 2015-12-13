package com.panda.model;

public class ShortBook {
	 private String bookId;

	 private String bookName;
	 
	 private String press;
	 
	 private Byte surplus;
	 
	 private Integer remainNum;

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Byte getSurplus() {
		return surplus;
	}

	public void setSurplus(Byte surplus) {
		this.surplus = surplus;
	}
}
