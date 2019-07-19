package br.com.digitalhouse.marvelapp.Core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.marvelapp.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputLayout nameCadastro;
    private TextInputLayout emailCadastro;
    private TextInputLayout passwordCadastro;
    private Button saveButton;
    SharedPreferences preferences;

    public CadastroActivity() {
        this.preferences = preferences;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initView();
        preferences = getSharedPreferences("APP_REGISTER", MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateAndGo(preferences);

            }
        });

    }

    private void validateAndGo(SharedPreferences preferences) {
        String email = emailCadastro.getEditText().getText().toString();
        String user = nameCadastro.getEditText().getText().toString();
        String password = passwordCadastro.getEditText().getText().toString();


        if (email.isEmpty()) {
            emailCadastro.setError(getString(R.string.invalid_email));
            return;
        }else if (!validateEmailFormat(email)){
            emailCadastro.setError(getString(R.string.invalid_email));
            return;
        }else {
            emailCadastro.setError(null);
        }

        if (user.isEmpty()) {
            nameCadastro.setError(getString(R.string.not_user));
            return;
        }else {
            nameCadastro.setError(null);
        }

        if (password.isEmpty()) {
            passwordCadastro.setError(getString(R.string.not_password));
            return;
        }else {
            passwordCadastro.setError(null);
        }

        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);

        preferences.edit().putString("EMAIL",email).commit();
        preferences.edit().putString("USER",user).commit();
        preferences.edit().putString("PASSWORD",password).commit();
        startActivity(intent);
        finish();
    }

    private boolean validateEmailFormat(final String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }


    public void initView()
    {

        nameCadastro = findViewById(R.id.nameCadastro);
        emailCadastro = findViewById(R.id.emailCadastro);
        passwordCadastro = findViewById(R.id.passwordCadastro);
        saveButton = findViewById(R.id.saveButton);

    }

}
