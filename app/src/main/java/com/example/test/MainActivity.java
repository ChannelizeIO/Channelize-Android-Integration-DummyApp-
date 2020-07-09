package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.channelize.apisdk.Channelize;
import com.channelize.apisdk.network.response.ChannelizeError;
import com.channelize.apisdk.network.response.CompletionHandler;
import com.channelize.apisdk.network.response.LoginResponse;
import com.channelize.apisdk.utils.ChannelizePreferences;
import com.channelize.uisdk.ChannelizeMainActivity;
import com.channelize.uisdk.utils.ChannelizeUtils;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Channelize channelize;
    private ChannelizeUtils channelizeUtils;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);

        mContext = this;
        channelize = Channelize.getInstance();
        String currentUserId = ChannelizePreferences.getCurrentUserId(mContext);

        channelizeUtils = ChannelizeUtils.getInstance();

        if (currentUserId != null && !currentUserId.isEmpty()
                && !currentUserId.equals("null")) {
            channelize.setCurrentUserId(currentUserId);
            Channelize.getInstance().setCurrentUserId(currentUserId);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*channelize.loginWithUserId("userId", "clientServerToken", new CompletionHandler<LoginResponse>() {
                    @Override
                    public void onComplete(LoginResponse loginResponse, ChannelizeError channelizeError) {
                        if (loginResponse != null && loginResponse.getUser() != null) {
                            startMainActivity();
                        }else if (channelizeError != null) {
                            Log.e("Error",channelizeError.toString());
                        }
                    }
                });*/

                channelize.loginWithEmailPassword("test10556@seaddons.com", "123456", new CompletionHandler<LoginResponse>() {
                    @Override
                    public void onComplete(LoginResponse loginResponse, ChannelizeError channelizeError) {
                        if (loginResponse != null && loginResponse.getUser() != null) {
                            startMainActivity();
                        }else if (channelizeError != null) {
                            Log.e("Error",channelizeError.toString());
                        }
                    }
                });
            }
        });
    }

    private void startMainActivity() {
        if (channelize.getCurrentUserId() != null
                && !channelize.getCurrentUserId().isEmpty()) {
            Channelize.connect();
            Intent intent = new Intent(mContext, ChannelizeMainActivity.class);
            startActivity(intent);
        }
    }
}
