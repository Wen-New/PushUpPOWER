package sg.edu.rp.c346.pushuppower;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Record> record;

    public CustomAdapter(Context context, int resource, ArrayList<Record> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        record = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvScore = (TextView) rowView.findViewById(R.id.score);
        TextView tvDateTime = (TextView) rowView.findViewById(R.id.datetime);

        Record currentItem = record.get(position);

        if (currentItem == null) {
            tvScore.setText("");
            tvDateTime.setText("");
        }
        else {
            String title = "Score: " + currentItem.getScore();
            String datetime = "Date and Time: " + currentItem.getDate() + " " + currentItem.getTime() + " h";
            tvScore.setText(title);
            tvDateTime.setText(datetime);
        }

        return rowView;
    }
}
