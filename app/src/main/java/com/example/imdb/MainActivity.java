package com.example.imdb;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdb.webservice_pojo.WebservicePojo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button btnSearch;
    EditText edtSearch;
    TextView txtdirectort;
    ImageView imgposter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch=(Button) findViewById(R.id.btnsearch);
        edtSearch=(EditText) findViewById(R.id.edtsearch);
        txtdirectort= (TextView) findViewById(R.id.txtdirectort);
        imgposter=(ImageView) findViewById(R.id.imgposter);


        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get("http://www.omdbapi.com/?t='" + edtSearch.getText().toString() + "'&apikey=70ad462a", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(MainActivity.this,"dsfsdfsdf",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Gson gson= new Gson();
                        WebservicePojo response=  gson.fromJson(responseString, WebservicePojo.class);
                        txtdirectort.setText(response.getDirector());
                        Picasso.get().load(response.getPoster()).into(imgposter);
                    }
                });
            }
        });



    }
}
