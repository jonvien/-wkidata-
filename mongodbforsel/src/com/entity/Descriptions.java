package com.entity;

import org.bson.types.ObjectId;

public class Descriptions {

	//_id id language value 
	private ObjectId _id;
	private String id;
	private String language;
	private String value;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Descriptions() {
		super();
	}
	public Descriptions(ObjectId _id, String id, String language, String value) {
		super();
		this._id = _id;
		this.id = id;
		this.language = language;
		this.value = value;
	}
	public Descriptions(String id, String language, String value) {
		super();
		this.id = id;
		this.language = language;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Descriptions [_id=" + _id + ", id=" + id + ", language=" + language + ", value=" + value + "]";
	}
	
}
