package com.infitry.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infitry.base.component.BlogComponent;
import com.infitry.base.entity.BlogPost;
import com.infitry.base.pageable.RestResponsePage;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	
	@Autowired
	BlogComponent blogComponent;
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 포스트 페이지
	 */
	@RequestMapping(value = "/post/list")
	public String list(Model model, 
			@RequestParam(value="blogPostCategorySeq", required=false, defaultValue="0") long blogPostCategorySeq) {
		model.addAttribute("blogPostCategorySeq", blogPostCategorySeq);
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 4. 2.
	 * @author leesw
	 * @description : post list 반환 axios요청
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/post/axios-list")
	public RestResponsePage getList(BlogPost blogPost) {
		return blogComponent.getBlogList(blogPost);
	}
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : post 상세
	 */
	@RequestMapping(value = "/post/detail/{blogPostSeq}")
	public String detail(Model model, @PathVariable long blogPostSeq,
			@RequestParam(value="blogPostCategorySeq", required=false, defaultValue="0") long blogPostCategorySeq) {
		model.addAttribute("blogPost", blogComponent.getBlogDetail(blogPostSeq));
		model.addAttribute("blogPostCategorySeq", blogPostCategorySeq);
		return "blog/post/detail";
	}
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 경력 및 소개
	 */
	@RequestMapping(value = "/about")
	public String about(Model model) {
		
		return "blog/about/about.html";
	}
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 문의하기
	 */
	@RequestMapping(value = "/question")
	public String question(Model model) {
		
		return "blog/question/question.html";
	}
}