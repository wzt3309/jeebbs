package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_grade table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_grade"
 */

public abstract class BaseBbsGrade  implements Serializable {

	public static String REF = "BbsGrade";
	public static String PROP_SCORE = "score";
	public static String PROP_GRADER = "grader";
	public static String PROP_POST = "post";
	public static String PROP_GRADE_TIME = "gradeTime";
	public static String PROP_ID = "id";
	public static String PROP_REASON = "reason";


	// constructors
	public BaseBbsGrade () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsGrade (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsGrade (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsPost post,
		com.jeecms.bbs.entity.BbsUser grader) {

		this.setId(id);
		this.setPost(post);
		this.setGrader(grader);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer score;
	private java.lang.String reason;
	private java.util.Date gradeTime;

	// many to one
	private com.jeecms.bbs.entity.BbsPost post;
	private com.jeecms.bbs.entity.BbsUser grader;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="GRADE_ID"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: SCORE
	 */
	public java.lang.Integer getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: SCORE
	 * @param score the SCORE value
	 */
	public void setScore (java.lang.Integer score) {
		this.score = score;
	}


	/**
	 * Return the value associated with the column: REASON
	 */
	public java.lang.String getReason () {
		return reason;
	}

	/**
	 * Set the value related to the column: REASON
	 * @param reason the REASON value
	 */
	public void setReason (java.lang.String reason) {
		this.reason = reason;
	}


	/**
	 * Return the value associated with the column: GRADE_TIME
	 */
	public java.util.Date getGradeTime () {
		return gradeTime;
	}

	/**
	 * Set the value related to the column: GRADE_TIME
	 * @param gradeTime the GRADE_TIME value
	 */
	public void setGradeTime (java.util.Date gradeTime) {
		this.gradeTime = gradeTime;
	}


	/**
	 * Return the value associated with the column: POST_ID
	 */
	public com.jeecms.bbs.entity.BbsPost getPost () {
		return post;
	}

	/**
	 * Set the value related to the column: POST_ID
	 * @param post the POST_ID value
	 */
	public void setPost (com.jeecms.bbs.entity.BbsPost post) {
		this.post = post;
	}


	/**
	 * Return the value associated with the column: user_id
	 */
	public com.jeecms.bbs.entity.BbsUser getGrader () {
		return grader;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param grader the user_id value
	 */
	public void setGrader (com.jeecms.bbs.entity.BbsUser grader) {
		this.grader = grader;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsGrade)) return false;
		else {
			com.jeecms.bbs.entity.BbsGrade bbsGrade = (com.jeecms.bbs.entity.BbsGrade) obj;
			if (null == this.getId() || null == bbsGrade.getId()) return false;
			else return (this.getId().equals(bbsGrade.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}