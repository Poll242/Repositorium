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
		sTrans.setUp("Ke1ys_to#Re9", "Ho5p1t@l_S3rv3r");
			sTrans.accept();
		while(true){
			sTrans.transceive();
		}
	}
}
