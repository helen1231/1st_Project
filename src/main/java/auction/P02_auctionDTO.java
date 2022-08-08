package auction;

import java.sql.Timestamp;

public class P02_auctionDTO {
	private int aucNum;			// 경매번호
	private String aucName;		// 경매품 이름
	private String aucContent;	// 경매품 설명
	private String aucImage;	// 경매품 이미지
	private int aucStatus;		// 경매상태
	private Timestamp startReg;	// 경매시작일
	private Timestamp endReg;	// 경매종료일
	
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