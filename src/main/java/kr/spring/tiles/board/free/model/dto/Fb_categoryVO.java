package kr.spring.tiles.board.free.model.dto;

import java.sql.Date;

public class Fb_categoryVO {
	private String cate_chk;
	private String cate_name;
	
	// Getter/Setter

	public String getCate_chk() {
		return cate_chk;
	}

	public void setCate_chk(String cate_chk) {
		this.cate_chk = cate_chk;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	
	
	// toString()
	@Override
	public String toString() {
		return "Fb_categoryVO [cate_chk=" + cate_chk + ", cate_name=" + cate_name + "]";
	}

}
