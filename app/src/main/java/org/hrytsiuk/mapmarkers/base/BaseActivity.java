package org.hrytsiuk.mapmarkers.base;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.hrytsiuk.mapmarkers.R;
import org.hrytsiuk.mapmarkers.base.mvp.BaseView;
import org.hrytsiuk.mapmarkers.network.rx.RetrofitException;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void showProgress(final boolean visible) {
        // no-op
    }

    @Override
    public void httpError(@NonNull RetrofitException e) {
        showToast("Http error");
    }

    @Override
    public void showToast(@NonNull final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTitle(ActionBar actionBar, String text) {
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.action_bar);
        }
        TextView title = (TextView) findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText(text);
    }
}
