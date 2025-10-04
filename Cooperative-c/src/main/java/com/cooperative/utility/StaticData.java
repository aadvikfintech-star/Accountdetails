package com.cooperative.utility;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.cooperative.model.UserModel;

public class StaticData{
	

	public static ModelAndView loadDataForHomepage(HttpSession session,Model model,
			Locale locale,ModelAndView view) {
		Long usmId= (Long) session.getAttribute("userId");
		if(usmId!=null) {
			UserModel usmModel = new UserModel();
			usmModel.setUserName((String) session.getAttribute("userName"));
			usmModel.setFirstName((String) session.getAttribute("firstName"));
			usmModel.setLastName((String) session.getAttribute("lastName"));
			usmModel.setRoleId((Long)session.getAttribute("roleId"));
			usmModel.setCopId((Long)session.getAttribute("copId"));
			usmModel.setCopName((String)session.getAttribute("copName"));
			usmModel.setCopLogo((String)session.getAttribute("copLogo"));
			usmModel.setWalletAmount((String)session.getAttribute("walletAmount"));
			
			model.addAttribute("user", usmModel);
			String language = locale.getLanguage();
			model.addAttribute("localeLanguage", language);
			model.addAttribute("currentLocale", locale.getDisplayLanguage());
		}else{
			view = new ModelAndView("Login");//from tiles
		}
		return view;
	}
}