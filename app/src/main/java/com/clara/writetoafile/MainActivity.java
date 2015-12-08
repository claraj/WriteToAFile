package com.clara.writetoafile;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

	EditText enterData;
	Button saveButton;
	Button readButton;
	TextView dataFromFile;

	String filename = "data.dat";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		enterData = (EditText) findViewById(R.id.editText);
		saveButton = (Button) findViewById(R.id.saveButton);
		readButton = (Button) findViewById(R.id.readButton);
		dataFromFile = (TextView) findViewById(R.id.textFromFile);

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				String data = enterData.getText().toString();
				//Save text box text to a file
				FileOutputStream outputStream;

				try {

					outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
					outputStream.write(data.getBytes());
					outputStream.close();
					Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_LONG).show();
					enterData.setText("");

				} catch (Exception e) {
					Log.e("WRITETOAFILE", "Error opening file", e);
				}

			}
		});


		readButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//read in and display in textbox

				FileInputStream inputStream;
				BufferedReader bufferedReader;
				try {
					inputStream = openFileInput(filename);
					byte[] bytes = new byte[inputStream.available()];  //how long is the stream?
					inputStream.read(bytes); 							//read it into this bytes array
					String data = new String(bytes); 					//and then turn it into a string
					inputStream.close();
					dataFromFile.setText(data);

				} catch (Exception e){
					Log.e("WRITETOAFILE", "Error opening file", e);
				}

			}
		});

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
