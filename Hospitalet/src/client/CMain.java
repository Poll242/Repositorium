package client;


public class CMain {

	public static void main(String[] args){
		CTransceiver cTrans = new CTransceiver();
		cTrans.sendData("JOHAN FTW!");
		Parser pars = new Parser(cTrans);
		pars.loginPrompt(args[0]);
		int mode = 0;
		while(mode != -1){
			mode = pars.readInput();
			
			
		}
	}
}