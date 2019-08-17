package com.example.user.task2;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<countryPic> countries;
    private Context context;
    private final int THUMBNAIL_SIZE = 250;

    public GridAdapter(List<countryPic> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        countryPic country=countries.get(position);
        if(convertView==null){
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.gridlayout,null);
            MyViewHolder myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }
        final MyViewHolder myViewHolder = (MyViewHolder) convertView.getTag();


        ImageLoader imageLoader = MySingleton.getmInstance(context).getmImageLoader();
        imageLoader.get(country.getUrl(), new ImageLoader.ImageListener() {

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap image = response.getBitmap();
                Bitmap thumbnail = ThumbnailUtils.extractThumbnail(image, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
                myViewHolder.imageView.setImageBitmap(thumbnail);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Image load"+error.getMessage());
            }
        });

        myViewHolder.imageView.setTag(country.getUrl());
        return convertView;
    }

    public class MyViewHolder{
        public ImageView imageView;

        public MyViewHolder(View view){
            imageView=view.findViewById(R.id.resultimage);
        }
    }
}
