package id.co.iak.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.model.UserModel;
import id.co.iak.quizapp.question.Question01Activity;
import id.co.iak.quizapp.question.ResultActivity;

public class MainActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.btn_continue)
    Button btnContinue;
    @BindView(R.id.edt_name)
    TextView edtName;
    @BindView(R.id.edt_email)
    TextView edtEmail;

    // Data
    private String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        name = edtName.getText().toString();
        email = edtEmail.getText().toString();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel user = new UserModel(name, email);
                Toast.makeText(getApplicationContext(), "Ready!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Question01Activity.class);
//                Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                i.putExtra("biodata", user.toString());
                startActivity(i);
                finish();
            }
        });
    }
}
