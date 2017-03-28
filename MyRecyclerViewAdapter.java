package com.tylerbrady34gmail.familyclient;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by tyler on 3/28/2017.
 */
public class MyRecyclerViewAdapter extends RecyclerView{
    public MyRecyclerViewAdapter(Context context) {
        super(context);
    }

    public MyRecyclerViewAdapter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerViewAdapter(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
