package edu.ktu.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class listAdapterhouselist extends ArrayAdapter<Skelbimas> {

    public listAdapterhouselist(Context context, ArrayList<Skelbimas> SkelbimaiArrayList){
        super(context,R.layout.list_item,SkelbimaiArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Skelbimas skelbimas = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.house_pic);
        TextView ad_title = convertView.findViewById(R.id.title);
        TextView ad_description = convertView.findViewById(R.id.description);

        imageView.setImageResource(skelbimas.imageId);
        ad_title.setText(skelbimas.title);
        ad_description.setText(skelbimas.description);

        return convertView;
    }
}
