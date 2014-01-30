package com.teambitcoin.coinwallet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
//import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.inputmethod.*;

public class ForgotPassword extends Activity{
	

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_main);
		
		
	//added this text when hit done after the user entered username
	EditText editText1 = (EditText) findViewById(R.id.editText1);
	
	editText1.setOnEditorActionListener(new OnEditorActionListener() {
	    @Override
	    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	        boolean handled = false;
	        if (actionId == EditorInfo.IME_ACTION_DONE) {
	        	
	        	Context context = getApplicationContext();
	        	CharSequence text = "Successfully entered username";
	        	int duration = Toast.LENGTH_SHORT;

	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        	
	        	//method to get the secret question according to username entered...
	        	
	        	CharSequence secretQuestion = "Here is your secret question...";
	        	int duration2 = (Toast.LENGTH_LONG)*200;//stay on screen longer
	        	
	        	Toast toast2 = Toast.makeText(context, secretQuestion, duration2);
	        	toast2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER, 0, -40);
	        	toast2.show();
	        	
	        	handled = true;
	        }
	        return handled;
	    }
	});
	
	//added this text when hit done after the user entered username
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		
		editText2.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		        if (actionId == EditorInfo.IME_ACTION_DONE) {
		        	
		        	Context context = getApplicationContext();
		        	CharSequence text = "Successfully answered question";
		        	int duration = Toast.LENGTH_SHORT;

		        	Toast toast = Toast.makeText(context, text, duration);
		        	toast.show();
		            
		            handled = true;
		          //call the method which will bring the correct secret question
		        	//corresponding to the inputted user name
		        }
		        return handled;
		    }
		});
	
	//adding the button object
	Button button = (Button) findViewById(R.id.button2);
	
	button.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		Context context = getApplicationContext();
    	CharSequence text = "Checking answer...";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
		
		//call the method which will check if the answer entered correspond
    	//to the right answer to that member's secret question
			
			
		}
	});
	
	
	}
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}