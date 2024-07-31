package db.shopping.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import db.shopping.model.vo.MemberVO;
import db.shopping.model.vo.QuestionVO;
import db.shopping.service.MemberService;
import db.shopping.service.MemberServiceImp;

public class MemberController {

	private MemberService memberService = new MemberServiceImp();
	private Scanner scan;
	
	public MemberController(Scanner scan) {
		this.scan = scan;
	}

	public MemberVO login() {
		System.out.print("아이디 : ");
		String id = scan.next();
		
		System.out.print("비밀번호 : ");
		String pw = scan.next();
		
		MemberVO user = memberService.login(id, pw);
		
		return user;
	}

	public boolean signup() {
		System.out.print("아이디 : ");
		String id = scan.next();
		if(!memberService.checkid(id)) return false;
		
		System.out.print("비밀번호 : ");
		String pw = scan.next();
		System.out.print("비밀번호 확인 : ");		
		String pw2 = scan.next();
		if(!checkPw(pw, pw2)) return false;
		
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("휴대번호('-'포함) : ");
		String phone = scan.next();
		if(!checkPhone(phone)) return false;
		
		System.out.print("주소 : ");
		scan.nextLine();
		String address = scan.nextLine();
		
		System.out.println("---비밀번호 분실 시 찾기 질문---");
		List<QuestionVO> list = memberService.getQuestionList();
		for(QuestionVO question : list) {
			System.out.println(question);
		}
		System.out.print("질문 번호 선택 : ");
		int question = scan.nextInt();
		System.out.print("답변 : ");
		String answer = scan.next();
	
		return memberService.signup(id, pw, name, phone, address, answer, question);
	}


	private boolean checkPw(String pw, String pw2) {
		String pwRegex = "^[a-zA-Z0-9!@#$]{6,15}$";
		if(!(Pattern.matches(pwRegex, pw)||Pattern.matches(pwRegex, pw2))) {
			System.out.println("비밀번호는 6~15자 사이의 영문과 숫자, 특수문자(!@#$)로만 이뤄져야 합니다.");
			return false;
		}
		
		if(!pw.equals(pw2)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return false;
		}
		return true;
	}
	
	private boolean checkPhone(String phone) {
		String regex = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$";
		if(!Pattern.matches(regex, phone)) {
			System.out.println("휴대번호 형식이 맞지 않습니다.");
			return false;
		}
		return true;
	}

	public MemberVO findId() {
		System.out.print("아이디 : ");
		String id = scan.next();
		MemberVO user = memberService.findId(id);

		return user;
	}
	
	public void findPw(MemberVO member) {
		
		List<QuestionVO> list = memberService.getQuestionList();
		for(QuestionVO question : list) {
			System.out.println(question);
		}
		System.out.print("질문 번호 선택 : ");
		int question = scan.nextInt();
		System.out.print("답변 : ");
		String answer = scan.next();
		
		if(checkQNA(member, question, answer)) {
			System.out.println("비밀번호는 [" + member.getMe_pw() + "]입니다.");
		}
		else {
			System.out.println("질문 또는 답변이 일치하지 않습니다.");
		}
			
	}

	private boolean checkQNA(MemberVO member, int question, String answer) {
		if(member.getMe_qu_num() != question) return false;
		if(!member.getMe_answer().equals(answer)) return false;
		return true;
	}

}
