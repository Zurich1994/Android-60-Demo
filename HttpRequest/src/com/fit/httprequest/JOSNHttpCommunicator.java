package com.fit.httprequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class JOSNHttpCommunicator {

	JSONObject jsonObjSend;
	JSONObject jsonObjRecv;
	Context localcontext;
	static JSONObject responseinjson;
	
	
	public JSONArray sendOutRequestforActiveJobs(){
		jsonObjSend=createJSONObjectGetActiveList();
		if(checkStatus()){
		}
		return null;
	}
	
	
	public JSONObject createJSONObjectGetActiveList(){
		
		JSONObject jsonObjSend = new JSONObject();
		try {

			// Add a nested JSONObject (e.g. for header information)
			JSONObject header = new JSONObject();
			 header.put("ApplicationKey","2911D97B6309492EB9FDE9F4E2A5007A");
//			header.put("ApplicationKey", "3B2F2591D237428CB8993DA540B201A1");
			header.put("AuthToken", JSONObject.NULL);
			header.put("DeviceId", JSONObject.NULL);
			header.put("DevicePlatform", JSONObject.NULL);
			header.put("Locale", JSONObject.NULL);
			header.put("MobileDeviceID", JSONObject.NULL);
			header.put("Password", JSONObject.NULL);
			header.put("ProgramCode", JSONObject.NULL);
			header.put("RegistrationID", JSONObject.NULL);
			header.put("RequestNumber", JSONObject.NULL);
			header.put("RequestService", "LIST");
			header.put("RequestTimeStamp", JSONObject.NULL);
			header.put("RequestType", "DISPATCH_JOB");
			header.put("Reserved1", JSONObject.NULL);
			header.put("Reserved2", JSONObject.NULL);
			header.put("Source", JSONObject.NULL);
			header.put("Token", JSONObject.NULL);
			header.put("VersionId", JSONObject.NULL);
			header.put("Vin", JSONObject.NULL);

			// Add a nested BODY JSON object.
			JSONObject body = new JSONObject();
			body.put("JobStatus", JSONObject.NULL);
			body.put("PONumber", JSONObject.NULL);
			body.put("Username", "protow");
			body.put("Password", "protow");

			// Add to the root JSON object
			jsonObjSend.put("Header", header);
			jsonObjSend.put("Body", body);
			Log.e("Joblist", jsonObjSend.toString());
			return jsonObjSend;
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	//检查网络状态
	private boolean checkStatus(){
		boolean status=false;
		final ConnectivityManager manager=(ConnectivityManager) localcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo infor=manager.getActiveNetworkInfo();
		if(infor!=null){
			status=infor.isConnected();
		}
		return status;
	}
	
	//发送http请求
	public JSONObject sendHttpPost(JSONObject jsonObject){
		DefaultHttpClient httpclient=null;
	
		try {

			httpclient=getClient();
			HttpParams params=httpclient.getParams();
			HttpConnectionParams.setSoTimeout(params,90000);
			HttpPost httpPostRequest=new HttpPost("https://devgateway.crosscountry-auto.com/MGv3.0_SP/ProcessRequest");
			StringEntity se;
			se = new StringEntity(jsonObject.toString(), "UTF-8");
			httpPostRequest.setEntity(se);
			httpPostRequest.setHeader("Connection","Keep-Alive");
			httpPostRequest.setHeader("Content-type","text/plain");
			
			BasicResponseHandler handler=new BasicResponseHandler();
			String response=httpclient.execute(httpPostRequest, handler);
			
			try {
				responseinjson=new JSONObject(response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return responseinjson;
	}
	
	//得到发送请求的client
	private DefaultHttpClient getClient(){
		BasicHttpParams httpParameters=new BasicHttpParams();
		//设定连接超时
		HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
		HttpConnectionParams.setSoTimeout(httpParameters, 60000);
		DefaultHttpClient client;
		//注册http
		SchemeRegistry registry=new SchemeRegistry();
		registry.register(new Scheme("http",PlainSocketFactory.getSocketFactory(), 80));
		KeyStore trustStore;
		SSLSocketFactory sslSocketFactory = null;
		try {
			trustStore=KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null,null);
			sslSocketFactory=new MySSLSocketFactory(trustStore);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		 registry.register(new Scheme("https", sslSocketFactory, 443));
		 ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(httpParameters,
	                registry);
	        client = new DefaultHttpClient(manager, httpParameters);
	        //client.setHttpRequestRetryHandler(new HttpRequestRetryHandler());
	        return client;
	}
	
	
	/*
	 * Custom SSL Socket Factory
	 */
     class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
	
}
