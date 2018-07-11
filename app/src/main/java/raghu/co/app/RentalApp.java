package raghu.co.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import javax.inject.Singleton;

import dagger.Component;
import timber.log.Timber;

public class RentalApp extends Application{
    private RentalAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // dependency injection graph
        component = DaggerRentalAppComponent.builder().rentalAppModule(new RentalAppModule(this)).build();
        component.inject(this);

        Timber.plant(new Timber.DebugTree());
        initializeFresco();
    }

    public RentalAppComponent getComponent() {
        return component;
    }

    // region private
    private void initializeFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }
    //endregion
}
