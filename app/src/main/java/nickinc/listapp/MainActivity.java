package nickinc.listapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayList<String> myList = new ArrayList<>();
        myList.add("Octopus");
        myList.add("Crab");
        myList.add("Platypus");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(itemClickListener);
    }//onCreate

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            intent.putExtra("Location", position);
            startActivity(intent);

        }
    };
}
