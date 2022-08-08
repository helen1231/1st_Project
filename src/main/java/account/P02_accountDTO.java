package account;
import java.sql.Timestamp;

public class P02_accountDTO {
	private String id;				// ���̵�
	private String pw;				// ��й�ȣ
	private String name;			// �̸�
	private String birth;			// �������
	private String phone;			// ��ȭ��ȣ(Ǯ ��ȭ��ȣ, ��Ż�~����ȣ)
	private String pC;				// ��ȭ��ȣ(��Ż�, phoneCompany)
	private String phone1;			// ��ȭ��ȣ(ó����ȣ)
	private String phone2;			// ��ȭ��ȣ(�߰�)
	private String phone3;			// ��ȭ��ȣ(����ȣ)
	private String mail;			// �̸���(Ǯ ����)
	private String mail1;			// �̸���(���Է�, ���̵�)
	private String mail2;			// �̸���(���� �ּ�)
	private String adrs;			// �����ȣ~���ּҸ� ��ģ �ּ�
	private String postcode;		// �����ȣ
	private String address;			// �ּ�
	private String detailAddress;	// ���ּ�
	private String extraAddress;	// �ּ� �����׸�
	private int area;				// �ּ� ���� ���� �з� �ڵ�
	private int point;				// ���Ž� ���� ����Ʈ
	private int status;				// ȸ�� ���� �ڵ�(�Ϲ�, Ż�� ��)
	private Timestamp loginTime;	// �α��� �ð�
	private Timestamp reg;			// ȸ������ �ð�
	private int usepoint; 			// �������Ʈ
	private int stackpoint; 		// ��������Ʈ
	private String question;		// ��й�ȣ ã�� ����
	private String ans;				// ��й�ȣ ã�� ���� ��
	
	public String getId() {return id;}
	public String getPw() {return pw;}
	public String getName() {return name;}
	public String getBirth() {return birth;}
	public String getpC() {return pC;}
	public String getPhone1() {return phone1;}
	public String getPhone2() {return phone2;}
	public String getPhone3() {return phone3;}
	public String getMail1() {return mail1;}
	public String getMail2() {return mail2;}
	public String getPostcode() {return postcode;}
	public String getAddress() {return address;}
	public String getDetailAddress() {return detailAddress;}
	public String getExtraAddress() {return extraAddress;}
	public int getArea() {return area;}
	public int getPoint() {return point;}
	public int getStatus() {return status;}
	public Timestamp getLoginTime() {return loginTime;}
	public Timestamp getReg() {return reg;}
	public int getUsepoint() {return usepoint;}
	public int getStackpoint() {return stackpoint;}
	public String getQuestion() {return question;}
	public String getAns() {return ans;}
	
	public void setpC(String pC) {this.pC = pC;}
	public void setBirth(String birth) {this.birth = birth;}
	public void setPhone(String phone) {this.phone = phone;}
	public void setMail(String mail) {this.mail = mail;}
	public void setDetailAddress(String detailAddress) {this.detailAddress = detailAddress;}
	public void setExtraAddress(String extraAddress) {this.extraAddress = extraAddress;}
	public void setArea(int area) {this.area = area;}
	public void setPoint(int point) {this.point = point;}
	public void setStatus(int status) {this.status = status;}
	public void setLoginTime(Timestamp loginTime) {this.loginTime = loginTime;}
	public void setReg(Timestamp reg) {this.reg = reg;}
	public void setStackpoint(int stackpoint) {this.stackpoint = stackpoint;}
	public void setUsepoint(int usepoint) {this.usepoint = usepoint;}
	
	public String getPhone() {
		if(phone1 != null && phone2 != null && phone3 != null) {
			return phone = getPhone1() + "-" + getPhone2() + "-" + getPhone3();
		} else {
			return phone;
		}
	}
	
	public String getMail() { 
		if(mail1 != null && mail2 != null) {
			return mail = getMail1() + "@" + getMail2();
		} else {
			return mail;
		}
	}
	
