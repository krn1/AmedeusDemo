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

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderView extends FrameLayout {

    @BindView(R.id.title)
    TextView titleView;

    public HeaderView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.car_list_header, this);
        ButterKnife.bind(this);
    }

    @ModelProp
    public void setHeader(@NonNull String title) {
        titleView.setText(title);
    }
}