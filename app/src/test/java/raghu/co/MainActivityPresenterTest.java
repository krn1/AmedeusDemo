package raghu.co;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import raghu.co.repository.model.Address;
import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;
import raghu.co.repository.model.Provider;
import raghu.co.repository.model.RentalCars;
import raghu.co.repository.model.Result;
import raghu.co.repository.model.VehicleInfo;
import raghu.co.repository.network.RestApi;
import raghu.co.util.TrampolineSchedulerUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    @Mock
    private MainActivityContract.View view;
    @Mock
    private RestApi apiService;

    private CompositeDisposable disposable;

    private MainActivityPresenter presenter;

    @BeforeClass
    public static void setUpClass() {
        TrampolineSchedulerUtils.convertSchedulersToTrampoline();
    }

    @Before
    public void setUp() throws Exception {
        disposable = spy(new CompositeDisposable());
        presenter = new MainActivityPresenter(view, apiService, "key", disposable);
    }

    @Test
    public void emptyList() throws Exception {
        // Given
        RentalCars emptyList = new RentalCars();
        emptyList.setResults(Collections.emptyList());
        Location userLocation = new Location(37.422740f, 37.422740f);
        when(apiService.getRentalCars("key",
                37.422740f,
                37.422740f,
                50,
                "2018-07-13",
                "2018-07-15")).thenReturn(Flowable.just(emptyList));

        // When
        presenter.getRentalCarList(userLocation, "2018-07-13",
                "2018-07-15");

        // Then
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).hideSpinner();
        verify(view, times(1)).showError("No cars available at this moment. Try again later or modify your search");
    }


    @Test
    public void fetchRentalCars() throws Exception {
        // Given
        RentalCars rentalCars = createCarsList(6);
        Location userLocation = new Location(37.422740f, 37.422740f);
        when(apiService.getRentalCars("key",
                37.422740f,
                37.422740f,
                50,
                "2018-07-13",
                "2018-07-15")).thenReturn(Flowable.just(rentalCars));

        // When
        presenter.getRentalCarList(userLocation, "2018-07-13",
                "2018-07-15");

        // Then
        verify(view, times(1)).showSpinner();
        verify(view, times(1)).hideSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showCarsList(presenter.carList);
    }

    // region private
    private RentalCars createCarsList(int size) {
        RentalCars rentalCars = new RentalCars();
        List<Result> results = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            results.add(createResult(i));
        }
        rentalCars.setResults(results);
        return rentalCars;
    }

    private Result createResult(int id) {
        Result result = new Result();
        result.setAddress(new Address().withLine1(String.valueOf(id)));
        result.setAirport("airport" + id);
        result.setLocation(new Location(id, id));
        Car car = new Car();
        car.setId(id);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        car.setVehicleInfo(getVehicleInfo());
        result.setProvider(new Provider().withCompanyCode(String.valueOf(id)).withCompanyName("aa"));
        result.setCars(cars);
        return result;
    }

    private VehicleInfo getVehicleInfo() {
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setCategory("aa");
        vehicleInfo.setCategory("bb");
        return vehicleInfo;
    }
    //endregion

}
