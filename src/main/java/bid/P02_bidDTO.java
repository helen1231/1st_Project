package bid;

import java.sql.Timestamp;

public class P02_bidDTO {
	private int bidNum;		// ������ȣ
	private int aucNum;		// ��Ź�ȣ
	private String id;		// ������
	private int price;		// ������
	private Timestamp reg;	// ������
	
	public void setBidNum(int bidNum) {this.bidNum = bidNum;}
	public void setAucNum(int aucNum) {this.aucNum = aucNum;}
	public void setId(String id) {this.id = id;}
	public void setPrice(int price) {this.price = price;}
	public void setReg(Timestamp reg) {this.reg = reg;}
	
	public int getBidNum() {return bidNum;}
	public int getAucNum() {return aucNum;}
	public String getId() {return id;}
	public int getPrice() {return price;}
	public Timestamp getReg() {return reg;}
}
