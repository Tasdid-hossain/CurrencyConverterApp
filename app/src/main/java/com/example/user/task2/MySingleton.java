package com.example.user.task2;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by 101210541 on 13/11/2018.
 */

public class MySingleton extends Application {
    private static MySingleton mInstance;
    private RequestQueue mRequestque;
    private ImageLoader mImageLoader;
    private static Context context;

    private MySingleton(Context context){
        this.context = context;
        mRequestque = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestque, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(5);
            @Override
            public Bitmap getBitmap(String url) {
                Bitmap bitmap = cache.get(url);
                if(bitmap==null){ System.out.println("Not in cache");

                }
                else
                    System.out.println("In cache");
                return bitmap;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                System.out.println("putBitmap");
                cache.put(url,bitmap);
            }
        });
    }

    public static synchronized MySingleton getmInstance(Context mCon){
        if(mInstance==null)
            mInstance= new MySingleton(mCon);
        return mInstance;
    }

    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }
}
