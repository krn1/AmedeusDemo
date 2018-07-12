package raghu.co;


interface MainActivityContract {
    interface View {

        void showSpinner();

        void hideSpinner();

        double getLatitude();

        double getLongitude();

        void showError(String msg);
    }

    interface Presenter {

        void stop();

        void getRentalCarList(String pickUpDate,String dropOffDate);
    }
}
