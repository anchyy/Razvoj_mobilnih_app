package strenja.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickInterface {
    private Button mButton;
    private AdapterListe adapterListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
         adapterListe = new AdapterListe(this);
        adapterListe.setClickListener(this);

        recyclerView.setAdapter(adapterListe);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                TextView enterText = findViewById(R.id.editText);
                String message = enterText.getText().toString();

                RESTTask restTask = new RESTTask();
                restTask.execute("https://api.yummly.com/v1/api/recipes?_app_id=dc0de3a6&_app_key=e18e7e77ab27a1004c0d89ab885b5507&q="+message);

            }
        });

    }


    private class RESTTask extends AsyncTask<String, Void, List<Recept>> {

        @Override
        protected List<Recept> doInBackground(String... strings) {


            String adresa = strings[0];

            try {
                Odgovor odgovor = new Odgovor();
                URL url = new URL(adresa);

                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader =
                        new InputStreamReader(connection.getInputStream());

                BufferedReader reader =
                        new BufferedReader(streamReader);

                StringBuilder sb= new StringBuilder();
                String line;
                while((line = reader.readLine())!=null)
                {
                    sb.append(line);
                }
                //Odgovor odgovor = new Gson().
                //  fromJson(reader,Odgovor.class);
                reader.close();
                streamReader.close();
                String jsonString= sb.toString();
                JSONObject jsonObject= new JSONObject(jsonString);
                JSONArray recepti= (JSONArray)jsonObject.get("matches");
                List<Recept> listaRecepti = new ArrayList<Recept>();
                for (int i = 0; i < recepti.length(); i++) {
                    try {
                        Recept recept = new Recept();
                        JSONObject jsonObj = recepti.getJSONObject(i);
                        recept.setId(jsonObj.get("id").toString());

                        JSONObject image= new JSONObject(jsonObj.get("imageUrlsBySize").toString());

                         recept.setUrlSlika(image.get("90").toString());
                         recept.setMainName(jsonObj.get("recipeName").toString());

                         recept.setRating(jsonObj.get("rating").toString());

                        JSONArray ingredients= (JSONArray)jsonObj.get("ingredients");
                        String ing="";
                        for(int j=0;j<ingredients.length();j++)
                        {
                            ing+= ingredients.get(j).toString()+"\n";
                        }
                        recept.setDescriptName(ing);


                        listaRecepti.add(recept);

                    }
                    catch (JSONException e) {

                    }

                }
                odgovor.setRecepti(listaRecepti);

                return odgovor.getRecepti();

            }
            catch(Exception e){
                Log.d("GREŠKA", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(List<Recept> recepti) {

            adapterListe.setRecepti(recepti);
            adapterListe.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Recept recept = adapterListe.getRecept(position);
        Log.d("Odabrana šifra",String.valueOf(recept.getId()));


        Intent intent = new Intent(
                this,Detalji.class);
        intent.putExtra("recept",recept);
        startActivity(intent);


    }
}

