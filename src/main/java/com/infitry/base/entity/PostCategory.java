package com.infitry.base.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @since 2020. 4. 1.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : blogPostCategory Entity
 */
@Data
public class PostCategory {
	private long blogPostCategorySeq;	//카테고리 일련번호
	private List<BlogPost> blogPosts;	//카테고리 내 포함된 포스트
	private int count;					//카테고리 내 포함된 포스트 갯 수
	private String name;				//카테고리명
	private String regUser;				//생성자
	private Date regDate;				//생성일
}
