package sg.edu.rp.c346.pushuppower;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int index;

    FirebaseDatabase database;
    DatabaseReference myRef;

    ListView lvRecords;
    ArrayList<Record> recordsList;
    CustomAdapter caRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("/Set");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("MainActivity", "onChildAdded()");
                Record record = dataSnapshot.getValue(Record.class);
                if (record != null) {
                    record.setId(dataSnapshot.getKey());

                    recordsList.add(record);
                    caRecord.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvRecords = (ListView)findViewById(R.id.record);
        recordsList = new ArrayList<Record>();
        caRecord = new CustomAdapter(this, R.layout.activity_row, recordsList);
        lvRecords.setAdapter(caRecord);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.AddNewRecord) {
            Intent addNew = new Intent(getBaseContext(),AddNewRecord.class);
            startActivity(addNew);
            return true;
        } else if (id == R.id.GraphOfPower) {
            Intent graph = new Intent(getBaseContext(), GraphOfPower.class);
            startActivity(graph);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
