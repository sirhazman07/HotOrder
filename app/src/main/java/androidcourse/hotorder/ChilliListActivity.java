package androidcourse.hotorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ChilliListActivity extends AppCompatActivity {

    //Text Data, using and in-memory String array..
    private Chilli[] mChillisCollection = new Chilli[]{
            new Chilli("Aji Rocoto","Capsicum pubescens", "chilli_rocoto_large", "some description goes here",5,4),
            new Chilli("Aji Mirasol","Capsicum pubescens", "chilli_mirasol_large", "also another description", 4,3),
            new Chilli("Aji Panca","Capsicum pubescens", "chilli_panca_large", "One more time here",4 , 3)

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilli_list);
        //Get the ListView form our xml Layout (Chilli Row!!)
        ListView mListViewChillies = (ListView)findViewById(R.id.ListViewChilli);

        //IMPORTANT: Create an Adapter to bind our data to a row template (View), and populate
        // the ListView with the row template

        ChillisAdapter adapter = new ChillisAdapter(this, R.layout.chilli_row, mChillisCollection);
        //Set the ListViews Adapter
        mListViewChillies.setAdapter(adapter);

        mListViewChillies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // First lets get the String of the item using the position passed to this callback
                String chilliName = mChillisCollection[position].getName();
                // NOTE: 0 index based
                // When an item (row) is clicked, lets display a Toast
                Toast.makeText(getApplicationContext(), chilliName, Toast.LENGTH_SHORT).show();
                // Context -> rather than using MainActivity.this (to refer to the context/activity)
                // we can use getApplicationContext()
            }
        });
    }
}
