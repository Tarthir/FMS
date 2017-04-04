package com.tylerbrady34gmail.familyclient.ExpandFamilyView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.tylerbrady34gmail.familyclient.Models.Model;
import com.tylerbrady34gmail.familyclient.Models.Utils;
import com.tylerbrady34gmail.familyclient.R;
import com.tylerbrady34gmail.familyclient.ui.PersonActivity;

import models.Person;

/**
 * Created by tyler on 3/29/2017.
 * A viewHolder for person's. THe parent of this class is FamilyViewHolder
 */

class PersonViewHolder extends ChildViewHolder {
    /**My textView*/
    private TextView mMyView;
    /**The image View to the left of the textview*/
    private ImageView mMyImageView;
    /**A string for the female gender*/
    private final String MALE ="m";
    /**A string for the female gender*/
    private final String FEMALE = "f";

    PersonViewHolder(View itemView) {
        super(itemView);
        mMyView = (TextView) itemView.findViewById(R.id.person_textView);
        mMyImageView = (ImageView) itemView.findViewById(R.id.person_view_image);

        mMyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO FIX THIS
                Log.d("PersonViewHolder","TextField has been clicked");
                /*Intent intent = new Intent(getActivity(),PersonActivity.class);
                Bundle b = new Bundle();
                //if its the mother
                if(mMyView.getText().toString().contains("MOTHER")){
                    b.putString("person_key",); //Your id
                }
                else{//or the father that we clicked on
                    b.putString("person_key",currEvent.getPersonID()); //Your id
                }
                intent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivity(intent);*/
            }
        });
    }

    void bind(Person person) {
        if (person == null){//if there is no family to show
            Toast.makeText(mMyView.getContext(),"No Family to show for this person",Toast.LENGTH_LONG).show();
        }
        else if(PersonActivity.clickedOn.getFather().equals(person.getPersonID())){
            mMyView.setText(person.getfName() + " " + person.getlName() +"\nFATHER");
            mMyImageView.setImageDrawable(Utils.getGenderIcon(mMyImageView.getContext(),MALE));
        }
        else{
            mMyView.setText(person.getfName() + " " + person.getlName() +"\nMOTHER");
            mMyImageView.setImageDrawable(Utils.getGenderIcon(mMyImageView.getContext(),FEMALE));
        }

    }
}
