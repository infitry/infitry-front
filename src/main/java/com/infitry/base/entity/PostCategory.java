package com.infitry.base.entity;

import java.util.Date;

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
	private String name;				//카테고리명
	private String regUser;				//생성자
	private Date regDate;				//생성일
}
