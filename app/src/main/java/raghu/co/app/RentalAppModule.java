package raghu.co.app;

import dagger.Module;
import dagger.Provides;

@Module
public class RentalAppModule {
    private final RentalApp application;

    RentalAppModule(RentalApp application) {
        this.application = application;
    }

    @Provides
    public RentalApp provideApplication() {
        return application;
    }

}
