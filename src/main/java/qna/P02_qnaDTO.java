package qna;

import java.sql.Timestamp;

public class P02_qnaDTO {
	private int qnaNum; 	// ���Ǳ� ��ȣ
	private int ans; 		// �� ����
	private int ref; 		// �۷��۷���
	private int step; 		// ���
	private String id; 		// �۾���
	private String title; 	// ������
	private String image; 	// �ۻ���
	private String content; // �۳���
	private Timestamp reg;	// �۾��ð�
	
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
