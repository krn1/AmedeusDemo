package raghu.co;


interface MainActivityContract {
    interface View {

        void showSpinner();

        void hideSpinner();
    }

    interface Presenter {

        void start();

        void stop();

    }
}
