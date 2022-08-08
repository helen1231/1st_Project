package item;

import java.sql.Timestamp;

public class P02_itemDTO {
	private int	pdNum;			// ��ǰ��ȣ
	private int	itemNum;		// �����۹�ȣ
	private int	itemSize;		// ������ ũ��
	private int	itemMat;		// ������ ����
	private int	itemBrand;		// ������ �귣��
	private int	itemGood;		// ������ ��ٱ��� �� ��
	private int	itemStock;		// ������ �� ����
	private int price;			// ������
	private int	price1;			// �Ϲݰŷ���
	private int	price2;			// �����ŷ���
	private int	price3;			// ����
	private int	readCount;		// ��ȸ��
	private int	pdStatus;		// ��ǰ����
	private int	searchSize;		// ������˻�
	private int	searchBrand1;	// 1�� �귣��˻�
	private int	searchBrand2;	// 2�� �귣��˻�
	private int	searchBrand3;	// 3�� �귣��˻�
	private int	searchMinprice;	// �ּڰ��˻�
	private int	searchMaxprice;	// �ִ񰡰˻�
	private int	itemPrice;		// ��ǰ �ֱٰŷ���
	private int itemcount;		// ������ ���
	private String	id;			// ȸ�� ���̵�
	private String	itemName;	// ������ �̸�
	private String	content;	// ������ ����
	private String	image;		// ������ ����
	private String	isize;		// ������ ũ�� String
	private String	ibrand;		// ������ �귣�� String
	private String	imat;		// ������ ���� String
	private String 	itemImage;	// ������ �̹���
	private Timestamp	pdReg;	// ��ǰ�����

	public void setPdNum(int pdNum) {this.pdNum = pdNum;}
	public void setItemNum(int itemNum) {this.itemNum = itemNum;}
	public void setItemSize(int itemSize) {this.itemSize = itemSize;}
	public void setItemMat(int itemMat) {this.itemMat = itemMat;}
	public void setImat(String imat) {this.imat = imat;}
	public void setItemBrand(int itemBrand) {this.itemBrand = itemBrand;}
	public void setIbrand(String ibrand) {this.ibrand = ibrand;}
	public void setIsize(String isize) {this.isize = isize;}
	public void setItemGood(int itemGood) {this.itemGood = itemGood;}
	public void setItemStock(int itemStock) {this.itemStock = itemStock;}
	public void setPrice1(int price1) {this.price1 = price1;}
	public void setPrice2(int price2) {this.price2 = price2;}
	public void setPrice3(int price3) {this.price3 = price3;}
	public void setPrice(int price) {this.price = price;}
	public void setItemPrice(int itemPrice) {this.itemPrice = itemPrice;}
	public void setReadCount(int readCount) {this.readCount = readCount;}
	public void setPdStatus(int pdStatus) {this.pdStatus = pdStatus;}
	public void setSearchSize(int searchSize) {this.searchSize = searchSize;}
	public void setSearchBrand1(int searchBrand1) {this.searchBrand1 = searchBrand1;}
	public void setSearchBrand2(int searchBrand2) {this.searchBrand2 = searchBrand2;}
	public void setSearchBrand3(int searchBrand3) {this.searchBrand3 = searchBrand3;}
	public void setSearchMinprice(int searchMinprice) {this.searchMinprice = searchMinprice;}
	public void setSearchMaxprice(int searchMaxprice) {this.searchMaxprice = searchMaxprice;}
	public void setId(String id) {this.id = id;}
	public void setItemName(String itemName) {this.itemName = itemName;}
	public void setContent(String content) {this.content = content;}
	public void setImage(String image) {this.image = image;}
	public void setItemImage(String itemImage) {this.itemImage = itemImage;}
	public void setPdReg(Timestamp pdReg) {this.pdReg = pdReg;}
	public void setItemcount(int itemcount) {this.itemcount = itemcount;}
	
	public int getPdNum() {return pdNum;}
	public int getItemNum() {return itemNum;}
	public int getItemSize() {return itemSize;}
	public int getItemMat() {return itemMat;}
	public String getImat() {return imat;}
	public int getItemBrand() {return itemBrand;}
	public String getIbrand() {return ibrand;}
	public int getItemGood() {return itemGood;}
	public int getItemStock() {return itemStock;}
	public int getPrice1() {return price1;}
	public int getPrice2() {return price2;}
	public int getPrice3() {return price3;}
	public int getItemPrice() {return itemPrice;}
	public int getPrice() {return price;}
	public int getReadCount() {return readCount;}
	public int getPdStatus() {return pdStatus;}
	public int getSearchSize() {return searchSize;}
	public int getSearchBrand1() {return searchBrand1;}
	public int getSearchBrand2() {return searchBrand2;}
	public int getSearchBrand3() {return searchBrand3;}
	public int getSearchMinprice() {return searchMinprice;}
	public int getSearchMaxprice() {return searchMaxprice;}
	public String getId() {return id;}
	public String getItemName() {return itemName;}
	public String getContent() {return content;}
	public String getImage() {return image;}
	public String getItemImage() {return itemImage;}
	public Timestamp getPdReg() {return pdReg;}
	public String getIsize() {return isize;}
	public int getItemcount() {return itemcount;}
}