package com.panda.model;

import java.util.Date;

public class ShortBooks {

	private String rank;
	private String bookName;
	private String press;
	private Short term;
	private Date borrowDate;
	private String borrowId;
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getRank() {
		return rank;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public Short getTerm() {
		return term;
	}
	public void setTerm(Short term) {
		this.term = term;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