	public String getAdrs() {
		if(postcode != null && address != null) {
			return adrs = getPostcode() + " " + getAddress() + " " + getDetailAddress() + " " + getExtraAddress();
		} else {
			return adrs;
		}
	}
	
	public void setId(String id) {
		if(id == null || id.isEmpty()) {
			System.out.println("���̵� �Է����ּ���.");
		}else if(id.length() < 6 || id.length() > 12) {
				System.out.println("���̵�� 6~12�� �̳��� �Է��ϼ���.");
		}else {
			this.id = id;
		}
	}
	
	public void setPw(String pw) {
		if(pw == null || pw.isEmpty()) {
			System.out.println("��й�ȣ�� �Է����ּ���.");
		}else if(pw.length() < 6 || pw.length() > 18) {
			System.out.println("��й�ȣ�� 6~18�� �̳��� �Է��ϼ���.");
		}else {
			this.pw = pw;
		}
	}
	
	public void setName(String name) {
		if(name == null || name.isEmpty()) {
			System.out.println("�̸��� �Է����ּ���.");
		}else if(name.length() < 2 || name.length() > 5){
			System.out.println("�̸��� 2~5�� �̳��� �Է��ϼ���.");
		}else {
			this.name = name;
		}
	}
	
	public void setPhone1(String phone1) {
		if(phone1 == null || phone1.isEmpty()) {
			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
		}else if(phone1.length() != 3) {
			System.out.println("��ȭ��ȣ�� �ٸ��� �Է����ּ���.");
		}else {		
			this.phone1 = phone1;
		}
	}
	
	public void setPhone2(String phone2) {
		if(phone2 == null || phone2.isEmpty()) {
			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
		}else if(phone2.length() < 3 || phone2.length() > 4) {
			System.out.println("��ȭ��ȣ�� �ٸ��� �Է����ּ���.");
		}else {		
			this.phone2 = phone2;
		}
	}
	
	public void setPhone3(String phone3) {
		if(phone3 == null || phone3.isEmpty()) {
			System.out.println("��ȭ��ȣ�� �Է����ּ���.");
		}else if(phone3.length() != 4) {
			System.out.println("��ȭ��ȣ�� �ٸ��� �Է����ּ���.");
		}else {		
			this.phone3 = phone3;
		}
	}
	
	public void setMail1(String mail1) {
		if(mail1 == null || mail1.isEmpty()) {
			System.out.println("�̸����� �Է����ּ���.");
		}else {
			this.mail1 = mail1;
		}
	}				
	
	public void setMail2(String mail2) {
		if(mail2 == null || mail2.isEmpty()) {
			System.out.println("�̸����� �Է����ּ���.");
		}else {
			this.mail2 = mail2;
		}
	}
		
	public void setPostcode(String postcode) {
		if(postcode == null || postcode.isEmpty()) {
			System.out.println("�����ȣ ã��� �˻��Ͽ� �����ȣ�� �Է����ּ���.");
		}else {
			this.postcode = postcode;
		}
	}
	
	public void setAddress(String address) {
		if(address == null || address.isEmpty()) {
			System.out.println("�����ȣ ã��� �˻��Ͽ� �ּҸ� �Է����ּ���.");
		}else {
			this.address = address;
		}
	}
	
	// �� �ּ� �׸� �� ���� null�� ���� > ���� �ּҸ� DB�� �����Ҷ� null �� ���� 
	public void setAdrs(String adrs) {
		if(adrs.indexOf("null") >= 0) {
			this.adrs = adrs.replace("null", "");
		}else if(adrs.indexOf("  ") != -1) { // ����Ǵ��� Ȯ�� �ʿ�
				this.adrs = adrs.replace("  ", " ");
		}else {
			this.adrs = adrs;
		}
	}

	public void setQuestion(String question) {
		if(question == null || question.isEmpty()) {
			System.out.println("��й�ȣ ã�� ������ �������ּ���.");
		}else {
			this.question = question;
		}
	}
	
	public void setAns(String ans) {
		if(ans == null || ans.isEmpty()) {
			System.out.println("��й�ȣ ã�� ������ ���� �Է����ּ���.");
		}else {
		this.ans = ans;
		}
	}
	
}