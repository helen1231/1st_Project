package product;

import java.sql.Timestamp;

public class P02_productDTO {
	private int pdNum;			// 상품번호
	private int itemNum;		// 아이템번호
	private int price1;			// 일반가격
	private int price2;			// 입찰가격
	private int price3;			// 예비가격
	private int status;			// 판매상태
	private int pdSell;			// 판매방식
	private int	itemStock;		// 아이템 수량
	private int	profit;			// 이익
	private int	usepoint;		// 사용된 포인트
	private String sellId;		// 판매자
	private String buyId;		// 구매자
	private String	itemName;	// 아이템 이름
	private String	content;	// 아이템 내용
	private String	image;		// 아이템 사진
	private String	isize;		// 아이템 크기
	private String	ibrand;		// 아이템 브랜드
	private String	imat;		// 아이템 재질
	private String pstatus;		// 판매상태 String
	private String ppdSell; 	// 상품 판매여부 String 
	private Timestamp pdReg; 	// 상품 등록일
	
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
