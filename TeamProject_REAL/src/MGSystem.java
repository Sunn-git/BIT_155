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


public class MGSystem implements Serializable{ 

	Scanner sc;
	Map<String ,User> userMap;
	Map<String, String> idMap;
	List<String> rankList;
	Map<String, Integer> scoreMap;
	
	static User currentUser;
	
	String userMapFileName;
	String idMapFileName;
	String scoreMapFileName;
	String userInfoTXTFileName;
	
	File userMapFile;
	File idMapFile;
	File scoreMapFile;
	
    Game1 game1;
    Game2 game2;
    Game3 game3;
    Game4 game4;
	
	
	MGSystem(){
		sc = new Scanner(System.in);
		userMap = new HashMap<String ,User>();
		idMap = new HashMap<String, String>();
		scoreMap = new HashMap<String, Integer>();
		rankList = null;
		currentUser = null;
		
		userMapFileName = "userMap.txt";
		idMapFileName = "idMap.txt";
		scoreMapFileName = "scoreMap.txt";
		userInfoTXTFileName = "userInfo.txt";
		userMapFile = new File(userMapFileName);
		idMapFile = new File(idMapFileName);
		scoreMapFile = new File(scoreMapFileName);
		
	    game1 = new Game1();
	    game2 = new Game2();
	    game3 = new Game3();
	    game4 = new Game4();

	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayMenu0() {
		
		this.gameDataLoad();
		
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println("��                                                                         ��");
		System.out.println("��  ##        ##  ###  ##    #  ###  #######    ##    ##       ##  #####   �� ");
		System.out.println("��  # #      # #   #   # #   #   #   #         #  #   # #     # #  #       ��");
		System.out.println("��  #  #    #  #   #   #  #  #   #   # #####  ######  #  #   #  #  #####   �� ");
		System.out.println("��  #   #  #   #   #   #   # #   #   #     # #      # #   # #   #  #       ��");
		System.out.println("��  #    #     #  ###  #    ##  ###  ####  # #      # #    #    #  #####   �� ");
		System.out.println("��                                                                         ��");
		System.out.println("��   #####     ##     ####       ##     ####    ###   #####   ####   ###   ��");
		System.out.println("��   #    #   ####    #   #     ####    #   #    #    #       #      ###   ��");
		System.out.println("��   #####   ##  ##   ####     ##  ##   #   #    #     ##     ####   ###   ��");
		System.out.println("��   #      #######   #   #   #######   #   #    #        #   #            ��");
		System.out.println("��   #     #       #  #    # #       #  ####    ###   #####   ####   ##    �� ");
		System.out.println("��                                                                         �� ");
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println("��� �Ϸ��� �ƹ�Ű�� �����ʽÿ�. . .                                     ");
		System.out.println();
		
		String maindisplayinput = this.sc.nextLine();
		while (maindisplayinput != null) { 
			this.showSignMenu();
		}
		
	}
	public void showSignMenu() { 
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println();
		System.out.println();
		System.out.println("                            <MINI GAME PARADISE>                             ");
		System.out.println();
		String result = String.format("%39s\n\n%39s\n\n%39s\n\n%39s\n\n", "��1. �α���","��2. ȸ������","��3. ��ŷ����","��0. ���α׷� ����");
		System.out.println(result);
		System.out.println();
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
			if(menu >= 0 && menu <= 3) {
					switch (menu) {
					case 1: 
						this.signIn(); 
							break;
					case 2: this.signUp(); 
						break;
					case 3: this.showRank(); 
					    break;
					case 0:System.exit(0);
					}
			}else {
				throw new Exception("�߸��� �Է��Դϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("�߸��� �����Դϴ�.");
			System.out.println("1~4���� �Է����ּ���.");
		}
		
		
	}
	public boolean idChecker(String id) {
		//���̵�: ����X, Ư������X, ����+����, 5���̻� 12���� ����
		String data = id;
		String regExp = "^[a-zA-Z0-9]*.{5,12}$";
		return data.matches(regExp);
	}
	public boolean pwdChecker(String pwd) {
		//���: ����XƯ������O, 5~12�̸�
		String data = pwd;
		String regExp = "^[a-zA-Z0-9_@./#&+-?~]*.{5,11}$";
		return data.matches(regExp);
	}
	public boolean emailChecker(String email) {
		String data = email;
		String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+[\\\\.a-zA-Z0-9-]+\\.[a-zA-Z]+[a-zA-Z]+$";
		return data.matches(regExp);
	}
	
	public void signUp() {
		String id;
		while (true) {
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			String result = String.format("%45s\n\n%38s", " ------< ȸ������ >------ ","ID�� �Է��ϼ���: ");
			System.out.println(result);
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t[�ڷΰ���� 0] \n");
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			id = sc.nextLine();
			
			if(id.equals("0")) {
				return;
			}
			
			if (!idMap.containsKey(id)) {
				if(idChecker(id)) {
					break;
				}else {
					System.out.println("���̵� 5�� �̻� 12���̳�(����, ���ڰ���)�� �������ּ���");
				}
				
			} else {
				System.out.println("�ߺ��� ���̵� �Դϴ�.");
			}
			
		}
		String pwd = null;
		String pwd2 = null;
		
		while (true) {
			//$$ ��й�ȣ ��ġ���� �ʾƼ� �ٽ� �Է��Ҷ� ��й�ȣ �Է�â���� �ٽ� �߰� �ڵ� ����
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			String result2 = String.format("%45s\n\n%35s", " ------< ȸ������ >------ ","��й�ȣ�� �Է��ϼ���: ");
			System.out.println(result2);
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t[�ڷΰ���� 0] \n");
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			
			pwd = sc.nextLine();
			if(pwd.equals("0")) {// �Է°��� 0�̸� �ڷΰ���
				return;
			}
			
			
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			String result3 = String.format("%45s\n\n%35s", " ------< ȸ������ >------ ","��й�ȣ�� Ȯ���ϼ���: ");
			System.out.println(result3);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			pwd2 = sc.nextLine();

			if (pwd.equals(pwd2)) {
				if(pwdChecker(pwd)) {
					break;
				}else {
					System.out.println("��й�ȣ�� 5��~12�ڷ� �������ּ���(����, ����, Ư�����ڰ���)");
				}
				
		}else {
			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
			
			}
		String email;
		while (true) {
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println();
		System.out.println();
		String result4 = String.format("%45s\n\n%38s", " ------< ȸ������ >------ ","�̸����� �Է��ϼ���: ");
		System.out.println(result4);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		
		
			email = sc.nextLine();
			if(emailChecker(email)) {
				break;
			}else {
				System.out.println("Email�� hibit@want.gohome ���·� �Է����ּ���");
			}	
		}

		idMap.put(id, pwd);
		userMap.put(id, new User(id, pwd, email));
		
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		this.saveData(userMap, userMapFileName);
		this.saveData(idMap, idMapFileName);
		
		saveMapAsTxt(userMap, userInfoTXTFileName);
	}

	public void signIn() {
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println();
		System.out.println();
		String result = String.format("%45s\n\n%38s", " ------< �α��� >------ ","ID�� �Է��ϼ���: ");
		System.out.println(result);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			String id = sc.nextLine();
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
		System.out.println();
		System.out.println();
		String result2 = String.format("%45s\n\n%35s", " ------< �α��� >------ ","��й�ȣ�� �Է��ϼ���: ");
		System.out.println(result2);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
	
		String pwd = sc.nextLine();
			if (idMap.containsKey(id)) {
				if (pwd.equals(idMap.get(id))) {
					System.out.println("�α��� ����");
					
					loadInfo(userMapFileName); 
					currentUser = this.userMap.get(id);
					displayMenu2();
					
				} else {
					System.out.println("�߸��� ��й�ȣ �Դϴ�.");
				}
			} else {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�.");
			}	
		}

	
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

		System.out.println("Ż��Ǿ����ϴ�.");
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
	}
	
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
		rankList = new ArrayList<String>();
		rankList.addAll(map.keySet());
		
		Collections.sort(rankList, new Comparator<Object>() {
		
			@Override
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
			return ((Comparable<Object>)v1).compareTo(v2);
			}
			
		});
		Collections.reverse(rankList);
		
		if(currentUser != null) {
			currentUser.setRank((rankList.indexOf(currentUser.getId()))+1);
		}
		return rankList;
	} 

