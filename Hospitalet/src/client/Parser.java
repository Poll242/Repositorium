package client;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;




public class Parser {
	private CTransceiver cTrans;
	private Console console;
	String userName, passWord;
	
	public Parser(CTransceiver cTrans){
		this.cTrans = cTrans;
		console = System.console();
	}
	
	public int readInput(){
		System.out.println("Vad vill du göra? ");
		System.out.println("1) Visa Journal(er) \n 2) ändra joiurnal(er)/ta bort journal(er) \n 3) Skapa ny journal");
		int input = Integer.parseInt(console.readLine());
		sendJournalRequest(input);
		return input;
	}
	
	public void loginPrompt(String trustPass){
		
		String username = console.readLine("Username: ");
		char[] password = console.readPassword("Password: ");
		cTrans.setUp(password.toString(),trustPass, username);
		username ="";
		password = null;
		cTrans.connect();
		
	}
	
	private void sendJournalRequest(int cmd){
		String cmdPars = Integer.toString(cmd);
		cTrans.sendData(cmdPars);
	}
}