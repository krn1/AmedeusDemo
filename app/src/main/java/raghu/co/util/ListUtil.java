package raghu.co.util;

import java.util.Collections;
import java.util.List;

import raghu.co.repository.model.Car;

public class ListUtil {

    public static List<Car> sortCarsByCompanyDescending(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (o1, o2) -> o2.getCompanyName().compareTo(o1.getCompanyName()));
        }
        return carList;
    }

    public static List<Car> sortCarsByDistanceDescending(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (car1, car2) -> {
                int inter1 = (int) car1.getCarDistance();
                int inter2 = (int) car2.getCarDistance();

                return inter2 - inter1;
            });
        }
        return carList;
    }

    public static List<Car> sortCarsByPriceDescending(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (car1, car2) -> {
                int inter1 = Integer.parseInt(car1.getEstimatedTotal().getAmount());
                int inter2 =  Integer.parseInt(car2.getEstimatedTotal().getAmount());

                return inter2 - inter1;
            });
        }
        return carList;
    }

    public static List<Car> sortCarsByCompanyAscending(List<Car> carList) {

        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (o1, o2) -> o1.getCompanyName().compareTo(o2.getCompanyName()));
        }
        return carList;
    }

    public static List<Car> sortCarsByPriceAscending(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (car1, car2) -> {
                int inter1 = Integer.parseInt(car1.getEstimatedTotal().getAmount());
                int inter2 =  Integer.parseInt(car2.getEstimatedTotal().getAmount());

                return inter1 - inter2;
            });
        }
        return carList;
    }

    public static List<Car> sortCarsByDistanceAscending(List<Car> carList) {
        if (carList != null && !carList.isEmpty()) {
            Collections.sort(carList, (car1, car2) -> {
                int inter1 = (int) car1.getCarDistance();
                int inter2 = (int) car2.getCarDistance();

                return inter1 - inter2;
            });
        }
        return carList;
    }

}
