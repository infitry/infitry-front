package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

/**
 * @since 2020. 4. 1.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : blogPost Entity
 */
@Data
public class BlogPost {
	private int pageSize;			//페이지 사이즈
	private int pageNumber;			//페이지 번호
	private long blogPostSeq;		//포스트 일련번호
	private long blogPostCategorySeq;	//포스트 카테고리 일련번호
	private String subject;				//제목
	private String description;			//간단설명
	private String contents;			//내용
	private String regUser;				//생성자
	private Date regDate;				//생성일
}
