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

    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot100);


        textResult = findViewById(R.id.hotTextView);


        OkHttpClient client = new OkHttpClient();

        String url = "https://billboard-api2.p.rapidapi.com/hot-100?range=1-10&date=2019-05-11";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "billboard-api2.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dd7ae0f3bemsh4ea9649509e8919p196695jsn723a3dbe9ad7")
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
                            textResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
//
//    class FetchHot extends AsyncTask<Integer, Void, String> {
//
//        @Override
//        protected String doInBackground(Integer... strings) {
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            String result = new String();
//            try {
//                URL url = new URL("https://billboard-api2.p.rapidapi.com/hot-100?range=1-10&date=2019-05-11");
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.setRequestProperty("x-rapidapi-key", "dd7ae0f3bemsh4ea9649509e8919p196695jsn723a3dbe9ad7");
//                urlConnection.connect();
//
//                InputStream in = urlConnection.getInputStream();
//                if (in == null) {
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(in));
//                result = getStringFromBuffer(reader);
//            } catch (Exception e) {
//
//
//            } finally {
//
//            }
//
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String res){
//            if (res != null){
//                Log.d(LOG_TAG, "result is null");
//            }
//
//        }
//
//
//        private String getStringFromBuffer(BufferedReader reader) {
//            StringBuffer buffer = new StringBuffer();
//            String line;
//
//            if(reader != null){
//                try{
//                    while((line = reader.readLine()) != null){
//                        buffer.append(line + "\n");
//                    }
//                    reader.close();
//                    return buffer.toString();
//                } catch (Exception e){
//                    Log.e("Hot100", "Error" + e.getMessage());
//                    return null;
//                }finally {
//
//                }
//            }
//            return null;
//        }
    }
