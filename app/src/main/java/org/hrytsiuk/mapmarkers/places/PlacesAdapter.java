package org.hrytsiuk.mapmarkers.places;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.hrytsiuk.mapmarkers.R;
import org.hrytsiuk.mapmarkers.base.ItemCheckListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PersonViewHolder> {

    private final List<Place> places;
    private final ItemCheckListener listener;

    public PlacesAdapter(@NonNull final List<Place> places, @NonNull final ItemCheckListener listener) {
        this.places = places;
        this.listener = listener;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new PersonViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false),
                listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, final int position) {
        holder.placeTitle.setText(places.get(position).getTitle());
        holder.placeDescription.setText(places.get(position).getDescription());
        Picasso.with(holder.itemView.getContext()).load(places.get(position).getImageUrl()).into(holder.placePhoto);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public Place getItem(final int position) {
        return places.get(position);
    }

    static final class PersonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.placePhoto)
        ImageView placePhoto;
        @BindView(R.id.placeTitle)
        TextView placeTitle;
        @BindView(R.id.placeDescription)
        TextView placeDescription;
        @BindView(R.id.checkBox)
        CheckBox checkBox;

        public PersonViewHolder(@NonNull final View itemView, @NonNull final ItemCheckListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(@NonNull final View view) {
                    if (checkBox.isChecked()) {
                        listener.itemChecked(getAdapterPosition());
                    }
                    if (!checkBox.isChecked()) {
                        listener.itemUnchecked(getAdapterPosition());
                    }
                }
            });
        }
    }
}
