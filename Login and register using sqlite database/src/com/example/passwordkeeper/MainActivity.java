package com.example.passwordkeeper;



import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	LoginDataBaseAdapter loginDataBaseAdapter;
 Button login;

 static Button registerr;
	EditText enterpassword;
	TextView forgetpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		login=(Button)findViewById(R.id.login_btn);
		registerr=(Button)findViewById(R.id.reg_btn);
		enterpassword=(EditText)findViewById(R.id.enterpassword);
		forgetpass=(TextView)findViewById(R.id.textView1);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
		loginDataBaseAdapter.open();
		if(Dec.isRegistered){
			registerr.setVisibility(View.INVISIBLE);
		}
		registerr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Registration.class);
				startActivity(i);
			}
		});
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String Password=enterpassword.getText().toString();
				
				String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Password);
				
				// check if the Stored password matches with  Password entered by user
				if(Password.equals(storedPassword))
				{
					Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
					Intent ii=new Intent(MainActivity.this,Home.class);
					startActivity(ii);
				}
				else
				{
					Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
				}
				
				
				
			}
		});
		
		forgetpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 final Dialog dialog = new Dialog(MainActivity.this);
		            dialog.setContentView(R.layout.forget);
		            dialog.setTitle("Get Password");
		 
		            // get the Refferences of views
		            final  EditText security=(EditText)dialog.findViewById(R.id.editText1);
		            final  TextView getpass=(TextView)dialog.findViewById(R.id.text_pass);
		 
		            Button ok=(Button)dialog.findViewById(R.id.button1);
		            Button cancel=(Button)dialog.findViewById(R.id.button2);
		 
		            // Set On ClickListener
		            ok.setOnClickListener(new View.OnClickListener() {
		 
		                public void onClick(View v) {
		                    // get The User name and Password
		                    String userName=security.getText().toString();
		                    //String password=getpass.getText().toString();
		 
		                    // fetch the Password form database for respective user name
		                    String storedPassword=loginDataBaseAdapter.getAllTags(userName);
		                    
		                    
		                    
		                    //Log.d("GET PASSWORD",String.valueOf( storedPassword.getCount() ));
		                    
		                    Log.d("GET PASSWORD",storedPassword);
		                    
		                    getpass.setText(storedPassword);
		                    
		 
		                    // check if the Stored password matches with  Password entered by user
		                   /* if(userName.equals(storedPassword))
		                    {
		                        //Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
		                    	getpass.setText(String.valueOf(storedPassword));
		                    	
		                        dialog.dismiss();
		                    }
		                    else
		                    {
		                        //Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
		                    }*/
		                }
		            });
		            cancel.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
		 
		            dialog.show();
				
			}
		});
	}
		
		
		
		public void forgetpass(View V)
	       {
	           
		
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	    // Close The Database
		loginDataBaseAdapter.close();
	}

	

}
