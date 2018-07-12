package raghu.co;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import raghu.co.app.dagger.PerActivity;
import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;
import raghu.co.repository.model.RentalCars;
import raghu.co.repository.model.Result;
import raghu.co.repository.network.RestApi;
import timber.log.Timber;

@PerActivity
class MainActivityPresenter implements MainActivityContract.Presenter {

    private static int DISTANCE_RADIUS = 35;

    private MainActivityContract.View view;
    private CompositeDisposable disposable;
    private RestApi apiService;
    private String amadeusApiKey;
    private Location userLocation;
    private ArrayList<Car> carList;

    @Inject
    MainActivityPresenter(MainActivityContract.View view,
                          RestApi apiService,
                          String amadeusApiKey,
                          CompositeDisposable disposable) {
        this.view = view;
        this.apiService = apiService;
        this.amadeusApiKey = amadeusApiKey;
        this.disposable = disposable;

        this.carList = new ArrayList<>();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    @Override
    public void getRentalCarList(Location userLocation, String pickUpDate, String dropOffDate) {
        this.userLocation = userLocation;
        view.showSpinner();
        disposable.add(apiService.getRentalCars(amadeusApiKey,
                userLocation.getLatitude(),
                userLocation.getLongitude(),
                DISTANCE_RADIUS,
                pickUpDate,
                dropOffDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RentalCars>() {
                    @Override
                    public void onNext(RentalCars rentalCars) {
                        Timber.d("We got size %d\n ", rentalCars.getResults().size());
                        view.hideSpinner();

                        if (rentalCars.getResults().isEmpty()) {
                            view.showError("No cars available at this moment. Try again later or modify your search");
                        } else {
                            updateSearchResult(rentalCars.getResults());
                        }
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

    private void updateSearchResult(List<Result> rentalCars) {
        ArrayList<Car> cars = new ArrayList<>();

        int id = 1;
        for (Result result : rentalCars) {
            for (Car car : result.getCars()) {
                car.setCompanyName(result.getProvider().getCompanyName());
                car.setCarLocation(result.getLocation());
                car.setUserLocation(userLocation);
                car.setAddress(result.getAddress());
                car.setAirport(result.getAirport());
                car.setId(id);
                cars.add(car);
                id++;
            }
        }
        if (!carList.isEmpty()) {
            carList.clear();
        }
        carList.addAll(cars);
        view.showCarsList(carList);

    }
}
