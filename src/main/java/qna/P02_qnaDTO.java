package qna;

import java.sql.Timestamp;

public class P02_qnaDTO {
	private int qnaNum; 	// 문의글 번호
	private int ans; 		// 글 상태
	private int ref; 		// 글레퍼런스
	private int step; 		// 답글
	private String id; 		// 글쓴이
	private String title; 	// 글제목
	private String image; 	// 글사진
	private String content; // 글내용
	private Timestamp reg;	// 글쓴시각
	
	public void setQnaNum(int qnaNum) {this.qnaNum = qnaNum;}
	public void setAns(int ans) {this.ans = ans;}
	public void setRef(int ref) {this.ref = ref;}
	public void setStep(int step) {this.step = step;}
	public void setId(String id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setImage(String image) {this.image = image;}
	public void setContent(String content) {this.content = content;}
	public void setReg(Timestamp reg) {this.reg = reg;} 

	public int getQnaNum() {return qnaNum;}
	public int getAns() {return ans;}
	public int getRef() {return ref;}
	public int getStep() {return step;}
	public String getId() {return id;}
	public String getTitle() {return title;}
	public String getImage() {return image;}
	public String getContent() {return content;}
	public Timestamp getReg() {return reg;}
}
