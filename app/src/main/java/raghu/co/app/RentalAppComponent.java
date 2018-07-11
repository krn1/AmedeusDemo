package raghu.co.app;

import javax.inject.Singleton;

import dagger.Component;
import raghu.co.repository.network.NetworkModule;
import raghu.co.repository.network.RestApi;

@Singleton
@Component(modules = {RentalAppModule.class, NetworkModule.class})
public interface RentalAppComponent {

    void inject(RentalApp application);

    RentalApp application();

    RestApi restApi();
}
