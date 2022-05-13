package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MemberManager {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	int input;
	
	public MemberManager() {} //기본 생성자
	
	public void readMenu() throws IOException {
		while(true) {
			System.out.println("목록을 원하시면 1번을 입력하세요.");
			System.out.println("등록을 원하시면 2번을 입력하세요.");
			System.out.println("수정을 원하시면 3번을 입력하세요.");
			System.out.println("삭제를 원하시면 4번을 입력하세요.");
			System.out.println("종료를 원하시면 0번을 입력하세요.");
			
			//번호 선택 입력 받기
			input =Integer.parseInt(br.readLine());
						
			if(input==1) {
				getMemberList(); //회원 목록
			}else if(input==2) {
				insertMember();  //회원 등록
			}else if(input==3) {
				updateMember();  //회원 수정
			}else if(input==4) {
				deleteMember();  //회원 삭제
			}else if(input==0){
				break;           //프로그램 종료
			}else {
				System.out.println("유효한 번호가 아닙니다.");
			}
		}
	}
	
	//회원 목록
	public void getMemberList() {
		
		MemberDAO dao = new MemberDAO();
		List<Member> memberList = dao.getMemberList();
		
		if(memberList.size()==0) {
				System.out.println("등록된 회원이 없습니다.");
		}else {
			System.out.println("현재 등록된 회원 목록입니다.");
			for (Member member : memberList) {
				System.out.print("--->");
				System.out.println(member.toString());
			}
		}	
	}
	
	//회원 등록
	public void insertMember() throws IOException {
		MemberDAO dao = new MemberDAO();
		Member member = new Member();
		
		System.out.print("아이디를 입력하세요 (형식 M-00001): ");
		String id=br.readLine();
		
		if(id=="") {
			System.out.println("아이디는 필수 입력항목입니다.");
			return;
		}else if(id.charAt(0)!='M' || id.charAt(1)!='-' || id.length()!=7){
			System.out.println("아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.");
			return;
		}else if(dao.searchMemberId(id)){
			System.out.println(id+"가 이미 존재합니다.");
			return;
		}else{
			member.setMemberId(id);	
		}
		 
		System.out.print("이름을 입력하세요 : ");
		String name= br.readLine();
		
		if(name=="") {
			System.out.println("이름은 필수 입력항목입니다.");
			return;
		}else {
			member.setName(name);
		}
				
		System.out.print("전화번호를 입력하세요 : ");
		String number = br.readLine();
		
		if(number=="") {
			System.out.println("전화번호는 필수 입력항목입니다.");
			return;
		}else if(number.length()<5||number.charAt(3)!='-'||number.charAt(8)!='-'||number.length()!=13){
			System.out.println("전화번호는 두 개의 '-'를 포함하여 총 13개의 문자로 구성해야 합니다.");
			return;
		}else {
			member.setPhoneNumber(number);
		}
		
		dao.insertMember(member);
		
		System.out.println("---> 회원가입에 성공하셨습니다.");
	}
	
	//회원 수정
	public void updateMember() throws IOException {
		MemberDAO dao = new MemberDAO();
		Member member = new Member();
		
		System.out.print("수정할 아이디를 입력하세요 (형식 M-00001): ");
		String updateId =br.readLine();
		
		if(updateId =="") {
			System.out.println("수정할 아이디를 입력해주셔야 합니다.");
			return;
		}else if(updateId.charAt(0)!='M' || updateId.charAt(1)!='-' || updateId.length()!=7){
			System.out.println("아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.");
			return;
		}else if(dao.searchMemberId(updateId)) {
			member.setMemberId(updateId);
			System.out.print("수정할 전화번호를 입력하세요 : ");
			String number =br.readLine();
			if(number=="") {
				System.out.println("전화번호는 필수 입력항목입니다.");
				return;
			}else if(number.length()<5||number.charAt(3)!='-'||number.charAt(8)!='-'||number.length()!=13) {
				System.out.println("전화번호는 두 개의 '-'를 포함하여 총 13개의 문자로 구성해야 합니다.");
				return;
			}else{
				member.setPhoneNumber(number);
			}
			dao.updateMember(member);
			System.out.println("---> 회원수정에 성공하셨습니다.");
		}else {
		System.out.println("수정할 "+updateId+" 회원 정보가 존재하지 않습니다.");
		}
	} 
			
			
	//회원 삭제
	public void deleteMember() throws IOException {
		MemberDAO dao = new MemberDAO();
		Member member =new Member();
				
		System.out.print("삭제할 아이디를 입력하세요 : ");
		String deleteId = br.readLine();
		
		if(deleteId =="") {
			System.out.println("삭제할 아이디를 입력해주셔야 합니다.");
			return;
		}else if(deleteId.charAt(0)!='M' || deleteId.charAt(1)!='-' || deleteId.length()!=7){
			System.out.println("아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.");
			return;
		}else if(dao.searchMemberId(deleteId)){
			member.setMemberId(deleteId);
			System.out.println("---> "+ member.getMemberId() +" 회원 삭제에 성공하셨습니다.");
			dao.deleteMember(member);
		}else {
		System.out.println("삭제할 "+deleteId+" 회원 정보가 존재하지 않습니다.");
		}	
	}
}
