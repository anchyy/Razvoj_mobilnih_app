package strenja.foodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class Detalji extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji);

        Intent intent = getIntent();
        Recept recept = (Recept) intent.getSerializableExtra(
                "recept");


        TextView mainName = findViewById(R.id.mainName);
        mainName.setText(String.valueOf(recept.getMainName()));


        TextView descriptName = findViewById(R.id.descriptName);
        descriptName.setText(String.valueOf(recept.getDescriptName()));

        TextView rating = findViewById(R.id.rating);
        rating.setText(String.valueOf(recept.getRating()));

        Button nazad = findViewById(R.id.nazad);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        new DownloadImageTask((ImageView) findViewById(R.id.urlSlika))
                .execute(recept.getUrlSlika());
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}


