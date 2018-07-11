package raghu.co;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import raghu.co.app.dagger.PerActivity;

@Module
class MainActivityModule {
    private MainActivityContract.View view;
    MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    MainActivityContract.View providesView() {
        return view;
    }

    @PerActivity
    @Provides
    CompositeDisposable providesDisposable() {
        return new CompositeDisposable();
    }

    @PerActivity
    @Provides
    String provideAmadeusKey() {
        return ((MainActivity) view).getResources().getString(R.string.api_key);
    }

}
