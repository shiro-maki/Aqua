package com.yc.cinema.biz;

import com.yc.cinema.dao.HallDao;

public class HallBiz {
	
	private MovieBiz mbiz;
	
	private HallDao hdao;

	public MovieBiz getMbiz() {
		return mbiz;
	}

	public void setMbiz(MovieBiz mbiz) {
		this.mbiz = mbiz;
	}

	public HallDao getHdao() {
		return hdao;
	}

	public void setHdao(HallDao hdao) {
		this.hdao = hdao;
	}
	
}
