package server;

import java.io.IOException;


public class SMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Database base = new Database();
		STransceiver sTrans = new STransceiver(base);
		sTrans.setUp("password", "password");
		
		while(true){
			sTrans.transceive();
		}
		
		
 	}
}
