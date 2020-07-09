package com.example.test;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.channelize.apisdk.Channelize;
import com.channelize.apisdk.ChannelizeConfig;
import com.channelize.apisdk.utils.ChannelizePreferences;
import com.channelize.uisdk.ChannelizeUI;
import com.channelize.uisdk.ChannelizeUIConfig;

public class AppController extends MultiDexApplication {
    private static Context context;
    private Channelize channelize;

    public AppController() {
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initializeChannelize();
    }

    private void initializeChannelize() {
        ChannelizeConfig channelizeConfig = new ChannelizeConfig.Builder(this)
                //.setAPIKey("Your Public Key")
                .setAPIKey("qHvonVEyIxDLa6zh")
                .setLoggingEnabled(true)
                .build();

        Channelize.initialize(channelizeConfig);

        Channelize.getInstance().setCurrentUserId(ChannelizePreferences.getCurrentUserId(getContext()));

        if (Channelize.getInstance().getCurrentUserId() != null
                && !Channelize.getInstance().getCurrentUserId().isEmpty()) {
            Channelize.connect();
        }

        ChannelizeUIConfig channelizeUIConfig = new ChannelizeUIConfig.Builder()
                .enableCall(true)
                .build();
        ChannelizeUI.initialize(channelizeUIConfig);

        channelize = Channelize.getInstance();
    }
}
