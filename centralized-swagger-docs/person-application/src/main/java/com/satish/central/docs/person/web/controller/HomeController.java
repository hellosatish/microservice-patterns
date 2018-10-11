package com.satish.central.docs.person.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}
}
