package com.android.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.listners.AlLoginHandler;

public class LoginActivity extends AppCompatActivity {

    public String tag;
    public Object msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acitivty);
        setTitle("Login");


         final EditText userIdText =  findViewById(R.id.userIdEditText);
        final EditText password =  findViewById(R.id.password);

        Button loginButton =  findViewById(R.id.chatButton);

        if(userIdText.getText().length() < 0){
            Log.i(tag:"UserId", msg:"Is empty");
          return;
       }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userIdText.getText().toString(), password.getText().toString());
            }
        });

    }
    //Login code
    void login(String userId, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setAuthenticationTypeId(User.AuthenticationType.APPLOZIC.getValue());
        user.setPassword(password);
        user.setImageLink("");

        Applozic.connectUser(context:this, user, new AlLoginHandler(){
            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {
                Intent mainIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
            @Override
            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                Toast.makeText(context:LoginActivity.this, text:"Error:" + registrationResponse, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
