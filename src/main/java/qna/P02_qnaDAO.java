package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.OracleConnection;

public class P02_qnaDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 문의사항 글작성 및 답글 작성
	public int insertQna(P02_qnaDTO dto) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "insert into p02_qna (qnanum, id, title, image, content)"
					+ " values (qnanum_seq.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getImage());
			pstmt.setString(4, dto.getContent());
			result = pstmt.executeUpdate();
			
			int max = 0;
			sql = "select max(qnanum) from p02_qna";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				max = rs.getInt("max(qnanum)");
			}
			
			if(dto.getQnaNum() == 0) {
				sql = "update p02_qna set ref=? where qnanum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, max);
				pstmt.setInt(2, max);
				result = pstmt.executeUpdate();
			}else {
				sql = "update p02_qna set ref=?, step=?, ans=? where qnanum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getRef());
				pstmt.setInt(2, 1);
				pstmt.setInt(3, 1);
				pstmt.setInt(4, max);
				result = pstmt.executeUpdate();
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
	
	//문의사항 리스트화
	public ArrayList<P02_qnaDTO> listQna(int start, int end){
		ArrayList<P02_qnaDTO> list = new ArrayList<P02_qnaDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from "
					+ "(select p02_qna.*, rownum r from"
					+ "(select * from p02_qna order by ref desc,step)p02_qna)"
					+ "where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_qnaDTO dto = new P02_qnaDTO();
				dto.setQnaNum(rs.getInt("qnanum"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setImage(rs.getString("image"));
				dto.setContent(rs.getString("content"));
				dto.setAns(rs.getInt("ans"));
				dto.setRef(rs.getInt("ref"));
				dto.setStep(rs.getInt("step"));
				dto.setReg(rs.getTimestamp("reg"));
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
	public int count() {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select count(*) from p02_qna";
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
	
	//문의사항 읽기
	public P02_qnaDTO readQna(int qnaNum) {
		P02_qnaDTO dto = new P02_qnaDTO();
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_qna where qnanum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setQnaNum(rs.getInt("qnanum"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setImage(rs.getString("image"));
				dto.setContent(rs.getString("content"));
				dto.setAns(rs.getInt("ans"));
				dto.setRef(rs.getInt("ref"));
				dto.setStep(rs.getInt("step"));
				dto.setReg(rs.getTimestamp("reg"));
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

	// 문의사항 수정
	public int updateQna(P02_qnaDTO dto, int qnaNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_qna set title=?, image=?, content=? where qnanum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getImage());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, qnaNum);
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
	
	// 문의사항 삭제
	public int deleteQna(int qnaNum) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "update p02_qna set ans=2 where qnanum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
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
		
	//글쓴이 확인
	public boolean checkUser(String sid, String pw) {
		boolean result = false;
		try {
			conn = OracleConnection.getConnection();
			String sql = "select * from p02_account where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			pstmt.setString(2, pw);
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
	//내가 쓴 QNA 글갯수 + 답변갯수
	public int myqnacount(String id) {
		int result = 0;
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT COUNT(*) FROM "
					+ "(SELECT *  FROM P02_qna WHERE REF IN "
					+ "((SELECT ref FROM p02_qna WHERE id = ? )))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
		
	//내가 쓴 QNA 글목록 + 답변 
	public ArrayList<P02_qnaDTO> mylistQna(int start, int end,String id){
		ArrayList<P02_qnaDTO> list = new ArrayList<P02_qnaDTO>();
		try {
			conn = OracleConnection.getConnection();
			String sql = " SELECT * FROM "
					+ " (SELECT P02_qna.*,ROWNUM r  FROM P02_qna WHERE REF IN "
					+ " ((SELECT ref FROM p02_qna WHERE id = ? )) order BY REF DESC,step) "
					+ " where r >= ? and r <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				P02_qnaDTO dto = new P02_qnaDTO();
				dto.setQnaNum(rs.getInt("qnanum"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setImage(rs.getString("image"));
				dto.setContent(rs.getString("content"));
				dto.setAns(rs.getInt("ans"));
				dto.setRef(rs.getInt("ref"));
				dto.setStep(rs.getInt("step"));
				dto.setReg(rs.getTimestamp("reg"));
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
