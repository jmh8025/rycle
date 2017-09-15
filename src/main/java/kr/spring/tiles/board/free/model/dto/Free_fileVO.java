package kr.spring.tiles.board.free.model.dto;

import java.sql.Date;

public class Free_fileVO {
	
	private int file_no;
	private int no;
	private String file_name; 
	private String ufile_name; 
	private int cate_no; 
	
	// Getter/Setter

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getUfile_name() {
		return ufile_name;
	}

	public void setUfile_name(String ufile_name) {
		this.ufile_name = ufile_name;
	}

	public int getCate_no() {
		return cate_no;
	}

	public void setCate_no(int cate_no) {
		this.cate_no = cate_no;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Free_fileVO [file_no=" + file_no + ", no=" + no + ", file_name=" + file_name + ", ufile_name="
				+ ufile_name + ", cate_no=" + cate_no + "]";
	}
}
