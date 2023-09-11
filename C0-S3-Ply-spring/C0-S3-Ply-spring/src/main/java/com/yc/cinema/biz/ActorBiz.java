package com.yc.cinema.biz;

import com.yc.cinema.dao.ActorDao;
import org.springframework.stereotype.Service;

@Service
public class ActorBiz {
	
	private ActorDao adao;

	public ActorDao getAdao() {
		return adao;
	}

	public void setAdao(ActorDao adao) {
		this.adao = adao;
	}
	
}
