package kr.spring.tiles.board.free.model.dto;

import java.sql.Date;

public class Free_boardVO {
	private int no;
	private String id;
	private String writer;
	private String subject;
	private String content; 
	private Date k_date; // java.sql.Date
	private int readcount;
	private String cate_chk;

	// Getter/Setter

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getK_date() {
		return k_date;
	}

	public void setK_date(Date k_date) {
		this.k_date = k_date;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getCate_chk() {
		return cate_chk;
	}

	public void setCate_chk(String cate_chk) {
		this.cate_chk = cate_chk;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Free_boardVO [no=" + no + ", id=" + id + ", writer=" + writer + ", subject="
				+ subject + ", content=" + content + ", k_date=" + k_date + 
				", readcount=" + readcount +", cate_chk=" + cate_chk + "]";
	}

}
