package com.infitry.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	@RequestMapping(value = "/list")
	public String list() {
		return "post/list";
	}
	
	@RequestMapping(value = "/detail")
	public String detail() {
		return "post/detail";
	}
}
