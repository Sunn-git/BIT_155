
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Game3 extends Game {
	private static int SIZE = 10;
	public static int matrix[][] = new int[SIZE][SIZE];
	Scanner sc = new Scanner(System.in);
	boolean checkEmptyKey = false;
	boolean solCol;
	boolean solRow;
	boolean solBox;
	int[][] matrixForSolve;

	public int intro() {
		score1 = MGSystem.currentUser.getGame1Score();
		score2 = MGSystem.currentUser.getGame2Score();
		score3 = MGSystem.currentUser.getGame3Score();
		score4 = MGSystem.currentUser.getGame4Score();

		System.out.println("\t\t<스  도  쿠  게  임>");
		System.out.println("\t\t\t<게임 설명>");
		System.out.println("\t[0]으로 셋팅되어 있는 곳을 숫자로 채워주세요!");
		System.out.println("\t각 행에는 1부터 9까지의 값이 중복 없이 입력되야 합니다.");
		System.out.println("\t각 열에는 1부터 9까지의 값이 중복 없이 입력되야 합니다.");
		System.out.println("각 [3 x 3] 박스에는 1부터 9까지의 값이 중복 없이 입력되야 합니다.");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("\t\t\t정답 입력시에 띄어쓰기는 [절대금지]!!!!!!!!");
		System.out.println("\t위 모든 조건이 만족되어야 정답입니다!");
		System.out.println("\t점수는 정답을 맞추지 못할 시 기본 [50점]이며,");
		System.out.println("정답을 맞추실 경우 기본점수 + play time에 기반한 점수가 합산됩니다.");
		System.out.println("\t매일 1개의 문제로 사고력 증진을 노려보세요!");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("1. Play Game   0. 이전메뉴로 돌아가기");
		int select = 0;
		do {
			try {
				select = Integer.parseInt(sc.nextLine());

				if (select == 0 || select == 1) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("1 또는 0만 입력해주세요");
				break;
			}
		} while (true);
		
		return select;
	}

	public void playGame3() {
		toGdp: while (true) {
			switch (this.intro()) {
			case 1:
				playGame();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				break toGdp;
			}
		}
	}

	public void playGame() {
		for (int i = 1; i < SIZE; i++) {
			for (int j = 1; j < SIZE; j++) {
				matrix[i][j] = 0;
			}
		}
		fill(0);

	}

	public void fill(int cnt) {

		if (cnt == 81) {
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <= 9; j++) {

				}
			}

			makeBlank(matrix);
			showMatrix(matrix); // 확인용
			long starttime = System.currentTimeMillis();
			while (!checkEmptyKey) {
				userInput(matrix);
				System.out.println();
				System.out.println("=========================================");
				showMatrix(matrix);
				checkEmpty(matrix);

			}
			solveBox(matrix);
			solveCol(matrix);
			solveRow(matrix);
			System.out.println("모두 입력하셨습니다.");
			System.out.println("채점을 하시려면 아무키나 눌러주세요!");
			sc.nextLine();

			if ((solBox == false) || (solCol == false) || (solRow == false)) {
				System.out.println("도전 실패! ㅜ^ㅜ");
				System.out.println("다시 플레이 하시겠습니까? ");
				System.out.println("1. YES!   0. 시스템 종료");
				int sel = Integer.parseInt(sc.nextLine());

				switch (sel) {
				case 1:
					playGame3();
				case 0:
					System.exit(0);
				}

			} else {
				System.out.println("축하합니다! 모두 맞추셨습니다!");
				long endtime = System.currentTimeMillis();
				float playTime = (endtime - starttime) / 1000f;
				newScore = 100 - ((int) ((playTime / 60f) * 10));
				scoreAutoSave(newScore);
				System.out.println("총 경과 시간: " + playTime);
				System.out.println("획득점수: " + newScore + "!!");
				System.out.println();
				playGame3();

			}

		} // if end

		int y = 0;
		int x = 0;
		boolean Check = true;
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if (matrix[i][j] == 0) {
					y = i;
					x = j;
					Check = false;
					break;
				}
			}
			if (!Check)
				break;
		}
		if (Check)
			return;

		for (int n = 1; n <= 9; n++) {
			Random r = new Random();
			int num = r.nextInt(9) + 1;
			if (isSafe(num, y, x)) {
				matrix[y][x] = num;
				fill(cnt + 1);
				matrix[y][x] = 0;
			}
		}
	} // fill end

	// 문제 생성 모든 규칙 확인
	public static boolean isSafe(int n, int y, int x) {
		if (checkRow(y, n) && checkCol(x, n) && checkBox(y, x, n))
			return true;

		return false;
	}

	// 문제 생성 열 규칙 확인
	public static boolean checkCol(int x, int n) {
		for (int i = 1; i <= 9; i++) {
			if (matrix[i][x] == n)
				return false;
		}
		return true;
	}

	// 문제 생성 행 규칙 확인
	public static boolean checkRow(int y, int n) {
		for (int j = 1; j <= 9; j++) {
			if (matrix[y][j] == n)
				return false;
		}
		return true;
	}

	// 문제 생성 박스 규칙 확인
	public static boolean checkBox(int y, int x, int n) {
		int row = ((int) Math.ceil(y / 3.0) - 1) * 3 + 1;
		int col = ((int) Math.ceil(x / 3.0) - 1) * 3 + 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[row + i][col + j] == n)
					return false;
			}
		}
		return true;
	}

	// 문제 보여주기
	public static void showMatrix(int[][] matrix) {
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		for (int o = 1; o < matrix.length; o++) {
			for (int p = 1; p < matrix[o].length; p++) {
				System.out.print("   " + matrix[o][p] + "   ");
			}
			System.out.println();
		}
	}

	// 빈칸 만들기
	public static void makeBlank(int[][] matrix) {
		Random ran1 = new Random();
		for (int a = 1; a <= 9; a++) {
			int[] c = { ran1.nextInt(9) + 1 };
			for (int k = 0; k < c.length; k++) {
				matrix[a][c[k]] = 0;
			}
			for (int s = 1; s <= 9; s++) {

			}
		}

	}

	// 유저 입력값 배열 저장
	public void userInput(int[][] matrix) {
		try {
			sc = new Scanner(System.in);
			String inputNumb;
			int col = 0;
			int row = 0;
			int sol = 0;

			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("숫자를 입력해 주세요: (예: [행],[열]:[입력값])");
			inputNumb = sc.nextLine();
			String[] inputNumbArray = inputNumb.split(",|:");
			row = Integer.parseInt(inputNumbArray[0]);
			col = Integer.parseInt(inputNumbArray[1]);
			sol = Integer.parseInt(inputNumbArray[2]);
			matrix[row][col] = sol;
			System.out.printf("[%d]행 [%d]열에 [%d]를 입력하였습니다.", row, col, sol);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// box 정답 확인
	public void solveBox(int[][] matrix) {
		Set<Integer> box1 = new HashSet<Integer>();
		Set<Integer> box2 = new HashSet<Integer>();
		Set<Integer> box3 = new HashSet<Integer>();
		Set<Integer> box4 = new HashSet<Integer>();
		Set<Integer> box5 = new HashSet<Integer>();
		Set<Integer> box6 = new HashSet<Integer>();
		Set<Integer> box7 = new HashSet<Integer>();
		Set<Integer> box8 = new HashSet<Integer>();
		Set<Integer> box9 = new HashSet<Integer>();

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				box1.add(matrix[i][j]);
			}
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 4; j < 7; j++) {
				box2.add(matrix[i][j]);
			}
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 7; j < 10; j++) {
				box3.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 1; j < 4; j++) {
				box4.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 4; j < 7; j++) {
				box5.add(matrix[i][j]);
			}
		}

		for (int i = 4; i < 7; i++) {
			for (int j = 7; j < 10; j++) {
				box6.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 1; j < 4; j++) {
				box7.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 4; j < 7; j++) {
				box8.add(matrix[i][j]);
			}
		}

		for (int i = 7; i < 10; i++) {
			for (int j = 7; j < 10; j++) {
				box9.add(matrix[i][j]);
			}
		}

		if ((box1.size() != 9) || (box2.size() != 9) || (box3.size() != 9) || (box4.size() != 9) || (box5.size() != 9)
				|| (box6.size() != 9) || (box7.size() != 9) || (box8.size() != 9) || (box9.size() != 9)) {
			solBox = false;
		} else {
			solBox = true;
		}
	}

	// 열 정답 확인
	public void solveCol(int[][] matrix) {
		Set<Integer> col1 = new HashSet<Integer>();
		Set<Integer> col2 = new HashSet<Integer>();
		Set<Integer> col3 = new HashSet<Integer>();
		Set<Integer> col4 = new HashSet<Integer>();
		Set<Integer> col5 = new HashSet<Integer>();
		Set<Integer> col6 = new HashSet<Integer>();
		Set<Integer> col7 = new HashSet<Integer>();
		Set<Integer> col8 = new HashSet<Integer>();
		Set<Integer> col9 = new HashSet<Integer>();
		for (int i = 1; i <= 9; i++) {
			col1.add(matrix[i][1]);
		}
		for (int i = 1; i <= 9; i++) {
			col2.add(matrix[i][2]);
		}
		for (int i = 1; i <= 9; i++) {
			col3.add(matrix[i][3]);
		}
		for (int i = 1; i <= 9; i++) {
			col4.add(matrix[i][4]);
		}
		for (int i = 1; i <= 9; i++) {
			col5.add(matrix[i][5]);
		}
		for (int i = 1; i <= 9; i++) {
			col6.add(matrix[i][6]);
		}
		for (int i = 1; i <= 9; i++) {
			col7.add(matrix[i][7]);
		}
		for (int i = 1; i <= 9; i++) {
			col8.add(matrix[i][8]);
		}
		for (int i = 1; i <= 9; i++) {
			col9.add(matrix[i][9]);
		}

		if ((col1.size() != 9) || (col2.size() != 9) || (col3.size() != 9) || (col4.size() != 9) || (col5.size() != 9)
				|| (col6.size() != 9) || (col7.size() != 9) || (col8.size() != 9) || (col9.size() != 9)) {
			solCol = false;
		} else {
			solCol = true;
		}

	}

	// 행 정답 확인
	public void solveRow(int[][] matrix) {
		Set<Integer> row1 = new HashSet<Integer>();
		Set<Integer> row2 = new HashSet<Integer>();
		Set<Integer> row3 = new HashSet<Integer>();
		Set<Integer> row4 = new HashSet<Integer>();
		Set<Integer> row5 = new HashSet<Integer>();
		Set<Integer> row6 = new HashSet<Integer>();
		Set<Integer> row7 = new HashSet<Integer>();
		Set<Integer> row8 = new HashSet<Integer>();
		Set<Integer> row9 = new HashSet<Integer>();

		for (int i = 1; i <= 9; i++) {
			row1.add(matrix[1][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row2.add(matrix[2][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row3.add(matrix[3][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row4.add(matrix[4][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row5.add(matrix[5][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row6.add(matrix[6][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row7.add(matrix[7][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row8.add(matrix[8][i]);
		}
		for (int i = 1; i <= 9; i++) {
			row9.add(matrix[9][i]);
		}

		if ((row1.size() != 9) || (row2.size() != 9) || (row3.size() != 9) || (row4.size() != 9) || (row5.size() != 9)
				|| (row6.size() != 9) || (row7.size() != 9) || (row8.size() != 9) || (row9.size() != 9)) {
			solRow = false;
		} else {
			solRow = true;
		}
	}

	// 빈칸 여부 확인
	public void checkEmpty(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				list.add(matrix[i][j]);
			}
		}
		if (!list.contains(0)) {
			checkEmptyKey = true;
		}
	}


	Game3() {
		newScore = 0;
	}

//
	@Override
	public void scoreAutoSave(int newScore) {
		if (newScore > score3) {
			score3 = newScore;
			MGSystem.currentUser.setGame1Score(score3); 
			MGSystem.currentUser.setTotalScore(score1 + score2 + score3 + score4);
		}
	}
}
