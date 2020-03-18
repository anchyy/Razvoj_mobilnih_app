package strenja.filmapp2.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import strenja.filmapp2.R;
import strenja.filmapp2.viewmodel.FilmViewModel;

import static android.R.layout.simple_spinner_item;

public class CUDFragment extends Fragment {

    static final int SLIKANJE =1;

    private String trenutnaPutanjaSlike;

    @BindView(R.id.naslov)
    EditText naslov;
    @BindView(R.id.redatelj)
    EditText redatelj;
    @BindView(R.id.datumIzlaska)
    TextView datumIzlaskaFilma;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    @BindView(R.id.spinner)
    Spinner dropDownMenu;
    @BindView(R.id.slikaFilma)
    ImageView slika;
    @BindView(R.id.noviFilm)
    Button noviFilm;
    @BindView(R.id.uslikaj)
    Button uslikaj;
    @BindView(R.id.promjenaFilma)
    Button promjenaFilma;
    @BindView(R.id.obrisiFilm)
    Button obrisiFilm;

    FilmViewModel modelFilm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this, view);
        modelFilm = ((MainActivity) getActivity()).getModel();


        //date time picker
        datumIzlaskaFilma = (TextView) view.findViewById(R.id.datumIzlaska);

        if(modelFilm.getFilm().getDatumIzlaska()!=null) {
            datumIzlaskaFilma.setText(modelFilm.getFilm().getDatumIzlaska());
        }
        datumIzlaskaFilma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow();
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "." +  month + "." + year;
                datumIzlaskaFilma.setText(date);
            }
        };

        if (modelFilm.getFilm().getId() == 0) {
            definirajNoviFilm();
            return view;
        }
        definirajPromjenaBrisanjeFilm();

        return view;
    }

    private void definirajSpinnerDetalja(){


        List<String> typeList=new ArrayList<>();
        typeList.add("komedija");
        typeList.add("drama");
        typeList.add("triler");
        typeList.add("akcijski");
        typeList.add("povijesni");
        typeList.add("avanturisticki");
        typeList.add("horor");
        typeList.add("obiteljski");
        typeList.add("sci-fi");
        typeList.add("dokumentarni");


        String zanrFilm = modelFilm.getFilm().getZanr();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), simple_spinner_item,typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownMenu.setAdapter(adapter);

        if (zanrFilm != null) {
            int spinnerPosition = adapter.getPosition(zanrFilm);
            dropDownMenu.setSelection(spinnerPosition);
        }

        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected=adapterView.getItemAtPosition(i).toString();
                modelFilm.getFilm().setZanr(itemSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void definirajPromjenaBrisanjeFilm() {
        noviFilm.setVisibility(View.GONE);
        naslov.setText(modelFilm.getFilm().getNaslov());
        redatelj.setText(modelFilm.getFilm().getRedatelj());

        definirajSpinnerDetalja();

        uslikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uslikaj();
            }
        });

        promjenaFilma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promjenaFilma();
            }
        });

        obrisiFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisiFilm();
            }
        });



        if(modelFilm.getFilm().getPutanjaSlika()==null){
            Picasso.get().load(R.drawable.movie_icon).fit().centerCrop().into(slika);
            return;
        }
        Picasso.get().load(modelFilm.getFilm().getPutanjaSlika()).fit().centerCrop().into(slika);

    }

    private void promjenaFilma() {
        modelFilm.getFilm().setNaslov(naslov.getText().toString());
        modelFilm.getFilm().setRedatelj(redatelj.getText().toString());
        modelFilm.getFilm().setDatumIzlaska(datumIzlaskaFilma.getText().toString());
        modelFilm.promjeniFilm();
        nazad();
    }

    private void definirajNoviFilm() {
        promjenaFilma.setVisibility(View.GONE);
        obrisiFilm.setVisibility(View.GONE);
        uslikaj.setVisibility(View.GONE);

        definirajSpinnerDetalja();
        noviFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noviFilm();
            }
        });
    }

    private void noviFilm() {
        modelFilm.getFilm().setNaslov(naslov.getText().toString());
        modelFilm.getFilm().setRedatelj(redatelj.getText().toString());
        modelFilm.getFilm().setDatumIzlaska(datumIzlaskaFilma.getText().toString());
        modelFilm.dodajNoviFilm();
        nazad();
    }

    private void obrisiFilm() {
        modelFilm.getFilm().setNaslov(naslov.getText().toString());
        modelFilm.getFilm().setRedatelj(redatelj.getText().toString());
        modelFilm.getFilm().setDatumIzlaska(datumIzlaskaFilma.getText().toString());
        modelFilm.obrisiOsobu();
        nazad();
    }

    @OnClick(R.id.nazad)
    public void nazad() {
        ((MainActivity) getActivity()).read();
    }

    private void uslikaj() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;

        }
        // Create the File where the photo should go
        File slika = null;
        try {
            slika = kreirajDatotekuSlike();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        if (slika == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        Uri slikaURI = FileProvider.getUriForFile(getActivity(),
                "strenja.filmapp2.provider",
                slika);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, slikaURI);
        startActivityForResult(takePictureIntent, SLIKANJE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SLIKANJE && resultCode == Activity.RESULT_OK) {

            modelFilm.getFilm().setPutanjaSlika("file://" + trenutnaPutanjaSlike);
            modelFilm.promjeniFilm();
            Picasso.get().load(modelFilm.getFilm().getPutanjaSlika()).into(slika);

        }
    }




    private File kreirajDatotekuSlike() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imeSlike = "OSOBA_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imeSlike,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        trenutnaPutanjaSlike = image.getAbsolutePath();
        return image;
    }


}



