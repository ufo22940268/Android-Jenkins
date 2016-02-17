package com.xinpinget.android_jenkins;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinpinget.android_jenkins.util.JenkinsManager;

import java.util.regex.Pattern;

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

        mProgressView = findViewById(R.id.login_progress);
        mForm = findViewById(R.id.coord);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });
    }

    public void submit(View view) {
        String addr = mServerAddrView.getText().toString();
        if (checkServerAddr(addr)) {
            new JenkinsManager(this).saveServerAddr(addr);
            startActivity(new Intent(this, ProjectActivity.class));
        } else {
            snack(R.string.address_invalid);
        }
    }

    private boolean checkServerAddr(String addr) {
        return Pattern.matches("^(http|https).*", addr);
    }

    private void snack(int str) {
        Snackbar.make(findViewById(R.id.coord), str, Snackbar.LENGTH_SHORT).show();
    }
}

