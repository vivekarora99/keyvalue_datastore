package com.datastore.main;

import com.datastore.main.model.Entry;
import com.datastore.main.utils.EntryUtils;
import com.datastore.main.utils.Constants;
import org.json.simple.JSONObject;

import java.util.Date;


public final class DataStore {

	private String location = "";
	private String name = "";

	public DataStore() {
		try {
			location = Constants.defaultLocation;
			name = "datastore-" + EntryUtils.getProcessName();
		} catch (Exception exception) {

		}
	}

	public DataStore(String filePath) {
		try {
			location = filePath;
			name = "datastore-" + EntryUtils.getProcessName();
		} catch (Exception exception) {

		}

	}

	// CRD Operations

	public synchronized String create(String key, JSONObject value) {
		try {
			return create(key, value, -1);
		} catch (Exception exception) {
			return Constants.CREATE_OPERATION_FAILED;
		}
	}

	public synchronized String create(String key, JSONObject value,
			int timeToLive) {
		try {
			String filePath = location + "/" + name;
			if (!EntryUtils.isValidKeyName(key)) {
				return Constants.LIMIT_32_CHARS;
			}
			if (EntryUtils.isExistingKey(key, filePath)) {
				return Constants.FAILED_DUE_TO_KEY_ALREADY_AVAILABLE;
			}
			Entry entry = new Entry();
			entry.setKey(key);
			if (timeToLive > 0) {
				entry.setTimeToLive(timeToLive);
			} else {
				entry.setTimeToLive(-1);
			}
			entry.setValue(value);
			entry.setCreationDateTimeMillis(new Date().getTime());

			if (EntryUtils.createEntry(entry, filePath)) {
				return Constants.CREATE_OPERATION_SUCCESSFUL;
			} else {
				return Constants.CREATE_OPERATION_FAILED;
			}
		} catch (Exception exception) {
			return Constants.CREATE_OPERATION_FAILED;
		}
	}

	public synchronized Object read(String key) {
		try {
			String filePath = location + "/" + name;
			if (!EntryUtils.isValidKeyName(key)) {
				return Constants.LIMIT_32_CHARS;
			}
			if (!EntryUtils.isExistingKey(key, filePath)) {
				return Constants.FAILED_DUE_TO_KEY_NOT_AVAILABLE;
			}
			Entry entry = EntryUtils.readEntry(key, filePath);
			if (null != entry) {
				return entry.getValue();
			}
			return Constants.UNKNOWN_ERROR_PLEASE_TRY_AGAIN_LATER;
		} catch (Exception exception) {
			exception.printStackTrace();
			return Constants.UNKNOWN_ERROR_PLEASE_TRY_AGAIN_LATER;
		}
	}

	public synchronized Object delete(String key) {
		try {
			String filePath = location + "/" + name;
			if (!EntryUtils.isValidKeyName(key)) {
				return Constants.LIMIT_32_CHARS;
			}
			if (!EntryUtils.isExistingKey(key, filePath)) {
				return Constants.FAILED_DUE_TO_KEY_NOT_AVAILABLE;
			}
			if (EntryUtils.deleteEntry(key, filePath)) {
				return Constants.DELETION_SUCCESSFUL;
			}
			return Constants.RECORD_DELETION_FAILED;
		} catch (Exception exception) {
			exception.printStackTrace();
			return Constants.RECORD_DELETION_FAILED;
		}
	}
}
