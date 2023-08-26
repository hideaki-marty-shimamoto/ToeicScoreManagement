package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;

//	ユーザ情報 Controller
@Controller
public class UserController {
	
	//	UserServiceクラスのインスタンスuserServiceを生成
	@Autowired
	private UserInfoService userInfoService;
	
	// ユーザ情報一覧画面を表示
	@GetMapping({"/", "/home", "/status"})
	public String displaySearch(Model model) {
		List<UserInfo> userList = userInfoService.findAll();
		model.addAttribute("userlist", userList);
		model.addAttribute("userSearchRequest", new UserSearchRequest());
		return "user/search";
	}
	
	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー新規追加画面
	 */
	@GetMapping(value="/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("userAddRequest", new UserAddRequest());
		return "user/add";
	}
	
	/**
	 * ユーザー新規登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@PostMapping("/user/create")
	public String create(@Validated @ModelAttribute UserAddRequest userAddRequest, BindingResult result, Model model) {
		if(result.hasErrors()) {
			//	入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/add";
		}
		userInfoService.save(userAddRequest);
		return "redirect:/";
	}
	
	/**
	 * ユーザー情報検索
	 * @param userSearchRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@PostMapping("/user/search")
	public String search(@ModelAttribute UserSearchRequest userSearchRequest, Model model) {
		List<UserInfo> userList = userInfoService.search(userSearchRequest);
		model.addAttribute("userlist", userList);
		return "user/search";
	}
	
	/**
	 * ユーザー情報編集画面を表示
	 * @param id ユーザーID
	 * @param model Model
	 * @return ユーザー情報編集画面
	 */
	@GetMapping("/user/{id}/edit")
	public String displayEdit(@PathVariable Long id, Model model) {
		UserInfo userInfo = userInfoService.findById(id);
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userUpdateRequest.setId(userInfo.getId());
		userUpdateRequest.setName(userInfo.getName());
		userUpdateRequest.setScore(userInfo.getScore());
		userUpdateRequest.setDate(userInfo.getDate());
		model.addAttribute("userUpdateRequest", userUpdateRequest);
		return "user/edit";
	}
	
	/**
	 * ユーザー情報更新
	 * @param userUpdateRequestリクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@PostMapping("/user/update")
	public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/edit";
		}
		//	ユーザー情報の更新
		userInfoService.update(userUpdateRequest);
		return "redirect:/";
	}
	
	/**
	 * ユーザー情報削除（論理削除）
	 * @param id ユーザーID
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping("/user/{id}/delete")
	public String delete(@PathVariable Long id, Model model) {
		//	ユーザー情報の削除
		userInfoService.delete(id);
		return "redirect:/";
	}

}
