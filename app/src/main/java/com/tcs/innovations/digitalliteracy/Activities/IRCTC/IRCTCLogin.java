package com.tcs.innovations.digitalliteracy.Activities.IRCTC;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.tcs.innovations.digitalliteracy.R;

public class IRCTCLogin extends AppCompatActivity {

    SubsamplingScaleImageView imageView;
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctc_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (SubsamplingScaleImageView) findViewById(R.id.loginImage);
        imageView.setImage(ImageSource.resource(R.drawable.irctc_login));

        final Dialog d = new Dialog(this, R.style.CustomDialogTheme);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                d.setContentView(R.layout.irctc_login_popup);
                d.show();

                final EditText user_id_input = (EditText) d.findViewById(R.id.user_id_input);
                final EditText password_input = (EditText) d.findViewById(R.id.password_input);
                final EditText captcha_input = (EditText) d.findViewById(R.id.captcha_input);

                Button login_btn = (Button) d.findViewById(R.id.login_button);
                ImageView captcha_reload = (ImageView) d.findViewById(R.id.captcha_reload);
                final ImageView captcha = (ImageView) d.findViewById(R.id.captcha);

                captcha_reload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        flag++;
                        if (flag == 2)
                            captcha.setImageResource(R.drawable.captcha2);
                        else if (flag == 3)
                            captcha.setImageResource(R.drawable.captcha3);
                        else if (flag == 4)
                            captcha.setImageResource(R.drawable.captcha4);
                        else {
                            captcha.setImageResource(R.drawable.captcha1);
                            flag = 1;
                        }

                    }
                });

                login_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        String username = String.valueOf(user_id_input.getText());
                        String password = String.valueOf(password_input.getText());
                        String captcha = String.valueOf(captcha_input.getText());

                        if (username.equals("") || password.equals("") || captcha.equals("")) {
                            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_LONG).show();
                        } else {
                            d.dismiss();
                            Intent intent = new Intent(IRCTCLogin.this, IRCTCLoginSuccess.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
