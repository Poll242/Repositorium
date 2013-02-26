package client;


public class CMain {

	public static void main(String[] args){
		CTransceiver cTrans = new CTransceiver();
		
		Parser pars = new Parser(cTrans);
		pars.loginPrompt("Ho5p1t@l_S3rv3r");
		cTrans.sendData("JOHAN FTW!");
		int mode = 0;
		while(mode != -1){
			mode = pars.readInput();
						
		}
	}
}
