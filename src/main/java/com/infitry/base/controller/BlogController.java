package com.infitry.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infitry.base.component.BlogComponent;
import com.infitry.base.entity.BlogPost;
import com.infitry.base.pageable.RestResponsePage;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	BlogComponent blogComponent;
	
	@RequestMapping(value = "/post/list")
	public String list(Model model, long blogPostCategorySeq) {
		model.addAttribute("blogPostCategorySeq", blogPostCategorySeq);
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 4. 2.
	 * @author leesw
	 * @description : post list 반환 axios 컨트롤러
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/post/axios-list")
	public RestResponsePage getList(BlogPost blogPost) {
		return blogComponent.getBlogList(blogPost);
	}
	
	@RequestMapping(value = "/post/detail/{blogPostSeq}")
	public String detail(Model model, @PathVariable long blogPostSeq) {
		model.addAttribute("blogPost", blogComponent.getBlogDetail(blogPostSeq));
		return "blog/post/detail";
	}
}