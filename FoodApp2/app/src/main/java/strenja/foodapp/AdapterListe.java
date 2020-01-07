package strenja.foodapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {

    private List<Recept> recepti;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public AdapterListe(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public void setRecepti(List<Recept> recepti) {
        this.recepti = recepti;
    }


    @Override
    public Red onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(
                R.layout.redak,parent,false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(Red holder, int position) {
        Recept recept = recepti.get(position);
        holder.mainName.setText(recept.getMainName());

    }

    @Override
    public int getItemCount() {

        return recepti==null ? 0 : recepti.size();
    }

    public Recept getRecept(int position) {
        return recepti.get(position);
    }



    public class Red extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private TextView mainName;
        private TextView descriptName;

        public Red(View view){
            super(view);
            mainName = view.findViewById(R.id.mainName);
            descriptName=view.findViewById(R.id.descriptName);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickInterface != null){
                itemClickInterface.onItemClick(v,getAdapterPosition());
            }
        }
    }


    public void setClickListener(ItemClickInterface clickListener) {
        this.itemClickInterface=clickListener;
    }

    public interface ItemClickInterface{
        void onItemClick(View view, int position);
    }

}

