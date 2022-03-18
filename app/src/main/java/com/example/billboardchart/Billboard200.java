package com.example.billboardchart;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import okhttp3.*;

import java.io.IOException;

public class Billboard200 extends AppCompatActivity {

    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard200);


        textResult = findViewById(R.id.billboard200text);


        OkHttpClient client = new OkHttpClient();

        String url = "https://billboard-api2.p.rapidapi.com/billboard-200?date=2019-05-11&range=1-10";

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

                    Billboard200.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }

}