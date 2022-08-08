package review;
import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jdbc.OracleConnection;


public class reviewDAO {
	Connection conn =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//댓글삭제
	public int deleteComments(int renum,String id,int comnum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_comments set status = 1 where renum =? and com_id = ? and comnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setString(2, id);
			pstmt.setInt(3, comnum);
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

	//글작성할 때 선택할 아이템명
	public ArrayList<reviewDTO> buyList(String id){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select distinct itemname from "
					+ " (select * from p02_s_record r, p02_item i where r.itemnum = i.itemnum) "
					+ " where buyid = ? and itemname not in '연락바랍니다.'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setItemname(rs.getString("itemname"));
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
	
	//누가 좋아요 눌렀는지
	public ArrayList<reviewDTO> whoGood(int renum) {
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select goodperson from p02_good where renum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reviewDTO dto = new reviewDTO();
				dto.setGoodPerson(rs.getString("goodperson"));
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
	
	//리뷰쓴 사람이 물건을 구매했는지 검사 후 (구매) 기입
	public boolean buyCheck(String id , String itemname) {
		boolean result = false;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from "
					+ " p02_s_record s , p02_item i where s.itemnum = i.itemnum "				
					+ " and s.buyid=? and i.itemname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, itemname);
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

	//삭제시 아이디 삭제
	public boolean passwordCheck(String id,String pw) {	
		boolean result = false;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_account where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
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

	//수정,삭제시 아이디 검사
	public reviewDTO idCheck(int renum) {
		reviewDTO dto = new reviewDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_review where renum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setRenum(rs.getInt("renum"));
				dto.setContent(rs.getString("content"));
				dto.setItemname(rs.getString("itemname"));
				dto.setImage(rs.getString("image"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
				dto.setTitle(rs.getString("title"));
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return dto;
	}	
	
	
	//대댓작성
	public int cocoInsert(reviewDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_comments(comnum,com_id,com_content,com_ref,com_step,renum) "
					+ "values(comments_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCom_id());
			pstmt.setString(2, dto.getCom_content());
			pstmt.setInt(3, dto.getCom_ref());
			pstmt.setInt(4,dto.getCom_step());
			pstmt.setInt(5, dto.getRenum());
			result =pstmt.executeUpdate();
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}	

	//댓글 갯수
	public int commentsCount(int renum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_comments where renum=? and status = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			result = rs.getInt(1);
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
	return result;
	}	

	//댓글 내용가져옴
	public ArrayList<reviewDTO> comList(int renum , int start , int end){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();	
			String sql =" select * from "
						+ " (select p02_comments.*,rownum r,to_char(comreg,'yy-mm-dd') setreg from "
						+ " (select * from p02_comments where renum = ? order by com_ref desc, comreg) p02_comments) "
						+ " where r >= ? and r <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setCom_content(rs.getString("com_content"));
				dto.setCom_id(rs.getString("com_id"));
				dto.setComnum(rs.getInt("comnum"));
				dto.setCom_ref(rs.getInt("com_ref"));
				dto.setCom_step(rs.getInt("com_step"));
				dto.setRenum(rs.getInt("renum"));
				dto.setComReg(rs.getTimestamp("comreg"));
				dto.setSetReg(rs.getString("setreg"));
				dto.setStatus(rs.getInt("status"));
				list.add(dto);
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return list;
	}
	
	//글 삭제
	public int reviewdelete(int renum){
		int result = 0;
		try {
	    	conn = OracleConnection.getConnection();
	    	String sql = "update P02_REVIEW set status = 1 where renum=?";
	     	pstmt = conn.prepareStatement(sql);
	     	pstmt.setInt(1, renum); 
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
	
	// 댓글 등록
	public int commentsInsert(reviewDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
		 	String sql = "insert into p02_comments(comnum,com_id,com_content,renum,com_ref,com_step,comreg) values(comments_seq.nextval,?,?,?,comments_seq.nextval,0,sysdate)";
		 	pstmt = conn.prepareStatement(sql);
		 	pstmt.setString(1,dto.getCom_id());
		 	pstmt.setString(2,dto.getCom_content());
		 	pstmt.setInt(3, dto.getRenum());
		 	result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}

	// 시간계산
	public String calculateTime(Date date) {
        long curTime = System.currentTimeMillis(); // System.currentTimeMillis() : 1970년 1월 1일 0시 0분 0초부터 현재까지의 시간을 밀리초(1/1000) 단위로 환산한 값을 숫자로 반환함.
        long regTime = date.getTime(); // date.getTime() : 받아온 date 에서 1970년 1월 1일 0시 0분 0초부터 현재까지의 시간을 밀리초(1/1000) 단위로 환산한 값을 숫자로 반환함.
        // Date.getTime()은 Date를 밀리세컨드로 변환해서 long형 숫자 데이터로 반환합니다.
        long diffTime = (curTime - regTime) / 1000; // 1000 밀리초는 1초니까 getTime()으로 구한 값을 1000으로 나누면 초를 얻는다.
        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간전";
        }else if(diffTime < TIME_MAXIMUM.YES) { 
        	msg = "어제";
        }else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달전";
        } else { 
        	diffTime /= TIME_MAXIMUM.MONTH;
            msg = (diffTime) + "년전";
        }
        return msg;
    }
	
	// 좋아요 취소
	public int gooddelete(int renum,String id) {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = "delete from P02_good where renum=? and goodPerson=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}

	// 좋아요 입력
	public int goodInsert(int renum,String id) {
		int result = 0;
		try{
			conn = OracleConnection.getConnection();
			String sql = " insert into p02_good(renum,goodperson) values(?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}
	
	// 나의 글목록 찾기
	public ArrayList<reviewDTO> reviewSearchMove(String id){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_review where id=? and status = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setRenum(rs.getInt("renum"));
				dto.setItemname(rs.getString("itemname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));
				dto.setId(rs.getString("id"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
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
	
	// 검색기능
	public ArrayList<reviewDTO> reviewSearch(String column, String search){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_review where "+ column +" like '%" + search + "%' and status = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setRenum(rs.getInt("renum"));
				dto.setItemname(rs.getString("itemname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));
				dto.setId(rs.getString("id"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
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
	
	// 조회수 카운트
	public int reviewCount() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_review where status = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}

	// 좋아요 취소
	public int goodMinor(int renum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_review set good = good - 1 where renum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}

	// 리뷰 읽기
	public reviewDTO readContent(int renum,String id) {
		reviewDTO dto = new reviewDTO();
		try{
			conn = OracleConnection.getConnection();
			String sql = "update P02_review set readcount = readcount+1 where renum = ? and status = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.executeUpdate();
			
			sql = "select * from p02_review where renum=?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setRenum(rs.getInt("renum"));
				dto.setContent(rs.getString("content"));
				dto.setItemname(rs.getString("itemname"));
				dto.setImage(rs.getString("image"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
				dto.setTitle(rs.getString("title"));
				dto.setStatus(rs.getInt("status"));
			}

			sql = "select count(*) from p02_good where renum=? and goodPerson=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setGoodCheck(rs.getInt("count(*)"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return dto;
	}
	
	// 리뷰 목록화
	public ArrayList<reviewDTO> reviewList(int start,int end){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = " select * from "
					+ " (select p02_review.* , rownum r from "
					+ " (select * from p02_review order by renum desc) p02_review where status = 0) "
					+ " where r >= ? and r <=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setId(rs.getString("id"));
				dto.setRenum(rs.getInt("renum"));
				dto.setItemname(rs.getString("itemname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readCount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
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

	// 리뷰 읽기
	public int reviewInsert(reviewDTO dto) {
		int result=0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_review(renum,id,itemname,title,content,image) "
					+ " values(review_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getItemname());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImage());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e) {}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}
	
	//글 수정 
	public int reviewupdate(reviewDTO dto){
		int result = 0;
	    try {
	    	conn = OracleConnection.getConnection();
	        String sql = "update P02_review set title = ? ,content = ? , image = ? where renum = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, dto.getTitle());
	        pstmt.setString(2, dto.getContent());
	        pstmt.setString(3, dto.getImage());
	        pstmt.setInt(4, dto.getRenum()); 
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
	
	//본인이 작성한 리뷰 리뷰넘버 가져오기
	public int maxrenum(String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT MAX(renum) FROM P02_REVIEW WHERE id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			result = rs.getInt("MAX(renum)");
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}	

	//상품별 리뷰리스트
	public ArrayList<reviewDTO> itemReviewList(int start,int end,String itemname){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = " select * from "
					+ " (select p02_review.* , rownum r from "
					+ " (select * from p02_review order by renum desc) p02_review where status = 0 and itemname = ? ) "
					+ " where r >= ? and r <=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemname);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO dto = new reviewDTO();
				dto.setId(rs.getString("id"));
				dto.setRenum(rs.getInt("renum"));
				dto.setItemname(rs.getString("itemname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));
				dto.setGood(rs.getInt("good"));
				dto.setReadCount(rs.getInt("readCount"));
				dto.setRevreg(rs.getTimestamp("revreg"));
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
	
	//좋아요 갯수 검색
	public int goodcount(int renum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT count(*) FROM P02_good WHERE renum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			result = rs.getInt("count(*)");
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
		return result;
	}	
	
	//리뷰글 내 좋아요 여부 확인
	public int goodcheck(int renum,String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT * FROM P02_good WHERE renum = ? and goodperson = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = result +1 ;
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally {
			if(rs!=null) {try{rs.close();}catch(SQLException e){}}
			if(conn !=null) {try {conn.close();}catch(SQLException e){}}
			if(pstmt !=null) {try {pstmt.close();}catch(SQLException e){}}
		}
	return result;
	}
}