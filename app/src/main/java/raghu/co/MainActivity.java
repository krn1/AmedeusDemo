package raghu.co;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import raghu.co.app.RentalApp;
import raghu.co.repository.model.cars.RentalCars;
import raghu.co.repository.network.RestApi;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    RestApi apiService;

    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        disposable = new CompositeDisposable();
        loadRentalCars();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder()
                .rentalAppComponent(((RentalApp) getApplication()).getComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
    }

    public void loadRentalCars() {
        String key = getResources().getString(R.string.api_key);
        disposable.add(apiService.getRentalCars(key, 37.422740f, -122.1399560f, 50, "2018-07-11", "2018-07-15")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RentalCars>() {
                    @Override
                    public void onNext(RentalCars rentalCars) {
                        Timber.e("We got size %d\n  %s", rentalCars.getResults().size(), rentalCars.getResults().toString());

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.e("Wtf "+throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }
}

