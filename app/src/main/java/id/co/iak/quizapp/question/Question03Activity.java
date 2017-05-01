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

public class Question03Activity extends AppCompatActivity {

    // Views
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03_question);
        ButterKnife.bind(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                QuestionModel question = new QuestionModel("", );
                Toast.makeText(getApplicationContext(), "Question04", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Question03Activity.this, Question04Activity.class);
                i.putExtra("question03", "");
                startActivity(i);
                finish();
            }
        });
    }
}
