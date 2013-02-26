package client;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
	private CTransceiver cTrans;
	private Console console;
	String userName, passWord;

	public Parser(CTransceiver cTrans) {
		this.cTrans = cTrans;
		console = System.console();
	}

	public int readInput() {
		
		System.out.println("Vad vill du g�ra? ");
		System.out
				.println("1) Visa Journal(er) \n 3) ändra journal(er) \n 4) Skapa ny journal \n 5) Ta bort ");
		String input = console.readLine();
		if (input.equals( "1")) {
			System.out.println(sendJournalRequest(input));
		} else if(input.equals("2")){
			System.out.println("Mata in ID på den journalen du vill visa: ");
			System.out.println(sendJournalRequest(console.readLine()));
		} else if (input.equals("3")) {
			System.out.println("Mata in ID på den journalen du vill ändra: ");
			sendJournalRequest(console.readLine());
			System.out.println("Skriv in det du vill lägga till: ");
			String addText = console.readLine();
			sendJournalRequest(addText);
		}else if(input.equals("4")){
			System.out.println("Mata in ID på den journalen du vill skapa: ");
			sendJournalRequest(console.readLine());
			System.out.println("Mata in namnet på patienten: ");
			sendJournalRequest(console.readLine());
			System.out.println("Mata in åkomma hos patienten: ");
			sendJournalRequest(console.readLine());
		}else if(input.equals("5")){
			System.out.println("Mata in ID på den journalen du vill ta bort: ");
			sendJournalRequest(console.readLine());
			System.out.println("Journal borttagen");
		}
		return Integer.parseInt(input);
	}

	public void loginPrompt(String trustPass) {

		String username = console.readLine("Username: ");
		char[] password = console.readPassword("Password: ");
		cTrans.setUp(password.toString(), trustPass, username);
		
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
