package fr.centrale.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class FilmContentDetailActivity extends AppCompatActivity {
    public static String KEY_OBJECT = "KEY_OBJECT";
    private TextView titre;
    private TextView titreOriginal;
    private TextView siteWeb;
    private TextView duree;
    private TextView distributeur;
    private TextView participants;
    private TextView realisateur;
    private TextView synopsis;
    private TextView annee;
    private TextView dateSortie;
    private TextView genre;
    private TextView categorie;
    private TextView pays;
    private LinearLayout llTitre;
    private LinearLayout llTitreOriginal;
    private LinearLayout llSiteWeb;
    private LinearLayout llDuree;
    private LinearLayout llDistributeur;
    private LinearLayout llParticipants;
    private LinearLayout llRealisateur;
    private LinearLayout llSynopsis;
    private LinearLayout llAnnee;
    private LinearLayout llDateSortie;
    private LinearLayout llGenre;
    private LinearLayout llCategorie;
    private LinearLayout llPays;
    private ExpandableHeightGridView gridView;
    private JSONObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_content_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindToLayout();

        object = new JSONObject();
        try {
            object = new JSONObject(getIntent().getExtras().getString(KEY_OBJECT));
            if (object.has("medias"))
            {
                gridView.setAdapter(new ImageAdapter(this,object.getJSONArray("medias")));
                gridView.setExpanded(true);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        onImageSelected(position);
                    }
                });
            }
            fillFields(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void bindToLayout() {
        titre= (TextView) findViewById(R.id.titre);
        titreOriginal= (TextView) findViewById(R.id.titreOriginal);
        siteWeb= (TextView) findViewById(R.id.siteWeb);
        duree= (TextView) findViewById(R.id.duree);
        distributeur= (TextView) findViewById(R.id.distributeur);
        participants= (TextView) findViewById(R.id.participants);
        realisateur= (TextView) findViewById(R.id.realisateur);
        synopsis= (TextView) findViewById(R.id.synopsis);
        annee= (TextView) findViewById(R.id.annee);
        dateSortie= (TextView) findViewById(R.id.dateSortie);
        genre= (TextView) findViewById(R.id.genre);
        categorie= (TextView) findViewById(R.id.categorie);
        pays= (TextView) findViewById(R.id.pays);
        llTitre= (LinearLayout) findViewById(R.id.llTitre);
        llTitreOriginal= (LinearLayout) findViewById(R.id.llTitreOriginal);
        llSiteWeb= (LinearLayout) findViewById(R.id.llSiteWeb);
        llDuree= (LinearLayout) findViewById(R.id.llDuree);
        llDistributeur= (LinearLayout) findViewById(R.id.llDistributeur);
        llParticipants= (LinearLayout) findViewById(R.id.llParticipants);
        llRealisateur= (LinearLayout) findViewById(R.id.llRealisateur);
        llSynopsis= (LinearLayout) findViewById(R.id.llSynopsis);
        llAnnee= (LinearLayout) findViewById(R.id.llAnnee);
        llDateSortie= (LinearLayout) findViewById(R.id.llDateSortie);
        llGenre= (LinearLayout) findViewById(R.id.llGenre);
        llCategorie= (LinearLayout) findViewById(R.id.llCategorie);
        llPays= (LinearLayout) findViewById(R.id.llPays);
        gridView=(ExpandableHeightGridView) findViewById(R.id.gridview);
    }

    private void fillFields(JSONObject object)throws JSONException {
        if (object.has("titre"))
        {
            titre.setText(object.getString("titre"));
        }
        else
        {
            llTitre.setVisibility(View.GONE);
        }
        if (object.has("titre_ori"))
        {
            titreOriginal.setText(object.getString("titre_ori"));
        }
        else
        {
            llTitreOriginal.setVisibility(View.GONE);
        }
        if (object.has("web"))
        {
            siteWeb.setText(object.getString("web"));
        }
        else
        {
            llSiteWeb.setVisibility(View.GONE);
        }
        if (object.has("duree"))
        {
            duree.setText(object.getString("duree"));
        }
        else
        {
            llDuree.setVisibility(View.GONE);
        }
        if (object.has("distributeur"))
        {
            distributeur.setText(object.getString("distributeur"));
        }
        else
        {
            llDistributeur.setVisibility(View.GONE);
        }
        if (object.has("participants"))
        {
            participants.setText(object.getString("participants"));
        }
        else
        {
            llParticipants.setVisibility(View.GONE);
        }
        if (object.has("realisateur"))
        {
            realisateur.setText(object.getString("realisateur"));
        }
        else
        {
            llRealisateur.setVisibility(View.GONE);
        }
        if (object.has("synopsis"))
        {
            synopsis.setText(object.getString("synopsis").trim());
        }
        else
        {
            llSynopsis.setVisibility(View.GONE);
        }
        if (object.has("annee"))
        {
            annee.setText(object.getString("annee"));
        }
        else
        {
            llAnnee.setVisibility(View.GONE);
        }
        if (object.has("dateSortie"))
        {
            dateSortie.setText(object.getString("dateSortie"));
        }
        else
        {
            llDateSortie.setVisibility(View.GONE);
        }
        if (object.has("genre"))
        {
            genre.setText(object.getString("genre"));
        }
        else
        {
            llGenre.setVisibility(View.GONE);
        }
        if (object.has("categorie"))
        {
            categorie.setText(object.getString("categorie"));
        }
        else
        {
            llCategorie.setVisibility(View.GONE);
        }
        if (object.has("pays"))
        {
            pays.setText(object.getString("pays"));
        }
        else
        {
            llPays.setVisibility(View.GONE);
        }
    }

    public void onImageSelected(int position) {
        Intent intent = new Intent(FilmContentDetailActivity.this,GalleryActivity.class);
        intent.putExtra(GalleryActivity.IMAGE_POSITION,position);
        try {
            intent.putExtra(GalleryActivity.ITEMS, object.getJSONArray("medias").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
