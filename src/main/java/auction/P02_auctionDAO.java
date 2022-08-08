package auction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bid.P02_bidDTO;
import jdbc.OracleConnection;

public class P02_auctionDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 경매품 등록
	public int insertAuction (P02_auctionDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_auction (aucnum, aucname, auccontent, aucimage, startreg, endreg)"
					+ "values(aucnum_seq.nextval, ?, ?, ?, sysdate, sysdate+1 )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAucName());
			pstmt.setString(2, dto.getAucContent());
			pstmt.setString(3, dto.getAucImage());
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
	
	// 경매품 내용 확인
	public P02_auctionDTO readContent () {
		P02_auctionDTO dto = new P02_auctionDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_auction where aucstatus = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setAucNum(rs.getInt("aucnum"));
				dto.setAucName(rs.getString("aucname"));
				dto.setAucContent(rs.getString("auccontent"));
				dto.setAucImage(rs.getString("aucImage"));
				dto.setStartReg(rs.getTimestamp("startreg"));
				dto.setEndReg(rs.getTimestamp("endreg"));
			}else {
				return dto = null;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close(); }catch(SQLException e) {}}
			if(pstmt != null) { try { pstmt.close(); }catch(SQLException e) {}}
			if(conn != null) { try { conn.close(); }catch(SQLException e) {}}
		}
		return dto;
	}
	
	// 경매품 정보 불러오기
	public P02_bidDTO confirmProduct(int aucNum) {
		P02_bidDTO dto = new P02_bidDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_bid "
					+ "where reg= (select min(reg) from p02_bid "
					+ "where price = (select max(price) from p02_bid where aucnum=?)"
					+ " and aucnum=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aucNum);
			pstmt.setInt(2, aucNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPrice(rs.getInt("price"));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close();}catch(SQLException e) {}};
			if(pstmt != null) { try { pstmt.close();}catch(SQLException e) {}};
			if(conn != null) { try { conn.close();}catch(SQLException e) {}};
		}
		return dto;
	}
	
	// 경매 종료
	public int endAuction(int aucNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_auction set aucstatus=1 where aucnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aucNum);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close();}catch(SQLException e) {}};
			if(pstmt != null) { try { pstmt.close();}catch(SQLException e) {}};
			if(conn != null) { try { conn.close();}catch(SQLException e) {}};
		}
		return result;
	}
	// 경매 종료후 레코드테이블에 기록
	public int resultAuction (int aucNum, String id, int price) {
		int result =0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_s_record (ordernum, aucnum, buyid, price, pdsell,sellid,itemnum,pdnum)"
					+ " values(record_seq.nextval, ?, ?, ?, 2,'경매품입니다',0,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aucNum);
			pstmt.setString(2, id);
			pstmt.setInt(3, price);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close();}catch(SQLException e) {}};
			if(pstmt != null) { try { pstmt.close();}catch(SQLException e) {}};
			if(conn != null) { try { conn.close();}catch(SQLException e) {}};
		}
		return result;
	}

	// 경매 즉시종료
	public int finish () {
		int result =0;
		try {
			conn = OracleConnection.getConnection();
			String sql = " UPDATE P02_auction SET ENDREG = SYSDATE WHERE aucstatus = 0 ";
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { try { rs.close();}catch(SQLException e) {}};
			if(pstmt != null) { try { pstmt.close();}catch(SQLException e) {}};
			if(conn != null) { try { conn.close();}catch(SQLException e) {}};
		}
		return result;
	}
}