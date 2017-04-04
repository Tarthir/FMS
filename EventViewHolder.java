package com.tylerbrady34gmail.familyclient.ExpandEventView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.tylerbrady34gmail.familyclient.ExpandFamilyView.Family;
import com.tylerbrady34gmail.familyclient.Models.Utils;
import com.tylerbrady34gmail.familyclient.R;

import models.Event;

/**
 * Created by tyler on 3/29/2017.
 * Holds our views for the children of the expandable recyclerview
 */

class EventViewHolder extends ChildViewHolder<Event>{

    private TextView mMyView;
    private ImageView mImageView;
    EventViewHolder(View itemView) {
        super(itemView);
        mMyView = (TextView) itemView.findViewById(R.id.event_textView);
        mImageView = (ImageView)  itemView.findViewById(R.id.marker_view_image);
        mMyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO FIX THIS

            }
        });
    }

    void bind(Event event) {
        if (event == null){//if there is no family to show
            Toast.makeText(mMyView.getContext(),"No Events to show for this person",Toast.LENGTH_LONG).show();
        }
        else {
            mMyView.setText(event.toString());
            mImageView.setImageDrawable(Utils.getLocIcon(mImageView.getContext()));
        }
    }

}
