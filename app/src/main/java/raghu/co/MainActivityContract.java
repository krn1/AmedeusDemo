package raghu.co;


import java.util.ArrayList;

import raghu.co.repository.model.Car;
import raghu.co.repository.model.Location;

interface MainActivityContract {
    interface View {

        void showSpinner();

        void hideSpinner();

        void showError(String msg);
        
        void showCarsList(ArrayList<Car> carList);
    }

    interface Presenter {

        void stop();

        void getRentalCarList(Location userLocation,String pickUpDate, String dropOffDate);
    }
}
