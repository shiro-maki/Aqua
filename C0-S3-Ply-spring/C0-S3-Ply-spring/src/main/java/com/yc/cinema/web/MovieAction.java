package com.yc.cinema.web;

import com.yc.cinema.biz.ActorBiz;
import com.yc.cinema.biz.CommentsBiz;
import com.yc.cinema.biz.UserBiz;

public class MovieAction {
	
	private CommentsBiz cbiz;
	
	private ActorBiz abiz;
	
	private UserBiz ubiz;

	public CommentsBiz getCbiz() {
		return cbiz;
	}

	public void setCbiz(CommentsBiz cbiz) {
		this.cbiz = cbiz;
	}

	public ActorBiz getAbiz() {
		return abiz;
	}

	public void setAbiz(ActorBiz abiz) {
		this.abiz = abiz;
	}

	public UserBiz getUbiz() {
		return ubiz;
	}

	public void setUbiz(UserBiz ubiz) {
		this.ubiz = ubiz;
	}

}
