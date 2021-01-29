package com.handong.handongapp.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	UserServiceImpl service;
	
	@RequestMapping(value="/login")
	public String login(String t, Model model) {
		return "login";
	}
	@RequestMapping(value="/loginOk", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, UserVO vo) {
		String returnURL="";
		if(session.getAttribute("login")!=null) {
			session.removeAttribute("login");
		}
		
		UserVO loginvo = service.getUser(vo);
		if(loginvo != null) {
			System.out.println("로그인 성공!"); //로그인 성공 
			session.setAttribute("login", loginvo);
			returnURL = "redirect:/board/list";
		}else {
			System.out.println("로그인 실패!");//로그인 실패 
			returnURL = "redirect:/login/login";
		}
		
		return returnURL;
	}
	
	//로그아웃 
	@RequestMapping(value="/logout")
	public String loginout(HttpSession session) {
		session.invalidate();
		return "redirect:/login/login";
	}
	
	
}
