package raghu.co;

import dagger.Component;
import raghu.co.app.RentalApp;
import raghu.co.app.RentalAppComponent;
import raghu.co.app.dagger.PerActivity;

@PerActivity
@Component(dependencies = RentalAppComponent.class, modules = MainActivityModule.class)
interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}

