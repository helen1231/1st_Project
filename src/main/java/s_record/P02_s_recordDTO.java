package s_record;

import java.sql.Timestamp;

public class P02_s_recordDTO {
	private int orderNum;	// 거래번호
	private int itemNum;	// 아이템번호
	private int pdNum;		// 상품번호
	private int aucNum;		// 경매번호
	private int pdSell;		// 판매상태
	private String buyId;	// 구매자
	private String sellId;	// 판매자
	private int price;		// 시세가
	private int	profit;		// 이익
	private int	usepoint;	// 사용포인트
	private String itemName;// 아이템이름
	private int	itemBrand;	// 아이템 브랜드
	private String ibrand;	// 아이템 브랜드 String
	private Timestamp selReg;// 거래일자
	
	public void setOrderNum(int orderNum) {this.orderNum = orderNum;}
	public void setItemNum(int itemNum) {this.itemNum = itemNum;}
	public void setPdNum(int pdNum) {this.pdNum = pdNum;}
	public void setAucNum(int aucNum) {this.aucNum = aucNum;}
	public void setPdSell(int pdSell) {this.pdSell = pdSell;}
	public void setBuyId(String buyId) {this.buyId = buyId;}
	public void setSellId(String sellId) {this.sellId = sellId;}
	public void setPrice(int price) {this.price = price;}
	public void setUsepoint(int usepoint) {this.usepoint = usepoint;}
	public void setProfit(int profit) {this.profit = profit;}
	public void setItemName(String itemName) {this.itemName = itemName;}
	public void setItemBrand(int itemBrand) {this.itemBrand = itemBrand;}
	public void setIbrand(String ibrand) {this.ibrand = ibrand;}
	public void setSelReg(Timestamp selReg) {this.selReg = selReg;}
	
	public int getOrderNum() {return orderNum;}
	public int getItemNum() {return itemNum;}
	public int getPdNum() {return pdNum;}
	public int getAucNum() {return aucNum;}
	public int getPdSell() {return pdSell;}
	public String getBuyId() {return buyId;}
	public String getSellId() {return sellId;}
	public int getPrice() {return price;}
	public int getUsepoint() {return usepoint;}
	public int getProfit() {return profit;}
	public String getItemName() {return itemName;}
	public int getItemBrand() {return itemBrand;}
	public String getIbrand() {return ibrand;}
	public Timestamp getSelReg() {return selReg;}
}
