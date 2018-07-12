package raghu.co;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import raghu.co.app.RentalApp;
import raghu.co.util.AlertUtil;
import raghu.co.util.KeyBoardUtil;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        DatePickerDialog.OnDateSetListener {

    @Inject
    MainActivityPresenter presenter;

    @BindView(R.id.location)
    PlacesAutocompleteTextView locationTextView;

    @BindView(R.id.pickup_date)
    Button pickUpBtn;

    @BindView(R.id.dropoff_date)
    Button dropOffBtn;

    @BindView(R.id.search)
    FloatingActionButton searchBtn;

    @BindView(R.id.progress_bar_container)
    FrameLayout progressBarContainer;

    private double longitude = -118.243685f;
    private double latitude = 34.052234f;
    private boolean isPickUpDate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getComponent().inject(this);

        setLocationView();
        enableSearch(false);
        searchBtn.setOnClickListener(listener -> {
            if (validateInput()) {
                presenter.getRentalCarList(pickUpBtn.getText().toString(),
                        dropOffBtn.getText().toString());
            }
        });
        pickUpBtn.setOnClickListener(listener -> {
            isPickUpDate = true;
            AlertUtil.dismissToast(this);
            showDatePickerDialog();
        });
        dropOffBtn.setOnClickListener(listener -> {
            isPickUpDate = false;
            AlertUtil.dismissToast(this);
            showDatePickerDialog();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
        Timber.e("Selected date " + date);
        if (isPickUpDate) {
            pickUpBtn.setText(date);
        } else {
            dropOffBtn.setText(date);
        }
        validateInput();
    }

    @Override
    public void showSpinner() {
        progressBarContainer.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideSpinner() {
        progressBarContainer.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public void showError(String msg) {
        AlertUtil.displayError(this, msg);
    }

    // region private

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder()
                .rentalAppComponent(((RentalApp) getApplication()).getComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
    }

    private void setLocationView() {
        locationTextView.setOnPlaceSelectedListener(
                place -> {
                    Timber.d("Place selected " + place.place_id);
                    getLongLatitude(place);
                    KeyBoardUtil.hideKeyboard(this);
                }
        );
    }

    private void getLongLatitude(Place place) {
        locationTextView.getDetailsFor(place, new DetailsCallback() {
            @Override
            public void onSuccess(PlaceDetails details) {
                latitude = details.geometry.location.lat;
                longitude = details.geometry.location.lng;

                validateInput();
                Timber.e("Long %f \t lat %f", longitude, latitude);
            }

            @Override
            public void onFailure(Throwable failure) {
            }

            public void onComplete() {
            }
        });
    }

    private void enableSearch(boolean enabled) {
        searchBtn.setEnabled(enabled);
        if (enabled) {
            searchBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        } else {
            searchBtn.setBackgroundTintList(getResources().getColorStateList(R.color.grey));
        }
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 0);
        DatePickerDialog dialog = new DatePickerDialog(this, this, year, month, day);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(locationTextView.getText())) {
            AlertUtil.toastUser(this, "You have to add your address");
            return false;
        }
        if (TextUtils.isEmpty(pickUpBtn.getText())) {
            AlertUtil.toastUser(this, "Please Add pickup date");
            return false;
        }
        if (TextUtils.isEmpty(dropOffBtn.getText())) {
            AlertUtil.toastUser(this, "Please Add DropOff date");
            return false;
        }
        if (!validateDate(pickUpBtn.getText().toString(), dropOffBtn.getText().toString())) {
            AlertUtil.toastUser(this, "Drop off date should be later than pick up date");
            return false;
        }
        enableSearch(true);
        return true;
    }

    private boolean validateDate(String pickUpDateStr, String dropOffDateStr) {
        String[] pickUpDate = pickUpDateStr.split("-");
        String[] dropDate = dropOffDateStr.split("-");
        return Integer.parseInt(pickUpDate[0]) <= Integer.parseInt(dropDate[0]) &&
                Integer.parseInt(pickUpDate[1]) <= Integer.parseInt(dropDate[1]) &&
                Integer.parseInt(pickUpDate[2]) <= Integer.parseInt(dropDate[2]);
    }

    //endregion private
}

