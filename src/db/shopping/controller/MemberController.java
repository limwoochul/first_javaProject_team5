package db.shopping.controller;

import java.util.InputMismatchException;
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

	// 숫자 입력 처리 메서드
	private int getIntInput(String prompt) {
		int number = -1;
		while (true) {
			try {
				System.out.print(prompt);
				number = scan.nextInt();
				break; // 올바른 입력이 들어오면 루프 종료
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해 주세요.");
				scan.next(); // 잘못된 입력을 소비하여 무한 루프 방지
				PrintController.printBar();
			}
		}
		return number;
	}
	
	//로그인
	public MemberVO login() {
		System.out.print("아이디 : ");
		String id = scan.next();
		
		System.out.print("비밀번호 : ");
		String pw = scan.next();
		
		MemberVO user = memberService.login(id, pw);
		
		return user;
	}

	//회원가입
	public boolean signup() {
		System.out.print("아이디 : ");
		String id = scan.next();
		if(!memberService.checkid(id)) return false; //아이디 중복 및 정규식확인
		
		System.out.print("비밀번호 : ");
		String pw = scan.next();
		System.out.print("비밀번호 확인 : ");		
		String pw2 = scan.next();
		if(!checkPw(pw, pw2)) return false; //비밀번호 정규식확인 및 일치확인
		
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("휴대번호('-'포함) : ");
		String phone = scan.next();
		if(!checkPhone(phone)) return false; //휴대번호 정규식 확인
		
		System.out.print("주소 : ");
		scan.nextLine();
		String address = scan.nextLine();
		
		System.out.println("---비밀번호 분실 시 찾기 질문---");
		List<QuestionVO> list = memberService.getQuestionList(); //비밀번호찾기 질문 리스트 출력
		for(QuestionVO question : list) {
			System.out.println(question);
		}

		int question = getIntInput("질문 번호 선택 : ");
		
		if(question < 0 || question > list.size()) {
			System.out.println("존재하지 않는 번호입니다.");
			return false;
		}
		
		System.out.print("답변 : ");
		String answer = scan.next();
	
		return memberService.signup(id, pw, name, phone, address, answer, question);
	}

	//비밀번호 정규식확인 및 일치확인
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
	
	//휴대번호 정규식 확인
	private boolean checkPhone(String phone) {
		String regex = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$";
		if(!Pattern.matches(regex, phone)) {
			System.out.println("휴대번호 형식이 맞지 않습니다.");
			return false;
		}
		return true;
	}

	//검색한 아이디 찾아오기
	public MemberVO findId() {
		System.out.print("아이디 : ");
		String id = scan.next();
		MemberVO user = memberService.findId(id);

		return user;
	}
	
	//찾아온 아이디로 비밀번호 질문,답변 확인
	public void findPw(MemberVO member) {
		
		List<QuestionVO> list = memberService.getQuestionList();
		for(QuestionVO question : list) {
			System.out.println(question);
		}
		
		int question = getIntInput("질문 번호 선택 : ");
		
		if(question < 0 || question > list.size()) {
			System.out.println("존재하지 않는 번호입니다.");
			return;
		}
		System.out.print("답변 : ");
		String answer = scan.next();
		
		if(checkQNA(member, question, answer)) {
			System.out.println("비밀번호는 [" + member.getMe_pw() + "]입니다.");
		}
		else {
			System.out.println("질문 또는 답변이 일치하지 않습니다.");
		}
			
	}

	//비밀번호질문,답변 일치하는지 체크
	private boolean checkQNA(MemberVO member, int question, String answer) {
		if(member.getMe_qu_num() != question) return false;
		if(!member.getMe_answer().equals(answer)) return false;
		return true;
	}

	public void deleteMember() {
		List<MemberVO> list = memberService.selectMemberList();
		for (MemberVO member : list) {
			System.out.println(member);
		}

		System.out.print("삭제할 회원 아이디: ");
		String id = scan.next();
		boolean exists = false;
		for (MemberVO member : list) {
			if (id.equals(member.getMe_id())) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			System.out.println("해당 아이디의 회원이 존재하지 않습니다.");
			return;
		}
		memberService.deleteMember(id);
		System.out.println("회원이 성공적으로 삭제되었습니다.");

	}

	public void updateMemberByAdmin() {
		List<MemberVO> list = memberService.selectMemberList();
		for (MemberVO member : list) {
			System.out.println(member);
		}
		System.out.print("업데이트할 회원 아이디: ");
		String id = scan.next();
		boolean exists = false;
		for (MemberVO member : list) {
			if (id.equals(member.getMe_id())) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			System.out.println("해당 아이디의 회원이 존재하지 않습니다.");
			return;
		}
		MemberVO user = memberService.findId(id);
		System.out.print("수정할 비밀번호 : ");
		String pw = scan.next();
		System.out.print("비밀번호 확인 : ");		
		String pw2 = scan.next();
		if(!checkPw(pw, pw2)) return; //비밀번호 정규식확인 및 일치확인

		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("휴대번호('-'포함) : ");
		String phone = scan.next();
		if(!checkPhone(phone)) return; //휴대번호 정규식 확인

		System.out.print("주소 : ");
		scan.nextLine();
		String address = scan.nextLine();

		user.setMe_pw(pw);
		user.setMe_name(name);
		user.setMe_phone(phone);
		user.setMe_address(address);
		if(memberService.updateMember(id, pw, name, phone, address)) {
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");			
		} else {
			System.out.println("회원 정보 수정 실패!");
		}
	}

	public void updateMember(String me_id) {
		
		MemberVO user = memberService.findId(me_id);
		
		System.out.println("-------나의 정보-------");
		System.out.println(user);
		
		System.out.print("수정할 비밀번호 : ");
		String pw = scan.next();
		System.out.print("비밀번호 확인 : ");		
		String pw2 = scan.next();
		if(!checkPw(pw, pw2)) return; //비밀번호 정규식확인 및 일치확인

		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("휴대번호('-'포함) : ");
		String phone = scan.next();
		if(!checkPhone(phone)) return; //휴대번호 정규식 확인

		System.out.print("주소 : ");
		scan.nextLine();
		String address = scan.nextLine();

		user.setMe_pw(pw);
		user.setMe_name(name);
		user.setMe_phone(phone);
		user.setMe_address(address);
		if(memberService.updateMember(me_id, pw, name, phone, address)) {
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");			
		} else {
			System.out.println("회원 정보 수정 실패!");
		}
	}

}
