package com.itschool.fragmentexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.URL;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    private final Handler mHandler;

    private Bitmap bitmap;

    public ImageDownloadTask(Handler mHandler) {
        this.mHandler = mHandler;
    }



    protected Bitmap doInBackground(String... url) {
        String imageURL = "https://www.meme-arsenal.com/memes/38f9366ebe183480a388201aae6a2212.jpg";
        try {
            // Download Image from URL
            InputStream input = new URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    @Override
    protected void onPostExecute(Bitmap bit) {
        Message m = new Message();

        Bundle b = new Bundle();
        b.putParcelable("image" , bitmap);
        m.setData(b);
        mHandler.sendMessage(m);

    }
}
