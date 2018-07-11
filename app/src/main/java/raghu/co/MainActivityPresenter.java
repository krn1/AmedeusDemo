package raghu.co;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import raghu.co.app.dagger.PerActivity;
import raghu.co.repository.model.cars.RentalCars;
import raghu.co.repository.network.RestApi;
import timber.log.Timber;

@PerActivity
class MainActivityPresenter implements MainActivityContract.Presenter {


    private MainActivityContract.View view;
    private CompositeDisposable disposable;
    private RestApi apiService;
    private String amadeusApiKey;

    @Inject
    MainActivityPresenter(MainActivityContract.View view,
                          RestApi apiService,
                          String amadeusApiKey,
                          CompositeDisposable disposable) {
        this.view = view;
        this.apiService = apiService;
        this.amadeusApiKey = amadeusApiKey;
        this.disposable = disposable;
    }

    @Override
    public void start() {
        loadRentalCars();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    public void loadRentalCars() {

        disposable.add(apiService.getRentalCars(amadeusApiKey, 37.422740f, -122.1399560f, 50, "2018-07-11", "2018-07-15")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RentalCars>() {
                    @Override
                    public void onNext(RentalCars rentalCars) {
                        Timber.e("We got size %d\n  %s", rentalCars.getResults().size(), rentalCars.getResults().toString());

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.e("Wtf " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
