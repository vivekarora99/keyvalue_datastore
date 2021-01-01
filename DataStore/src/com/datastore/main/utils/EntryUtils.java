package com.datastore.main.utils;

import com.datastore.main.model.Entry;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

public class EntryUtils {

	public static String getProcessName() {
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		return processName;
	}

	public static boolean isValidKeyName(String key) {
		if (key.length() > Constants.KEY_MAX_LENGTH) {
			return false;
		}
		return true;
	}

	public static boolean isExistingKey(String key, String filePath) {
		boolean isKeyExists = false;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		HashMap<String, Entry> entryMap = new HashMap<>();
		try {
			File file = new File(filePath);
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				entryMap = (HashMap<String, Entry>) objectInputStream
						.readObject();
				if (entryMap.containsKey(key)) {
					isKeyExists = true;
				}

				fileInputStream.close();
				objectInputStream.close();
			}
			if (isKeyExists) {
				Entry entry = entryMap.get(key);
				long currentDateTimeMillis = new Date().getTime();
				if (entry.getTimeToLive() > 0
						&& (currentDateTimeMillis - entry
								.getCreationDateTimeMillis()) >= (entry
								.getTimeToLive() * Constants.MILLISECONDS)) {
					// the object is expired, So remove from datastore
					entryMap.remove(key);
					fileOutputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(
							fileOutputStream);
					objectOutputStream.writeObject(entryMap);
					fileOutputStream.close();
					objectOutputStream.close();

					// Since object is removed the key is available for storage
					isKeyExists = false;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isKeyExists;
	}

	public static boolean createEntry(Entry entry, String filePath) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Entry> entryMap = null;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				// read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				entryMap = (HashMap<String, Entry>) objectInputStream
						.readObject();

				fileInputStream.close();
				objectInputStream.close();

				// add the new element
				entryMap.put(entry.getKey(), entry);

				// write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(entryMap);
				fileOutputStream.close();
				objectOutputStream.close();

				return true;
			} else {
				entryMap = new HashMap<String, Entry>();
				entryMap.put(entry.getKey(), entry);

				// write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(entryMap);
				fileOutputStream.close();
				objectOutputStream.close();

				return true;
			}
		} catch (Exception exception) {
			return false;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Entry readEntry(String key, String filePath) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Entry> entryMap = null;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				// read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				entryMap = (HashMap<String, Entry>) objectInputStream
						.readObject();

				fileInputStream.close();
				objectInputStream.close();
				return entryMap.get(key);
			} else {
				return null;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean deleteEntry(String key, String filePath) {

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Entry> entryMap = null;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				// read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				entryMap = (HashMap<String, Entry>) objectInputStream
						.readObject();

				fileInputStream.close();
				objectInputStream.close();

				// add the new element
				entryMap.remove(key);

				// write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(entryMap);
				fileOutputStream.close();
				objectOutputStream.close();

				return true;
			}
			return false;
		} catch (Exception exception) {
			return false;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
