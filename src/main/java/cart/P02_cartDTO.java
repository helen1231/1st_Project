package cart;
import java.sql.Timestamp;

public class P02_cartDTO {
	private String id;			// 아이디
	private int itemNum;		// 상품번호
	private String itemName;	// 상품명
	private String ibrand;		// 상품 브랜드명
	private int price;			// 장바구니에 있는 상품의 최근 거래가격
	private Timestamp selReg;	// 거래일시
	private Timestamp cartReg;	// 장바구니에 넣은 시각
	
	public void setId(String id) { this.id = id; }
	public void setItemNum(int itemNum) { this.itemNum = itemNum ; }
	public void setItemName(String itemName) { this.itemName = itemName ; }
	public void setIbrand(String ibrand) { this.ibrand = ibrand; }
	public void setPrice(int price) { this.price = price ; }
	public void setSelReg(Timestamp selReg) {this.selReg = selReg;}
	public void setCartReg(Timestamp cartReg) {this.cartReg = cartReg;}
	
	public String getId() { return id; }
	public int getItemNum() { return itemNum; }
	public String getItemName() { return itemName; }
	public String getIbrand() { return ibrand; }
	public int getPrice() { return price; }
	public Timestamp getSelReg() {return selReg;}
	public Timestamp getCartReg() {return cartReg;}

}
