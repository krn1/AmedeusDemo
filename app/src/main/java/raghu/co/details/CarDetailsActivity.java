package raghu.co.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import raghu.co.R;
import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;
import raghu.co.util.DistanceUtil;

public class CarDetailsActivity extends AppCompatActivity {

    private static final String KEY_CAR_ITEM = "car";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.map)
    MapView mapView;

    @BindView(R.id.address)
    TextView addressView;

    @BindView(R.id.car)
    TextView carTextView;

    @BindView(R.id.provider)
    TextView providerTextView;

    @BindView(R.id.distance)
    TextView distanceView;

    @BindView(R.id.price)
    TextView priceView;

    @BindView(R.id.fuel)
    TextView fuelView;

    @BindView(R.id.transmission)
    TextView transmissionView;

    @BindView(R.id.ac)
    TextView acView;

    @BindView(R.id.fab)
    FloatingActionButton directionsFab;

    private Car car;
    private GoogleMap map;

    // region static
    public static void start(Activity context, Car car) {
        Intent intent = new Intent(context, CarDetailsActivity.class);
        intent.putExtra(KEY_CAR_ITEM, car);
        context.startActivity(intent);
    }
    //endregion static

    // region override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_CAR_ITEM)) {
            car = intent.getExtras().getParcelable(KEY_CAR_ITEM);
        }

        setUpMap();
        setupToolbar();
        showCarInfo();
        directionsFab.setOnClickListener(listener -> startNavigation());
    }

    private void setUpMap() {
        mapView.onCreate(null);
        mapView.getMapAsync(googleMap -> {
            map = googleMap;
            MapsInitializer.initialize(this);
            map.getUiSettings().setMapToolbarEnabled(false);
            updateMapContents(car.getCarLocation());
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion override

    // region private

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(getDrawable(R.drawable.abc_ic_ab_back_material));
        toolbar.setNavigationOnClickListener(toolbar -> onBackPressed());
        toolbar.setTitle(car.getReadableAddress());
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void showCarInfo() {
        addressView.setText(car.getReadableAddress());
        carTextView.setText(car.getCarType());
        providerTextView.setText(car.getCompanyName());
        distanceView.setText(car.getDistance());
        priceView.setText(car.getRates().get(0).getPrice().getAmount());
        fuelView.setText(car.getVehicleInfo().getFuel());
        transmissionView.setText(car.getVehicleInfo().getTransmission());
        acView.setText(car.getVehicleInfo().getAirConditioning());
    }

    private void updateMapContents(Location carLocation) {
        LatLng latlng = new LatLng(carLocation.getLatitude(), carLocation.getLongitude());
        // Since the mapView is re-used, need to remove pre-existing mapView features.
        map.clear();

        // Update the mapView feature data and camera position.
        map.addMarker(new MarkerOptions().position(latlng));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 10f);
        map.moveCamera(cameraUpdate);
    }

    private void startNavigation() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                DistanceUtil.getMapUrl(car.getUserLocation(), car.getCarLocation()));
        startActivity(intent);
    }
    // endregion
}
