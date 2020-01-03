package ie.gmit.sw;

import java.util.Scanner;

import java.io.*;

public class Runner {
	private static Scanner console = new Scanner(System.in);

	private static  String wili;
	private static  String query;
	
	public static void main(String[] args) throws Throwable {

		Menu menu = new Menu();
		setWili();
		setQuery();
		menu.displayMenu();
		Parser parser  = new Parser(getWili(),6);
		
			
			Database db = new Database();
			parser.setDb(db);
			Thread t = new Thread(parser);
			t.start();
		
			t.join();
			
			db.resize(300);
			
			parser.analyseQuery(getQuery());
		
		}

	//set/get wili file.
	public static void setWili(){
		String input = "";
		boolean fileReal = false;
		
		System.out.println("______________________________Wili___________________________");
		
		while (!fileReal) {
			System.out.println("please enter wili file name(please include the (.txt)\n");
			input = console.next();
			if (new File(input).isFile()) { // checks if file exists
				fileReal = true;
			} else {
				System.out.println("thats not a file try again");
			}
		}
		wili = input;
	}
	
	public static String getWili() {
		return wili;
	}
	

	//set/get query file.
	public static void setQuery() {
		String input = "";
		boolean fileReal = false;
		
		System.out.println("_______________________________Query_________________________");
			
		while (!fileReal) {
			System.out.println("please enter query file name(please include the (.txt)\n");
			input = console.next();
			if (new File(input).isFile()) { // checks if file exists
				fileReal = true;
			} else {
				System.out.println("thats not a file try again");
			}
		}
		query = input;
	}
	
	public static String getQuery() {
		return query;
	}
	
}

