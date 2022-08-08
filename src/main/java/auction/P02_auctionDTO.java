package auction;

import java.sql.Timestamp;

public class P02_auctionDTO {
	private int aucNum;			// ��Ź�ȣ
	private String aucName;		// ���ǰ �̸�
	private String aucContent;	// ���ǰ ����
	private String aucImage;	// ���ǰ �̹���
	private int aucStatus;		// ��Ż���
	private Timestamp startReg;	// ��Ž�����
	private Timestamp endReg;	// ���������
	
	public void setAucNum(int aucNum) {this.aucNum = aucNum;}
	public void setAucName(String aucName) {this.aucName = aucName;}
	public void setAucContent(String aucContent) {this.aucContent = aucContent;}
	public void setAucImage(String aucImage) {this.aucImage = aucImage;}
	public void setAucStatus(int aucStatus) {this.aucStatus = aucStatus;}
	public void setStartReg(Timestamp startReg) {this.startReg = startReg;}
	public void setEndReg(Timestamp endReg) {this.endReg = endReg;}
	
	public int getAucNum() {return aucNum;	}
	public String getAucName() {return aucName;	}
	public String getAucContent() {return aucContent;}
	public String getAucImage() {return aucImage;}
	public int getAucStatus() {return aucStatus;}
	public Timestamp getStartReg() {return startReg;}
	public Timestamp getEndReg() {return endReg;}
}