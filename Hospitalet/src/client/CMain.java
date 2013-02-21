package client;


public class CMain {

	public static void main(String[] args){
		CTransceiver cTrans = new CTransceiver();
		cTrans.setUp("docdoc","Ho5p1t@l_S3rv3r");
		cTrans.connect();
		cTrans.sendData("JOHAN FTW!");
	}
}
