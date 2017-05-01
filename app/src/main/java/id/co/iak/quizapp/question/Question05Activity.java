package id.co.iak.quizapp.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.iak.quizapp.R;

public class Question05Activity extends AppCompatActivity {

    // Views
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05_question);
        ButterKnife.bind(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                QuestionModel question = new QuestionModel("", );
                Toast.makeText(getApplicationContext(), "Result!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Question05Activity.this, ResultActivity.class);
                i.putExtra("question05", "");
                startActivity(i);
                finish();
            }
        });
    }
}
