package server;

import javax.net.ssl.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.*;
import java.security.cert.CertificateException;

public class STransceiver {
	private KeyStore ksKeys, ksTrust;
	private KeyManagerFactory kmf;
	private TrustManagerFactory tmf;
	private SSLServerSocket ss;
	private SSLSocket newSS;
	private SSLContext sslContext;
	private char[] keystorePass, truststorePass;

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

			newSS = (SSLSocket) ss.accept();
			ss.setUseClientMode(false);
			ss.setNeedClientAuth(true);

			InputStream is = newSS.getInputStream();
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

		} catch (IOException e) {
			System.out.println("Felaktigt CA-cert");
		}
	}

}
