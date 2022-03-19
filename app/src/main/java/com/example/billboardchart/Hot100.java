//Rest API Connection Information from: https://codinginflow.com/tutorials/android/okhttp-simple-get-request

package com.example.billboardchart;

import android.os.AsyncTask;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

public class Hot100 extends AppCompatActivity {
    private TextView textResult1, textResult2, textResult3, textResult4, textResult5;
    private TextView textResult6, textResult7, textResult8, textResult9, textResult10;
    private TextView textResult11, textResult12, textResult13, textResult14, textResult15;
//    private TextView textResult16, textResult17, textResult18, textResult19, textResult20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot100);

        textResult1 = findViewById(R.id.hotTextTitle);
        textResult2 = findViewById(R.id.hotTextArtist);
        textResult3 = findViewById(R.id.hotTextRank);
        textResult4 = findViewById(R.id.hotTextPeak);
        textResult5 = findViewById(R.id.hotTextWeeks);

        textResult6 = findViewById(R.id.hotTextTitle1);
        textResult7 = findViewById(R.id.hotTextArtist1);
        textResult8 = findViewById(R.id.hotTextRank1);
        textResult9 = findViewById(R.id.hotTextPeak1);
        textResult10 = findViewById(R.id.hotTextWeeks1);

        textResult11 = findViewById(R.id.hotTextTitle2);
        textResult12 = findViewById(R.id.hotTextArtist2);
        textResult13 = findViewById(R.id.hotTextRank2);
        textResult14 = findViewById(R.id.hotTextPeak2);
        textResult15 = findViewById(R.id.hotTextWeeks2);


        OkHttpClient client = new OkHttpClient();

//        String url = "https://billboard-api2.p.rapidapi.com/hot-100?range=1-5&date=2022-03-01";

        Request request = new Request.Builder()
                .url("https://billboard-api2.p.rapidapi.com/hot-100?range=1-5&date=2022-03-01")
                .get()
                .addHeader("x-rapidapi-host", "billboard-api2.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "50a31e52e7msh2d536d59c13a5c1p1414b9jsn14b3f1f13394")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Hot100.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj1 = new JSONObject(myResponse);

                                JSONObject songsObject = obj1.getJSONObject("content");
                                JSONArray songsArray = songsObject.toJSONArray(songsObject.names());

                                textResult1.setText("Song Title: " +songsArray.getJSONObject(0).getString("title"));
                                textResult2.setText("Artist: " +songsArray.getJSONObject(0).getString("artist"));
                                textResult3.setText("Rank: " +songsArray.getJSONObject(0).getString("rank"));
                                textResult4.setText("Peak Position: " +songsArray.getJSONObject(0).getString("peak position"));
                                textResult5.setText("Weeks On Chart: " + songsArray.getJSONObject(0).getString("weeks on chart"));

                                textResult6.setText("Song Title: " +songsArray.getJSONObject(1).getString("title"));
                                textResult7.setText("Artist: "+songsArray.getJSONObject(1).getString("artist"));
                                textResult8.setText("Rank: " +songsArray.getJSONObject(1).getString("rank"));
                                textResult9.setText("Peak Position: " +songsArray.getJSONObject(1).getString("peak position"));
                                textResult10.setText("Weeks On Chart: " +songsArray.getJSONObject(1).getString("weeks on chart"));

                                textResult11.setText("Song Title: " +songsArray.getJSONObject(2).getString("title"));
                                textResult12.setText("Artist: "+songsArray.getJSONObject(2).getString("artist"));
                                textResult13.setText("Rank: " +songsArray.getJSONObject(2).getString("rank"));
                                textResult14.setText("Peak Position: " +songsArray.getJSONObject(2).getString("peak position"));
                                textResult15.setText("Weeks On Chart: " +songsArray.getJSONObject(2).getString("weeks on chart"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
    }

}