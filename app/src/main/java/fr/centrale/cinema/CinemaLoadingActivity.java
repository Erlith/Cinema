package fr.centrale.cinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CinemaLoadingActivity extends AppCompatActivity {

    private LinearLayout erreurLayout;
    private Button buttonReesayer;
    private ProgressBar bar;
    private static String URL="http://voyage3.corellis.eu/filmsSeances.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_loading);
        bar= (ProgressBar) findViewById(R.id.progressBar);
        erreurLayout = (LinearLayout) findViewById(R.id.erreurLayout);
        buttonReesayer = (Button) findViewById(R.id.buttonReesayer);
        buttonReesayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDataLoad();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        launchDataLoad();
    }

    private void launchDataLoad()
    {
        DownloadDataTask task = new DownloadDataTask();
        task.execute();
    }

    private class DownloadDataTask extends AsyncTask<Void, Integer, String>
    {
        @Override
        protected void onPreExecute()
        {
            bar.setProgress(0);
            bar.setVisibility(View.VISIBLE);
            erreurLayout.setVisibility(View.GONE);

        }
        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            // Mise à jour de la ProgressBar
            bar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Void... arg0) {
            HttpURLConnection urlConnection = null;
            URL url;
            int dataSize;
            int currentDataLengthRead = 0;
            InputStream is=null;
            try {

                String jsonString;
                url = new URL(URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("HEAD");
                dataSize = urlConnection.getContentLength();
                urlConnection.disconnect();
                urlConnection = (HttpURLConnection) url.openConnection();
                is = new BufferedInputStream(urlConnection.getInputStream());
                byte[] buffer = new byte[256];
                int readCount;
                ByteArrayOutputStream stringStream = new ByteArrayOutputStream();
                while ((readCount = is.read(buffer)) != -1) {
                    currentDataLengthRead += readCount;
                    stringStream.write(buffer, 0, readCount);
                    publishProgress((int) ((currentDataLengthRead * 100) / dataSize));
                }
                jsonString = new String(stringStream.toByteArray());
                stringStream.close();

                return jsonString;
            }
            catch (Exception e) {
                return null;
            } finally{
                if (is!=null)
                {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (urlConnection!=null)
                {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result==null)
            {
                erreurLayout.setVisibility(View.VISIBLE);
                bar.setVisibility(View.GONE);
            }
            else {
                Intent intent = new Intent(CinemaLoadingActivity.this,CinemaContentActivity.class);
                intent.putExtra(CinemaContentActivity.KEY_JSON,result);
                startActivity(intent);
                finish();
            }
        }
    }
}
