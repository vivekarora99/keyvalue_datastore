package com.datastore.main.utils;

public class Constants {
	public static final String defaultLocation = "C:\\Users\\Public\\Documents";
	public static final int MILLISECONDS = 1000;
	public static final int KEY_MAX_LENGTH = 32;

	// Messages
	public static final String LIMIT_32_CHARS = "Operation failed due to key length exceeded the limit(32chars)";
	public static final String FAILED_DUE_TO_KEY_ALREADY_AVAILABLE = "Operation failed due to key already available";
	public static final String FAILED_DUE_TO_KEY_NOT_AVAILABLE = "Operation failed due to key not available";
	public static final String CREATE_OPERATION_SUCCESSFUL = "Create operation successful";
	public static final String CREATE_OPERATION_FAILED = "Create operation failed";
	public static final String UNKNOWN_ERROR_PLEASE_TRY_AGAIN_LATER = "Rread operation failed due to unknown error, please try again later!";
	public static final String DELETION_SUCCESSFUL = "Record deletion successful";
	public static final String RECORD_DELETION_FAILED = "Record deletion failed";
}
