package sg.edu.rp.c346.pushuppower;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddNewRecord extends AppCompatActivity {

    TextView timer;
    Button start;
    Button total;
    Button end;

    int totalPush = 1;
    String date, time;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference recordListRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        timer = (TextView) findViewById(R.id.timer);
        start = (Button) findViewById(R.id.start);
        total = (Button) findViewById(R.id.total);
        end = (Button) findViewById(R.id.end);

        firebaseDatabase = FirebaseDatabase.getInstance();
        recordListRef = firebaseDatabase.getReference("/Set");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText(millisUntilFinished / 1000 + "");
                    }

                    public void onFinish() {
                        timer.setText("done!");

                    }
                }.start();
                start.setVisibility(View.GONE);
                total.setVisibility(View.VISIBLE);
            }
        });

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPush++;
                total.setText(totalPush + "");
                if (timer.getText() == "done!") {
                    totalPush = totalPush - 1;
                    total.setVisibility(View.GONE);
                    end.setVisibility(View.VISIBLE);
                }
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder simpleBuild = new AlertDialog.Builder(AddNewRecord.this);

                String score = totalPush + "";
                Calendar datetime = Calendar.getInstance();
                int dayOfMonth = datetime.get(Calendar.DAY_OF_MONTH);
                int monthOfYear = datetime.get(Calendar.MONTH) + 1;
                int year = datetime.get(Calendar.YEAR);
                int hour = datetime.get(Calendar.HOUR_OF_DAY);
                int minute = datetime.get(Calendar.MINUTE);
                date = dayOfMonth + "/" + monthOfYear + "/" + year + ", ";
                time = String.format("%02d:%02d", hour, minute);

                simpleBuild.setTitle("Total Score");
                simpleBuild.setMessage("Your score is " + score);
                simpleBuild.setCancelable(false);

                simpleBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        Record record = new Record(totalPush + "", date, time);
                        recordListRef.push().setValue(record);

                        startActivity(intent);
                        Toast toast = Toast.makeText(getBaseContext(), "Set Complete", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                AlertDialog dialog = simpleBuild.create();
                dialog.show();
            }
        });
    }
}
