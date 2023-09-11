package com.yc.cinema;

import org.junit.Assert;
import org.junit.Test;

import com.yc.cinema.web.IndexAction;
import com.yc.cinema.web.MovieAction;
import com.yc.cinema.web.UserAction;

public class BaseTest {

	private IndexAction iAction;

	private MovieAction mAction;

	private UserAction uAction;

	@Test
	public void test1() {
		Assert.assertNotNull(uAction);
		Assert.assertNotNull(uAction.getCbiz());
		Assert.assertNotNull(uAction.getUbiz());
		Assert.assertNotNull(uAction.getCbiz().getCdao());
		Assert.assertNotNull(uAction.getCbiz().getMdao());
		Assert.assertNotNull(uAction.getCbiz().getUbiz());
		Assert.assertNotNull(uAction.getUbiz().getUdao());
	}
	
	@Test
	public void test2() {
		Assert.assertNotNull(iAction);
		Assert.assertNotNull(iAction.getHbiz());
		Assert.assertNotNull(iAction.getMbiz());
		Assert.assertNotNull(iAction.getUbiz());
		Assert.assertNotNull(iAction.getHbiz().getHdao());
		Assert.assertNotNull(iAction.getHbiz().getMbiz());
		Assert.assertNotNull(iAction.getMbiz().getAdao());
		Assert.assertNotNull(iAction.getMbiz().getMadao());
		Assert.assertNotNull(iAction.getMbiz().getMidao());
		Assert.assertNotNull(iAction.getMbiz().getMtdao());
		Assert.assertNotNull(iAction.getUbiz().getUdao());
	}

	@Test
	public void test3() {
		Assert.assertNotNull(mAction);
		Assert.assertNotNull(mAction.getAbiz());
		Assert.assertNotNull(mAction.getCbiz());
		Assert.assertNotNull(mAction.getUbiz());
		Assert.assertNotNull(mAction.getAbiz().getAdao());
		Assert.assertNotNull(mAction.getCbiz().getCdao());
		Assert.assertNotNull(mAction.getCbiz().getMdao());
		Assert.assertNotNull(mAction.getCbiz().getUbiz());
		Assert.assertNotNull(mAction.getUbiz().getUdao());
	}

}
