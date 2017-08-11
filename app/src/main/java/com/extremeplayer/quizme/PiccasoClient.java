package com.extremeplayer.quizme;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Abishaik on 14-07-2017.
 */

public class PiccasoClient {

    public static void downloadImage(Context c, String url, ImageView img)
    {
        if(url!=null && url.length()>0)
            Picasso.with(c).load(url).into(img);
    }
}
