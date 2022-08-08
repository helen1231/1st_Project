package cart;
import java.sql.*;
import java.util.ArrayList;
import jdbc.OracleConnection;

public class P02_cartDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 장바구니(관심상품) 등록 개수
	public int cartCount(String id){
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_cart where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return result;
	}
	
	// 마이페이지-관심상품(장바구니) 리스트
	public ArrayList<P02_cartDTO> cartList(String id, int start, int end){
		ArrayList<P02_cartDTO> list = new ArrayList<P02_cartDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from (select d.* , rownum rn from "
					+ "(select d.* from (select i.itemnum, i.itemname, ib.ibrand, r.price, r.ordernum, r.selreg, c.cartreg "
					+ "from p02_item i, p02_cart c, p02_itembrand ib, p02_s_record r "
					+ "where i.itemnum=c.itemnum and i.itembrand=ib.brandcode and c.id=? and r.itemnum=c.itemnum) d, "
					+ "(select itemnum, max(ordernum) max_ordernum from p02_s_record group by itemnum) mo "
					+ "where mo.max_ordernum = d.ordernum order by d.cartreg desc) d) "
					+ "where rn >= ? and rn <= ?";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_cartDTO dto = new P02_cartDTO();
				dto.setItemName(rs.getString("itemName"));
				dto.setItemNum(rs.getInt("itemNum"));
				dto.setIbrand(rs.getString("ibrand"));
				dto.setPrice(rs.getInt("price"));
				dto.setSelReg(rs.getTimestamp("selreg"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return list;
	}
}

