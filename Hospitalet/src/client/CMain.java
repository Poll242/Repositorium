package client;


public class CMain {

	public static void main(String[] args){
		CTransceiver cTrans = new CTransceiver();
		
		Parser pars = new Parser(cTrans);
		pars.loginPrompt("password");

		int mode = 0;
		while(mode != -1){
			mode = pars.readInput();
						

		}
	}
}
