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
	
	//��ۻ���
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

	//���ۼ��� �� ������ �����۸�
	public ArrayList<reviewDTO> buyList(String id){
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select distinct itemname from "
					+ " (select * from p02_s_record r, p02_item i where r.itemnum = i.itemnum) "
					+ " where buyid = ? and itemname not in '�����ٶ��ϴ�.'";
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
	
	//���� ���ƿ� ��������
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
	
	//���侴 ����� ������ �����ߴ��� �˻� �� (����) ����
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

	//������ ���̵� ����
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

	//����,������ ���̵� �˻�
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
	
	
	//����ۼ�
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

	//��� ����
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

	//��� ���밡����
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
	
	//�� ����
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
	
	// ��� ���
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

	// �ð����
	public String calculateTime(Date date) {
        long curTime = System.currentTimeMillis(); // System.currentTimeMillis() : 1970�� 1�� 1�� 0�� 0�� 0�ʺ��� ��������� �ð��� �и���(1/1000) ������ ȯ���� ���� ���ڷ� ��ȯ��.
        long regTime = date.getTime(); // date.getTime() : �޾ƿ� date ���� 1970�� 1�� 1�� 0�� 0�� 0�ʺ��� ��������� �ð��� �и���(1/1000) ������ ȯ���� ���� ���ڷ� ��ȯ��.
        // Date.getTime()�� Date�� �и�������� ��ȯ�ؼ� long�� ���� �����ͷ� ��ȯ�մϴ�.
        long diffTime = (curTime - regTime) / 1000; // 1000 �и��ʴ� 1�ʴϱ� getTime()���� ���� ���� 1000���� ������ �ʸ� ��´�.
        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "�����";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "����";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "�ð���";
        }else if(diffTime < TIME_MAXIMUM.YES) { 
        	msg = "����";
        }else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "����";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "����";
        } else { 
        	diffTime /= TIME_MAXIMUM.MONTH;
            msg = (diffTime) + "����";
        }
        return msg;
    }
	
	// ���ƿ� ���
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

	// ���ƿ� �Է�
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
	
	// ���� �۸�� ã��
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
	
	// �˻����
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
	
	// ��ȸ�� ī��Ʈ
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

	// ���ƿ� ���
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

	// ���� �б�
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
	
	// ���� ���ȭ
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

	// ���� �б�
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
	
	//�� ���� 
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
	
	//������ �ۼ��� ���� ����ѹ� ��������
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

	//��ǰ�� ���丮��Ʈ
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
	
	//���ƿ� ���� �˻�
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
	
	//����� �� ���ƿ� ���� Ȯ��
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