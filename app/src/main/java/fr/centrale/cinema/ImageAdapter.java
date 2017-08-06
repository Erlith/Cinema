package fr.centrale.cinema;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by pierre on 25/07/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray listeImages;

    public ImageAdapter(Context c, JSONArray listeImages) {
        mContext = c;
        this.listeImages=listeImages;
    }


    public int getCount() {
        return listeImages.length();
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public Object getItem(int position) {
        try {
            return listeImages.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        LinearLayout layoutView = (LinearLayout) inflater.inflate(R.layout.image_gallery,null);
        ImageView imageView=layoutView.findViewById(R.id.imageButton);
        int size = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, mContext.getResources().getDisplayMetrics());
        layoutView.setLayoutParams(new GridView.LayoutParams(size, size));
        layoutView.setPadding(8, 8, 8, 8);
        ImageCinema imageCinema=new ImageCinema(imageView);
        try {
            imageCinema.loadFromUrl(listeImages.getJSONObject(position).getString("path")
                    .replace("{{width}}","200").replace("{{height}}","200"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return layoutView;
    }

}
