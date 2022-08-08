package item;
import java.util.ArrayList;

import jdbc.OracleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P02_itemDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// ������ ��� �ڵ� ������Ʈ
	public int stockupdate() {
		P02_itemDTO dto = new P02_itemDTO();
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = " SELECT COUNT(*) FROM P02_ITEM ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	dto.setItemcount(rs.getInt("count(*)")); 
		    } 
		    for( int i =0;  i < dto.getItemcount(); i++ ) { 
		    	sql = " UPDATE P02_ITEM SET ITEMSTOCK = (SELECT COUNT(*) FROM P02_PRODUCT "
			 		+ " WHERE STATUS=0 AND ITEMNUM ="+(400000+i)+" ) WHERE ITEMNUM="+(400000+i);
			 	pstmt = conn.prepareStatement(sql);
				result = pstmt.executeUpdate();
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
	
	// ������ �˻�
	public ArrayList<P02_itemDTO> search(int searchBrand1,int searchBrand2,int searchBrand3,String column,String search){
		ArrayList<P02_itemDTO> list = new ArrayList<P02_itemDTO>();
		try {
			conn = OracleConnection.getConnection();
			/* ���üũ ������*/
			String sql1 = "SELECT * FROM p02_item WHERE itembrand LIKE '%%'   AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 1 üũ��*/
			String sql2 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand1+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 2 üũ��*/
			String sql3 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand2+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 3 üũ��*/
			String sql4 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand3+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 1,2 üũ��*/
			String sql5 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand2+"%')  AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 1,2,3 üũ��*/
			String sql6 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand2+"%' OR itembrand LIKE '%"+searchBrand3+"%' ) AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 2,3 üũ��*/
			String sql7 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand2+"%' OR itembrand LIKE '%"+searchBrand3+"%' )   AND ITEMNAME LIKE '%"+search+"%' ";
			/* �귣�� 1,3 üũ��*/
			String sql8 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand3+"%' )   AND ITEMNAME LIKE '%"+search+"%' ";

			if(searchBrand1 == 3 && searchBrand2 == 3 && searchBrand3 == 3){ /* ���üũ ������*/
				 pstmt = conn.prepareStatement(sql1);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 == 3 && searchBrand3 == 3){ /* �귣�� 1 üũ��*/
				 pstmt = conn.prepareStatement(sql2);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 != 3 && searchBrand3 == 3){/* �귣�� 2 üũ��*/
				 pstmt = conn.prepareStatement(sql3);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 == 3 && searchBrand3 != 3){/* �귣�� 3 üũ��*/
				 pstmt = conn.prepareStatement(sql4);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 != 3 && searchBrand3 == 3){/* �귣�� 1,2 üũ��*/
				 pstmt = conn.prepareStatement(sql5);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 != 3 && searchBrand3 != 3){ /* �귣�� 1,2,3 üũ��*/
				 pstmt = conn.prepareStatement(sql6);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 != 3 && searchBrand3 != 3){ /* �귣�� 2,3 üũ��*/
				 pstmt = conn.prepareStatement(sql7);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 == 3 && searchBrand3 != 3){ /* �귣�� 1,3 üũ��*/
				 pstmt = conn.prepareStatement(sql8);
			     rs = pstmt.executeQuery();
			}  
			while(rs.next()) {
		    	P02_itemDTO dto = new P02_itemDTO();
		    	dto.setItemNum(rs.getInt("itemnum"));
		    	dto.setItemImage(rs.getString("image"));
		    	dto.setItemName(rs.getString("itemName"));
		    	dto.setReadCount(rs.getInt("readcount"));
		    	dto.setItemGood(rs.getInt("itemgood"));
		    	dto.setPrice(rs.getInt("price"));
			    list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null)try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null)try { conn.close(); }catch(SQLException e) {}
		}
		return list;
	}
	
	// ������ ���� Ȯ��
	public P02_itemDTO readContent(int itemnum) {
		P02_itemDTO dto = new P02_itemDTO();
	    try {
	    	conn = OracleConnection.getConnection();
	        String sql = "update P02_ITEM set readcount=readcount+1 where ITEMNUM=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, itemnum);
	        pstmt.executeUpdate();
	        
	        sql = " SELECT I.ITEMNUM,I.ITEMNAME,I.CONTENT,I.IMAGE,I.ITEMSTOCK,I.ITEMGOOD,I.READCOUNT,"
	        		 + " M.IMAT,B.IBRAND,S.ISIZE,I.IMAGE,I.PRICE" 
	        		 + " FROM P02_ITEM I , P02_ITEMMAT M, P02_ITEMBRAND B ,P02_ITEMSIZE S " 
	        		 + " WHERE I.ITEMMAT=M.MATCODE AND I.ITEMBRAND = B.BRANDCODE AND I.ITEMSIZE = S.SIZECODE AND I.ITEMNUM = ? ";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, itemnum);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	dto.setItemNum(rs.getInt("ITEMNUM"));
	        	dto.setItemName(rs.getString("ITEMNAME"));
	        	dto.setContent(rs.getString("CONTENT"));
	        	dto.setImage(rs.getString("IMAGE"));
	        	dto.setItemStock(rs.getInt("ITEMSTOCK"));
	        	dto.setItemGood(rs.getInt("ITEMGOOD"));
	        	dto.setReadCount(rs.getInt("READCOUNT"));
	        	dto.setImat(rs.getString("IMAT"));
	        	dto.setIbrand(rs.getString("IBRAND"));
	        	dto.setIsize(rs.getString("ISIZE"));
	        	dto.setItemImage(rs.getString("IMAGE"));
	        	dto.setItemPrice(rs.getInt("PRICE"));
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
	//	���̺���� ������� ������ �� �Ϲݵ����ʹ� �ٷ� �ҷ����� 
    // 	��ǰ�귣��,����,������� �ڵ带 �������� JOIN�Ͽ� �ҷ��ö� �ڵ带 ��Ī���� ��ȯ�Ͽ� �ҷ���
    
	// ������ ��� 
	public int InsertItem(P02_itemDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_item (itemNum, itemName, itemBrand, itemMat, itemSize, content)"
					+ " values (item_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getItemName());
			pstmt.setInt(2, dto.getItemBrand());
			pstmt.setInt(3, dto.getItemMat());
			pstmt.setInt(4, dto.getItemSize());
			pstmt.setString(5, dto.getContent());
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

	// �Ǹ����� ������ ����Ʈȭ
	public ArrayList<P02_itemDTO> listItem(int start ,int end){
		ArrayList<P02_itemDTO> list = new ArrayList<P02_itemDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from "
					+ " (select p02_item.*, rownum r from "
					+ " (select * from p02_item order by itemnum) p02_item) "
					+ "where r>=? and r<=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_itemDTO dto = new P02_itemDTO();
				dto.setItemNum(rs.getInt("itemnum"));
				dto.setItemName(rs.getString("itemname"));
				dto.setItemBrand(rs.getInt("itembrand"));
				dto.setItemMat(rs.getInt("itemmat"));
				dto.setItemSize(rs.getInt("itemnum"));
				dto.setContent(rs.getString("content"));
				dto.setItemGood(rs.getInt("itemgood"));
				dto.setItemStock(rs.getInt("itemstock"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setItemImage(rs.getString("image"));
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

	// ������ ����(����¡ ����)
	public int countListItem() {
		int result=0;
		try {
			conn = OracleConnection.getConnection();
			String sql ="select count(*) from p02_item";
			pstmt = conn.prepareStatement(sql);
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

	// ������ ���� ����
	public int changeImageItem(String img, int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_item set image=? where itemnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img);
			pstmt.setInt(2, itemNum);
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
	
	// ��ٱ��Ͽ� �� ������ ī��Ʈ
	public int countCartItem(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql ="select count(*) from p02_cart where itemnum =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
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
	
	// ������ ���ƿ� ���� ����
	public int updateGood(int itemNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_item set itemgood = itemgood+1 where itemnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
	         e.printStackTrace();
	    }finally {
	        if(rs != null) {try { rs.close(); }catch(SQLException e) {}}
	        if(pstmt != null) {try { pstmt.close(); }catch(SQLException e) {}}
	        if(conn != null) {try { conn.close(); }catch(SQLException e) {}}
	    }
		return result;
	}
}
