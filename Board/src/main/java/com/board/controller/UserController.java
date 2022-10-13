package com.board.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	/* 회원가입 화면 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signup() {
		System.out.println("회원가입 화면");
	}
	
	/* 아이디 정규식 검사 */
	@RequestMapping(value = "/isId", method = RequestMethod.GET)
	@ResponseBody
	public String isId(String id) {
		
		//시작은 영문으로만, 영문, 숫자로만 이루어진 5 ~ 12자 이하
		boolean result = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", id);
		
		String data_result = null;
		
		if(!result) {	//사용할 수 없는 아이디 형식
			data_result = "fail";
			
			return data_result;
			
		}else {	//사용 가능한 아이디 형식
			int idCnt = userService.isId(id);
			
			System.out.println("아이디 존재 유무 - idCnt : " + idCnt);
			
			if(idCnt == 0) {	//중복 아이디 X
				System.out.println("중복아이디가 없습니다. - idCnt : " + idCnt);
				
				data_result = "success";
				
				return data_result;
				
			}else {	//중복 아이디 O
				System.out.println("중복아이디 존재 - idCnt : " + idCnt);
				
				data_result = "fail";
				
				return data_result;
			}
		}
	}
	
	/* 회원가입 처리 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(UserDTO userDTO, RedirectAttributes rttr) {
		System.out.println("회원가입 처리 - userDTO : " + userDTO);
		
		try {
			//비밀번호 암호화
			String password = userDTO.getPassword();
			String crypto_pass = passwordEncoder.encode(password);
			
			userDTO.setPassword(crypto_pass);
			
			userService.signup(userDTO);
			rttr.addFlashAttribute("signup_success", "signup_success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	/* 로그인 처리 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO userDTO, HttpServletRequest request, RedirectAttributes rttr) {
		System.out.println("로그인 처리 - userDTO : " + userDTO);
		
		HttpSession session = request.getSession();
		String rawPass = "";
		String cryptoPass = "";
		
		UserDTO login = userService.login(userDTO);
		
		if(login != null) {	//일치하는 유저가 있는 경우
			rawPass = userDTO.getPassword();	//사용자가 제출한 비밀번호
			cryptoPass = login.getPassword();	//DB에서 꺼내온 비밀번호
			
			if(passwordEncoder.matches(rawPass, cryptoPass)) {	//비밀번호 일치
				login.setPassword("");	//인코딩된 비밀번호 지움
				
				session.setAttribute("login", login);	//session에 사용자의 정보 저장
				
				rttr.addFlashAttribute("result", 1);
				
				return "redirect:/board/list";	
			}else {	//비밀번호 불일치
				rttr.addFlashAttribute("result", 0);
				
				return "redirect:/";
			}
			
		}else {	//일치하는 유저가 없는 경우
			
			rttr.addFlashAttribute("result", 0);
			
			return "redirect:/";
		}
			
	}
	
	/* 로그아웃 처리 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		System.out.println("로그아웃 - session : " + session);
		
		session.invalidate();
	}
	
	/* 유저 리스트 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Criteria cri, Model model, HttpServletRequest request) {
		
		System.out.println("유저 리스트");
		
		List<UserDTO> userList = userService.list(cri);
		
		model.addAttribute("userList", userList);
		
		int total = userService.getTotal(cri);
		
		model.addAttribute("total", total);
		
		PageDTO pageMaker = new PageDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	/* 유저 정보 자세히 보기 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		System.out.println("로그인 유저 정보 자세히 보기 - login : " + login);
		
		UserDTO user = userService.read(login.getUserId());
		
		model.addAttribute("user", user);
	}
	
	/* 유저 정보 수정 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void update(HttpServletRequest request, Model model) {
		
		System.out.println("유저 업데이트 화면");
		
		HttpSession session = request.getSession();
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		UserDTO user = userService.read(login.getUserId());
		
		model.addAttribute("user", user);
	}
	
	/* 유저 정보 수정 처리 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(UserDTO userDTO, RedirectAttributes rttr) {
		
		System.out.println("유저 업데이트 처리 - userDTO : " + userDTO);
		
		String rawPassword = userDTO.getPassword();
		
		String cryptoPassowrd = passwordEncoder.encode(rawPassword);
		
		userDTO.setPassword(cryptoPassowrd);
		
		userService.update(userDTO);
		
		rttr.addFlashAttribute("update_success", "update_success");
		
		return "redirect:/user/read";
	}
	
	/* 유저 삭제 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("userId") String userId, HttpServletRequest request) {
		
		System.out.println("delete 처리 - userId : " + userId);
		
		int del_result = userService.delete(userId);
		
		String result = null;
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		if(del_result == 1) {
			result = "success";
		}
		
		return result;
	}
	
	/* 아이디 찾기 */
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() {
		
		System.out.println("아이디 찾기 화면");
		
	}
	
	/* 아이디 찾기 처리 */
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	@ResponseBody
	public String findId(String userName) {
		System.out.println("아이디 찾기 처리 - userName : " + userName);
		
		UserDTO findId = userService.findId(userName);
		
		if(findId != null) {
			
			return findId.getUserId();
		}
		
		return null;
	}
	
	/* 비밀번호 찾기 */
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public void findPw() {
		
		System.out.println("비밀번호 찾기 화면");
		
	}
	
	/* 비밀번호 찾기 처리 */
	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	@ResponseBody
	public String findPw(UserDTO userDTO) {
		
		System.out.println("비밀번호 찾기 처리 - userDTO : " + userDTO);
		
		String pw = "";
		
		UserDTO findDto = userService.findPw(userDTO);
		
		if(findDto != null) {
			UUID uid = UUID.randomUUID();
			pw = uid.toString().substring(0, 6);
			
			userDTO.setPassword(passwordEncoder.encode(pw));	//dto 객체에 암호화 시킨 비밀번호 저장 
			
			userService.setPwChange(userDTO);	//암호화 시킨 비밀번호 DB에 저장
			
			return pw;
		}
		
		return null;
	}
}
