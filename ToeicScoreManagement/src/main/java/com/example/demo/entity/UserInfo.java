package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//	entityクラス
@Data
public class UserInfo implements Serializable {
	//	id
	private Long id;
	//	名前
	@Size(min = 1, max = 100)
	private String name;
	//	得点
	@NotNull
	@Range(min = 10, max = 990)
	private Integer score;
	//	受験日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	//	更新日時
	@NotNull
	private Date updateDate;
	//	作成日時
	@NotNull
	private Date createDate;
	//	削除日時
	private Date deleteDate;
}
