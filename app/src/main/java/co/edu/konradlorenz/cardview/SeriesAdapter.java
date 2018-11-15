package co.edu.konradlorenz.cardview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Serie> serieList;
    private  Context contextIntent;
    private ImageView imageView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, temporada;
        public ImageView thumbnail;
        private View elementView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            temporada = (TextView) view.findViewById(R.id.temporada);
            elementView = view;
        }
    }


    public SeriesAdapter(Context mContext, List<Serie> serieList) {
        this.mContext = mContext;
        this.serieList = serieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Serie serie = serieList.get(position);

        holder.elementView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent i = new Intent(mContext,SerieDetailActivity.class);
                i.putExtra("thumbnail", serie.getThumbnail());
                i.putExtra("temporadas",serie.getTemporadas());

                i.putExtra("nombre", serie.getName());
                i.putExtra("description", serie.getDescription());

                mContext.startActivity(i);



                Toast.makeText(mContext, serie.getName(),Toast.LENGTH_SHORT).show();

            }
        });
        holder.title.setText(serie.getName());
        holder.temporada.setText(serie.getTemporadas() + " Temporadas");

        // loading serie cover using Glide library
        Glide.with(mContext).load(serie.getThumbnail()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return serieList.size();
    }


}
