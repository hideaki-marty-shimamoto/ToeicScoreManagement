package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

//ユーザー新規登録 リクエストデータ
@Data
public class UserAddRequest implements Serializable {
	//	名前
	@NotEmpty(message = "名前を入力してください")
	@Size(max = 100, message = "名前は100文字以内で入力してください")
	private String name;
	
	//	得点
	@Range(min = 10, max = 990, message = "得点は10～990の範囲で入力してください")
	private int score;
	
	//	受験日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

}
