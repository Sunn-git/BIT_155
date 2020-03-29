import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;


public class Game2 extends Game{ //Continuous_Press
	Scanner scanner = new Scanner(System.in);
	
	public void playGame2() {
		System.out.println();
		System.out.println();
		System.out.println("Game2를 실행합니다.");
		System.out.println("프로젝트 폴더에서 gageBar.jar 을 실행하세요.");
		System.out.println("아무 키나 입력하면 이전메뉴로 돌아갑니다.");
		System.out.println();
		scanner.nextLine();
	}
}
