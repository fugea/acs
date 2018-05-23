package com.suma.acs.messageDo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class InformDoTest {
	
	public static void main(String[] args){
		try {
			doSoapRequest();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public static void doSoapRequest() throws Exception{
		URL httpUrl = new URL("http://localhost:3000/acs");
		HttpURLConnection httpConnection = (HttpURLConnection) httpUrl
				.openConnection();
		setHeaders(httpConnection);

		// httpConnection = setContent(element, httpConnection, Id);
		OutputStreamWriter writer;
		writer = new OutputStreamWriter(httpConnection.getOutputStream());
		ByteArrayOutputStream logDebug;
		logDebug = new ByteArrayOutputStream();
		writer = new OutputStreamWriter(logDebug);
		FileOutputStream fos = new FileOutputStream(new File(
				"D:\\Inform-20140723150240.xml"));
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		osw.close();
		httpConnection.getOutputStream().close();

		httpConnection.connect();
		 int httpCode = httpConnection.getResponseCode();
		 System.out.println("httpCode : = " + httpCode);

	}

	public static void setHeaders(final HttpURLConnection httpConnection) throws Exception{
		httpConnection.setRequestMethod("POST");
		httpConnection.setAllowUserInteraction(false);
		httpConnection.setDoOutput(true); // want to send
		httpConnection.setRequestProperty("Content-type", "text/xml");
		httpConnection.setRequestProperty("Hostname", "cpe");
		httpConnection.setRequestProperty("Authorization", " Digest realm=\""
				+ "sss" + "\"," + " username=\"" + "test" + "\","
				+ "nonce=\"" + "test" + "\"," + "uri=\"/acs\","
				+ "qop=\"auth\"," + "nc=" + "test" + "," + "cnonce=\"" + "test"
				+ "\"," + "response=\"" + "test" + "\","
				+ "opaque=\"(null)\"");
	}

}
