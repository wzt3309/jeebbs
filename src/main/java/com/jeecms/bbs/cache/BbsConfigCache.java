package com.jeecms.bbs.cache;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.bbs.entity.BbsUser;

@SuppressWarnings("serial")
public class BbsConfigCache implements Serializable {

	private int postToday;
	private int postYestoday;
	private int topicTotal;
	private int postTotal;
	private int postMax;
	private Date postMaxDate;
	private int userTotal;
	private BbsUser lastUser;

	public int getPostToday() {
		return postToday;
	}

	public void setPostToday(int postToday) {
		this.postToday = postToday;
	}

	public int getPostYestoday() {
		return postYestoday;
	}

	public void setPostYestoday(int postYestoday) {
		this.postYestoday = postYestoday;
	}

	public int getTopicTotal() {
		return topicTotal;
	}

	public void setTopicTotal(int topicTotal) {
		this.topicTotal = topicTotal;
	}

	public int getPostTotal() {
		return postTotal;
	}

	public void setPostTotal(int postTotal) {
		this.postTotal = postTotal;
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public int getPostMax() {
		return postMax;
	}

	public void setPostMax(int postMax) {
		this.postMax = postMax;
	}

	public Date getPostMaxDate() {
		return postMaxDate;
	}

	public void setPostMaxDate(Date postMaxDate) {
		this.postMaxDate = postMaxDate;
	}

	public BbsUser getLastUser() {
		return lastUser;
	}

	public void setLastUser(BbsUser lastUser) {
		this.lastUser = lastUser;
	}
}
