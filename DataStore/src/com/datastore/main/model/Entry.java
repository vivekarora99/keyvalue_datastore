package com.datastore.main.model;


import org.json.simple.JSONObject;

public class Entry {
	String key;
	int timeToLive;
	JSONObject value;
	long creationDateTimeMillis;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public JSONObject getValue() {
		return value;
	}

	public void setValue(JSONObject value) {
		this.value = value;
	}

	public long getCreationDateTimeMillis() {
		return creationDateTimeMillis;
	}

	public void setCreationDateTimeMillis(long creationDateTimeMillis) {
		this.creationDateTimeMillis = creationDateTimeMillis;
	}

}
