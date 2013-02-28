package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Database base = new Database();
		STransceiver sTrans = new STransceiver(base);
		sTrans.setUp("password", "password");
			sTrans.accept();
		while(true){
			sTrans.transceive();
		}
		
		
 	}
}
