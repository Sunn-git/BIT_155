import java.util.Random;
import java.util.Scanner;
public class Game4 extends Game{
	int x;// 랜덤으로 생성할 숫자
	int y;
	int z;
	String input;// 사람이 적을 답
	int answer;// 계산된 답
	long start;//게임 시작 시간
	long end;//게임 종료 시간
	long result;//종료 - 시작
	Scanner scan;
	Random random;
	
	Game4() {
		input = "";
		newScore = 0; 
		scan = new Scanner(System.in);
		random = new Random();	
	}
	
	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("		구구단 게임");
		System.out.println("**************************************************");
		System.out.println();
		System.out.println("	답을 빨리 맞출 수록 더 많은 점수를 획득할 수 있습니다 ");
		System.out.println();
		System.out.println("**************************************************");
		System.out.println("	1. 게임 실행하기  	 0. 이전 메뉴로 돌아가기");
		System.out.println("**************************************************");
		
		int menu = 0;
		do {
			try {
				menu = Integer.parseInt(scan.nextLine());
				if(menu == 0 || menu == 1) {
					break;
				}else {
					throw new Exception();
				}
			}catch(Exception e) {
				System.out.println("1 또는 0만 입력해주세요");
			}		
		}while(true);
		return menu;
	}
	
	public void playGame4() {

		toGdp : while(true) {

			switch(this.intro()) {
			case 1 : this.makeSubGame();
				break;
			case 0 : 
				break toGdp;
			}
		}
	}

	
	void makeSubGame() {
		int i = 0;
        start = System.currentTimeMillis();

		while (i < 5) {	
			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");

    		
    		while(true) {
    			try {
    				input = scan.nextLine();
    				if(isNumeric(input)) {
    					z = Integer.parseInt(input);
    					break;
    				}else {
    					throw new Exception("숫자만 입력하세요");
  
    				}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    		}

    		if (z == answer) {
				System.out.println("정답입니다!");
				newScore += 15;
			} else {
				System.out.println("오답입니다!");
			}
		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
		int bonus = (int)playTime; // 시간가산점용
		if(playTime < 6) {
			bonus = 50;
		}
		
		newScore += newScore*12/bonus;
		
		System.out.printf("\n\n[%s]님의 점수는 [%d]점 입니다!\n", MGSystem.currentUser.getId(), newScore);
		
		scoreAutoSave(newScore);
	}
	
	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score4) {
			score4 = newScore;
			MGSystem.currentUser.setGame4Score(score4); // 커런트유저 점수 세팅
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
		}
	}
	
	public boolean isNumeric(String input) { 
		try { Double.parseDouble(input); 
				return true; 
			} catch (NumberFormatException e) { 
				return false; 
			} 
		}

}