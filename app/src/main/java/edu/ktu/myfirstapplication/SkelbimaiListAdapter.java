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

import com.squareup.picasso.Picasso;

import java.util.List;

public class SkelbimaiListAdapter extends ArrayAdapter<SkelbimaiList> {

    public SkelbimaiListAdapter(Context context, List<SkelbimaiList> objects) {
        super(context, R.layout.skelbimailistdesign, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.skelbimailistdesign, null);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView room_count = (TextView) view.findViewById(R.id.room_count);

        SkelbimaiList item = getItem(position);

        title.setText(item.getTitle());
        price.setText(item.getPrice()+"€");
        room_count.setText(item.getRoom_count()+" kambariai");
        Picasso.get().load(item.getImage().toString()).into(image);

        return view;
    }
}
