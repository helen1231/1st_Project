package product;

import java.sql.Timestamp;

public class P02_productDTO {
	private int pdNum;			// ��ǰ��ȣ
	private int itemNum;		// �����۹�ȣ
	private int price1;			// �Ϲݰ���
	private int price2;			// ��������
	private int price3;			// ���񰡰�
	private int status;			// �ǸŻ���
	private int pdSell;			// �ǸŹ��
	private int	itemStock;		// ������ ����
	private int	profit;			// ����
	private int	usepoint;		// ���� ����Ʈ
	private String sellId;		// �Ǹ���
	private String buyId;		// ������
	private String	itemName;	// ������ �̸�
	private String	content;	// ������ ����
	private String	image;		// ������ ����
	private String	isize;		// ������ ũ��
	private String	ibrand;		// ������ �귣��
	private String	imat;		// ������ ����
	private String pstatus;		// �ǸŻ��� String
	private String ppdSell; 	// ��ǰ �Ǹſ��� String 
	private Timestamp pdReg; 	// ��ǰ �����
	
	public void setUsepoint(int usepoint) {this.usepoint = usepoint;}
	public void setProfit(int profit) {this.profit = profit;}
	public void setPdNum(int pdNum) {this.pdNum = pdNum;}
	public void setItemNum(int itemNum) {this.itemNum = itemNum;}
	public void setPrice1(int price1) {this.price1 = price1;}
	public void setPrice2(int price2) {this.price2 = price2;}
	public void setPrice3(int price3) {this.price3 = price3;}
	public void setStatus(int status) {this.status = status;}
	public void setPdSell(int pdSell) {this.pdSell = pdSell;}
	public void setItemStock(int itemStock) {this.itemStock = itemStock;}
	public void setSellId(String sellId) {this.sellId = sellId;}
	public void setBuyId(String buyId) {this.buyId = buyId;}
	public void setItemName(String itemName) {this.itemName = itemName;}
	public void setContent(String content) {this.content = content;}
	public void setImage(String image) {this.image = image;}
	public void setIsize(String isize) {this.isize = isize;}
	public void setIbrand(String ibrand) {this.ibrand = ibrand;}
	public void setImat(String imat) {this.imat = imat;}
	public void setPstatus(String pstatus) {this.pstatus = pstatus;}
	public void setPpdSell(String ppdSell) {this.ppdSell = ppdSell;}
	public void setPdReg(Timestamp pdReg) {this.pdReg = pdReg;}

	public int getUsepoint() {return usepoint;}
	public int getProfit() {return profit;}
	public int getItemNum() {return itemNum;}
	public int getPdNum() {return pdNum;}
	public int getPrice1() {return price1;}
	public int getPrice2() {return price2;}
	public int getPrice3() {return price3;}
	public int getStatus() {return status;}
	public int getPdSell() {return pdSell;}
	public int getItemStock() {return itemStock;}
	public String getSellId() {return sellId;}
	public String getBuyId() {return buyId;}
	public String getItemName() {return itemName;}
	public String getContent() {return content;}
	public String getImage() {return image;}
	public String getIsize() {return isize;}
	public String getIbrand() {return ibrand;}
	public String getImat() {return imat;}
	public String getPstatus() {return pstatus;}
	public String getPpdSell() {return ppdSell;}
	public Timestamp getPdReg() {return pdReg;}
}
