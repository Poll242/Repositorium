package client;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.*;
import java.security.cert.CertificateException;

public class CTransceiver{
	private KeyStore ksKeys, ksTrust;
	private KeyManagerFactory kmf;
	private TrustManagerFactory tmf;
	private SSLSocket ss;
	private SSLContext sslContext;
	private OutputStream os;
	private InputStream is;
	private char[] keystorePass, truststorePass;

	public CTransceiver() {

	}

	public void connect() {
		try {
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			ss = (SSLSocket) ssf.createSocket("localhost", 65004);
			is = ss.getInputStream();
			os = ss.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendData(String cmd) {
		try {

			PrintWriter bw = new PrintWriter(ss.getOutputStream(), true);
			bw.println(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUp(String keyPass, String trustPass, String usrName) {
		keystorePass = keyPass.toCharArray();
		truststorePass = trustPass.toCharArray();
		
		setUpKeystore(usrName);
		setUpTruststore();
		setUpSSLContext();

	}

	private void setUpSSLContext() {
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpTruststore() {
		try {
			ksTrust = KeyStore.getInstance("JKS");
			ksTrust.load(new FileInputStream("nytt/clienttruststore"),
					truststorePass);
			tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ksTrust);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpKeystore(String usrName) {
		try {
			ksKeys = KeyStore.getInstance("JKS");
			ksKeys.load(new FileInputStream("nytt/" +usrName),
					keystorePass);
			kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ksKeys, keystorePass);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String receiveData() throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		String received = br.readLine();

		return received;
	}
	
	
}
