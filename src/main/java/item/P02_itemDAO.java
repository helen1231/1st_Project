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
	
	// 아이템 재고 자동 업데이트
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
	
	// 아이템 검색
	public ArrayList<P02_itemDTO> search(int searchBrand1,int searchBrand2,int searchBrand3,String column,String search){
		ArrayList<P02_itemDTO> list = new ArrayList<P02_itemDTO>();
		try {
			conn = OracleConnection.getConnection();
			/* 모든체크 해제시*/
			String sql1 = "SELECT * FROM p02_item WHERE itembrand LIKE '%%'   AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 1 체크시*/
			String sql2 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand1+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 2 체크시*/
			String sql3 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand2+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 3 체크시*/
			String sql4 =  "SELECT * FROM p02_item WHERE itembrand LIKE '%"+searchBrand3+"%'  AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 1,2 체크시*/
			String sql5 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand2+"%')  AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 1,2,3 체크시*/
			String sql6 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand2+"%' OR itembrand LIKE '%"+searchBrand3+"%' ) AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 2,3 체크시*/
			String sql7 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand2+"%' OR itembrand LIKE '%"+searchBrand3+"%' )   AND ITEMNAME LIKE '%"+search+"%' ";
			/* 브랜드 1,3 체크시*/
			String sql8 =  "SELECT * FROM p02_item WHERE (itembrand LIKE '%"+searchBrand1+"%' OR itembrand LIKE '%"+searchBrand3+"%' )   AND ITEMNAME LIKE '%"+search+"%' ";

			if(searchBrand1 == 3 && searchBrand2 == 3 && searchBrand3 == 3){ /* 모든체크 해제시*/
				 pstmt = conn.prepareStatement(sql1);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 == 3 && searchBrand3 == 3){ /* 브랜드 1 체크시*/
				 pstmt = conn.prepareStatement(sql2);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 != 3 && searchBrand3 == 3){/* 브랜드 2 체크시*/
				 pstmt = conn.prepareStatement(sql3);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 == 3 && searchBrand3 != 3){/* 브랜드 3 체크시*/
				 pstmt = conn.prepareStatement(sql4);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 != 3 && searchBrand3 == 3){/* 브랜드 1,2 체크시*/
				 pstmt = conn.prepareStatement(sql5);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 != 3 && searchBrand3 != 3){ /* 브랜드 1,2,3 체크시*/
				 pstmt = conn.prepareStatement(sql6);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 == 3 && searchBrand2 != 3 && searchBrand3 != 3){ /* 브랜드 2,3 체크시*/
				 pstmt = conn.prepareStatement(sql7);
			     rs = pstmt.executeQuery();
			}
			if(searchBrand1 != 3 && searchBrand2 == 3 && searchBrand3 != 3){ /* 브랜드 1,3 체크시*/
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
	
	// 아이템 정보 확인
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
	//	테이블명을 약식으로 정의한 후 일반데이터는 바로 불러오고 
    // 	상품브랜드,재질,사이즈는 코드를 기준으로 JOIN하여 불러올때 코드를 명칭으로 변환하여 불러냄
    
	// 아이템 등록 
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

	// 판매중인 아이템 리스트화
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

	// 아이템 숫자(페이징 위함)
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

	// 아이템 사진 저장
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
	
	// 장바구니에 들어간 아이템 카운트
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
	
	// 아이템 좋아요 숫자 증가
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
