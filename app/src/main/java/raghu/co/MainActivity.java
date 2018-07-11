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
    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);

        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder()
                .rentalAppComponent(((RentalApp) getApplication()).getComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
    }



    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }
}

