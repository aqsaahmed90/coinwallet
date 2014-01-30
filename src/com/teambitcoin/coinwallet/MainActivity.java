package com.teambitcoin.coinwallet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.teambitcoin.coinwallet.*;

public class MainActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        //adding the button object
    	Button button = (Button) findViewById(R.id.button1);
    	button.setOnClickListener(this);
    	
    	
    }
    public void clickButton1(){
    	Context context = getApplicationContext();
    	CharSequence text = "Successfully entered forgot password menu";
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	
    	startActivity(new Intent("com.teambitcoin.coinwallet.ForgotPassword"));
    }
    
    public void onClick(View v) {
    	switch (v.getId()){
    	case R.id.button1:
    	clickButton1();
    	break;
    	}
    	
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
