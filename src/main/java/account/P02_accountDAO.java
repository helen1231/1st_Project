package account;
import java.sql.*;
import java.util.ArrayList;

import jdbc.OracleConnection;

public class P02_accountDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// ȸ������
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
	
	// ȸ������ - ID �ߺ��˻�
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
	
	// �α���
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
	
	// ȸ������ �ҷ�����(���̵� ����)
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
	
	// ȸ������ ������Ʈ, ��й�ȣ�� ��ġ�ؾ���.
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
				
	// ȸ��Ż�� - ��й�ȣ�� ��ġ�ؾ���, DB���� ������ �ƴ϶� ȸ������ �ڵ带 1�� ����
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

	// ���̵�ã��
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
	
	// ��й�ȣ ã�� - ��ϵ� ���̵� & ��й�ȣ ã�� ����,�� 
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
	
	// �α��� �ð�(��¥) ������Ʈ
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

	// ��ٱ��� �ֱ�(�ߺ� �Ұ�)
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
	
	// ��ٱ��� ����
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
	
	// ��й�ȣ ����
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
	
	// �ּҺ���
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
	
	// ����Ʈ ��ȸ
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
	
	// ����Ʈ ����
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
	
	// ����Ʈ ���
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
