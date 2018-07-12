package raghu.co.carlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;

import butterknife.BindView;
import butterknife.ButterKnife;
import raghu.co.R;
import raghu.co.repository.model.Car;


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ItemView extends FrameLayout {


    @BindView(R.id.car)
    TextView carTextView;

    @BindView(R.id.provider)
    TextView providerTextView;

    @BindView(R.id.distance)
    TextView distanceView;

    @BindView(R.id.price)
    TextView priceView;

    private Car carInfo;

    public ItemView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.car_list_item, this);
        ButterKnife.bind(this);
    }

    @ModelProp
    public void setContent(@NonNull Car carInfo) {
        this.carInfo = carInfo;

        carTextView.setText(getCarType());
        providerTextView.setText(carInfo.getCompanyName());
        distanceView.setText(carInfo.getDistance());
        priceView.setText(carInfo.getRates().get(0).getPrice().getAmount());
    }

    private String getCarType() {
        return carInfo.getVehicleInfo().getAcrissCode() + " " +
                carInfo.getVehicleInfo().getCategory() + " " +
                carInfo.getVehicleInfo().getType();
    }
}
