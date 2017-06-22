package m.groups;

import android.app.DownloadManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        String value = dataSnapshot.getValue(String.class);
        //Log.i(TAG,"Value is: " + value);
        //Context context = getApplicationContext();
        //CharSequence text = "Hello toast!";
        //int duration = Toast.LENGTH_LONG;

        //Toast toast = Toast.makeText(context, value, duration);
        //toast.show();

        TextView post = (TextView) findViewById(R.id.post);
        post.setText(value);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.i(TAG,"Value is: " + value);
                //Context context = getApplicationContext();
                //CharSequence text = "Hello toast!";
                //int duration = Toast.LENGTH_LONG;

                //Toast toast = Toast.makeText(context, value, duration);
                //toast.show();

                TextView post = (TextView) findViewById(R.id.post);
                post.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

    }

    public void sendMessage(View v)
    {
        TextView t = (TextView) findViewById(R.id.editText);
        String message = String.valueOf(t.getText());
        //Context context = getApplicationContext();
        //CharSequence text = "Hello toast!";
        //int duration = Toast.LENGTH_LONG;

        //Toast toast = Toast.makeText(context, message, duration);
        //toast.show();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(message);


    }

}
