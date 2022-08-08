package manager;

public class P02_managerDTO {
	private String adminId;		// 관리자 아이디
	private String adminPw;		// 관리자 비밀번호
	private String adminName;	// 관리자이름
	
	public void setAdminId(String adminId) {this.adminId = adminId;}
	public void setAdminPw(String adminPw) {this.adminPw = adminPw;}
	public void setAdminName(String adminName) {this.adminName = adminName;}
	
	public String getAdminId() {return adminId;}
	public String getAdminPw() {return adminPw;}
	public String getAdminName() {return adminName;}
}
