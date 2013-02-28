package client; 

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
	private CTransceiver cTrans;
	private Console console;
	private Scanner scan;
	String userName, passWord;

	public Parser(CTransceiver cTrans) {
		this.cTrans = cTrans;
		console = System.console();
		scan = new Scanner(System.in);
	}

	public int readInput() {
		
		System.out.println("Vad vill du göra? ");
		System.out
				.println("1) Visa alla journaler \n 2) Visa en journal \n 3) ändra journal(er) \n 4) Skapa ny journal \n 5) Ta bort ");
		String input = scan.nextLine();
		
		String addText = "";
		if (input.equals( "1")) {
			System.out.println(sendJournalRequest(input).replace("#", "\n"));
		} else if(input.equals("2")){
			System.out.println("Mata in ID på den journalen du vill visa: ");
			addText = input + "!" + scan.nextLine();
			System.out.println(sendJournalRequest(addText).replace("#", "\n"));
		} else if (input.equals("3")) {
			System.out.println("Mata in ID på den journalen du vill ändra: ");
			addText = input + "!" +scan.nextLine();
			System.out.println("Skriv in det du vill lägga till: ");
			addText = addText + "!" +scan.nextLine();
			sendJournalRequest(addText);
		}else if(input.equals("4")){
			System.out.println("Mata in ID på den journalen du vill skapa: ");
			addText =input + "!" +scan.nextLine();
			System.out.println("Mata in namnet på patienten: ");
			addText = addText + "!" +scan.nextLine();
			System.out.println("Mata in namnet på din sjuksköterska: ");
			addText = addText + "!" +scan.nextLine();
			System.out.println(sendJournalRequest(addText).replace("#", "\n"));
		}else if(input.equals("5")){
			System.out.println("Mata in ID på den journalen du vill ta bort: ");
			System.out.println(sendJournalRequest(input + "!" + scan.nextLine()).replace("#", "\n"));
		}
		return Integer.parseInt(input);
	}

	public void loginPrompt(String trustPass) {

		System.out.println("Användarnamn: ");
		String username = scan.nextLine();
		System.out.println("Lösenord: ");
		String password = scan.nextLine();
		cTrans.setUp(password, trustPass, username);
		
		cTrans.connect();
		username = "";
		password = null;

	}

	private String sendJournalRequest(String cmd) {
		String data = "";
		cTrans.sendData(cmd);
		try {
			data = cTrans.receiveData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
