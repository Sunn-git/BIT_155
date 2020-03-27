import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MGSystem implements Serializable{ // 회원가입, 로그인 기능

	Scanner sc;
	Map<String ,User> userMap;
	Map<String, String> idMap;
	List<String> rankList;
	
	static Map<String, Integer> scoreMap;
	static User currentUser;
	
	String userMapFileName;
	String idMapFileName;
	String scoreMapFileName;
	String userInfoTXTFileName;
	
	File userMapFile;
	File idMapFile;
	File scoreMapFile;
	
    Game1 game1 = new Game1();
    Game2 game2 = new Game2();
    Game3 game3 = new Game3();
    Game4 game4 = new Game4();
	
	
	MGSystem(){
		sc = new Scanner(System.in);
		userMap = new HashMap<String ,User>();
		idMap = new HashMap<String, String>();
		scoreMap = new HashMap<String, Integer>();
		rankList = null; //#null값으로 수정
		currentUser = null;
		
		userMapFileName = "userMap.txt";
		idMapFileName = "idMap.txt";
		scoreMapFileName = "scoreMap.txt";
		userInfoTXTFileName = "userInfo.txt";
		userMapFile = new File(userMapFileName);
		idMapFile = new File(idMapFileName);
		scoreMapFile = new File(scoreMapFileName);

	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayMenu0() {//완료
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println("■                                                                         ■");
		System.out.println("■  ##        ##  ###  ##    #  ###  #######    ##    ##       ##  #####   ■ ");
		System.out.println("■  # #      # #   #   # #   #   #   #         #  #   # #     # #  #       ■");
		System.out.println("■  #  #    #  #   #   #  #  #   #   # #####  ######  #  #   #  #  #####   ■ ");
		System.out.println("■  #   #  #   #   #   #   # #   #   #     # #      # #   # #   #  #       ■");
		System.out.println("■  #    #     #  ###  #    ##  ###  ####  # #      # #    #    #  #####   ■ ");
		System.out.println("■                                                                         ■");
		System.out.println("■   #####     ##     ####       ##     ####    ###   #####   ####   ###   ■");
		System.out.println("■   #    #   ####    #   #     ####    #   #    #    #       #      ###   ■");
		System.out.println("■   #####   ##  ##   ####     ##  ##   #   #    #     ##     ####   ###   ■");
		System.out.println("■   #      #######   #   #   #######   #   #    #        #   #            ■");
		System.out.println("■   #     #       #  #    # #       #  ####    ###   #####   ####   ##    ■ ");
		System.out.println("■                                                                         ■ ");
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println("계속 하려면 아무키나 누르십시오. . .                                     ");
		System.out.println();
		
		String maindisplayinput = this.sc.nextLine();
		while (maindisplayinput != null) {
			this.showSignMenu();
		}
		
	}
	public void showSignMenu() { //완료
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println();
		System.out.println("                            <MINI GAME PARADISE>                             ");
		System.out.println();
		String result = String.format("%39s\n\n%39s\n\n%39s\n\n%39s\n\n", "▶1. 로그인","▶2. 회원가입","▶3. 랭킹보기","▶0. 프로그램 종료");
		System.out.println(result);
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
			if(menu >= 0 && menu <= 3) {
					switch (menu) {
					case 1: 
						this.signIn(); //로그인
							break;
					case 2: this.signUp(); //회원가입
						break;
					case 3: this.showRank(); //랭킹확인
					    break;
					case 0:System.exit(0);
					}
			}else {
				throw new Exception("잘못된 입력입니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("잘못된 선택입니다.");
			System.out.println("1~4까지 입력해주세요.");
		}
		
		
	}
	public boolean idChecker(String id) {
		//아이디: 공백X, 특수문자X, 영어+숫자, 5자이상 12문자 이하
		String data = id;
		String regExp = "^[a-zA-Z0-9]*.{5,12}$";
		return data.matches(regExp);
	}
	public boolean pwdChecker(String pwd) {
		//비번: 공백X특수문자O, 5~12미만
		String data = pwd;
		String regExp = "^[a-zA-Z0-9_@./#&+-?~]*.{5,11}$";
		return data.matches(regExp);
	}
	public boolean emailChecker(String email) {
		String data = email;
		String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		return data.matches(regExp);
	}
	
	public void signUp() {
		String id;
		while (true) {
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			System.out.println();
			System.out.println();
			String result = String.format("%45s\n\n%38s", " ------< 회원가입 >------ ","ID를 입력하세요: ");
			System.out.println(result);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			id = sc.nextLine();
			if (!idMap.containsKey(id)) {
				if(idChecker(id)) {
					break;
				}else {
					System.out.println("아이디를 12자이내(영어, 숫자가능)로 설정해주세요");
				}
				
			} else {
				System.out.println("중복된 아이디 입니다.");
			}
			
		}
		
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println();
		String result2 = String.format("%45s\n\n%35s", " ------< 회원가입 >------ ","비밀번호를 입력하세요: ");
		System.out.println(result2);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		String pwd = null;
		String pwd2 = null;
		
		while (true) {
			pwd = sc.nextLine();
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			System.out.println();
			System.out.println();
			String result3 = String.format("%45s\n\n%35s", " ------< 회원가입 >------ ","비밀번호를 확인하세요: ");
			System.out.println(result3);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			pwd2 = sc.nextLine();

			if (pwd.equals(pwd2)) {
				if(pwdChecker(pwd)) {
					break;
				}else {
					System.out.println("비밀번호를 5자~12자로 설정해주세요(영어, 숫자, 특수문자가능)");
				}
				
		}else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
			
			}
		String email;
		while (true) {
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println();
		String result4 = String.format("%45s\n\n%38s", " ------< 회원가입 >------ ","이메일을 입력하세요: ");
		System.out.println(result4);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		
		
			email = sc.nextLine();
			if(emailChecker(email)) {
				break;
			}else {
				System.out.println("Email을 hibit@want.gohome 형태로 입력해주세요");
			}	
		}

		idMap.put(id, pwd);
		userMap.put(id, new User(id, pwd, email));
		
		System.out.println("회원가입이 완료되었습니다.");
		this.saveData(userMap, userMapFileName);
		this.saveData(idMap, idMapFileName);
//		this.saveData(scoreMap, scoreMapFileName); 일단 주석처리
		
		
		System.out.println("SI id맵확인 : "+idMap.toString()); // 맵 확인용
		System.out.println("SI user맵확인 : "+userMap.toString()); // 리스트 확인용
		
		saveMapAsTxt(userMap, userInfoTXTFileName);
	}

	public void signIn() {
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println();
		String result = String.format("%45s\n\n%38s", " ------< 로그인 >------ ","ID를 입력하세요: ");
		System.out.println(result);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			String id = sc.nextLine();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println();
		String result2 = String.format("%45s\n\n%35s", " ------< 로그인 >------ ","비밀번호를 입력하세요: ");
		System.out.println(result2);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println(this.userMap.toString());	
		String pwd = sc.nextLine();
			if (idMap.containsKey(id)) {
				if (pwd.equals(idMap.get(id))) {
					System.out.println("로그인 성공");
					
//					System.out.println("beforeCU"+currentUser.toString());
					loadInfo(userMapFileName); // 이거 왜 주석처리 해놨더라? 시작하면서 파일 로드해서?
					currentUser = this.userMap.get(id);// 로그인이 성공하면 해당 유저의 정보를 불러오는 기능
					System.out.println("afterCU"+currentUser.toString());
					displayMenu2();
					
					
				} else {
					System.out.println("잘못된 비밀번호 입니다.");
				}
			} else {
				System.out.println("존재하지 않는 아이디 입니다.");
			}
			
			
		}
//	}
	
	
	//덕기오빠거 추가
	public void deleteUser() {
		String deletingUser = currentUser.getId();

		userMap.remove(deletingUser);
		idMap.remove(deletingUser);
		scoreMap.remove(deletingUser);

		
		
		saveData(userMap, userMapFileName);
		saveData(idMap, idMapFileName);
		saveData(scoreMap, scoreMapFileName);
		loadInfo(userMapFileName);
		loadInfo(idMapFileName);
		loadInfo(scoreMapFileName);

		
		System.out.println("탈퇴되었습니다.");
	}

	
	
	public Map loadInfo(String fileName) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		Map loadedMap = null;
		try {
			fis = new FileInputStream(fileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			loadedMap = (HashMap)ois.readObject();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			
			try {
				ois.close();
				bis.close();
				fis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return loadedMap;
	}
	public void saveMapAsTxt(Map map, String fileName) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(map.toString());
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//saveMapAsTxt end
	
	public void saveData(Map map, String fileName) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(map);
			
			oos.flush();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public List<String> sortByValue(final Map<String, Integer> map){
		rankList = new ArrayList<String>(); //#추가
		rankList.addAll(map.keySet());
		
		Collections.sort(rankList, new Comparator<Object>() {
		
			@Override
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
			return ((Comparable<Object>)v1).compareTo(v2);
			}
			
		});
		Collections.reverse(rankList); //오름차순
		
		if(currentUser != null) {
			currentUser.setRank((rankList.indexOf(currentUser.getId()))+1);
		}//#추가	로그인유저 랭킹 
		
		return rankList;
	} // makeRank 대신 쓰는중

	void showUserInfo() {
		System.out.println("showUserInfo start");
		sortByValue(scoreMap); //랭킹만들기
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println("                     ------< USER INFORMATION >------                        ");
		String str = String.format("\t\t[ ID ] : %s\n\t\t[ EMAIL ] : %s\n\t\t[GAME1 SCORE] : %s\n\t\t[GAME2 SCORE] : %s\n\t\t[GAME3 SCORE] : %s\n\t\t[GAME4 SCORE] : %s\n\t\t[TOTAL SCORE] : %s\n\t\t[TOTAL RANK] : %s\n\t\t" 
				,currentUser.getId()
				,currentUser.getEmail()
				,currentUser.getGame1Score()
				,currentUser.getGame2Score()
				,currentUser.getGame3Score()
				,currentUser.getGame4Score()
				,currentUser.getTotalScore()
				,currentUser.getRank());
		System.out.println();
		System.out.println();
		String str2 = String.format("\t\t\t\t\t\t[회원 탈퇴는 %s를 입력해주세요] \n\t\t\t\t\t\t\t\t[뒤로가기는 0] \n"
				+ "■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■", 9);
		System.out.println(str);
		System.out.println(str2);
		String menu = "0";
		label : while(true) {
			System.out.println("showUserInfo while start before scanner");

		menu = sc.nextLine();
			switch (menu) {
			case "0":
				System.out.println("showUserInfo switch case 0");

//			displayMenu2(); // ##지금 여기 막아봤음
			break label; // 이렇게 하면 showUserInfo를 나가서 dp2에서 로그아웃 했을 때 showsignmenu로 돌아감.
			case "9":
				System.out.println("showUserInfo switch case 9");

				deleteUser();
				displayMenu0();
				break;
			default: System.out.println("잘못된 값을 입력하셨습니다. 0 또는 9를 입력해주세요.");
				break;
			}
			System.out.println("showUserInfo switch end");

		}
		System.out.println("showUserInfo while out");
	}//showUserInfo end
	public void showRank() {	
		sortByValue(scoreMap); //랭킹만들기
		
		if(scoreMap.size() < 5) {
			if(scoreMap.size() == 0) {
				System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
				System.out.println();
				System.out.println();
				String result = String.format("%46s", " ------< 랭킹정보가 없습니다 >------ ");
				System.out.println(result);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			}else {
				System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
				System.out.println();
				System.out.println();
				System.out.println("                                <RANK LIST>                                  ");
			
				System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
				for(int i = 0; i < scoreMap.size(); i++) {
					System.out.printf("\t\t\t\t[%dst] : %-20s\t\t",i+1 , rankList.get(i) + "\n\n");
			
				}
			}
			
		}else {
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			System.out.println();
			System.out.println();
			System.out.println("                                <RANK LIST>                                  ");
		
				for(int i = 0; i < 5; i++) {
				System.out.printf("\t\t\t\t[%dst] : %-20s\t\t    \n",i+1 , rankList.get(i));
			//String result = String.format("■%39s %33s\n■ %75s\n■%39s %32s\n■ %75s\n■%39s %32s\n■ %75s\n■%39s %30s\n■ %75s", rankList.get(0),"■","■",rankList.get(1),"■","■",rankList.get(2),"■","■",rankList.get(3),"■","■",rankList.get(4));
			//System.out.println(result);
				}
		
			
//			String str = String.format("■\t\t[1st] : %-50s■\n■\t\t[2nd] : %-50s■\n■\t\t[3rd] : %-50s■\n■\t\t[4th] : %-50s■\n■\t\t[5th] : %-50s■\n"
//					,rankList.get(0)
//					,rankList.get(1)
//					,rankList.get(2)
//					,rankList.get(3)
//					,rankList.get(4));
//			System.out.printf(str);                                                                     	
	}
		}
	void saveCUGameScore() { // ## 메소드 추가
		userMap.put(currentUser.getId(), currentUser);
		scoreMap.put(currentUser.getId(), currentUser.getTotalScore());
		
		sortByValue(scoreMap); // 랭킹 만들기
		
		saveData(userMap, userMapFileName);
		saveData(scoreMap, scoreMapFileName);
		saveMapAsTxt(userMap, userInfoTXTFileName);
		loadInfo(userMapFileName);
		loadInfo(scoreMapFileName);
	}
	
	void gameMenudisplay() {
		toDp2 : while(true) {																	 //여기41s가 게임4시작부분
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			System.out.println();
			System.out.println();
			String result = String.format("%39s\n\n%39s\n\n%39s\n\n%39s\n\n%38s\n\n%35s", "▶1. 게임1 시작","▶2. 게임2 시작","▶3. 게임3 시작","▶4. 게임4 시작","▶5. 처음으로 돌아가기","▶0. 뒤로가기");
			System.out.println(result);
			System.out.println();
			System.out.println();
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			int menu = 0;
				try {
					menu = Integer.parseInt(sc.nextLine());
					if(menu >= 0 && menu <= 5) {
						toGmd : while(true) {
							switch (menu) {
							case 0: break toDp2; //로그인 이후화면
							case 1: this.game1.tgMain();
									saveCUGameScore();
									break toGmd;
							case 2: this.game2.playGame2();
								saveCUGameScore();
								break toGmd;
							case 3: System.out.println("게임3 실행");//this.playGame3; //준수도쿠
								saveCUGameScore();
							break toGmd;
							case 4: this.game4.makeSubGame();
								saveCUGameScore();
							break toGmd;
							case 5: this.displayMenu0(); //맨처음화면
								break;
							}
							}
					}else {
						throw new Exception("잘못된 입력입니다.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("잘못된 선택입니다.");
					System.out.println("0~4까지 입력해주세요.");
				} 
		}
	}// gameMenudisplay end
	
//int displayMenu1() {
//	System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
//	System.out.println();
//	System.out.println();
//	String result = String.format("%39s\n\n%39s\n\n%40s\n\n%39s\n\n", "▶1. 로그인","▶2. 회원가입","▶3. 랭킹보기","▶0. 프로그램 종료");
//	System.out.println(result);
//	System.out.println();
//	System.out.println();
//	System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
//	
//	int menu = 0;
//			try {
//				menu = Integer.parseInt(sc.nextLine());
//				if(menu >= 1 && menu <= 4) {
//						switch (menu) {
//						case 1: 
//							this.signIn(); //로그인
//								break;
//						case 2: this.signUp(); //회원가입
//							break;
//						case 3: System.out.println("before showRank");
//
//							this.showRank(); //랭킹확인
//						System.out.println("after showRank");
//						    break;
//						case 4:System.exit(0);
//						}
//				}else {
//					throw new Exception("잘못된 입력입니다.");
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				System.out.println("잘못된 선택입니다.");
//				System.out.println("1~4까지 입력해주세요.");
//			}
//		return menu;
//		}	//displayMenu1 end
	
	void displayMenu2() {
		toDp1 : while(true) {
			System.out.println("dp2 while start");
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			System.out.println();
			System.out.println();
			String result = String.format("%39s\n\n%41s\n\n%39s\n\n%39s\n\n", "▶1. 게임시작","▶2. 종합RANK","▶3. 회원정보","▶0. 로그아웃");
			System.out.println(result);
			System.out.println();
			System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
			int menu = 0;
				try {
					menu = Integer.parseInt(sc.nextLine());
					if(menu >= 0 && menu <= 3) {
						 toDp2 :while(true) {
							switch (menu) {
							case 0: //displayMenu1(); //뒤로가기 == 로그아웃
									if(currentUser != null) {
										//여기에 밑에 두 줄 넣기 근데 왜 0해서 로그아웃 한다음에
										System.out.println("CU!=null"+currentUser.toString());
										userMap.put(currentUser.getId(), currentUser);
										scoreMap.put(currentUser.getId(), currentUser.getTotalScore());
									//이렇게 하면 에러는 안나는데 ........
									//왜 break를 타고 다시 dp2로 가지? showsign으로 가야하는데...
									}
									

									currentUser = null;
									System.out.println("로그아웃 되었습니다.");
									System.out.println("dp2 while switch case 0");

									break toDp1; // 여기가 문제임 !!!!!!!!!!!!!!!!!!!!!!!!
							case 1: this.gameMenudisplay(); //게임선택화면
									break toDp2;
							case 2: this.showRank(); //종합랭킹
								break toDp2;
							case 3: this.showUserInfo();
							    break toDp2;
							    }
						}		
					}else {
						throw new Exception("잘못된 입력입니다.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("잘못된 선택입니다.");
					System.out.println("1~4까지 입력해주세요.");
				}
				
//			return menu;
		}


		} //displayMenu2 end
	
	
	
	public void run() {
			if(this.userMapFile.exists() == true) {
				userMap = this.loadInfo(userMapFileName);
				} 
			System.out.println("loaded" + userMap.toString());
			
			if(this.idMapFile.exists() == true) {
				idMap = this.loadInfo(idMapFileName);
				}
			System.out.println("idMap"+idMap.toString());
			if(this.scoreMapFile.exists() == true) {
				scoreMap = this.loadInfo(scoreMapFileName);
			}
			System.out.println("scoreMap" + scoreMap.toString());
			// 데이터 로드 및 확인작업
			
			sortByValue(scoreMap).toString(); // 랭킹 만들기
			
			
			
			while(true) {
				this.displayMenu0(); // 로그인, 회원가입, 랭킹확인, 종료
				
			}

			
			

		
		
	} //run end
	
	public void test() {
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
		System.out.println();
		System.out.println("                                <RANK LIST>                                  ");
		String result = String.format("%39s %33s\n %75s\n%39s %32s\n %75s\n%39s %32s\n %75s\n%39s %30s\n %75s", rankList.get(0),rankList.get(1),rankList.get(2),rankList.get(3),rankList.get(4));
		System.out.println(result);
		System.out.println();
		System.out.println("■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ■");
//		String str = String.format("■\t\t[1st] : %-50s■\n■\t\t[2nd] : %-50s■\n■\t\t[3rd] : %-50s■\n■\t\t[4th] : %-50s■\n■\t\t[5th] : %-50s■\n"
//				,rankList.get(0)
//				,rankList.get(1)
//				,rankList.get(2)
//				,rankList.get(3)
//				,rankList.get(4));
//		System.out.printf(str);
	}	                                                             

	public static void main(String[] args) { //test용 main
		MGSystem um = new MGSystem();
			um.run();



	}

}


