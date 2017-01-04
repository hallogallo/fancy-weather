package com.miri.fancyweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by miri on 17.12.16.
 */

public class networkSocket {

    Context mContext;

    public networkSocket(Context mContext) {
        this.mContext = mContext;
    }

    public weatherObj getWeather() {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return null;
        } else {
            return null;
        }
    }

}