	void showUserInfo() {
		sortByValue(scoreMap);
		System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
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
				, (currentUser.getTotalScore() == 0) ? "���� ������ �����ϴ�" : currentUser.getRank());
		System.out.println();
		System.out.println();
		String str2 = String.format("\t\t\t\t\t\t[ȸ�� Ż��� %s�� �Է����ּ���] \n\t\t\t\t\t\t\t\t[�ڷΰ���� 0] \n"
				+ "�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��", 9);
		System.out.println(str);
		System.out.println(str2);
		String menu = "0";
		label : while(true) {

		menu = sc.nextLine();
			switch (menu) {
				case "0":
			break label;
				case "9":
				deleteUser();
				displayMenu0();
				break;
			default: System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. 0 �Ǵ� 9�� �Է����ּ���.");
				break;
			}
		}
	}//showUserInfo end
	
	public void showRank() {	
		sortByValue(scoreMap);

		if(scoreMap.size() < 5) {
			if((scoreMap.size() == 0) || (scoreMap.get(rankList.get(0)) == 0)) {
				System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
				System.out.println();
				System.out.println();
				String result = String.format("%46s", " ------< ��ŷ������ �����ϴ� >------ ");
				System.out.println(result);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			}else {
				System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
				System.out.println();
				System.out.println();
				System.out.println("                                <RANK LIST>                                  ");
			
				for(int i = 0; i < scoreMap.size(); i++) {
					if(scoreMap.get(rankList.get(i)) != 0) {
						System.out.printf("\t\t\t[%dst] : %-15s\t%-20s\t\t     \n",i+1 , rankList.get(i), scoreMap.get(rankList.get(i)));
					}
				}
				System.out.println();
				System.out.println();
				System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");

			}
			
		}else {
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			System.out.println("                                <RANK LIST>                                  ");
				
				for(int i = 0;  i < 5; i++) {
					if(scoreMap.get(rankList.get(i)) != 0) {
						System.out.printf("\t\t\t[%dst] : %-15s\t%-20s\t\t     \n",i+1 , rankList.get(i), scoreMap.get(rankList.get(i)));
					}
				}
			System.out.println();
			System.out.println();
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");

		                                                                    	
	}
		System.out.println("�޴��� �������� �ƹ�Ű�� �����ʽÿ�. . .                                     ");
		String input = this.sc.nextLine();

		
		}
	void saveCUGameScore() { 
		userMap.put(currentUser.getId(), currentUser);
		scoreMap.put(currentUser.getId(), currentUser.getTotalScore());
		
		sortByValue(scoreMap); 
		
		saveData(userMap, userMapFileName);
		saveData(scoreMap, scoreMapFileName);
		saveMapAsTxt(userMap, userInfoTXTFileName);
		loadInfo(userMapFileName);
		loadInfo(scoreMapFileName);
	}
	
	void gameMenudisplay() {
		toDp2 : while(true) {	
			
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			String result = String.format("%39s\n\n%39s\n\n%39s\n\n%39s\n\n%38s\n\n%35s", "��1. ����1 ����","��2. ����2 ����","��3. ����3 ����","��4. ����4 ����","��5. ó������ ���ư���","��0. �ڷΰ���");
			System.out.println(result);
			System.out.println();
			System.out.println();
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			int menu = 0;
			
				try {

					menu = Integer.parseInt(sc.nextLine());

					if(menu >= 0 && menu <= 5) {

						toGmd : while(true) { 
							switch (menu) {
							case 0: break toDp2; 
							case 1: this.game1.playGame1();
									saveCUGameScore();
									break toGmd;
							case 2: this.game2.playGame2();
									saveCUGameScore();
								break toGmd;
							case 3:	this.game3.playGame3();
								saveCUGameScore();
							break toGmd;
							case 4: this.game4.playGame4();
								saveCUGameScore();
							break toGmd;
							case 5: signOut();							
								this.displayMenu0(); 
								break;
							}
						}//while end
						
					}else {
						throw new Exception("�߸��� �Է��Դϴ�.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("�߸��� �����Դϴ�.");
					System.out.println("0~4���� �Է����ּ���.");
				}
		}
	}// gameMenudisplay end
	
	void displayMenu2() {
		toSsm : while(true) {
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			System.out.println();
			System.out.println();
			String result = String.format("%39s\n\n%41s\n\n%39s\n\n%39s\n\n", "��1. ���ӽ���","��2. ����RANK","��3. ȸ������","��0. �α׾ƿ�");
			System.out.println(result);
			System.out.println();
			System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��");
			int menu = 0;
				try {
					menu = Integer.parseInt(sc.nextLine());
					if(menu >= 0 && menu <= 3) {
						 toDp2 :while(true) {
							switch (menu) {
							case 0: //�ڷΰ��� == �α׾ƿ�
									signOut();
									break toSsm;
							case 1: this.gameMenudisplay(); //���Ӽ���ȭ��
									break toDp2;
							case 2: this.showRank(); //���շ�ŷ
								break toDp2;
							case 3: this.showUserInfo();
							    break toDp2;
							    }
						}		
					}else {
						throw new Exception("�߸��� �Է��Դϴ�.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("�߸��� �����Դϴ�.");
					System.out.println("1~4���� �Է����ּ���.");
				}
		}
		} //displayMenu2 end
	
	public void gameDataLoad() {
		if(this.userMapFile.exists() == true) {
			userMap = this.loadInfo(userMapFileName);
			} 
		
		if(this.idMapFile.exists() == true) {
			idMap = this.loadInfo(idMapFileName);
			}
		
		if(this.scoreMapFile.exists() == true) {
			scoreMap = this.loadInfo(scoreMapFileName);
		}
		
		sortByValue(scoreMap); 
	}
	
	public void signOut() {
		if(currentUser != null) {
			userMap.put(currentUser.getId(), currentUser);
			scoreMap.put(currentUser.getId(), currentUser.getTotalScore());
			
			saveData(userMap, userMapFileName);
			saveData(scoreMap, scoreMapFileName);
		}
		currentUser = null;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
	
	
	public void run() {
			while(true) {
				this.displayMenu0(); 
			}	
	} //run end

}


