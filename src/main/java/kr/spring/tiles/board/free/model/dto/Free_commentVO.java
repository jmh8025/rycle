package kr.spring.tiles.board.free.model.dto;

import java.sql.Date;

public class Free_commentVO {
	
	private int cmt_no;
	private int no;
	private int gr_no;
	private int gr_order;
	private int depth;

	private String content;
	
	private String id; 
	private String writer; 
	
	private Date k_date; // java.sql.Date
	
	// Getter/Setter
	
	public int getCmt_no() {
		return cmt_no;
	}

	public void setCmt_no(int cmt_no) {
		this.cmt_no = cmt_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getGr_no() {
		return gr_no;
	}

	public void setGr_no(int gr_no) {
		this.gr_no = gr_no;
	}

	public int getGr_order() {
		return gr_order;
	}

	public void setGr_order(int gr_order) {
		this.gr_order = gr_order;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getK_date() {
		return k_date;
	}

	public void setK_date(Date k_date) {
		this.k_date = k_date;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Free_commentVO [cmt_no=" + cmt_no + ", no=" + no + ", gr_no=" + gr_no + ", gr_order="
				+ gr_order + ", depth=" + depth + ", content=" + content 
				+ ", id=" + id + ", writer=" + writer + ", k_date=" + k_date + "]";
	}
}
