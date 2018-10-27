package com.fit.chat1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Proj_chat1Activity extends Activity {
	/** Called when the activity is first created. */

	private EditText chattxt;
	private TextView chattxt2;
	private Button chatok;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		chattxt = (EditText) findViewById(R.id.chattxt);
		chattxt2 = (TextView) findViewById(R.id.chattxt2);
		chatok=(Button) findViewById(R.id.send);
		System.out.println(chatok == null);	
		
		chatok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					connecttoserver(chattxt.getText().toString());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		});

	}



	public void connecttoserver(String socketData) throws UnknownHostException,
			IOException {
		Socket socket = RequestSocket("10.20.70.55", 5000);
		SendMsg(socket, "user1:"+socketData);
		
		StringBuffer buffer=new StringBuffer();
		String txt = ReceiveMsg(socket);
		buffer.append(txt);
		this.chattxt2.setText(buffer.toString());
	}

	private Socket RequestSocket(String host, int port)
			throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		return socket;
	}

	private void SendMsg(Socket socket, String msg) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		writer.write(msg.replace("\n", " ") + "\n");
		writer.flush();
	}

	private String ReceiveMsg(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		String txt = reader.readLine();
		return txt;

	}

}