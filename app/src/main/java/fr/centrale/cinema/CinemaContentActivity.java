package fr.centrale.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CinemaContentActivity extends AppCompatActivity {

    public static final String KEY_JSON = "KEY_JSON";
    private List<JSONObject> data;
    private ListView listview;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_content);
        listview= (ListView) findViewById(R.id.listView);
        Bundle bundle = getIntent().getExtras();
        loadDataFromBundle(bundle);
    }


    @Override
    protected void onStart() {
        super.onStart();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        listview.setAdapter(new FilmAdapter(this,data));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                JSONObject object = (JSONObject) adapter.getItemAtPosition(position);
                Intent intent = new Intent(CinemaContentActivity.this,FilmContentDetailActivity.class);
                intent.putExtra(FilmContentDetailActivity.KEY_OBJECT,object.toString());
                startActivity(intent);
            }
        });
    }

    private void loadDataFromBundle(Bundle bundle)
    {
        jsonArray=new JSONArray();
        try {
            jsonArray = new JSONArray(bundle.getString(KEY_JSON));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        data=new ArrayList<JSONObject>();
        for (int i=0;i<jsonArray.length();i++)
        {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.has("is_visible") && object.getBoolean("is_visible"))
                {
                    data.add(object);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
