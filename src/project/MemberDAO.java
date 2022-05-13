package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
		
		//회원 등록
		private String MEMBER_INSERT = "insert into member values(?,?,?)";
		public void insertMember(Member member) {
			try {
				conn=JDBCUtil.getConnection();
				stmt=conn.prepareStatement(MEMBER_INSERT);
					
				stmt.setString(1, member.getMemberId());
				stmt.setString(2, member.getName());
				stmt.setString(3, member.getPhoneNumber());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		//회원 수정
		private String MEMBER_UPDATE="update member set phone_number=? where member_id=?";
		public void updateMember(Member member) {
			try {
				conn=JDBCUtil.getConnection();
				stmt=conn.prepareStatement(MEMBER_UPDATE);

				stmt.setString(1, member.getPhoneNumber());
				stmt.setString(2, member.getMemberId());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		//회원 삭제
		private String MEMBER_DELETE ="delete member where member_id=?";
		public void deleteMember(Member member) {
			try {
				conn=JDBCUtil.getConnection();
				stmt=conn.prepareStatement(MEMBER_DELETE);
				
				stmt.setString(1, member.getMemberId());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		//회원 상세 조회(아이디 입력값으로 존재 유무 확인)
		private String MEMBER_SEARCH="select member_id from member";
		public boolean searchMemberId(String inputId) {
			List<Member> memberIdList = new ArrayList<>();
			try {
				conn=JDBCUtil.getConnection();
				stmt=conn.prepareStatement(MEMBER_SEARCH);
				rs=stmt.executeQuery();
				
				while(rs.next()) {
					Member member = new Member();
					member.setMemberId(rs.getString("MEMBER_ID"));	
					memberIdList.add(member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}	
			
			for(Member m :memberIdList){
				if(m.getMemberId().equals(inputId)){
					return true;
				}	
			}
		 return false;
		}
			
		//회원 목록 검색(회원 목록 출력)
		private String MEMBER_LIST="select * from member";
		public List<Member> getMemberList() {
			List<Member> memberList = new ArrayList<>();
			try {
				conn=JDBCUtil.getConnection();
				stmt=conn.prepareStatement(MEMBER_LIST);
				rs=stmt.executeQuery();
				
				while(rs.next()) {
					Member member = new Member();
					member.setMemberId(rs.getString("MEMBER_ID"));
					member.setName(rs.getString("NAME"));
					member.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					memberList.add(member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return memberList;
		}
}
