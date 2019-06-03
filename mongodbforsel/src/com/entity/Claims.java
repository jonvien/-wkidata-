package com.entity;

import org.bson.types.ObjectId;

public class Claims {

	//mainsnak rank type references qualifiers qualifiers-order (顺序不一致 )
	private ObjectId _id;
	private String id;
	private String mainsnak;
	private String rank;
	private String type;
	private String references;
	private String qualifiers;
	private String qualifiersOrder;
	private String property;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
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
	public String getMainsnak() {
		return mainsnak;
	}
	public void setMainsnak(String mainsnak) {
		this.mainsnak = mainsnak;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getQualifiers() {
		return qualifiers;
	}
	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
	}
	public String getQualifiersOrder() {
		return qualifiersOrder;
	}
	public void setQualifiersOrder(String qualifiersOrder) {
		this.qualifiersOrder = qualifiersOrder;
	}
	public Claims() {
		super();
	}
	
	public Claims(String id, String property) {
		super();
		this.id = id;
		this.property = property;
	}
	public Claims(ObjectId _id, String id, String mainsnak, String rank, String type, String references,
			String qualifiers, String qualifiersOrder) {
		super();
		this._id = _id;
		this.id = id;
		this.mainsnak = mainsnak;
		this.rank = rank;
		this.type = type;
		this.references = references;
		this.qualifiers = qualifiers;
		this.qualifiersOrder = qualifiersOrder;
	}
	public Claims(String id, String mainsnak, String rank, String type, String references, String qualifiers,
			String qualifiersOrder) {
		super();
		this.id = id;
		this.mainsnak = mainsnak;
		this.rank = rank;
		this.type = type;
		this.references = references;
		this.qualifiers = qualifiers;
		this.qualifiersOrder = qualifiersOrder;
	}
	@Override
	public String toString() {
		return "Claims [_id=" + _id + ", id=" + id + ", mainsnak=" + mainsnak + ", rank=" + rank + ", type=" + type
				+ ", references=" + references + ", qualifiers=" + qualifiers + ", qualifiersOrder=" + qualifiersOrder
				+ "]";
	}
	
	
	
}
