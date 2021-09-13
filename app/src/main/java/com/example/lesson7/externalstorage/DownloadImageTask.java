package com.example.lesson7.externalstorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;


/**
 * Created by hungnm24 on 4/27/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

    private CallBack callBack;
    private Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public DownloadImageTask(Context context, CallBack callBack) {
        super();
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        callBack.onDownloadComplete(bitmap);
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = Utils.downloadImage(strings[0]);
        Utils.saveBitmapToFile(context, bitmap);
        return bitmap;
    }

    interface CallBack {
        void onDownloadComplete(Bitmap bitmap);
    }
}
