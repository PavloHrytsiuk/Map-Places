package org.hrytsiuk.mapmarkers.places;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.hrytsiuk.mapmarkers.R;
import org.hrytsiuk.mapmarkers.base.BaseActivity;
import org.hrytsiuk.mapmarkers.base.ItemCheckListener;
import org.hrytsiuk.mapmarkers.map.MapsActivity;
import org.hrytsiuk.mapmarkers.places.presentation.PlacePresenter;
import org.hrytsiuk.mapmarkers.places.presentation.PlacePresenterImpl;
import org.hrytsiuk.mapmarkers.places.presentation.PlaceView;
import org.hrytsiuk.mapmarkers.utils.Utils;

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

    private PlacePresenter presenter;
    private HashSet<Place> checkedPlaces;
    private PlacesAdapter adapter;

    private static final String CHECKED_PLACES = "Checked places";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView title = (TextView) findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText(R.string.title_main_activity);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        if (Utils.isNetworkConnected(this)) {
            presenter = new PlacePresenterImpl();
            presenter.attachView(this);
            presenter.fetchPlaces();

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            checkedPlaces = new HashSet<>();
            final Intent intent = new Intent(MainActivity.this, MapsActivity.class);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(@NonNull final View view) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(CHECKED_PLACES, new ArrayList<Parcelable>(checkedPlaces));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else {
            button.setVisibility(View.GONE);
            showToast(getString(R.string.message_no_connection));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void loadPlaces(@NonNull final List<Place> places) {
        adapter = new PlacesAdapter(places, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemChecked(final int position) {
        checkedPlaces.add(adapter.getItem(position));
    }

    @Override
    public void itemUnchecked(final int position) {
        checkedPlaces.remove(adapter.getItem(position));
    }
}
