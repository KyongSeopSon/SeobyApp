package com.aos.seobyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class GoogleAppIdTask extends AsyncTask<Void, Void, String> {
    protected Context mContext = null;
    protected TextView tvInformation = null;

    public GoogleAppIdTask(Context context, TextView tv){
        mContext = context;
        tvInformation = tv;
    }

    protected String doInBackground(final Void... params) {
        String adid = null;
        try {
            adid = AdvertisingIdClient.getAdvertisingIdInfo(mContext).getId();
        } catch (Exception ex) {


        }
        return adid;
    }
    protected void onPostExecute(String adid) {

        tvInformation.setText(tvInformation.getText() + "\nADID : " + adid);

    }

}
