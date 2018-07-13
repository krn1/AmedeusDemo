package raghu.co.carlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.airbnb.epoxy.EpoxyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raghu.co.R;
import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;
import raghu.co.util.ListUtil;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_car_list, menu);
        MenuItem item = menu.findItem(R.id.overflow);
        Spinner spinner = (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedSort = (String) parent.getItemAtPosition(position);
                    sortList(selectedSort);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return true;
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
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public String getCarAvailability() {
        int size = carList.size();
        return size == 1 ? "1 Car Available" : size + " Cars Available";
    }

    private void showCarList() {
        listController = new CarListController();
        list.setController(listController);
        listController.setHeader(getCarAvailability());
        // Default is price ascending
        sortList("");
    }

    private void sortList(String sort) {

        switch (sort) {
            case "Company ,Des":
                carList = ListUtil.sortCarsByCompanyDescending(carList);
                break;
            case "Distance ,Des":
                carList = ListUtil.sortCarsByDistanceDescending(carList);
                break;
            case "Price ,Des":
                carList = ListUtil.sortCarsByPriceDescending(carList);
                break;
            case "Company ,Asc":
                carList = ListUtil.sortCarsByCompanyAscending(carList);
                break;
            case "Distance ,Asc":
                carList = ListUtil.sortCarsByDistanceAscending(carList);
                break;
            default:
                carList = ListUtil.sortCarsByPriceAscending(carList);
                break;
        }
        listController.setContents(carList);
    }
    // endregion
}
