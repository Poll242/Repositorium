package server;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.io.OutputStream;
=======
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
>>>>>>> det nya fixade
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Scanner;
>>>>>>> det nya fixade

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
<<<<<<< HEAD
=======
	private String doctor, division;
	private BufferedReader br;

	public STransceiver(Database imdb) {
		this.imdb = imdb;
	}
>>>>>>> det nya fixade

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
			ksTrust.load(new FileInputStream("nytt/server_truststore"),
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
			ksKeys.load(new FileInputStream("nytt/server_keystore"),
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

<<<<<<< HEAD
			
			
			newSS = (SSLSocket) ss.accept();
			is = newSS.getInputStream();
			ss.setUseClientMode(false);
			ss.setNeedClientAuth(true);
			
			os = newSS.getOutputStream();
			SSLSession s = newSS.getSession();
			X509Certificate cert = (X509Certificate)s.getPeerCertificateChain()[0];
			String name = cert.getSubjectDN().getName();
			System.out.println(name);
			byte[] buffer = new byte[1024];
			int bytesRead = is.read(buffer);
			if (bytesRead == -1)
				throw new IOException("Unexpected End-of-file Received");
			String received = new String(buffer, 0, bytesRead);
			/*
			 * Write results to screen.
			 */
			System.out.println("Read " + received.length() + " bytes...");
			System.out.println(received);
			
=======
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

>>>>>>> det nya fixade
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transceive() throws IOException {
<<<<<<< HEAD
		
		byte[] buffer = new byte[1024];
		int bytesRead = is.read(buffer);
		if (bytesRead == -1)
			throw new IOException("Unexpected End-of-file Received");
		String received = new String(buffer, 0, bytesRead);
		int cmd = Integer.parseInt(received);
		switch(cmd){
		case(1): 
//			os.write(sendAlljournals(name,))
		break;
		case(2):
			
		}
		
	
//	SSLSession s = newSS.getSession();
//	X509Certificate cert = (X509Certificate)s.getPeerCertificateChain()[0];
//	String name = cert.getSubjectDN().getName();
//	System.out.println(name);
=======
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
			pw.println(imdb.deleteJournal(received, doctor));
			pw.flush();
			break;
		}

	}

	private void retrieveUserInfo(String info) {
		String[] temp = info.split(",");
		doctor = temp[0].split("=")[1];
		division = temp[1].split("=")[1];

>>>>>>> det nya fixade
	}
	
//	private void sendAllJournals(String subject){
//		ArrayList<Journals> list = imdb.getAssociatedJournals(subject);
//		os.write(list);
//	}

}
