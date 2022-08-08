package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import account.P02_accountDTO;
import jdbc.OracleConnection;
import s_record.P02_s_recordDTO;

public class P02_managerDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// ������ ���� �α���
	public boolean adminLogin(P02_managerDTO dto) {
		boolean result = false;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_admin where adminid=? and adminpw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAdminId());
			pstmt.setString(2, dto.getAdminPw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ȸ�������� Ȯ�� ����
	public boolean adminLogin(String adminId, String adminPw) {
		boolean result = false;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_admin where adminid=? and adminpw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ȸ������
	public int deleteUser(String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_account set status=1 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ȸ�� ����Ʈ ���
	public ArrayList<P02_accountDTO> accountList(){
		ArrayList<P02_accountDTO> list = new ArrayList<P02_accountDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select id, pw, name, to_char(birth, 'yyyy-mm-dd') birth, pC, phone, mail, adrs, area, point, status, question, ans, loginTime, reg from P02_ACCOUNT ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_accountDTO dto = new P02_accountDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setpC(rs.getString("pc"));
				dto.setName(rs.getString("name")); 
				dto.setBirth(rs.getString("birth")); 	
				dto.setPhone(rs.getString("phone")); 		
				dto.setMail(rs.getString("mail")); 		
				dto.setAdrs(rs.getString("adrs")); 		
				dto.setPoint(rs.getInt("point")); 		
				dto.setArea(rs.getInt("area")); 		
				dto.setStatus(rs.getInt("status"));		
				dto.setQuestion(rs.getString("question"));		
				dto.setAns(rs.getString("ans")); 	
				dto.setLoginTime(rs.getTimestamp("logintime")); 		
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return list;
	}
	
	// �ŷ���� ���
	public ArrayList<P02_s_recordDTO> recordList(){
		ArrayList<P02_s_recordDTO> list = new ArrayList<P02_s_recordDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_s_record order by ordernum desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_s_recordDTO dto = new P02_s_recordDTO();
				dto.setOrderNum(rs.getInt("ordernum"));
				dto.setPdNum(rs.getInt("pdnum"));
				dto.setBuyId(rs.getString("buyid"));
				dto.setSellId(rs.getString("sellid"));
				dto.setPrice(rs.getInt("price"));
				dto.setPdSell(rs.getInt("pdsell"));
				dto.setItemNum(rs.getInt("itemnum"));
				dto.setAucNum(rs.getInt("aucnum"));
				dto.setProfit(rs.getInt("profit"));
				dto.setUsepoint(rs.getInt("point"));
				dto.setSelReg(rs.getTimestamp("selreg"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return list;
	}
	
	// ���� ��
	public int sumPrice() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select sum(price) from p02_s_record";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("sum(price)");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ������
	public int sumProfit() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select sum(profit) from p02_s_record";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("sum(profit)");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ���� ����Ʈ��
	public int sumPoint() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select sum(point) from p02_s_record";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("sum(point)");
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( rs != null ) {try { rs.close();}catch(SQLException e) {}}
			if( pstmt != null ) {try { pstmt.close();}catch(SQLException e) {}}
			if( conn != null ) {try { conn.close();}catch(SQLException e) {}}
		}
		return result;
	}
	
	// ���� �ŷ� �Ѿ�
	public int sumTodayPrice() {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(price) from p02_s_record where to_char(selreg,'yy-mm-dd') = to_char(sysdate, 'yy-mm-dd')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(price)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}
	
	// ���� ������ �Ѿ�
	public int sumTodayProfit() {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(profit) from p02_s_record where to_char(selreg,'yy-mm-dd') = to_char(sysdate, 'yy-mm-dd')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(profit)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}

	// ���� ���� ����Ʈ �Ѿ�
	public int sumTodayPoint() {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(point) from p02_s_record where to_char(selreg,'yy-mm-dd') = to_char(sysdate, 'yy-mm-dd')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(point)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}

	// ������ �����
	public ArrayList<P02_s_recordDTO> selectList(String selectDay){
		ArrayList<P02_s_recordDTO> list = new ArrayList<P02_s_recordDTO>();
		try{
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_s_record "
					+ "where to_char(selreg,'yyyy-mm-dd') = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectDay);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_s_recordDTO dto = new P02_s_recordDTO();
				dto.setOrderNum(rs.getInt("ordernum"));
				dto.setPdNum(rs.getInt("pdnum"));
				dto.setBuyId(rs.getString("buyid"));
				dto.setSellId(rs.getString("sellid"));
				dto.setPrice(rs.getInt("price"));
				dto.setPdSell(rs.getInt("pdsell"));
				dto.setItemNum(rs.getInt("itemnum"));
				dto.setAucNum(rs.getInt("aucnum"));
				dto.setProfit(rs.getInt("profit"));
				dto.setUsepoint(rs.getInt("point"));
				dto.setSelReg(rs.getTimestamp("selreg"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return list;
	}
	
	// ������ �ŷ� �Ѿ�
	public int sumSelectPrice(String selectDay) {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(price) from p02_s_record where to_char(selreg,'yyyy-mm-dd') = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectDay);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(price)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}
	
	// ������ ������ �Ѿ�
	public int sumSelectProfit(String selectDay) {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(profit) from p02_s_record where to_char(selreg,'yyyy-mm-dd') = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectDay);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(profit)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return result;
	}

	// ������ ���� ����Ʈ �Ѿ�
	public int sumSelectPoint(String selectDay) {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "select sum(point) from p02_s_record where to_char(selreg,'yyyy-mm-dd') = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectDay);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("sum(point)");
			}
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
