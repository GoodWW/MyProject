package com.xunman.yibenjiapu.bean;

import java.io.Serializable;
import java.util.List;

public class SurnameList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5964925718666121681L;
	private List<ResultBean> result;

	public List<ResultBean> getResult() {
		return result;
	}

	public void setResult(List<ResultBean> result) {
		this.result = result;
	}

	public static class ResultBean {
		private String ancestors;
		private String ancestorsintr;
		private String celebrity;
		private String cradle;
		private String cradleintr;
		private String distribution;
		private int iD;
		private int number;
		private String percentage;
		private String surname;
		private String video;

		public String getAncestors() {
			return ancestors;
		}

		public void setAncestors(String ancestors) {
			this.ancestors = ancestors;
		}

		public String getAncestorsintr() {
			return ancestorsintr;
		}

		public void setAncestorsintr(String ancestorsintr) {
			this.ancestorsintr = ancestorsintr;
		}

		public String getCelebrity() {
			return celebrity;
		}

		public void setCelebrity(String celebrity) {
			this.celebrity = celebrity;
		}

		public String getCradle() {
			return cradle;
		}

		public void setCradle(String cradle) {
			this.cradle = cradle;
		}

		public String getCradleintr() {
			return cradleintr;
		}

		public void setCradleintr(String cradleintr) {
			this.cradleintr = cradleintr;
		}

		public String getDistribution() {
			return distribution;
		}

		public void setDistribution(String distribution) {
			this.distribution = distribution;
		}

		public int getID() {
			return iD;
		}

		public void setID(int iD) {
			this.iD = iD;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getPercentage() {
			return percentage;
		}

		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getVideo() {
			return video;
		}

		public void setVideo(String video) {
			this.video = video;
		}
	}

}
