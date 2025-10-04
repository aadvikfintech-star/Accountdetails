package com.cooperative.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.cooperative.entity.WalletHistory;
import com.cooperative.model.AadharData;
import com.cooperative.model.AadharResponse;
import com.cooperative.model.UserModel;
import com.cooperative.service.CommonService;
import com.cooperative.utility.PropertyFetcher;
import com.cooperative.utility.StaticData;
import com.cooperative.utility.WebConstants.FileName;

@RestController
@Transactional
public class DashboardController {
	
	@Autowired
	private CommonService commonService;
	
	private static String API_URL = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG, "AADHAR_API_URL");
	private static String TOKEN = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG, "TOKEN");
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView loadIndexPage(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		Long roleId = (Long) session.getAttribute("roleId");
		Long copId = (Long) session.getAttribute("copId");
		Long userId = (Long) session.getAttribute("userId");

		if (userId!= null){
			String where = "";
			if (roleId.equals(1l)){
				where += " AND cop_id = "+copId;
			}
			if (roleId.equals(2l)){
				where += " AND cop_id = "+copId+" AND created_by = "+userId;
			}
			
			List<Object[]> lstAllDocuments = commonService.fetchByNativeQuery("select count(doc_id) as total,"
					+ " coalesce(sum(case when api_status IN ( '200', '422') then 1 else 0 end),0) as passed,"
					+ " coalesce(sum(case when api_status NOT IN ( '200', '422') then 1 else 0 end),0) as failed from tbl_document where 1=1 "+where);
			
			model.addAttribute("total", lstAllDocuments.get(0)[0]);
			model.addAttribute("total_success", lstAllDocuments.get(0)[1]);
			model.addAttribute("total_failed", lstAllDocuments.get(0)[2]);
			
			String where1 = "";
			if (roleId.equals(1l)){
				where1 += " AND d.cop_id = "+copId;
			}
			List<Object[]> lstCorp = commonService.fetchByNativeQuery("select c.cop_name, count(*) as count from tbl_document d JOIN tbl_cooperative c ON d.cop_id = c.cop_id "
					+ "  where 1=1 "+ where1 +" group by c.cop_name");
			model.addAttribute("lstCorp", lstCorp);
			
			List<Object[]> lstUser = commonService.fetchByNativeQuery("select u.first_name||' '||u.last_name as userName, count(*) as count "
					+ " from tbl_document d JOIN tbl_user u ON u.user_id = d.created_by where 1=1 "+ where1 +" group by userName");
			model.addAttribute("lstUser", lstUser);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView report(Model model,String type,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("report");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		Long roleId = (Long) session.getAttribute("roleId");
		Long copId = (Long) session.getAttribute("copId");
		Long userId = (Long) session.getAttribute("userId");

		if (userId != null){
			String where = "";
			if (roleId.equals(1l))
				where += " AND d.cop_id = "+copId;

			if (roleId.equals(2l))
				where += " AND d.cop_id = "+copId+" AND d.created_by = "+userId;
			
			if(type != null && type.equals("S"))
				where += " AND api_status IN ( '200', '422') "; 

			if(type != null && type.equals("F"))
				where += " AND api_status NOT IN ( '200', '422') "; 
			
			List<Object[]> lstAllDocuments = commonService.fetchByNativeQuery("select to_char(d.created_date,'dd/MM/yyyy') as date, s.sys_name as document_type,"
					+ " doc_number, api_status, api_response, "
					+ " s.api_charges as api_charges, u.first_name||' '||u.last_name as user_name, c.cop_name as cop_name "
					+ " FROM tbl_document d "
					+ " JOIN system_configuration s on d.doc_type_id = s.id "
					+ " JOIN tbl_user u on u.user_id = d.created_by "
					+ " JOIN tbl_cooperative c on c.cop_id = d.cop_id "+where+" order by d.created_date desc ");
			model.addAttribute("lstAllDocuments", lstAllDocuments);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public ModelAndView loadTrackDocument(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("document");
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
	
	@RequestMapping(value = "/submitOTP", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> submitOTP(String otp,HttpSession session, String clientId, String docNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResponseEntity<AadharResponse> apiResponse =null;
		try {
			Long copId  = (Long) session.getAttribute("copId");

			try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    headers.set("Authorization", TOKEN);
			    JSONObject doc = new JSONObject();
			    doc.put("client_id", clientId);
			    doc.put("otp", otp);
			    HttpEntity<String> request = new HttpEntity<String>(doc.toString(), headers);
			    
			    apiResponse = restTemplate.postForEntity(API_URL, request, AadharResponse.class);
			    
			} catch (Exception e) {
				if(e.getMessage().contains("422 UNPROCESSABLE ENTITY")){
					AadharResponse response = new AadharResponse();
					response.setStatus_code("422");
					response.setMessage("Verification Failed.");
					response.setData(new AadharData(docNumber,"false","Invalid Number"));
					apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
				} else if(e.getMessage().contains("401")){
					AadharResponse response = new AadharResponse();
					response.setStatus_code("401");
					response.setMessage("Unauthorized");
					response.setData(new AadharData(docNumber,"false","Unauthorized"));
					apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.UNAUTHORIZED);
				} else if(e.getMessage().contains("500")){
					AadharResponse response = new AadharResponse();
					response.setStatus_code("500");
					response.setMessage("Internal API Issue");
					response.setData(new AadharData(docNumber,"false","Internal API Issue"));
					apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else 
					e.printStackTrace();	
			}
		    
			if(apiResponse != null && apiResponse.getBody().getData()!=null) {
				ObjectMapper mapper = new ObjectMapper();
				String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiResponse.getBody());
				map.put("response", response);
				map.put("address", apiResponse.getBody().getData().getAddress().toString());
					
				SystemConfiguration configuration = (SystemConfiguration) commonService.findById(SystemConfiguration.class, 1l);
				
				Document document = new Document();
				document.setCreatedDate(new Date());
				document.setCreatedBy((Long) session.getAttribute("userId"));
				document.setDocTypeId(configuration);
				document.setApiResponse(response);
				document.setDocNumber(apiResponse.getBody().getData().getAadhaar_number());
				document.setApiStatus(apiResponse.getStatusCode().toString());
				if(copId != null){
					document.setCopId((Cooperative) commonService.findById(Cooperative.class, copId));
				}
				commonService.saveOrUpdate(document);
				
				if(copId != null){
					Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
					if(wallet != null){
						wallet.setBalancedAmount(wallet.getBalancedAmount()-configuration.getApiCharges());
						commonService.saveOrUpdate(wallet);
					}
				}
					
					
		    	map.put("profile",  apiResponse.getBody().getData().getProfile_image());
		    }
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/loadDocumentDetails", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loadDocumentDetails(String docNumber, String birthDate, HttpSession session, Long docTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long copId  = (Long) session.getAttribute("copId");
		ResponseEntity<AadharResponse> apiResponse =null;
		try {
			 
			if (docTypeId != null) {
				SystemConfiguration config = (SystemConfiguration) commonService.findById(SystemConfiguration.class, docTypeId);
				
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    headers.set("Authorization", TOKEN);
			    JSONObject doc = new JSONObject();
			    doc.put("id_number", docNumber);
			    if (docTypeId == 4){
			    	doc.put("dob", birthDate);
			    }
			    HttpEntity<String> request = new HttpEntity<String>(doc.toString(), headers);
			    
			    apiResponse = restTemplate.postForEntity(config.getApiURL(), request, AadharResponse.class);
			}
		} catch (Exception e) {
			if(e.getMessage().contains("422 UNPROCESSABLE ENTITY")){
				AadharResponse response = new AadharResponse();
				response.setStatus_code("422");
				response.setMessage("Verification Failed.");
				response.setData(new AadharData(docNumber,"false","Invalid Number"));
				apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
			} else if(e.getMessage().contains("401")){
				AadharResponse response = new AadharResponse();
				response.setStatus_code("401");
				response.setMessage("Unauthorized");
				response.setData(new AadharData(docNumber,"false","Unauthorized"));
				apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.UNAUTHORIZED);
			} else if(e.getMessage().contains("500")){
				AadharResponse response = new AadharResponse();
				response.setStatus_code("500");
				response.setMessage("Internal API Issue");
				response.setData(new AadharData(docNumber,"false","Internal API Issue"));
				apiResponse = new ResponseEntity<AadharResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else 
				e.printStackTrace();	
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiResponse.getBody());

			if (docTypeId != null &&  !docTypeId.equals(1l) && !apiResponse.getStatusCode().is5xxServerError()) {
				
				SystemConfiguration configuration = (SystemConfiguration) commonService.findById(SystemConfiguration.class, docTypeId);
				
				Document document = new Document();
				document.setCreatedDate(new Date());
				document.setCreatedBy((Long) session.getAttribute("userId"));
				document.setDocTypeId(configuration);
				document.setApiResponse(response);
				document.setDocNumber(docNumber);
				document.setApiStatus(apiResponse.getStatusCode().toString());
				if(copId != null){
					document.setCopId((Cooperative) commonService.findById(Cooperative.class, copId));
				}
				commonService.saveOrUpdate(document);
				
				if(copId != null && (apiResponse.getStatusCode().toString().equals("200") || apiResponse.getStatusCode().toString().equals("422"))){
					Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
					if(wallet != null){
						wallet.setBalancedAmount(wallet.getBalancedAmount()-configuration.getApiCharges());
						commonService.saveOrUpdate(wallet);
					}
				}
			}
			map.put("response", response);
			if(apiResponse.getBody().getData().getAddress() != null)
				map.put("address", apiResponse.getBody().getData().getAddress().toString());
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public ModelAndView loadWallet(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("wallet");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		Long copId  = (Long) session.getAttribute("copId");
		Long roleId = (Long) session.getAttribute("roleId");
		
		List<Cooperative> lstCooperative =commonService.fetchByNamedQuery("fetchActiveCooperativeList","");
		model.addAttribute("lstCooperative", lstCooperative);
		
		List<Wallet> lstWallet =commonService.fetchByNamedQuery("fetchAllWalletByCooprative","");
		model.addAttribute("lstWallet", lstWallet);

		if(roleId.equals(1l) || roleId.equals(2l)) {
			Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+copId+"=long");
			if(wallet != null){
				model.addAttribute("walletAmount", wallet.getBalancedAmount());
			}else {
				model.addAttribute("walletAmount", 0l);
			}
		}else {
			model.addAttribute("walletAmount", 0l);
		}
		
		if(roleId.equals(1l)) {
			List<WalletHistory> lstWalletHistory =commonService.fetchByNamedQuery("fetchWalletHistoryByCooprative","copId="+copId+"=long");
			model.addAttribute("lstWalletHistory", lstWalletHistory);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/wallet", method = RequestMethod.POST)
	public ModelAndView saveWallet(@ModelAttribute UserModel userModel, Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("wallet");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		
		try {
			if (session.getAttribute("userId")!= null){
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+userModel.getCopId()+"=long");
				if(wallet == null){
					wallet = new Wallet(); 
					wallet.setAmount(userModel.getAmount());
					wallet.setBalancedAmount(userModel.getAmount());
					wallet.setCopId((Cooperative)  commonService.findById(Cooperative.class,userModel.getCopId()));
					wallet.setIsActive('Y');
					wallet.setCreatedBy((Long) session.getAttribute("userId"));
					wallet.setCreatedDate(new Date());
				} else {
					wallet.setAmount(wallet.getAmount() + userModel.getAmount());
					wallet.setBalancedAmount(wallet.getBalancedAmount() + userModel.getAmount());
					wallet.setUpdatedBy((Long) session.getAttribute("userId"));
					wallet.setUpdatedDate(new Date());
				}
				commonService.saveOrUpdate(wallet);
				WalletHistory history = new WalletHistory();
				history.setWalletId(wallet);
				history.setAmount(userModel.getAmount());
				history.setPaymentMode("CASH");
				history.setCreatedBy((Long) session.getAttribute("userId"));
				history.setCreatedDate(new Date());
				commonService.saveOrUpdate(history);
			}
			List<Cooperative> lstCooperative =commonService.fetchByNamedQuery("fetchActiveCooperativeList","");
			model.addAttribute("lstCooperative", lstCooperative);
			
			List<Wallet> lstWallet =commonService.fetchByNamedQuery("fetchAllWalletByCooprative","");
			model.addAttribute("lstWallet", lstWallet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}
