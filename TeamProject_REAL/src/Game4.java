import java.util.Random;
import java.util.Scanner;

public class Game4 extends Game{
	int x;// 랜덤으로 생성할 숫자
	int y;
	int z;// 사람이 적을 답
	int answer;// 계산된 답
	int i;// 문제 반복
//	int score4;// 점수
	long start;//게임 시작 시간
	long end;//게임 종료 시간
	long result;//종료 - 시작
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();	
	
	

	Game4() {
		score4 = 0;	
	}
	
	void makeSubGame() {
		System.out.println("**************************************");
		System.out.println("구구단 게임을 시작합니다!!");
		System.out.println("**************************************");
            start = System.currentTimeMillis();
          
		while (i < 5) {			
			i++;
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
			answer = x * y;
			System.out.println(x + "X" + y + "=?");
			z = scan.nextInt();
			if (z == answer) {
				System.out.println("정답입니다!");
				newScore += 10;
			} else {
				System.out.println("오답입니다!");
			}

		}
		end = System.currentTimeMillis();
		float playTime = (end - start)/1000f;
		System.out.println("원래 점수 newScore : "  + newScore);
		int bonus = (int)playTime;
		if(playTime < 6) {
			bonus = 30;
		}
		
		newScore += newScore*15/bonus;
		
		if(newScore > 100) {
			System.out.println("최대 점수는 100입니다");
			newScore = 100;
		}
		
		if (newScore == 100) {
			System.out.println("만점입니다");			
		} else if(newScore < 100 ){
			System.out.println("최종점수는 100점 만점 중" + newScore + " 점입니다." + "\n아쉽게 만점을 받지 못했습니다\n" + "분발하세요!");
		}
		
		scoreAutoSave(newScore);
	}
	
	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score4) {
			score4 = newScore;
			MGSystem.currentUser.setGame4Score(score4); // 커런트유저 점수 세팅
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());//확인용
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());//확인용

		}
	}
	


}
