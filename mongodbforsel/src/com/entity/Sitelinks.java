package com.entity;

import org.bson.types.ObjectId;

public class Sitelinks {

	//badges site title 
	private ObjectId _id;
	private String id;
	private String badges;
	private String site;
	private String title;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBadges() {
		return badges;
	}
	public void setBadges(String badges) {
		this.badges = badges;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Sitelinks() {
		super();
	}
	public Sitelinks(ObjectId _id, String id, String badges, String site, String title) {
		super();
		this._id = _id;
		this.id = id;
		this.badges = badges;
		this.site = site;
		this.title = title;
	}
	public Sitelinks(String id, String badges, String site, String title) {
		super();
		this.id = id;
		this.badges = badges;
		this.site = site;
		this.title = title;
	}
	@Override
	public String toString() {
		return "Sitelinks [_id=" + _id + ", id=" + id + ", badges=" + badges + ", site=" + site + ", title=" + title
				+ "]";
	}
	
	
}
