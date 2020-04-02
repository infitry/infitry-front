package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

/**
 * @since 2020. 3. 31.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 유저 클래스
 */
@Data
public class User {
	private long userSeq;
	private String id;
	private String password;
	private String name;
	private Date regDate;
}
