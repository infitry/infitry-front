package com.infitry.base.component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.BlogPost;
import com.infitry.base.entity.PostCategory;
import com.infitry.base.pageable.RestResponsePage;

/**
 * @since 2020. 4. 8.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 블로그 컴포넌트
 */
@Component
public class BlogComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogComponent.class);
	
	@Value("${infitry.blog.url}")
	String blogUrl;
	
	RestTemplate blogClient = new RestTemplate();
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 카테고리 목록
	 */
	public List<PostCategory> getCategoryList() {
		PostCategory[] postCategoryList = null;
		try {
			postCategoryList = blogClient.getForObject(blogUrl + "/blog/category/list-all", PostCategory[].class);
		} catch (Exception e) {
			logger.error("[ERROR] - " + e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		return Arrays.asList(postCategoryList);
	}
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 블로그 목록
	 */
	@SuppressWarnings("rawtypes")
	public RestResponsePage getBlogList(BlogPost blogPost) {
		RestResponsePage blogPostList = null;
		try {
			/* paging 처리를 위한 Pageable의 getPageSize이용, getPageNumber 이용
			 * paramter명으로 size, page 사용
			 * Page<Object> 형식으로 응답을 받기위해 Custom PageImpl생성. 
			 */
			blogPostList = blogClient.postForObject(blogUrl + "/blog/post/list", blogPost, RestResponsePage.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		return blogPostList;
	}
	
	/**
	 * @since 2020. 4. 14.
	 * @author leesw
	 * @description : 블로그 상세
	 */
	public BlogPost getBlogDetail(long blogPostSeq) {
		BlogPost blogPost = null;
		try {
			blogPost = blogClient.getForObject(blogUrl + "/blog/post/detail?blogPostSeq=" + blogPostSeq, BlogPost.class);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		
		return blogPost;
	}
}
