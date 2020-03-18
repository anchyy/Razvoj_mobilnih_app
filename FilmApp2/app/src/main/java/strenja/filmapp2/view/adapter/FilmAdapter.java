package strenja.filmapp2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import strenja.filmapp2.R;
import strenja.filmapp2.model.Film;

public class FilmAdapter extends BaseAdapter {

    private List<Film> podaci;
    private FilmClickListener filmClickListener;
    private LayoutInflater layoutInflater;
    ViewHolder holder;
    Film film;


    public FilmAdapter(FragmentActivity activity, FilmClickListener filmClickListener) {
        this.filmClickListener=filmClickListener;
        layoutInflater = LayoutInflater.from(activity);
    }
    public void setPodaci(List<Film> filmovi) {

        this.podaci = filmovi;
    }

    @Override
    public int getCount() {
        return podaci == null ? 0:podaci.size();
    }

    @Override
    public Object getItem(int position) {
        return podaci.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        film=podaci.get(position);


        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.red_liste, null);
            holder = new ViewHolder (convertView);
            holder.filmName=convertView.findViewById(R.id.naslov);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.filmName.setText(film.getNaslov().toUpperCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filmClickListener.onItemClick(podaci.get(position));
            }
        });

        if (film.getPutanjaSlika() == null) {
            Picasso.get().load(R.drawable.movie_icon).fit().centerCrop().into(holder.picture);
            return convertView;
        }
        Picasso.get().load(film.getPutanjaSlika()).fit().centerCrop().into(holder.picture);

        return convertView;
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView filmName;


        ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.slikaFilma);
            filmName = itemView.findViewById(R.id.naslov);

        }
    }







}

