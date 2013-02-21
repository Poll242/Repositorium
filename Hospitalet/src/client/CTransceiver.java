package client;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	private char[] keystorePass, truststorePass;

	public CTransceiver() {

	}

	public void connect() {
		try {
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			ss = (SSLSocket) ssf.createSocket("localhost", 65004);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendData(String cmd) {
		try {
			OutputStream os = ss.getOutputStream();
			os.write(cmd.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void setUpKeystore() {
		try {
			ksKeys = KeyStore.getInstance("JKS");
			ksKeys.load(new FileInputStream("nytt/fakedoc_keystore"),
					keystorePass);
			kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ksKeys, keystorePass);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
