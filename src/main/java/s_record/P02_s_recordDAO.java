package s_record;
import java.sql.*;
import java.util.ArrayList;

import jdbc.OracleConnection;

public class P02_s_recordDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// �ü� �ֱ� �ŷ���
	public int recentPrice(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select price from p02_s_record "
					+ " where ordernum = (select max(ordernum) from p02_s_record where itemnum=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("price");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}

	// ���� �ְ� �ŷ���
	public int maxPrice(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select max(price) from p02_s_record where itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("max(price)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}

	// ���� ���� �ŷ���
	public int minPrice(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select min(price) from p02_s_record where itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("min(price)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}

	// ���� ��� �ŷ���
	public int avgToday(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select round( "
					+ "(select avg(price) from p02_s_record "
					+ "where itemnum=? and to_char(selreg,'yy/mm/dd') = to_char(sysdate, 'yy/mm/dd')), -1) "
					+ "as AVG from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("avg");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}
	
	// ���� ��� �ŷ���
	public int avgTomo(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select round( "
					+ "(select avg(price) from p02_s_record "
					+ "where itemnum=? and to_char(selreg,'yy/mm/dd') = to_char(sysdate-1, 'yy/mm/dd')), -1) "
					+ "as AVG from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("avg");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}	

	// 2���� ��� �ŷ���
	public int avgThree(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select round( "
					+ "(select avg(price) from p02_s_record "
					+ "where itemnum=? and to_char(selreg,'yy/mm/dd') = to_char(sysdate-2, 'yy/mm/dd')), -1) "
					+ "as AVG from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("avg");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}
	
	// ���������� ���ų���
	public ArrayList<P02_s_recordDTO> buyList(String id, int start, int end){
		ArrayList<P02_s_recordDTO> list = new ArrayList<P02_s_recordDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from "
					+ " (select p02_s_record.*, rownum r from "
					+ " (select r.ordernum, r.itemnum, i.itemname, ib.ibrand, r.price, r.sellid, r.selreg ,r.profit,r.point"
					+ " from p02_s_record r, p02_item i, p02_itembrand ib "
					+ " where r.itemnum=i.itemnum and i.itembrand=ib.brandcode and r.buyid=? order by ordernum desc) p02_s_record) "
					+ " where r >= ? and r <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_s_recordDTO dto = new P02_s_recordDTO();
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setItemNum(rs.getInt("itemNum"));
				dto.setItemName(rs.getString("itemName"));
				dto.setIbrand(rs.getString("ibrand"));
				dto.setPrice(rs.getInt("price"));
				dto.setSellId(rs.getString("sellId"));
				dto.setSelReg(rs.getTimestamp("selReg"));
				dto.setProfit(rs.getInt("Profit"));
				dto.setUsepoint(rs.getInt("point"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return list;
	}
	
	// ���������� - �� ���Ű���
	public int buyCount(String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_s_record where buyid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
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
	      
	// ���������� - ���ǰ ���Ű���
	public int buyActionCount(String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_s_record where buyid=? and aucnum is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
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
}
