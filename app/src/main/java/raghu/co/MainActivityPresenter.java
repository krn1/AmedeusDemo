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

    private static int DISTANCE_RADIUS = 35;

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
    public void stop() {
        disposable.clear();
    }

    @Override
    public void getRentalCarList(String pickUpDate,String dropOffDate) {
        view.showSpinner();
        disposable.add(apiService.getRentalCars(amadeusApiKey,
                 view.getLatitude(),
                 view.getLongitude(),
                 DISTANCE_RADIUS,
                 pickUpDate,
                 dropOffDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RentalCars>() {
                    @Override
                    public void onNext(RentalCars rentalCars) {
                        Timber.e("We got size %d\n  %s", rentalCars.getResults().size(), rentalCars.getResults().toString());
                        view.hideSpinner();

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.e("Wtf " + throwable.getMessage());
                        view.hideSpinner();
                        view.showError("");
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
