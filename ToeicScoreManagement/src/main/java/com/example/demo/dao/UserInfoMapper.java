package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	/**
	 * ユーザ情報全件検索
	 * @param user 検索用リクエストデータ
	 * @return 全件検索
	 */
	List<UserInfo> findAll();
	
	/**
	 * ユーザー情報検索
	 * @param user 検索用リクエストデータ
	 * @return 検索結果
	 */
	List<UserInfo> search(UserSearchRequest user);
	
	/**
	 * ユーザー新規登録
	 * @param userAddRequest リクエストデータ
	 */
	void save(UserAddRequest userAddRequest);
	
	/**
	 * ユーザー情報主キー検索
	 * ※ユーザー情報編集画面表示時に共に表示する元のユーザ情報
	 * @param id 主キー
	 * @return 検索結果
	 */
	UserInfo findById(Long id);
	
	/**
	 * ユーザー情報更新
	 * ※ユーザー情報編集後の保存時に起動
	 * @param userUpdateRequest 更新用リクエストデータ
	 */
	void update(UserUpdateRequest userUpdateRequest);
	
	/**
	 * ユーザー情報の論理削除
	 * @param id ID
	 */
	void delete(Long id);
}
