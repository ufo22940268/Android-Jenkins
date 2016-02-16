package com.xinpinget.android_jenkins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinpinget.android_jenkins.api.ApiService;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot;

import java.io.IOException;

import retrofit2.Response;

public class ConfigActivity extends AppCompatActivity {

    private EditText mServerAddrView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        mServerAddrView = (EditText) findViewById(R.id.server_name);

        Button mEmailSignInButton = (Button) findViewById(R.id.submit);

        mProgressView = findViewById(R.id.login_progress);
        mForm = findViewById(R.id.form);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });
    }

    public void submit(View view) {
//        String serverAddr = "http://jenkins.xinpinget.com/api/json";
//        String serverAddr = "http://jenkins.xinpinget.com/api/json";
//        new AsyncTask<String, Void, Response<ApiJsonRoot>>() {
//            @Override
//            protected Response<ApiJsonRoot> doInBackground(String... params) {
//                String serverAddr = params[0];
//                try {
//                    return ApiService.create(serverAddr).jenkins().list().execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Response<ApiJsonRoot> listResponse) {
//                super.onPostExecute(listResponse);
//                if (listResponse != null && listResponse.isSuccess()) {
//                    System.out.println("listResponse = " + listResponse);
//                } else {
//                    Snackbar.make(mForm, R.string.server_error, Snackbar.LENGTH_LONG).show();
//                }
//            }
//        }.execute(serverAddr);
    }
}

