package raghu.co.carlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.airbnb.epoxy.EpoxyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raghu.co.R;
import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;
import timber.log.Timber;

public class CarListActivity extends AppCompatActivity {

    private static final String KEY_CAR_LIST = "car_list";
    private static final String KEY_USER_LOCATION = "user_location";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    EpoxyRecyclerView list;

    private List<Car> carList;
    private Location userLocation;
    private CarListController listController;

    // region static
    public static void start(Activity context, ArrayList<Car> cars, Location userLocation) {
        Intent intent = new Intent(context, CarListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_CAR_LIST, cars);
        intent.putExtras(bundle);
        intent.putExtra(KEY_USER_LOCATION, userLocation);
        context.startActivity(intent);
    }
    //endregion static

    // region override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_CAR_LIST)) {
            carList = intent.getExtras().getParcelableArrayList(KEY_CAR_LIST);
        }
        if (intent.hasExtra(KEY_USER_LOCATION)) {
            userLocation = intent.getExtras().getParcelable(KEY_USER_LOCATION);
        }
        for (Car car : carList) {
            car.setDistance(userLocation.getLatitude(), userLocation.getLongitude());
        }

        setupToolbar();
        showCarList();
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
        toolbar.setTitle(getString(R.string.list_toolbar));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public String getCarAvailability() {
        int size = carList.size();
        return size == 1 ? "1 Car Available" : size + " Cars Available";
    }

    private void showCarList() {
        Timber.e("Total cars available : " + carList.size());

        listController = new CarListController();
        list.setController(listController);
        listController.setHeader(getCarAvailability());
        listController.setContents(carList);
    }
    // endregion
}
