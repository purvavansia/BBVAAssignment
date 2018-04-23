package com.example.purva.bbvaassignment.ui.info;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by purva on 4/22/18.
 */

public class PresenterInfo implements IPresenterInfo {

    IViewInfo iViewInfo;
    Context context;
    public PresenterInfo(IViewInfo iViewInfo, Context context,String lat, String lng, String name, String id, String addr, String rating, String icon) {
        this.iViewInfo = iViewInfo;
        this.context = context;
        iViewInfo.showData(name, addr, lat, lng, id, rating, icon);
    }


    @Override
    public void onButtonClicked(String lat, String lng) {

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+lat+","+lng);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }
}
