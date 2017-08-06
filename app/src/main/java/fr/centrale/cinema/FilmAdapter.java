package fr.centrale.cinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by pierre on 23/07/2017.
 */

public class FilmAdapter extends BaseAdapter {

    private final List<JSONObject> data;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public FilmAdapter (Context context, List<JSONObject> data)
    {
        this.data=data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= layoutInflater.inflate(R.layout.film_adapter, null);
        JSONObject item = (JSONObject) getItem(i);
        final ImageView affiche = view.findViewById(R.id.affiche);
        TextView titre = view.findViewById(R.id.titre);
        TextView genre = view.findViewById(R.id.genre);
        TextView categorie = view.findViewById(R.id.categorie);

        try
        {
            titre.setText(item.getString("titre"));
            genre.setText(item.getString("genre"));
            categorie.setText(item.getString("categorie"));
            String url = item.getString("affiche").replace("{{width}}","200").replace("{{height}}","200");
            ImageCinema imageCinema = new ImageCinema(affiche);
            imageCinema.loadFromUrl(url);
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }

        return view;
    }
}
