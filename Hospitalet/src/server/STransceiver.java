package server;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.*;

public class STransceiver {
	private KeyStore ksKeys, ksTrust;
	private KeyManagerFactory kmf;
	private TrustManagerFactory tmf;
	private SSLServerSocket ss;
	private SSLSocket newSS;
	private SSLContext sslContext;
	private InputStream is;
	private OutputStream os;
	private char[] keystorePass, truststorePass;
	private Database imdb;
	private String doctor, division;
	private BufferedReader br;


	public STransceiver(Database imdb){
		this.imdb = imdb;
	}
	
	public void setUp(String keyPass, String trustPass) {
		keystorePass = keyPass.toCharArray();
		truststorePass = trustPass.toCharArray();

		setUpKeystore();
		setUpTruststore();
		setUpSSLContext();
	}

	private void setUpSSLContext() {
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
			ss = (SSLServerSocket) ssf.createServerSocket(65004);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpTruststore() {
		try {
			ksTrust = KeyStore.getInstance("JKS");
			ksTrust.load(new FileInputStream("nytt/server/server_truststore.jks"),
					truststorePass);
			tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ksTrust);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpKeystore() {
		try {
			ksKeys = KeyStore.getInstance("JKS");
			ksKeys.load(new FileInputStream("nytt/server/server_keystore"),
					keystorePass);

			kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ksKeys, keystorePass);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void accept() {

		try {
			System.out.println("Server awaiting connection...");

			ss.setUseClientMode(false);
			ss.setNeedClientAuth(true);

			newSS = (SSLSocket) ss.accept();
			is = newSS.getInputStream();
			os = newSS.getOutputStream();

			SSLSession s = newSS.getSession();
			X509Certificate cert = (X509Certificate) s
					.getPeerCertificateChain()[0];
			String info = cert.getSubjectDN().getName();
			retrieveUserInfo(info);
			/*
			 * Write results to screen.
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transceive() throws IOException {
		String received;

		br = new BufferedReader(new InputStreamReader(newSS.getInputStream()));
		received = br.readLine();

		String[] temp;
		PrintWriter pw = new PrintWriter(newSS.getOutputStream(), true);
		switch (received.charAt(0)) {
		case ('1'):
			pw.println(imdb.getAssociatedJournals(doctor, division));
			pw.flush();
			break;
		case ('2'):
			temp = received.split("!");
			pw.println(imdb.getJournal(temp[1], doctor, division));
			pw.flush();

			break;
		case ('3'):
			temp = received.split("!");
			pw.println(imdb.appendToText(doctor, temp[1], temp[2]));
			pw.flush();

			break;
		case ('4'):
			temp = received.split("!");
			String mess = imdb.addJournal(temp[1], doctor, temp[3], division,
					temp[2]);
			pw.println(mess);

			break;
		case ('5'):
			temp = received.split("!");
			pw.println(imdb.deleteJournal(temp[1], doctor));
			pw.flush();
			break;
		}

	}

	private void retrieveUserInfo(String info) {
		String[] temp = info.split(",");
		doctor = temp[0].split("=")[1];
		division = temp[1].split("=")[1];

	}
	
//	private void sendAllJournals(String subject){
//		ArrayList<Journals> list = imdb.getAssociatedJournals(subject);
//		os.write(list);
//	}

}
