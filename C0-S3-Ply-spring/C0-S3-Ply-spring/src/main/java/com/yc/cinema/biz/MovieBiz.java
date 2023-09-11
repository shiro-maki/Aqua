package com.yc.cinema.biz;

import com.yc.cinema.dao.ActorDao;
import com.yc.cinema.dao.MovieActorDao;
import com.yc.cinema.dao.MovieImageDao;
import com.yc.cinema.dao.MovieTypeDao;

public class MovieBiz {

	private MovieActorDao madao;
	
	private ActorDao adao;

	private MovieImageDao midao;

	private MovieTypeDao mtdao;

	public MovieActorDao getMadao() {
		return madao;
	}

	public void setMadao(MovieActorDao madao) {
		this.madao = madao;
	}

	public ActorDao getAdao() {
		return adao;
	}

	public void setAdao(ActorDao adao) {
		this.adao = adao;
	}

	public MovieImageDao getMidao() {
		return midao;
	}

	public void setMidao(MovieImageDao midao) {
		this.midao = midao;
	}

	public MovieTypeDao getMtdao() {
		return mtdao;
	}

	public void setMtdao(MovieTypeDao mtdao) {
		this.mtdao = mtdao;
	}

}
