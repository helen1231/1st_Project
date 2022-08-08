package account;
import java.sql.*;
import java.util.ArrayList;

import jdbc.OracleConnection;

public class P02_accountDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 회원가입
	public int joinInsert(P02_accountDTO dto) {	 
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_account (id,pw,name,birth,pC,phone,mail,adrs,question,ans,loginTime,reg) "
					+ "values(?,?,?,?,?,?,?,?,?,?,sysdate,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getpC());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getMail());
			pstmt.setString(8, dto.getAdrs());
			pstmt.setString(9, dto.getQuestion());
			pstmt.setString(10, dto.getAns());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){} 
			}
		return result;
	}
	
	// 회원가입 - ID 중복검사
	public int idDuplicate(String id) {		
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) as cnt from p02_account where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next() == true) {
				result = rs.getInt("cnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){} 
			}
		return result;
	}
	
	// 로그인
	public boolean login(P02_accountDTO dto) {	
		boolean result = false;
		try {
		conn = OracleConnection.getConnection();
			String sql = "select * from p02_account where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}
	
	// 회원정보 불러오기(아이디 기준)
	public P02_accountDTO readAccountInform(String id) { 
		P02_accountDTO dto = new P02_accountDTO();
	try {
		conn = OracleConnection.getConnection();
		String sql = "select id, pw, name, to_char(birth, 'yyyy-mm-dd') birth, pC, phone, mail, adrs, area, point, status, question, ans, "
				+ "loginTime, reg from p02_account where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setBirth(rs.getString("birth"));
			dto.setpC(rs.getString("pC"));
			dto.setPhone(rs.getString("phone"));
			dto.setMail(rs.getString("mail"));
			dto.setAdrs(rs.getString("adrs"));
			dto.setArea(rs.getInt("area"));
			dto.setPoint(rs.getInt("point"));
			dto.setStatus(rs.getInt("status"));
			dto.setQuestion(rs.getString("question"));
			dto.setAns(rs.getString("ans"));
			dto.setLoginTime(rs.getTimestamp("loginTime"));
			dto.setReg(rs.getTimestamp("reg"));
			dto.setPoint(rs.getInt("point"));
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs != null) try {rs.close();}catch(SQLException e) {}
		if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
		if(conn != null) try { conn.close(); }catch(SQLException e){}
		}
	return dto;
	}
	
	// 회원정보 업데이트, 비밀번호가 일치해야함.
	public int accountUpdate(P02_accountDTO dto) {	
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set phone=?, mail=?, question=?, ans=? where id=? and pw=?"; // adrs=?
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getMail());
			//pstmt.setString(3, dto.getAdrs());
			pstmt.setString(3, dto.getQuestion());
			pstmt.setString(4, dto.getAns());
			pstmt.setString(5, dto.getId());
			pstmt.setString(6, dto.getPw());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}	
				
	// 회원탈퇴 - 비밀번호가 일치해야함, DB에서 삭제가 아니라 회원상태 코드를 1로 변경
	public int accountDelete(P02_accountDTO dto) {	
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set status=? where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}	

	// 아이디찾기
	public ArrayList<P02_accountDTO> idFind(String name, String pCompany, String phone) { 
		ArrayList<P02_accountDTO> list = new ArrayList<P02_accountDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select id from p02_account where name=? and pC=? and phone=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pCompany);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
		while(rs.next()) {
			P02_accountDTO dto = new P02_accountDTO();
			dto.setId(rs.getString("id"));
			list.add(dto);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
		}
		return list;
	}
	
	// 비밀번호 찾기 - 등록된 아이디 & 비밀번호 찾기 질문,답 
	public String pwFind(String id, String question, String ans) {	
		String result = null;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select pw from p02_account where id=? and question=? and ans=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, question);
			pstmt.setString(3, ans);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			result = rs.getString("pw");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
		}
	
	// 로그인 시간(날짜) 업데이트
	public int loginTimeUpdate(P02_accountDTO dto) {	
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set loginTime=sysdate  where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}	

	// 장바구니 넣기(중복 불가)
	public int productCart (String sid, int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_cart where id=? and itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setInt(2, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return result;
			}
			
			sql = "insert into p02_cart (id, itemnum, cartreg) values (?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setInt(2, itemNum);
			result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}
	
	// 장바구니 빼기
	public int cartDelete (String sid, int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "delete from p02_cart where id=? and itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setInt(2, itemNum);
			result = pstmt.executeUpdate();
			
			sql = "update p02_item set itemgood= itemgood-1 where itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}
	
	// 비밀번호 변경
	public int pwChange(String id, String pw_old, String pw_new) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set pw=? where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw_new);
			pstmt.setString(2, id);
			pstmt.setString(3, pw_old);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}
	
	// 주소변경
	public int adrsUpdate(P02_accountDTO dto) {	
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set adrs=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdrs());
			pstmt.setString(2, dto.getId());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e){}
			if(conn != null) try { conn.close(); }catch(SQLException e){}
			}
		return result;
	}	
	
	// 포인트 조회
	public P02_accountDTO showpoint(String id){
		P02_accountDTO dto = new P02_accountDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from P02_ACCOUNT where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) { 
			  dto.setId(rs.getString("id"));
			  dto.setPoint(rs.getInt("point"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return dto;
	}
	
	// 포인트 적립
	public int StackPoint(String id,int stackpoint) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = " UPDATE P02_ACCOUNT SET point = point + ? WHERE id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stackpoint);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}
	
	// 포인트 사용
	public int UsePoint(String id,int usepoint) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = " UPDATE P02_ACCOUNT SET point = point - ? WHERE id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usepoint);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}
}
