package org.hrytsiuk.mapmarkers.places.presentation;

import org.hrytsiuk.mapmarkers.places.Place;
import org.hrytsiuk.mapmarkers.places.interactor.PlaceInteractor;
import org.hrytsiuk.mapmarkers.places.interactor.PlaceInteractorImpl;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public final class PlacePresenterImpl implements PlacePresenter {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final PlaceInteractor interactor = new PlaceInteractorImpl();
    private PlaceView view;

    @Override
    public void fetchPlaces() {
        final Subscription subscription = interactor.fetchPlaces()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Place>>() {
                    @Override
                    public void onCompleted() {
                        // hide progress
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        throwable.printStackTrace();
                        // handle error
                    }

                    @Override
                    public void onNext(final List<Place> places) {
                        view.loadPlaces(places);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void onDestroy() {
        subscriptions.unsubscribe();
    }

    @Override
    public void attachView(final PlaceView view) {
        this.view = view;
    }
}
