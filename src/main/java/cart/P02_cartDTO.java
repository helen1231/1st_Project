package cart;
import java.sql.Timestamp;

public class P02_cartDTO {
	private String id;			// ���̵�
	private int itemNum;		// ��ǰ��ȣ
	private String itemName;	// ��ǰ��
	private String ibrand;		// ��ǰ �귣���
	private int price;			// ��ٱ��Ͽ� �ִ� ��ǰ�� �ֱ� �ŷ�����
	private Timestamp selReg;	// �ŷ��Ͻ�
	private Timestamp cartReg;	// ��ٱ��Ͽ� ���� �ð�
	
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
