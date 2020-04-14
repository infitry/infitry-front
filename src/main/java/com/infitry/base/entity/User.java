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
	private long userSeq;	//사용자 일련번호
	private String id;		//아이디
	private String password;//비밀번호
	private String name;	//유저명
	private Date regDate;	//생성일
}
