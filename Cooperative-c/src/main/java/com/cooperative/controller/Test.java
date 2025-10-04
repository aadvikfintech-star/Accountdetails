package com.cooperative.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.cooperative.model.AadharResponse;

public class Test {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTcwNTIyMzEwMSwianRpIjoiMzM3Mjc0ZDktY2E4ZS00MDk2LThiZTYtMTFiNThjNWE0OTBhIiwidHlwZSI6ImFjY2VzcyIsImlkZW50aXR5IjoiZGV2LnNhbnRvc2hAc3VyZXBhc3MuaW8iLCJuYmYiOjE3MDUyMjMxMDEsImV4cCI6MTcwNjA4NzEwMSwidXNlcl9jbGFpbXMiOnsic2NvcGVzIjpbInVzZXIiXX19.fKij0b97mdCUoaO_DZFKsEG87zaL2pZzSU9aI6GaqRg");
	    JSONObject doc = new JSONObject();
	    doc.put("id_number", "735405373235");
	    

	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(doc.toString(), headers);
	    
	    AadharResponse response = restTemplate.postForObject("https://sandbox.surepass.io/api/v1/aadhaar-validation/aadhaar-validation", request, AadharResponse.class);
	    System.out.print(response);
	}
}
