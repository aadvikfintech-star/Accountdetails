package com.cooperative.controller;

import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cooperative.entity.Cooperative;
import com.cooperative.entity.Document;
import com.cooperative.entity.SystemConfiguration;
import com.cooperative.entity.Wallet;
import com.cooperative.model.AccountDetail;
import com.cooperative.model.AccountDetailsData;
import com.cooperative.model.AccountDetailsResponse;
import com.cooperative.model.CibilData;
import com.cooperative.model.CibilResponse;
import com.cooperative.model.ProfileData;
import com.cooperative.model.ProfileResponse;
import com.cooperative.service.CommonService;
import com.cooperative.utility.PropertyFetcher;
import com.cooperative.utility.StaticData;
import com.cooperative.utility.WebConstants.FileName;

@RestController
@Transactional
public class ProfileController {
	
	@Autowired
	private CommonService commonService;
	
	private static String TOKEN = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG, "TOKEN");

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView loadProfile(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("profile");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		if (session.getAttribute("userId")!= null){
			List<SystemConfiguration> lstDocumentType = commonService.findByConfigType("Document_Type");
			model.addAttribute("lstDocumentType", lstDocumentType);

			Long copId  = (Long) session.getAttribute("copId");
			Long roleId = (Long) session.getAttribute("roleId");
			
			if(roleId.equals(0l))
				model.addAttribute("eligible", true);
			else 
				model.addAttribute("eligible", false);

			if(copId != null){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet!= null && wallet.getBalancedAmount()>0d){
					model.addAttribute("walletAmount", wallet.getBalancedAmount());
					model.addAttribute("eligible", true);
				}else{
					model.addAttribute("walletAmount", 0d);
					model.addAttribute("eligible", false);
				}
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/accountdetails", method = RequestMethod.GET)
	public ModelAndView accountdetails(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("accountdetails");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		if (session.getAttribute("userId")!= null){
			List<SystemConfiguration> lstDocumentType = commonService.findByConfigType("Document_Type");
			model.addAttribute("lstDocumentType", lstDocumentType);

			Long copId  = (Long) session.getAttribute("copId");
			Long roleId = (Long) session.getAttribute("roleId");
			
			if(roleId.equals(0l))
				model.addAttribute("eligible", true);
			else 
				model.addAttribute("eligible", false);

			if(copId != null){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet!= null && wallet.getBalancedAmount()>0d){
					model.addAttribute("walletAmount", wallet.getBalancedAmount());
					model.addAttribute("eligible", true);
				}else{
					model.addAttribute("walletAmount", 0d);
					model.addAttribute("eligible", false);
				}
			}
		}

		return modelAndView;
	}
	
	
	@RequestMapping(value = "/loadProfileDetails", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loadProfileDetails(String mobileNumber, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long copId  = (Long) session.getAttribute("copId");
		ResponseEntity<ProfileResponse> apiResponse = null;
		SystemConfiguration config = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			config = (SystemConfiguration) commonService.findById(SystemConfiguration.class, 6l);
			if (config != null && config.getApiURL() != null) {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    headers.set("Authorization", TOKEN);
			    JSONObject doc = new JSONObject();
			    doc.put("mobile", mobileNumber);
			    doc.put("name", name);
			    HttpEntity<String> request = new HttpEntity<String>(doc.toString(), headers);
				apiResponse  = restTemplate.postForEntity(config.getApiURL(), request, ProfileResponse.class);
				
			}else {
				InputStream inputStream = ProfileController.class.getResourceAsStream("/profile.json");
				ProfileResponse profileResponse = mapper.readValue(inputStream, ProfileResponse.class);
				map.put("response", profileResponse);
				
				apiResponse = new ResponseEntity<ProfileResponse>(profileResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("422 UNPROCESSABLE ENTITY")){
				ProfileResponse response1 = new ProfileResponse();
				response1.setStatus_code("422");
				response1.setMessage("Verification Failed.");
				response1.setData(new ProfileData("Test_client",name,mobileNumber));
				apiResponse = new ResponseEntity<ProfileResponse>(response1, HttpStatus.UNPROCESSABLE_ENTITY);
			} else if(e.getMessage().contains("401")){
				ProfileResponse response1 = new ProfileResponse();
				response1.setStatus_code("401");
				response1.setMessage("Unauthorized");
				response1.setData(new ProfileData("Test_client",name,mobileNumber));
				apiResponse = new ResponseEntity<ProfileResponse>(response1, HttpStatus.UNAUTHORIZED);
			} else if(e.getMessage().contains("500")){
				ProfileResponse response1 = new ProfileResponse();
				response1.setStatus_code("500");
				response1.setMessage("Internal API Issue");
				response1.setData(new ProfileData("Test_client",name,mobileNumber));
				apiResponse = new ResponseEntity<ProfileResponse>(response1, HttpStatus.INTERNAL_SERVER_ERROR);
			} else 
				e.printStackTrace();		
		}
		
		try {
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiResponse.getBody());
			 
			Document document = new Document();
			document.setCreatedDate(new Date());
			document.setCreatedBy((Long) session.getAttribute("userId"));
			document.setDocTypeId(config);
			document.setApiResponse(response);
			document.setDocNumber(mobileNumber);
			document.setApiStatus(apiResponse.getStatusCode().toString());
			if(copId != null){
				document.setCopId((Cooperative) commonService.findById(Cooperative.class, copId));
			}
			commonService.saveOrUpdate(document);
			
			if(copId != null && config != null && (apiResponse.getStatusCode().toString().equals("200") || apiResponse.getStatusCode().toString().equals("422")) ){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet != null){
					wallet.setBalancedAmount(wallet.getBalancedAmount()-config.getApiCharges());
					commonService.merge(wallet);
				}
			}
			if (response != null) {
				map.put("response", apiResponse.getBody());
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	

	@RequestMapping(value = "/cibil", method = RequestMethod.GET)
	public ModelAndView loadCibil(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("cibil");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		if (session.getAttribute("userId")!= null){
			List<SystemConfiguration> lstDocumentType = commonService.findByConfigType("Document_Type");
			model.addAttribute("lstDocumentType", lstDocumentType);

			Long copId  = (Long) session.getAttribute("copId");
			Long roleId = (Long) session.getAttribute("roleId");
			
			if(roleId.equals(0l))
				model.addAttribute("eligible", true);
			else 
				model.addAttribute("eligible", false);

			if(copId != null){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet!= null && wallet.getBalancedAmount()>0d){
					model.addAttribute("walletAmount", wallet.getBalancedAmount());
					model.addAttribute("eligible", true);
				}else{
					model.addAttribute("walletAmount", 0d);
					model.addAttribute("eligible", false);
				}
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/loadCibilReport", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loadCibilReport(String mobile, String name,String pan, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long copId  = (Long) session.getAttribute("copId");
		ResponseEntity<CibilResponse> apiResponse = null;
		ObjectMapper mapper = new ObjectMapper();
		SystemConfiguration config = null ;

		try {
			config = (SystemConfiguration) commonService.findById(SystemConfiguration.class, 7l);
			if (config != null && config.getApiURL() != null) {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    headers.set("Authorization", TOKEN);
			    JSONObject doc = new JSONObject();
			    doc.put("mobile", mobile);
			    doc.put("name", name);
			    doc.put("pan", pan);
			    doc.put("consent", "Y");
			    HttpEntity<String> request = new HttpEntity<String>(doc.toString(), headers);
				apiResponse  = restTemplate.postForEntity(config.getApiURL(), request, CibilResponse.class);
				
			}else {
				InputStream inputStream = ProfileController.class.getResourceAsStream("/cibil.json");
				CibilResponse cibilResponse = mapper.readValue(inputStream, CibilResponse.class);
				map.put("response", cibilResponse);
				
				apiResponse = new ResponseEntity<CibilResponse>(cibilResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			if(e.getMessage().contains("422 UNPROCESSABLE ENTITY")){
				CibilResponse response1 = new CibilResponse();
				response1.setStatus_code("422");
				response1.setMessage("Verification Failed.");
				response1.setData(new CibilData("Test_client",name,mobile,pan));
				apiResponse = new ResponseEntity<CibilResponse>(response1, HttpStatus.UNPROCESSABLE_ENTITY);
			} else if(e.getMessage().contains("401")){
				CibilResponse response1 = new CibilResponse();
				response1.setStatus_code("401");
				response1.setMessage("Unauthorized");
				response1.setData(new CibilData("Test_client",name,mobile,pan));
				apiResponse = new ResponseEntity<CibilResponse>(response1, HttpStatus.UNAUTHORIZED);
			} else if(e.getMessage().contains("500")){
				CibilResponse response1 = new CibilResponse();
				response1.setStatus_code("500");
				response1.setMessage("Internal API Issue");
				response1.setData(new CibilData("Test_client",name,mobile,pan));
				apiResponse = new ResponseEntity<CibilResponse>(response1, HttpStatus.INTERNAL_SERVER_ERROR);
			} else 
				e.printStackTrace();		
		}
			
		try{
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiResponse.getBody());
			
			Document document = new Document();
			document.setCreatedDate(new Date());
			document.setCreatedBy((Long) session.getAttribute("userId"));
			document.setDocTypeId(config);
			document.setApiResponse(response);
			document.setDocNumber(mobile);
			document.setApiStatus(apiResponse.getStatusCode().toString());
			if(copId != null){
				document.setCopId((Cooperative) commonService.findById(Cooperative.class, copId));
			}
			commonService.saveOrUpdate(document);
			
			if(copId != null && config != null && (apiResponse.getStatusCode().toString().equals("200") || apiResponse.getStatusCode().toString().equals("422"))){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet != null){
					wallet.setBalancedAmount(wallet.getBalancedAmount()-config.getApiCharges());
					commonService.merge(wallet);
				}
			}
			
			if (response != null) {
 				map.put("response", apiResponse.getBody());
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return map;
	}
	
	
	
	@RequestMapping(value = "/loadAccountsDetails", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loadAccountsDetails(String mobileNumber, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long copId  = (Long) session.getAttribute("copId");
		ResponseEntity<AccountDetailsResponse> apiResponse = null;
		SystemConfiguration config = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			config = (SystemConfiguration) commonService.findById(SystemConfiguration.class, 3l);
			if (config != null && config.getApiURL() != null) {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    headers.set("Authorization", TOKEN);
			    JSONObject doc = new JSONObject();
			    doc.put("mobile", mobileNumber);
			    //doc.put("name", name);
			    HttpEntity<String> request = new HttpEntity<String>(doc.toString(), headers);
				apiResponse  = restTemplate.postForEntity(config.getApiURL(), request, AccountDetailsResponse.class);
				
			}else {
				InputStream inputStream = ProfileController.class.getResourceAsStream("/accountdetail.json");
				AccountDetailsResponse profileResponse = mapper.readValue(inputStream, AccountDetailsResponse.class);
				map.put("response", profileResponse);
				
				apiResponse = new ResponseEntity<AccountDetailsResponse>(profileResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("422 UNPROCESSABLE ENTITY")){
			    AccountDetail accountDetail = new AccountDetail("Test_client", name, mobileNumber);

			    AccountDetailsData data = new AccountDetailsData();
			    data.setClient_id("Test_client");
			    data.setMobile_number(mobileNumber);
			    data.setDetails(Collections.singletonList(accountDetail));

			    AccountDetailsResponse response1 = new AccountDetailsResponse();
			    response1.setStatus_code(422);
			    response1.setMessage("Verification Failed.");
			    response1.setData(data);

			    apiResponse = new ResponseEntity<AccountDetailsResponse>(response1, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// 401 Unauthorized
			else if(e.getMessage().contains("401")){
			    AccountDetail accountDetail = new AccountDetail("Test_client", name, mobileNumber);

			    AccountDetailsData data = new AccountDetailsData();
			    data.setClient_id("Test_client");
			    data.setMobile_number(mobileNumber);
			    data.setDetails(Collections.singletonList(accountDetail));

			    AccountDetailsResponse response1 = new AccountDetailsResponse();
			    response1.setStatus_code(401);
			    response1.setMessage("Unauthorized");
			    response1.setData(data);

			    apiResponse = new ResponseEntity<AccountDetailsResponse>(response1, HttpStatus.UNAUTHORIZED);
			}

			// 500 Internal Server Error
			else if(e.getMessage().contains("500")){
			    AccountDetail accountDetail = new AccountDetail("Test_client", name, mobileNumber);

			    AccountDetailsData data = new AccountDetailsData();
			    data.setClient_id("Test_client");
			    data.setMobile_number(mobileNumber);
			    data.setDetails(Collections.singletonList(accountDetail));

			    AccountDetailsResponse response1 = new AccountDetailsResponse();
			    response1.setStatus_code(500);
			    response1.setMessage("Internal API Issue");
			    response1.setData(data);

			    apiResponse = new ResponseEntity<AccountDetailsResponse>(response1, HttpStatus.INTERNAL_SERVER_ERROR);
			} 
			else {
			    e.printStackTrace();
			}
		}
		
		try {
			// Hardcoded JSON string
//			String json = "{\n" +
//			"  \"data\": {\n" +
//			"    \"client_id\": \"mobile_to_multiple_account_xkshYhduqplMnoZtGrt\",\n" +
//			"    \"mobile_number\": \"9234567890\",\n" +
//			"    \"details\": [\n" +
//			"      {\n" +
//			"        \"full_name\": \"RAHUL SHARMA\",\n" +
//			"        \"account_number\": \"123456789012\",\n" +
//			"        \"ifsc\": \"HDFC0001234\"\n" +
//			"      },\n" +
//			"      {\n" +
//			"        \"full_name\": \"PRIYA VERMA\",\n" +
//			"        \"account_number\": \"987654321098\",\n" +
//			"        \"ifsc\": \"SBIN0005678\"\n" +
//			"      },\n" +
//			"      {\n" +
//			"        \"full_name\": \"AMAN GUPTA\",\n" +
//			"        \"account_number\": \"456789123456\",\n" +
//			"        \"ifsc\": \"ICIC0004321\"\n" +
//			"      }\n" +
//			"    ]\n" +
//			"  },\n" +
//			"  \"status_code\": 200,\n" +
//			"  \"success\": true,\n" +
//			"  \"message\": \"Success\",\n" +
//			"  \"message_code\": \"success\"\n" +
//			"}";

//			AccountDetailsResponse profileResponse = mapper.readValue(json, AccountDetailsResponse.class);

//			apiResponse =new ResponseEntity<>(profileResponse, HttpStatus.OK);

			String response = mapper.writerWithDefaultPrettyPrinter()
			        .writeValueAsString(apiResponse.getBody());
			Document document = new Document();
			document.setCreatedDate(new Date());
			document.setCreatedBy((Long) session.getAttribute("userId"));
			document.setDocTypeId(config);
			document.setApiResponse(response);
			document.setDocNumber(mobileNumber);
			document.setApiStatus(apiResponse.getStatusCode().toString());
			if(copId != null){
				document.setCopId((Cooperative) commonService.findById(Cooperative.class, copId));
			}
			commonService.saveOrUpdate(document);
			
			if(copId != null && config != null && (apiResponse.getStatusCode().toString().equals("200") || apiResponse.getStatusCode().toString().equals("422")) ){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
				if(wallet != null){
					wallet.setBalancedAmount(wallet.getBalancedAmount()-config.getApiCharges());
					commonService.merge(wallet);
				}
			}
			if (response != null) {
				map.put("response", apiResponse.getBody());
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
