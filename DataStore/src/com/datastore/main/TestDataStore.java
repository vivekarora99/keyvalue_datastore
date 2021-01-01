package com.datastore.main;

import org.json.simple.JSONObject;

public class TestDataStore {
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("studentid", "101");
		jsonObject.put("studentname", "vivek");
		jsonObject.put("age", "21");
		System.out
				.println("CREATE:");
		// Create Operation
		// DataStore myDataStore = new DataStore();
		DataStore myDataStore = new DataStore(
				"C:\\Users\\Vivek\\Documents\\DataStore");
		System.out.println(myDataStore.create("100", jsonObject, 15));// success
		System.out.println(myDataStore.create("100", jsonObject));// failure
		System.out.println(myDataStore.create("100", jsonObject, 30));// failure
		System.out.println(myDataStore.create("2", jsonObject));// success
		System.out.println(myDataStore.create(
				"testkey", new JSONObject()));// failure
		try {
			// wait for 15 seconds
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("WAITED 15 seconds");
		jsonObject.put("group", "a+");
		System.out.println(myDataStore.create("100", jsonObject, 15));// success
		System.out.println(myDataStore.create("100", jsonObject));// failure
		System.out.println(myDataStore.create("100", jsonObject, 30));// failure
		System.out.println(myDataStore.create("2", jsonObject));// failure

		// Read Operation
		System.out
				.println("Read Operation:");
		System.out.println(myDataStore.read("100"));// success
		System.out.println(myDataStore.read("200"));// success
		System.out.println(myDataStore.read("300"));// failure
		System.out.println(myDataStore
				.read("testkey"));// failure
		try {
			// wait for 15 seconds
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("WAITED 15 seconds");
		System.out.println(myDataStore.read("100"));// failure
		System.out.println(myDataStore.read("200"));// success

		System.out
				.println("DELETE:");
		System.out.println(myDataStore.delete("100"));// failure
		System.out.println(myDataStore.delete("200"));// success
		System.out.println(myDataStore.delete("200"));// failure
		System.out.println(myDataStore.delete("300"));// failure
		System.out.println(myDataStore
				.delete("testkey"));// failure
	}
}
