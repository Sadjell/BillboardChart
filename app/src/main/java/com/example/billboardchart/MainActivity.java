package com.example.billboardchart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   //method to connect the elements in the menu to the layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //method to register selected item and what happens when they are selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.settings:
                View view = this.getWindow().getDecorView();
                view.setBackgroundResource(R.color.purple_200);
                break;
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("API used: Billboard. \n Developers: Caprice and Sadjell");
                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Check out our app!");
                intent.putExtra(Intent.EXTRA_TEXT, "Our app link here");
                startActivity(Intent.createChooser(intent, "Share via" ));
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);
    }

    //method that specify which activity comes one depending on the spinner's selection
    public void validate(View view) {
        Spinner spin = (Spinner) findViewById(R.id.category);
        String chosenValue = spin.getSelectedItem().toString();

        if (chosenValue.equals("Hot 100")) {
            Intent intent = new Intent(MainActivity.this, Hot100.class);
            startActivity(intent);

        } else if (chosenValue.equals("Billboard 200")) {
            Intent intent = new Intent(MainActivity.this, Billboard200.class);
            startActivity(intent);

        } else if (chosenValue.equals("Artist 100")) {
            Intent intent = new Intent(MainActivity.this, Artist100.class);
            startActivity(intent);
        }
    }

    public void onClick(View view) {
        validate(view);

    }

}
