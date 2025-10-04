
package com.cooperative.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cooperative.entity.Cooperative;
import com.cooperative.entity.Role;
import com.cooperative.entity.User;
import com.cooperative.entity.Wallet;
import com.cooperative.model.CooperativeModel;
import com.cooperative.model.UserModel;
import com.cooperative.service.CommonService;
import com.cooperative.utility.PropertyFetcher;
import com.cooperative.utility.StaticData;
import com.cooperative.utility.WebConstants.FileName;

@RestController
@Transactional
public class UserController {
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loadLoginPage(Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("Login");
		modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
		if (session.getAttribute("usmId")!= null){
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutApp(HttpSession session,Locale locale) {
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("Login");
		return modelAndView;
	}
	

	// Check Login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checkLogin(UserModel userModel, Model model,HttpSession session,Locale locale) {
		ModelAndView modelAndView = new ModelAndView("index");// from tiles

		User user = (User) commonService.fetchObjectByNamedQuery("checkUser", "userName="+userModel.getUserName()+"=string&"
				+ "password="+userModel.getPassword()+"=string");
		if(user!=null){
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("roleId", user.getRoleId().getRoleId());
			if (user.getCopId()!= null){
				session.setAttribute("copId", user.getCopId().getCopId());
				session.setAttribute("copName", user.getCopId().getCopName());
				
				if(user.getCopId().getCopLogo()!=null) {
					try {
						Path imagePath = Paths.get(user.getCopId().getCopLogo());
						byte[] imageBytes = Files.readAllBytes(imagePath);
						String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
						session.setAttribute("copLogo", base64Image);
					}catch (IOException e) {
						e.printStackTrace();
					}
				} 
				
				Wallet wallet = (Wallet) commonService.fetchObjectByNamedQuery("fetchWalletByCooprative", "copId="+user.getCopId().getCopId()+"=long");
				if(wallet!= null && wallet.getBalancedAmount()>0d){
					session.setAttribute("walletAmount", wallet.getBalancedAmount()+"");
				}
			}

			modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
			
			String where = "";
			if (user.getRoleId().getRoleId().equals(1l)){
				where += " AND cop_id = "+user.getCopId().getCopId();
			}
			if (user.getRoleId().getRoleId().equals(2l)){
				where += " AND cop_id = "+user.getCopId().getCopId()+" AND created_by = "+user.getUserId();
			}
				
			List<Object[]> lstAllDocuments = commonService.fetchByNativeQuery("select count(doc_id) as total,"
					+ " coalesce(sum(case when api_status IN ( '200', '422') then 1 else 0 end),0) as passed,"
					+ " coalesce(sum(case when api_status NOT IN ( '200', '422') then 1 else 0 end),0) as failed from tbl_document where 1=1 "+where);
			
			model.addAttribute("total", lstAllDocuments.get(0)[0]);
			model.addAttribute("total_success", lstAllDocuments.get(0)[1]);
			model.addAttribute("total_failed", lstAllDocuments.get(0)[2]);
			
			String where1 = "";
			if (user.getRoleId().getRoleId().equals(1l)){
				where1 += " AND d.cop_id = "+user.getCopId().getCopId();
			}
			List<Object[]> lstCorp = commonService.fetchByNativeQuery("select c.cop_name, count(*) as count from tbl_document d JOIN tbl_cooperative c ON d.cop_id = c.cop_id "
					+ "  where 1=1 "+ where1 +" group by c.cop_name");
			model.addAttribute("lstCorp", lstCorp);
			
			List<Object[]> lstUser = commonService.fetchByNativeQuery("select u.first_name||' '||u.last_name as userName, count(*) as count "
					+ " from tbl_document d JOIN tbl_user u ON u.user_id = d.created::::bigint_by where 1=1 "+ where1 +" group by userName");
			model.addAttribute("lstUser", lstUser);
			
		}else{
			modelAndView = new ModelAndView("Login");
		}
		
		return modelAndView;
	}

		@RequestMapping(value = "/institution", method = RequestMethod.GET)
		public ModelAndView loadCooperative(Model model,HttpSession session,Locale locale) {
			ModelAndView modelAndView = new ModelAndView("cooperative");// from tiles
			modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
			if (session.getAttribute("userId")!= null){
				
				List<CooperativeModel> lstModel = mapCooperativeEntityToModel();
				
				model.addAttribute("lstCooperative", lstModel);

			}
			return modelAndView;
		}

		private List<CooperativeModel> mapCooperativeEntityToModel() {
			List<Cooperative> lstCooperative = commonService.fetchByNamedQuery("fetchCooprativeList","");
			List<CooperativeModel> lstModel = new ArrayList<CooperativeModel>();
			for (Cooperative cooperative : lstCooperative) {
				CooperativeModel cooperativeModel = new CooperativeModel();
				cooperativeModel.setCopId(cooperative.getCopId());
				cooperativeModel.setCopName(cooperative.getCopName());
				cooperativeModel.setCopRegNo(cooperative.getCopRegNo());
				cooperativeModel.setCopAddress(cooperative.getCopAddress());
				cooperativeModel.setIsActive(cooperative.getIsActive());
				try {
					if(cooperative.getCopLogo()!=null) {
						Path imagePath = Paths.get(cooperative.getCopLogo());
						byte[] imageBytes = Files.readAllBytes(imagePath);
						String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
						cooperativeModel.setLogoBytes(base64Image);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				lstModel.add(cooperativeModel);
			}
			return lstModel;
		}


		@RequestMapping(value = "/institution", method = RequestMethod.POST)
		public ModelAndView saveCooperative(@ModelAttribute  CooperativeModel copModel, Model model, HttpSession session, Locale locale) {
			ModelAndView modelAndView = new ModelAndView("cooperative");
			modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
			String str = null;
			if (session.getAttribute("userId")!= null){
				try {
					Cooperative cooperative = null;
					if (copModel.getCopId() != null) {
						cooperative = (Cooperative) commonService.findById(Cooperative.class,copModel.getCopId());
						cooperative.setUpdatedBy((Long)session.getAttribute("userId"));
						cooperative.setUpdatedDate(new Date());
						str = "Record updated successfully";
					} else {
						cooperative = new Cooperative();
						cooperative.setCreatedBy((Long)session.getAttribute("userId"));
						cooperative.setCreatedDate(new Date());
						str = "New record added successfully";
					}
					cooperative.setCopAddress(copModel.getCopAddress());
					cooperative.setCopName(copModel.getCopName());
					cooperative.setCopRegNo(copModel.getCopRegNo());
					cooperative.setIsActive('Y');
					
					if (copModel.getLogo() != null && !copModel.getLogo().getOriginalFilename().equals("")) {
						MultipartFile multipartFile = copModel.getLogo();
						String filePath  ="";
						String osName = System.getProperty("os.name");
						if (osName.toLowerCase().contains("windows")) {
							 filePath = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG, "WINDOWS_USER_DOCUMENT");
						}else 
							 filePath = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG, "OTHER_USER_DOCUMENT");
						
						String fileName = multipartFile.getOriginalFilename();
						try {
							if (fileName != null && !fileName.equals("")) {
								File file = new File(filePath);
								if (!file.exists()) {
									file.mkdir();
								}
								file = new File(filePath + File.separator + fileName);
								FileCopyUtils.copy(multipartFile.getBytes(), file);
								cooperative.setCopLogo(file.getPath());
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					commonService.saveOrUpdate(cooperative);
				} catch (Exception e) {
					e.printStackTrace();
					str = "Transaction Failed";
				}
			}
			List<CooperativeModel> lstModel = mapCooperativeEntityToModel();
			model.addAttribute("lstCooperative", lstModel);
			model.addAttribute("msg", str);
			return modelAndView;
		}

		@RequestMapping(value = "/loadCooperativeDetails", method = RequestMethod.GET)
		public @ResponseBody Map<String, Object> loadCooperativeDetails(Long copId) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				if (copId != null) {
					Cooperative cooperative = (Cooperative) commonService.findById(Cooperative.class,copId);
					if(cooperative!=null){
						map.put("copModel", cooperative);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
		
		@RequestMapping(value = "/activateDeactivateCoperative", method = RequestMethod.POST)
		public @ResponseBody Map<String, Object> activateDeactivateCoperative(Long copId) {
			Map<String, Object> map = new HashMap<String, Object>();
			String str = null;
			try {
				if (copId != null) {
					Cooperative cooperative = (Cooperative) commonService.findById(Cooperative.class,copId);
					
					if (cooperative.getIsActive() != null && cooperative.getIsActive() == 'Y')
						cooperative.setIsActive('N');
					else if (cooperative.getIsActive() == null || cooperative.getIsActive() == 'N')
						cooperative.setIsActive('Y');
					
					commonService.saveOrUpdate(cooperative);
					str = "Record updated successfully";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				str = "Transaction Failed";
			}
			map.put("message", str);
			return map;
		}
			
			@RequestMapping(value = "/userRegistration", method = RequestMethod.GET)
			public ModelAndView userList(Model model,HttpSession session,Locale locale) {
				ModelAndView modelAndView = new ModelAndView("UserRegistration");// from tiles
				modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
				if (session.getAttribute("userId")!= null){
					List<User> lst = commonService.fetchByNamedQuery("fetchUserList","");
					model.addAttribute("lstUser", lst);
					List<Role> lst1 =commonService.fetchByNamedQuery("fetchActiveRoleList","");
					model.addAttribute("lstRole", lst1);
					List<Cooperative> lstCooperative =commonService.fetchByNamedQuery("fetchActiveCooperativeList","");
					model.addAttribute("lstCooperative", lstCooperative);
				}
				return modelAndView;
			}


			@RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
			public ModelAndView addEditUser(@ModelAttribute  UserModel userModel, Model model,HttpSession session,Locale locale) {
				ModelAndView modelAndView = new ModelAndView("UserRegistration");// from tiles
				modelAndView = StaticData.loadDataForHomepage(session, model, locale, modelAndView);
				String str = null;
				if (session.getAttribute("userId")!= null){
					try {
						User user = null;
						if (userModel.getUserId() != null) {
							user = (User) commonService.findById(User.class,userModel.getUserId());
							user.setUpdatedBy((Long)session.getAttribute("userId"));
							user.setUpdatedDate(new Date());
							str = "Record updated successfully";
						} else {
							user = new User();
							user.setCreatedBy((Long)session.getAttribute("userId"));
							user.setCreatedDate(new Date());
							str = "New record added successfully";
						}
						user.setUserName(userModel.getUserName());
						user.setPassword(userModel.getPassword());
						user.setFirstName(userModel.getFirstName());
						user.setMiddleName(userModel.getMiddleName());
						user.setLastName(userModel.getLastName());
						user.setEmail(userModel.getEmail());
						user.setMobileNo(userModel.getMobileNo());
						if(userModel.getRoleId()!=null){
							user.setRoleId((Role)  commonService.findById(Role.class,userModel.getRoleId()));
						}
						if(userModel.getCopId()!=null){
							user.setCopId((Cooperative)  commonService.findById(Cooperative.class,userModel.getCopId()));
						}
						user.setIsActive('Y');
						commonService.saveOrUpdate(user);
					} catch (Exception e) {
						e.printStackTrace();
						str = "Transaction Failed";
					}
				}
				List<User> lst = commonService.fetchByNamedQuery("fetchUserList","");
				model.addAttribute("lstUser", lst);
				List<Role> lst1 =commonService.fetchByNamedQuery("fetchActiveRoleList","");
				model.addAttribute("lstRole", lst1);
				List<Cooperative> lstCooperative =commonService.fetchByNamedQuery("fetchActiveCooperativeList","");
				model.addAttribute("lstCooperative", lstCooperative);
				
				model.addAttribute("msg", str);
				return modelAndView;
			}

			// Get User Details by ID
			@RequestMapping(value = "/loadUserDetails", method = RequestMethod.GET)
			public @ResponseBody Map<String, Object> loadItemDetails(Long userId) {
				Map<String, Object> map = new HashMap<String, Object>();
				try {
					if (userId != null) {
						User user= (User) commonService.findById(User.class, userId);
						if(user!=null){
							map.put("userModel", user);
						}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return map;
			}
			
			// Activate / Deactive User
			@RequestMapping(value = "/activateDeactivateUser", method = RequestMethod.POST)
			public @ResponseBody Map<String, Object> activateDeactivateUser(Long userId) {
				Map<String, Object> map = new HashMap<String, Object>();
				String str = null;
				try {
					User user = null;
					if (userId != null) {
						user = (User) commonService.findById(User.class,userId);
						user.setUpdatedDate(new Date());
						if (user.getIsActive() != null && user.getIsActive() == 'Y')
							user.setIsActive('N');
						else if (user.getIsActive() == null || user.getIsActive() == 'N')
							user.setIsActive('Y');
						commonService.saveOrUpdate(user);
						str = "Record updated successfully";
					}
				} catch (Exception e) {
					e.printStackTrace();
					str = "Transaction Failed";
				}
				map.put("message", str);
				return map;
			}

}
