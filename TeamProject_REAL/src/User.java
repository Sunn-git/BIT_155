import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String pwd;
	private String email;
	private int game1Score, game2Score, game3Score, game4Score, totalScore;
	private int rank;


	User(String id, String pwd, String email) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		game1Score = 0;
		game2Score = 0;
		game3Score = 0;
		game4Score = 0;
		totalScore = 0;
		rank = 0;
	}

	public String getId() {
		return id;
	}
	// public void setId(String id) { // 아이디는 변경하지 않을 것이기 때문에 필요 없음
	// this.id = id;
	// }

	public String getPwd() { // 비밀번호 찾기에 쓰이려나?
		return pwd;
	}
	// public void setPwd(String pwd) { // 아이디와 같음
	// this.pwd = pwd;
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {// 메일주소 변경에 쓰일 수 있음
		this.email = email;
	}

	public int getGame1Score() {
		return game1Score;
	}

	public void setGame1Score(int game1Score) {
		this.game1Score = game1Score;
	}

	public int getGame2Score() {
		return game2Score;
	}

	public void setGame2Score(int game2Score) {
		this.game2Score = game2Score;
	}

	public int getGame3Score() {
		return game3Score;
	}

	public void setGame3Score(int game3Score) {
		this.game3Score = game3Score;
	}

	public int getGame4Score() {
		return game4Score;
	}

	public void setGame4Score(int game4Score) {
		this.game4Score = game4Score;
	}
	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", game1Score=" + game1Score
				+ ", game2Score=" + game2Score + ", game3Score=" + game3Score + ", game4Score =" +game4Score+ ", totalScore=" + totalScore + ", rank="
				+ rank + "]";
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
