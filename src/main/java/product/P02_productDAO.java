package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.OracleConnection;
import java.util.ArrayList;

public class P02_productDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// �ǸűⰣ ���� ��ǰ�� �ǸŻ��� ��ȯ	
	public int update() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = " update P02_PRODUCT SET status =4  "
					+ " WHERE  status = 3 AND PDSELL = 1 AND sellid IS NOT NULL AND buyid IS NULL AND PDREG < (SYSDATE-6.9999) ";
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();	
			
			sql = " update P02_PRODUCT SET status =5  "
					+ " WHERE status = 2 AND PDSELL = 1 AND sellid IS NULL AND buyid IS NOT NULL AND PDREG < (SYSDATE-6.9999) ";
			pstmt = conn.prepareStatement(sql);
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
	
	// �Ϲ� ���Խ� ǥ�ð���
	public P02_productDTO price(int itemnum) {
		P02_productDTO dto = new P02_productDTO();
	    try {
	    	conn = OracleConnection.getConnection();
	        String sql =  " SELECT * FROM " 
	        	+ " (SELECT *  FROM P02_S_RECORD WHERE sellid NOT LIKE 'null' AND BUYID NOT LIKE 'null' AND itemnum = ? ORDER BY SELREG desc) "
	        	+ " WHERE ROWNUM = 1"; 
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, itemnum);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	 dto.setPrice3(rs.getInt("PRICE")); 
	        	 dto.setItemNum(rs.getInt("ITEMNUM"));
        	 }else {
	        	 dto.setItemNum(itemnum);
	         }
	    }catch(Exception e) {
	    	e.printStackTrace();
	 	}finally {
	    	if(rs != null) {try { rs.close(); }catch(SQLException e) {}}
	        if(pstmt != null) {try { pstmt.close(); }catch(SQLException e) {}}
	        if(conn != null) {try { conn.close(); }catch(SQLException e) {}}
	    }
	    return dto;
	}
	
	// ������ ����
	public P02_productDTO iteminfo(int itemnum) {
		P02_productDTO dto = new P02_productDTO();
	    try {
	    	conn = OracleConnection.getConnection();
	    	String sql = " SELECT I.ITEMNUM,I.ITEMNAME,I.CONTENT,I.IMAGE,I.ITEMSTOCK,M.IMAT,B.IBRAND,S.ISIZE "
				+" FROM P02_ITEM I , P02_ITEMMAT M, P02_ITEMBRAND B ,P02_ITEMSIZE S "
	        	+" WHERE I.ITEMMAT=M.MATCODE AND I.ITEMBRAND = B.BRANDCODE AND I.ITEMSIZE = S.SIZECODE AND I.ITEMNUM = ? ";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, itemnum);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	dto.setItemNum(rs.getInt("ITEMNUM"));
	        	dto.setItemName(rs.getString("ITEMNAME"));
	        	dto.setContent(rs.getString("CONTENT"));
	        	dto.setImage(rs.getString("IMAGE"));
	        	dto.setItemStock(rs.getInt("ITEMSTOCK"));
	        	dto.setImat(rs.getString("IMAT"));
	        	dto.setIbrand(rs.getString("IBRAND"));
	        	dto.setIsize(rs.getString("ISIZE"));
	        }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	       if(rs != null) {try { rs.close(); }catch(SQLException e) {}}
	       if(pstmt != null) {try { pstmt.close(); }catch(SQLException e) {}}
	       if(conn != null) {try { conn.close(); }catch(SQLException e) {}}
	    }
	    return dto;
	}

	// ��ǰ �Ϲ� �Ǹ� : �Ǹű�Ͽ� �߰� �� ������ ����
	public int Sell1Product(P02_productDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into P02_PRODUCT (PDNUM,ITEMNUM,PRICE1,STATUS,PDSELL,SELLID) values(PRODUCT_SEQ.NEXTVAL,?,?,0,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getItemNum());
			pstmt.setInt(2, dto.getPrice1());
			pstmt.setString(3, dto.getSellId());
			result = pstmt.executeUpdate();
			
			sql = "update P02_ITEM set itemstock=itemstock+1 where ITEMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getItemNum());
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

	// ��ǰ ���� �Ǹ� : �Ϲ� �ǸŶ� ��� �ǸŹ�ĸ� �ٸ� 
	public int Sell2Product(P02_productDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into P02_PRODUCT (PDNUM,ITEMNUM,PRICE2,STATUS,PDSELL,SELLID) values(PRODUCT_SEQ.NEXTVAL,?,?,0,1,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getItemNum());
			pstmt.setInt(2, dto.getPrice2());
			pstmt.setString(3, dto.getSellId());
			result = pstmt.executeUpdate();

			sql = "update P02_ITEM set itemstock=itemstock+1 where ITEMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getItemNum());
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

	// ��ǰ ���� ���
	public int countListProduct(int pdNum) {
		int result=0;
		try {
			conn = OracleConnection.getConnection();
			String sql ="select count(*) from p02_product where pdnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
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
	
	// �Ϲ� ���� : �Ǹ� ���� ��ȭ �� ��� ��ȭ, �Ǹ� ��� �߰�
	public int buyProduct(int itemNum, int pdNum, String id,int usepoint) {
		int result = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		try {
			conn = OracleConnection.getConnection();
			
			String sql = "update p02_product set status ='1' where pdnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			result = pstmt.executeUpdate();
			
			sql = "update p02_item set itemstock = itemstock-1 where itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			result = pstmt.executeUpdate();
		
			sql = "update p02_product set buyid = ? where pdnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pdNum);
			result = pstmt.executeUpdate();
		
			sql = "select price1,price2 from p02_product where pdnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				a = (rs.getInt("price1"));
				b = (rs.getInt("price2"));
			}
			if(a == 0) {
				c = b;
			}else if (b == 0) {
				c = a;
			}
				
			sql = "insert into p02_s_record (ordernum, itemnum, pdnum, pdsell, buyid, sellid, price, profit ,point, selreg) "
					+ "values (record_seq.nextval, ?, ?, 1 ,? ,"
					+ "(select sellid from p02_product where pdnum = ?), "
					+ " ? , ? ,?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			pstmt.setInt(2, pdNum);
			pstmt.setString(3, id);
			pstmt.setInt(4, pdNum);
			pstmt.setInt(5, c);
			pstmt.setInt(6, (int)(c*0.1));
			pstmt.setInt(7, usepoint);
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
	
	// �Ϲ� ���Ž� ������ �����ֱ� �Ϲ� ���� ��ǰ ������ �����Ǹ� �ּҰ� ������
	public P02_productDTO showProduct(int itemNum){
		P02_productDTO dto = new P02_productDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = " select * from p02_product where "
						  + "(price1 = (select min(LEAST(nvl(price1,1/0f), nvl(price2,1/0f))) as price from p02_product "
						  + " where itemnum = ? and status = 0 AND sellid IS NOT NULL AND buyid IS NULL) "
						  + " OR price2 = (select min(LEAST(nvl(price1,1/0f), nvl(price2,1/0f))) as price from p02_product "
						  + " where itemnum = ? and status = 0 AND sellid IS NOT NULL AND buyid IS NULL)) "
						  + " and pdreg=(select min(pdreg) from "
						  + " (select * from p02_product where "
						  + " (price1 = (select min(LEAST(nvl(price1,1/0f), nvl(price2,1/0f))) as price from p02_product "
						  + " where itemnum = ? and status = 0 AND sellid IS NOT NULL AND buyid IS NULL) "
						  + " OR price2 = (select min(LEAST(nvl(price1,1/0f), nvl(price2,1/0f))) as price from p02_product "
						  + " where itemnum = ? and status = 0 AND sellid IS NOT NULL AND buyid IS NULL)) "
						  + " AND itemnum = ? AND status = 0  )) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			pstmt.setInt(2, itemNum);
			pstmt.setInt(3, itemNum);
			pstmt.setInt(4, itemNum);
			pstmt.setInt(5, itemNum);
			rs = pstmt.executeQuery();
			if(rs.next()) { 
				 if(rs.getInt("price1") != 0) {
					dto.setPrice1(rs.getInt("price1"));
					}
				 else {
					 dto.setPrice1(rs.getInt("price2"));
				 }
			  dto.setItemNum(rs.getInt("itemNum"));
			  dto.setPdNum(rs.getInt("pdnum"));
			  dto.setStatus(rs.getInt("status"));
			  dto.setPdSell(rs.getInt("pdsell"));
			  dto.setSellId(rs.getString("sellid"));
			  dto.setBuyId(rs.getString("buyid"));
			  dto.setPdReg(rs.getTimestamp("pdreg"));
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
	
	// ���� ���� : ��ǰ ����Ʈ�� �ö�
	public int tenderProduct(P02_productDTO dto, String id){
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_product (pdnum, itemnum, price2, status, pdsell, buyid, pdreg) "
					+ "values (product_seq.nextval , ?, ?, 2, 1, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getItemNum());
			pstmt.setInt(2, dto.getPrice2());
			pstmt.setString(3, id);
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

	// ���������� - �ǸŰ���
	public int sellCount(String id){
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_product where sellid=?";
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
		
	// ���������� - �ǸŸ��
	public ArrayList<P02_productDTO> sellList(String id, int start, int end){
		ArrayList<P02_productDTO> list = new ArrayList<P02_productDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from "
					+ "(select p02_product.*, rownum r from "
					+ "(select p.pdnum, p.itemnum, p.price1,p.price2, ps.pstatus, ppd.ppdsell, p.buyid, p.pdreg "
					+ "from p02_product p, p02_productstatus ps, p02_productpdsell ppd "
					+ "where p.status=ps.statuscode and p.pdsell=ppd.pdsellcode and p.sellid=?) p02_product) "
					+ "where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_productDTO dto = new P02_productDTO();
				dto.setPdNum(rs.getInt("pdnum"));
				dto.setItemNum(rs.getInt("itemNum"));
				if(rs.getInt("price1") == 0) {
					dto.setPrice1(rs.getInt("price2"));	
				}else {
					dto.setPrice1(rs.getInt("price1"));
				}
				dto.setPstatus(rs.getString("pstatus")); 
				dto.setPpdSell(rs.getString("ppdSell")); 
				dto.setBuyId(rs.getString("buyid"));
				dto.setPdReg(rs.getTimestamp("pdreg"));
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
}