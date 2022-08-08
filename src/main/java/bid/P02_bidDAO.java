package bid;

import java.sql.*;

import jdbc.OracleConnection;

public class P02_bidDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// ¿‘¬˚«œ±‚
	public int insertBid(P02_bidDTO dto, int aucNum, String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_bid values (bidnum_seq.nextval, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aucNum);
			pstmt.setString(2, id);
			pstmt.setInt(3, dto.getPrice());
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
