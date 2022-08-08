package review;
import java.sql.Timestamp;
public class reviewDTO {
	private int renum;		// 글번호
	private String itemname;// 아이템이름
	private String id;		// 글쓴이
	private String setReg;	// 글쓴시각으로부터 얼마나 지났는지 표시
	private int status;		// 글 상태
	private String title;	// 글제목
	private String content;	// 글내용
	private String image;	// 첨부사진
	private int good;		// 글 좋아요
	private String goodPerson;// 좋아요 누른 회원
	private int goodCheck;	// 좋아요 수
	private int readCount;	// 조회 수
	private Timestamp revreg;// 글쓴 시각
	private int comnum;		// 댓글 번호
	private String com_content;// 댓글 내용
	private int com_ref;	// 댓글 래퍼런스
	private int com_step;	// 댓글 단계
	private String com_id;	// 댓글쓴이
	private Timestamp comReg;// 댓글 쓴 시각

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
