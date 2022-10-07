package com.board.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public boolean isId(String id) {
		
		//시작은 영문으로만, 영문, 숫자로만 이루어진 5 ~ 12자 이하
		boolean result = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", id);
		
		if(!result) {	//사용할 수 없는 아이디 형식
			return false;
		}else {	//사용 가능한 아이디 형식
			int idCnt = userService.isId(id);
			
			if(idCnt == 0) {
				return true;
			}else {
				return false;
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
			rttr.addFlashAttribute("insert-success", 1);
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
	
}
