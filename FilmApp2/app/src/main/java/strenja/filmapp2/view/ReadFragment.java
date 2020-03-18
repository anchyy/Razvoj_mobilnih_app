package strenja.filmapp2.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import strenja.filmapp2.R;
import strenja.filmapp2.model.Film;
import strenja.filmapp2.view.adapter.FilmAdapter;
import strenja.filmapp2.view.adapter.FilmClickListener;
import strenja.filmapp2.viewmodel.FilmViewModel;

public class ReadFragment extends Fragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    ListView listView;

    FilmViewModel modelFilm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,
                container, false);
        ButterKnife.bind(this,view);

        modelFilm = ((MainActivity)getActivity()).getModel();

        definirajListu();
        definirajSwipe();
        osvjeziPodatke();


        return view;
    }

    private void osvjeziPodatke(){
        Log.wtf("kreuno","osvježi podatke");
        modelFilm.dohvatiFilmove().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> filmList) {
                Log.wtf("završio","osvježi podatke");
                swipeRefreshLayout.setRefreshing(false);
                ((FilmAdapter)listView.getAdapter()).setPodaci(filmList);
                ((FilmAdapter) listView.getAdapter()).notifyDataSetChanged();

            }
        });
    }
    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                osvjeziPodatke();
            }
        });

    }

    private void definirajListu() {
        listView =(ListView) listView.findViewById(R.id.lista);
        listView.setAdapter(new FilmAdapter(getActivity(), new FilmClickListener() {
            @Override
            public void onItemClick(Film film) {
                modelFilm.setFilm(film);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void noviFilm(){
        modelFilm.setFilm(new Film());
        ((MainActivity)getActivity()).cud();
    }


}

