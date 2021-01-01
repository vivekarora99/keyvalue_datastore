# DataStore
Problem Statement:
File-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store can be exposed as a library to clients that can instantiate a class and work with the data store.

<h2>How to use my library</h2>

<p>Create a project and add the DataStore dependency, then you will be able instantiate and use the DataStore for your project usecase. For now the DataStore is available as a jar dependency only.<br/>
<p>Here's an example given below</p>
<pre>
///////////////////////////////////////////////////////////////////////
     For Default Storage Location:
	DataStore myDataStore = new DataStore();
	// default location will be "C:\\Users\\Public\\Documents"
     For Given Storage Location:
	DataStore myDataStore = new DataStore(String filePath);//pass file location
///////////////////////////////////////////////////////////////////////
CRD Operations:
	  CREATE:
	  This Method is used to create an entry in the DataStore
	  myDataStore.create(String key, JSONObject value);
	  key-The key of the entry
	  value- The value of the entry
	  returns the status of operation
 
////////////////////////////////////////////////////////////////////// 
	  This Method is used to create an entry in the DataStore
	      myDataStore.create(String key, JSONObject value, int timeToLive);
	     key-  The key of the entry
	     value-  The value of the entry
	     timeToLive-  Number of seconds after which the entry is destroyed
	     returns status of the operation


///////////////////////////////////////////////////////////////////////
     READ:
         This Method is used to to read an entry from the DataStore
         myDataStore.read(String key)
         key- The key of the entry to read the entry
         returns value as type of JSONObject
       

////////////////////////////////////////////////////////////////////////
     DELETE:
         This Method is used to delete an entry from the DataStore
	 myDataStore.delete(String key)
	 where
         key-key of the entry to read the entry
	 returns status of the delete operation
//////////////////////////////////////////////////////////////////////	  
</pre>
