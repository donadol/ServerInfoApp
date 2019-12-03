package co.edu.javeriana.whoisapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.edu.javeriana.whoisapp.R;

public class MainActivity extends AppCompatActivity {
    private Button btn_search;
    private Button btn_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = findViewById(R.id.btn_search);
        btn_previous = findViewById(R.id.btn_previous);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //algo
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //algo
            }
        });
    }
    public void consumeRESTVolley(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://maps.googleapis.com/maps/api/directions/json?";

        StringRequest req = new StringRequest(DownloadManager.Request.Method, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        String data = (String)response;
                        parseJSON(data);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG", "Error handling rest invocation"+error.getCause());
                    } }
        );
        queue.add(req);
    }

    private void parseJSON(String data) {
        ArrayList<LatLng> result = new ArrayList<>();
        String distance="";
        Double d=0.0;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray steps = jsonObject.getJSONArray("routes");
            steps = steps.getJSONObject(0).getJSONArray("legs");
            d = steps.getJSONObject(0).getJSONObject("distance").getDouble("value");
            steps = steps.getJSONObject(0).getJSONArray("steps");

            result.add(new LatLng(((JSONObject)((JSONObject)steps.get(0)).get("start_location")).getDouble("lat"), ((JSONObject)((JSONObject)steps.get(0)).get("start_location")).getDouble("lng")));
            for(int i=0;i<steps.length();++i) {
                JSONObject punto = steps.getJSONObject(i);
                result.add(new LatLng(((JSONObject)punto.get("end_location")).getDouble("lat"), ((JSONObject)punto.get("end_location")).getDouble("lng")));
            }

            distance = "La distancia es: " + d/1000.0 + " Km a su objetivo";
            //mMap.moveCamera(CameraUpdateFactory.zoomTo((int)(15*d)));

            //Toast.makeText(getApplicationContext(),  distance ,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
