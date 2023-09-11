package com.yc.cinema.biz;

import com.yc.cinema.dao.CinemaDao;

public class CinemaBiz {
	
	private UserBiz ubiz;
	
	private MovieBiz mbiz;
	
	private CinemaDao cdao;

	public UserBiz getUbiz() {
		return ubiz;
	}

	public void setUbiz(UserBiz ubiz) {
		this.ubiz = ubiz;
	}

	public MovieBiz getMbiz() {
		return mbiz;
	}

	public void setMbiz(MovieBiz mbiz) {
		this.mbiz = mbiz;
	}

	public CinemaDao getCdao() {
		return cdao;
	}

	public void setCdao(CinemaDao cdao) {
		this.cdao = cdao;
	}

}
