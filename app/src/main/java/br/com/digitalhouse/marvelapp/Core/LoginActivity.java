package br.com.digitalhouse.marvelapp.Core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.digitalhouse.marvelapp.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailLogin;
    private TextInputLayout passwordLogin;
    private Button loginButton;
    private TextView createAccount;


    private String passwordbundle;
    private String userbundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);
        createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));

            }
        });

     loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            validateAndGo();
              startActivity(new Intent(LoginActivity.this, HQsActivity.class));
          }
      });

    }
    private void validateAndGo() {
        String user = emailLogin.getEditText().getText().toString();
        String password = passwordLogin.getEditText().getText().toString();

        if (user.isEmpty()) {
            emailLogin.setError(getString(R.string.not_user));
            return;
        } else if (userbundle != null && !user.equals(userbundle)) {
            emailLogin.setError(getString(R.string.not_user));
            return;
        } else {
            emailLogin.setError(null);
        }

        if (password.isEmpty()) {
            passwordLogin.setError(getString(R.string.not_password));
            return;
        } else if (passwordbundle != null && !password.equals(passwordbundle)) {
            passwordLogin.setError(getString(R.string.not_password));
            return;
        } else {
            passwordLogin.setError(null);
        }

        if (userbundle == null || passwordbundle == null) {
            Snackbar.make(emailLogin, "Usuário não cadastrado :(", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }


        Intent intent = new Intent(LoginActivity.this, HQsActivity.class);
        startActivity(intent);
    }

    private void preferences() {
        final SharedPreferences preferences = getSharedPreferences("APP_REGISTER", MODE_PRIVATE);

        passwordbundle = preferences.getString("PASSWORD", "");
        userbundle = preferences.getString("USER", "");

        emailLogin.getEditText().setText(preferences.getString("USER", ""));

    }

}
