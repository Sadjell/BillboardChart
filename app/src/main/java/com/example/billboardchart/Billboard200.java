package com.example.billboardchart;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

import java.io.IOException;

public class Billboard200 extends AppCompatActivity {

    private TextView textResult1, textResult2, textResult3, textResult4, textResult5;
    private TextView textResult6, textResult7, textResult8, textResult9, textResult10;
    private TextView textResult11, textResult12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard200);

        textResult1 = findViewById(R.id.BillboardTextAlbum);
        textResult2 = findViewById(R.id.BillboardTextArtist);
        textResult3 = findViewById(R.id.BillboardTextPeak);
        textResult4 = findViewById(R.id.BillboardTextWeeks);

        textResult5 = findViewById(R.id.BillboardTextAlbum1);
        textResult6 = findViewById(R.id.BillboardTextArtist1);
        textResult7 = findViewById(R.id.BillboardTextPeak1);
        textResult8 = findViewById(R.id.BillboardTextWeeks1);

        textResult9 = findViewById(R.id.BillboardTextAlbum2);
        textResult10 = findViewById(R.id.BillboardTextArtist2);
        textResult11 = findViewById(R.id.BillboardTextPeak2);
        textResult12 = findViewById(R.id.BillboardTextWeeks2);


        //API connection
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://billboard-api2.p.rapidapi.com/billboard-200?date=2022-03-01&range=1-5")
                .get()
                .addHeader("x-rapidapi-host", "billboard-api2.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "50a31e52e7msh2d536d59c13a5c1p1414b9jsn14b3f1f13394")
                .build();

        client.newCall(request).enqueue(new Callback() {

            //Parsing of the JSON file
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Billboard200.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                JSONObject obj1 = new JSONObject(myResponse);

                                JSONObject songsObject = obj1.getJSONObject("content");
                                JSONArray songsArray = songsObject.toJSONArray(songsObject.names());

                                textResult1.setText("Album: " +songsArray.getJSONObject(0).getString("album"));
                                textResult2.setText("Artist: " +songsArray.getJSONObject(0).getString("artist"));
                                textResult3.setText("Peak Position: " +songsArray.getJSONObject(0).getString("peak position"));
                                textResult4.setText("Weeks On Chart: " + songsArray.getJSONObject(0).getString("weeks on chart"));

                                textResult5.setText("Album: " +songsArray.getJSONObject(1).getString("album"));
                                textResult6.setText("Artist: " +songsArray.getJSONObject(1).getString("artist"));
                                textResult7.setText("Peak Position: " +songsArray.getJSONObject(1).getString("peak position"));
                                textResult8.setText("Weeks On Chart: " + songsArray.getJSONObject(1).getString("weeks on chart"));

                                textResult9.setText("Album: " +songsArray.getJSONObject(2).getString("album"));
                                textResult10.setText("Artist: " +songsArray.getJSONObject(2).getString("artist"));
                                textResult11.setText("Peak Position: " +songsArray.getJSONObject(2).getString("peak position"));
                                textResult12.setText("Weeks On Chart: " + songsArray.getJSONObject(2).getString("weeks on chart"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

//                            textResult.setText(myResponse);


                        }
                    });
                }
            }
        });
    }
}
