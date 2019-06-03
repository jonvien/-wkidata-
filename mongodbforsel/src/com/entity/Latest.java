package com.entity;

import org.bson.types.ObjectId;

public class Latest {

	private ObjectId _id;
	private String type;
	private String id;
	private String labels;
	private String descriptions ;
	private String aliases;
	private String claims;
	private String sitelinks;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	public String getClaims() {
		return claims;
	}
	public void setClaims(String claims) {
		this.claims = claims;
	}
	public String getSitelinks() {
		return sitelinks;
	}
	public void setSitelinks(String sitelinks) {
		this.sitelinks = sitelinks;
	}
	public Latest() {
		super();
	}
	public Latest(ObjectId _id, String type, String id, String labels, String descriptions, String aliases,
			String claims, String sitelinks) {
		super();
		this._id = _id;
		this.type = type;
		this.id = id;
		this.labels = labels;
		this.descriptions = descriptions;
		this.aliases = aliases;
		this.claims = claims;
		this.sitelinks = sitelinks;
	}
	public Latest(ObjectId _id, String type, String id) {
		super();
		this._id = _id;
		this.type = type;
		this.id = id;
	}
	public Latest(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Latest [_id=" + _id + ", type=" + type + ", id=" + id + ", labels=" + labels + ", descriptions="
				+ descriptions + ", aliases=" + aliases + ", claims=" + claims + ", sitelinks=" + sitelinks + "]";
	}
	
	
	
}
