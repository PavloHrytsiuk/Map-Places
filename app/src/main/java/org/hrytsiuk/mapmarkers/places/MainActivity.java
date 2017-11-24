package org.hrytsiuk.mapmarkers.places;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.hrytsiuk.mapmarkers.R;
import org.hrytsiuk.mapmarkers.base.BaseActivity;
import org.hrytsiuk.mapmarkers.base.ItemCheckListener;
import org.hrytsiuk.mapmarkers.map.MapsActivity;
import org.hrytsiuk.mapmarkers.places.presentation.PlacePresenter;
import org.hrytsiuk.mapmarkers.places.presentation.PlacePresenterImpl;
import org.hrytsiuk.mapmarkers.places.presentation.PlaceView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity implements PlaceView, ItemCheckListener {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.buttonShow)
    Button button;

    private PlacesAdapter adapter;
    private PlacePresenter presenter;
    private List<Place> places;
    private Intent intent;
    private HashSet<Integer> checkedPlaces;

    private static final String CHECKED_PLACES = "Checked places";
    private static final String LIST_PLACES = "List_places";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new PlacePresenterImpl();
        presenter.attachView(this);
        presenter.fetchPlaces();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkedPlaces = new HashSet<>();
        intent = new Intent(MainActivity.this, MapsActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(LIST_PLACES, (ArrayList<? extends Parcelable>) places);
                intent.putExtras(bundle);
                ArrayList<Integer> list = new ArrayList<>();
                for (Integer x : checkedPlaces) {
                    list.add(x);
                }
                intent.putIntegerArrayListExtra(CHECKED_PLACES, list);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void loadPlaces(@NonNull final List<Place> places) {
        this.places = places;
        adapter = new PlacesAdapter(places, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemChecked(final int position) {
        checkedPlaces.add(position);
    }

    @Override
    public void itemUnchecked(final int position) {
        checkedPlaces.remove(position);
    }
}
