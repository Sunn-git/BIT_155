import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game1 extends Game{
	int rAnswer;
	int bonus;
	Scanner scanner;
	String[][][] quizArr;
	Map<String, String> quizMap;
	
	Game1(){
		
		rAnswer = 0;
		scanner = new Scanner(System.in);
		quizArr = new String[3][2][];
		quizMap = new HashMap<String, String>();
		newScore = 0;

	}
	
	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();
		
		System.out.println();
		System.out.println("**************************************");
		System.out.println("                         영타 맞추기 게임");
		System.out.println("**************************************");
		System.out.println("          < 게임 설명 >");
		System.out.println("	당신은 영타로 쓰여진 한국어를 얼마나 빨리 알아볼 수 있나요?");
		System.out.println("	영타로 쓰여진 문제를 보고 한국어로 답을 입력하세요");
		System.out.println("	답을 빨리 맞출 수록 더 많은 점수를 획득할 수 있습니다 ");
		System.out.println("	10문제 안에 더 많은 점수를 획득해보세요 !");
		System.out.println("**************************************");
		System.out.println("1. 게임 실행하기  	 0. 이전 메뉴로 돌아가기");
		System.out.println("**************************************");
		
		int menu = 0;
		do {
			try {
				menu = Integer.parseInt(scanner.nextLine());
				
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
	
	public void tgMain() {
		toGdp : while(true) {
			switch(this.intro()) {
			case 1 : playGame1();
				break;
			case 0 : System.out.println("이전메뉴로 돌아갑니다.");
				break toGdp; 
			}
		}
	}

	public void playGame1() {
		//이 문제&정답 데이터도 파일로 저장해서 사용할 수 있지 않을까?
		quizArr[0][0] = new String[]{"qkek", "thrma", "rkawk", "skrxk", "zpckq", "zjvl", "qhfvps", "rydbr", "dhdl", "tnqns"};
		quizArr[0][1] = new String[]{"바다", "소금", "감자", "낙타", "케찹", "커피", "볼펜", "교육", "오이", "수분"};
		
		quizArr[1][0] = new String[] {"djtjdch", "vudckdtn", "xjaqmffj", "roskfl", "clawjsanf", "chzhclq", "dbfanck", "thswjsemd", "tjsdlswkd", "ahtjfl"};
		quizArr[1][1] = new String[] {"어성초", "평창수", "텀블러", "개나리", "침전물", "초코칩", "율무차", "손전등", "선인장", "모서리"};
		
		quizArr[2][0] = new String[] {"wpwhdlfwk", "wjsxhdtlwkd", "vmfhvhffltm", "dhwhscjfl", "wlrtkrhkdtjs", "qlxmzoavm", "tmakxmvhs", "qhemakzk", "dlstnqnsgo", "skqkrrlacl"};
		quizArr[2][1] = new String[] {"제조일자", "전통시장", "프로폴리스", "오존처리", "직사광선", "비트캠프", "스마트폰", "보드마카", "인수분해", "나박김치"};

		
		
		for(int i = 0; i < quizArr[0][0].length; i++) {
			quizMap.put(quizArr[0][0][i], quizArr[0][1][i]);
			quizMap.put(quizArr[1][0][i], quizArr[1][1][i]);	
			quizMap.put(quizArr[2][0][i], quizArr[2][1][i]);
		}
		
		//keyList new 자리 이거 밖으로 빼면 에러
		List<String> keyList = new ArrayList<String>(quizMap.keySet());
		
		int randomIndex = 0;
		Set<Integer> indexCheck = new HashSet<Integer>();

		long starttime = System.currentTimeMillis();
		
		for(int i = 0; i < 10; i++) {
				
			while(true) {
				randomIndex = (int)(Math.random()*14);	
				if(indexCheck.add(randomIndex) == true) {
					break;
				}
			}
			

			
			System.out.println();
			System.out.println("====================================");
			System.out.printf("                      %s\n",keyList.get(randomIndex));
			System.out.println("====================================");
			System.out.println();
			
			String input = scanner.nextLine();
			
			
			
			if(input.equals(quizMap.get(keyList.get(randomIndex)))) {
				
				System.out.println("정답 ~");
				newScore += 5;
				rAnswer++;
				
				for(int j = 0; j < quizArr[0][0].length; j++) { //긴단어 가산점 주기
					if(quizArr[1][0][j] == keyList.get(randomIndex)) {
						newScore += 1;
					}else if(quizArr[2][0][j] == keyList.get(randomIndex)){
						newScore += 2;
					}
				}
				
			}else {
				System.out.println(" 땡 ! ");
			}
			
		}//for문 끝
		
		long endTime = System.currentTimeMillis();
		float playTime = (endTime - starttime)/1000f;
		System.out.println("원래 점수 newScore : "+newScore);
		int bonus = (int)playTime;
		if(playTime < 6) {
			bonus = 40;
		}
		
		
		newScore += newScore*15/(int)playTime;
		
		

		System.out.printf("소요시간 : %.2f초\t 맞춘 문제 수 : %d\n", playTime, rAnswer);
//		System.out.println(newScore + "점 ! ");
		
		
		//로그인했을 때만 실행가능한 구문
		System.out.printf("[%s]님의 점수는 [%d]점 입니다!\n", MGSystem.currentUser.getId(), newScore);
		
//		자동저장 들어갈 부분
		scoreAutoSave(newScore);
	}

	@Override
	public void scoreAutoSave(int newScore) {
		if(newScore > score1) {
			score1 = newScore;
			MGSystem.currentUser.setGame1Score(score1); // 커런트유저 점수 세팅
			System.out.println("before CU totalsc : "+MGSystem.currentUser.getTotalScore());
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
			System.out.println("After CU totalsc : "+MGSystem.currentUser.getTotalScore());

		}
	}
	
	


}

