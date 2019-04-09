package br.com.lrdsilva.tvmazeapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.lrdsilva.tvmazeapp.R;

public class ActivityNoConnection extends AppCompatActivity {

    Button button;
    Context context = this;
        @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_connection);
        button = (Button) findViewById(R.id.retryCon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ActivityAllShows.class);
                startActivity(intent);
           }
        });
    }
}
