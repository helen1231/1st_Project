package account;
import java.sql.Timestamp;

public class P02_accountDTO {
	private String id;				// 아이디
	private String pw;				// 비밀번호
	private String name;			// 이름
	private String birth;			// 생년월일
	private String phone;			// 전화번호(풀 전화번호, 통신사~끝번호)
	private String pC;				// 전화번호(통신사, phoneCompany)
	private String phone1;			// 전화번호(처음번호)
	private String phone2;			// 전화번호(중간)
	private String phone3;			// 전화번호(끝번호)
	private String mail;			// 이메일(풀 계정)
	private String mail1;			// 이메일(고객입력, 아이디)
	private String mail2;			// 이메일(계정 주소)
	private String adrs;			// 우편번호~상세주소를 합친 주소
	private String postcode;		// 우편번호
	private String address;			// 주소
	private String detailAddress;	// 상세주소
	private String extraAddress;	// 주소 참고항목
	private int area;				// 주소 기준 지역 분류 코드
	private int point;				// 구매시 적립 포인트
	private int status;				// 회원 상태 코드(일반, 탈퇴 등)
	private Timestamp loginTime;	// 로그인 시간
	private Timestamp reg;			// 회원가입 시간
	private int usepoint; 			// 사용포인트
	private int stackpoint; 		// 적립포인트
	private String question;		// 비밀번호 찾기 질문
	private String ans;				// 비밀번호 찾기 질문 답
	
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
			System.out.println("아이디를 입력해주세요.");
		}else if(id.length() < 6 || id.length() > 12) {
				System.out.println("아이디는 6~12자 이내로 입력하세요.");
		}else {
			this.id = id;
		}
	}
	
	public void setPw(String pw) {
		if(pw == null || pw.isEmpty()) {
			System.out.println("비밀번호를 입력해주세요.");
		}else if(pw.length() < 6 || pw.length() > 18) {
			System.out.println("비밀번호는 6~18자 이내로 입력하세요.");
		}else {
			this.pw = pw;
		}
	}
	
	public void setName(String name) {
		if(name == null || name.isEmpty()) {
			System.out.println("이름을 입력해주세요.");
		}else if(name.length() < 2 || name.length() > 5){
			System.out.println("이름은 2~5자 이내로 입력하세요.");
		}else {
			this.name = name;
		}
	}
	
	public void setPhone1(String phone1) {
		if(phone1 == null || phone1.isEmpty()) {
			System.out.println("전화번호를 입력해주세요.");
		}else if(phone1.length() != 3) {
			System.out.println("전화번호를 바르게 입력해주세요.");
		}else {		
			this.phone1 = phone1;
		}
	}
	
	public void setPhone2(String phone2) {
		if(phone2 == null || phone2.isEmpty()) {
			System.out.println("전화번호를 입력해주세요.");
		}else if(phone2.length() < 3 || phone2.length() > 4) {
			System.out.println("전화번호를 바르게 입력해주세요.");
		}else {		
			this.phone2 = phone2;
		}
	}
	
	public void setPhone3(String phone3) {
		if(phone3 == null || phone3.isEmpty()) {
			System.out.println("전화번호를 입력해주세요.");
		}else if(phone3.length() != 4) {
			System.out.println("전화번호를 바르게 입력해주세요.");
		}else {		
			this.phone3 = phone3;
		}
	}
	
	public void setMail1(String mail1) {
		if(mail1 == null || mail1.isEmpty()) {
			System.out.println("이메일을 입력해주세요.");
		}else {
			this.mail1 = mail1;
		}
	}				
	
	public void setMail2(String mail2) {
		if(mail2 == null || mail2.isEmpty()) {
			System.out.println("이메일을 입력해주세요.");
		}else {
			this.mail2 = mail2;
		}
	}
		
	public void setPostcode(String postcode) {
		if(postcode == null || postcode.isEmpty()) {
			System.out.println("우편번호 찾기로 검색하여 우편번호를 입력해주세요.");
		}else {
			this.postcode = postcode;
		}
	}
	
	public void setAddress(String address) {
		if(address == null || address.isEmpty()) {
			System.out.println("우편번호 찾기로 검색하여 주소를 입력해주세요.");
		}else {
			this.address = address;
		}
	}
	
	// 각 주소 항목에 빈 값을 null로 받음 > 받은 주소를 DB에 저장할때 null 값 삭제 
	public void setAdrs(String adrs) {
		if(adrs.indexOf("null") >= 0) {
			this.adrs = adrs.replace("null", "");
		}else if(adrs.indexOf("  ") != -1) { // 적용되는지 확인 필요
				this.adrs = adrs.replace("  ", " ");
		}else {
			this.adrs = adrs;
		}
	}

	public void setQuestion(String question) {
		if(question == null || question.isEmpty()) {
			System.out.println("비밀번호 찾기 질문을 선택해주세요.");
		}else {
			this.question = question;
		}
	}
	
	public void setAns(String ans) {
		if(ans == null || ans.isEmpty()) {
			System.out.println("비밀번호 찾기 질문의 답을 입력해주세요.");
		}else {
		this.ans = ans;
		}
	}
	
}