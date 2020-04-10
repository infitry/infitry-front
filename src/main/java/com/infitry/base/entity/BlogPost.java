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
	private int pageSize;
	private int pageNumber;
	private long blogPostSeq;
	private long blogPostCategorySeq;
	private PostCategory postCategory;
	private String subject;
	private String description;
	private String contents;
	private String regUser;
	private Date regDate;
}
