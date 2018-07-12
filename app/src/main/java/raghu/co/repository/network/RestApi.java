package raghu.co.repository.network;

import io.reactivex.Flowable;
import raghu.co.repository.model.cars.RentalCars;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("v1.2/cars/search-circle")
    Flowable<RentalCars> getRentalCars(
            @Query("apikey") String apiKey,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("radius") int radius,
            @Query("pick_up") String pick_up,
            @Query("drop_off") String drop_off
    );
}
