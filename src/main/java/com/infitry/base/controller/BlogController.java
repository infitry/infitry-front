package com.infitry.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.pageable.RestResponsePage;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	RestTemplate blogClient = new RestTemplate();
	
	@Value("${infitry.blog.url}")
	String blogUrl;
	
	@RequestMapping(value = "/post/list")
	public String list(Model model, Pageable paging) {
		
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 4. 2.
	 * @author leesw
	 * @description : post list 반환 axios 컨트롤러
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/post/axios-list")
	public RestResponsePage getList(Pageable paging) {
		RestResponsePage blogPostList = null;
		try {
			/* paging 처리를 위한 Pageable의 getPageSize이용, getPageNumber 이용
			 * paramter명으로 size, page 사용
			 * Page<Object> 형식으로 응답을 받기위해 Custom PageImpl생성. 
			 */
			blogPostList = blogClient.getForObject(blogUrl + "/blog/post/list?size=" + paging.getPageSize() 
				+ "&page=" + paging.getPageNumber(), RestResponsePage.class);
			logger.info(blogPostList.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		
		return blogPostList;
	}
	
	
	
	@RequestMapping(value = "/post/detail")
	public String detail() {
		return "blog/post/detail";
	}
}