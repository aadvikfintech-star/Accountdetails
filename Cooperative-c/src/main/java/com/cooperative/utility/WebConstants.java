package com.cooperative.utility;


public interface WebConstants {


	public interface APIUrl {
		String URL = "http://localhost:8082/eBillAPI-U/";//eBill APi url
		//String URL = "http://172.105.57.198:8080/eBillAPI/";//eBill APi url
	}
	
	public interface SessionData {
		String USERS = "Users";
		String CITIZEN = "Citizen";//Citizen Session
	}

	public interface FileName {
		String APPLICATION_CONFIG = "application-config";
		String VALIDATION = "validation";
		String QUERY_FILE = "QueryFile";
		public static final int BUFFER_SIZE = 4096;
	}
	
	
}
