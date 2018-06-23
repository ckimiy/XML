package com.xml.administrator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
	
	@RequestMapping(value= "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String redirectHome() {
		return "redirect:/index.html";
	}

}