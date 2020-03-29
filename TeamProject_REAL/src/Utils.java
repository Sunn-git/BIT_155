import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Utils {
	Scanner sc;
	Calendar calendar;
	SimpleDateFormat dataFormat;
	
	Utils(){
		sc = new Scanner(System.in);
		calendar = Calendar.getInstance();
		dataFormat = new SimpleDateFormat("yyyy-MM-dd\tHH:mm:ss");
	}
	
	void printDate() {
		System.out.println(this.dataFormat.format(this.calendar.getTime()));
	}

	
	public static void main(String[] args) { //test¿ë
		Utils utils = new Utils();
		utils.printDate();
		String name = utils.sc.nextLine();

	}

}
