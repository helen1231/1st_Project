package review;
import java.sql.Timestamp;
public class reviewDTO {
	private int renum;		// �۹�ȣ
	private String itemname;// �������̸�
	private String id;		// �۾���
	private String setReg;	// �۾��ð����κ��� �󸶳� �������� ǥ��
	private int status;		// �� ����
	private String title;	// ������
	private String content;	// �۳���
	private String image;	// ÷�λ���
	private int good;		// �� ���ƿ�
	private String goodPerson;// ���ƿ� ���� ȸ��
	private int goodCheck;	// ���ƿ� ��
	private int readCount;	// ��ȸ ��
	private Timestamp revreg;// �۾� �ð�
	private int comnum;		// ��� ��ȣ
	private String com_content;// ��� ����
	private int com_ref;	// ��� ���۷���
	private int com_step;	// ��� �ܰ�
	private String com_id;	// ��۾���
	private Timestamp comReg;// ��� �� �ð�

	public void setRenum(int renum) {this.renum = renum;}
	public void setItemname(String itemname) {this.itemname = itemname;}
	public void setId(String id) {this.id = id;}
	public void setSetReg(String setReg) {this.setReg = setReg;}
	public void setStatus(int status) {this.status = status;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setImage(String image) {this.image = image;}
	public void setGood(int good) {this.good = good;}
	public void setGoodPerson(String goodPerson) {this.goodPerson = goodPerson;}
	public void setGoodCheck(int goodCheck) {this.goodCheck = goodCheck;}
	public void setReadCount(int readCount) {this.readCount = readCount;}
	public void setRevreg(Timestamp revreg) {this.revreg = revreg;}
	public void setComnum(int comnum) {this.comnum = comnum;}
	public void setCom_content(String com_content) {this.com_content = com_content;}
	public void setCom_ref(int com_ref) {this.com_ref = com_ref;}
	public void setCom_step(int com_step) {this.com_step = com_step;}
	public void setCom_id(String com_id) {this.com_id = com_id;}
	public void setComReg(Timestamp comReg) {this.comReg = comReg;}

	public int getRenum() {return renum;}
	public String getItemname() {return itemname;}
	public String getId() {return id;}
	public String getSetReg() {return setReg;}
	public int getStatus() {return status;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getImage() {return image;}
	public int getGood() {return good;}
	public String getGoodPerson() {return goodPerson;}
	public int getGoodCheck() {return goodCheck;}
	public int getReadCount() {return readCount;}
	public Timestamp getRevreg() {return revreg;}
	public int getComnum() {return comnum;}
	public String getCom_content() {return com_content;}
	public int getCom_ref() {return com_ref;}
	public int getCom_step() {return com_step;}
	public String getCom_id() {return com_id;}
	public Timestamp getComReg() {return comReg;}
}
