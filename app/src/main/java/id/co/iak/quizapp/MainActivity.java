package id.co.iak.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.model.UserModel;
import id.co.iak.quizapp.question.Question01Activity;

public class MainActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.btn_continue)
    Button btnContinue;
    @BindView(R.id.edt_name)
    TextView edtName;
    @BindView(R.id.edt_email)
    TextView edtEmail;
    @BindView(R.id.cb_eula)
    CheckBox cbEula;

    // Data
    private String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbEula.isChecked()) {
                    name = edtName.getText().toString();
                    email = edtEmail.getText().toString();
                    UserModel user = new UserModel(name, email, 0);
                    Toast.makeText(MainActivity.this, "Ready!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Question01Activity.class);
                    i.putExtra("biodata", user.toString());
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Please ensure your submission!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
