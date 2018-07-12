package raghu.co.carlist;

import com.airbnb.epoxy.EpoxyController;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import raghu.co.repository.model.Car;

class CarListController extends EpoxyController {

    private Set<Car> cars;
    private String title = null;

    public CarListController() {
        cars = new LinkedHashSet<>();
    }

    @Override
    protected void buildModels() {
        new HeaderViewModel_()
                .id("Header")
                .header(title)
                .addTo(this);

        for (Car car : cars) {
            new ItemViewModel_()
                    .id(car.getId())
                    .content(car)
                    .addTo(this);
        }
    }


    public void setContents(List<Car> carList) {
        if (!cars.isEmpty()) {
            cars.clear();
        }
        cars.addAll(carList);
        requestModelBuild();
    }

    public void setHeader(String title) {
        this.title = title;
        requestModelBuild();
    }

}
