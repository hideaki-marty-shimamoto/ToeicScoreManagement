package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザ情報 検索用リクエストデータ
 */
@Data
public class UserSearchRequest implements Serializable{
	//	ユーザID
	private Long id;
	//	ユーザ名
	private String name;
}
