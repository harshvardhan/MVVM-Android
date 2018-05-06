package dailydomain.test.com.sgpowermap.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dailydomain.test.com.sgpowermap.ui.MapsViewModel;
import dailydomain.test.com.sgpowermap.viewmodel.SGPowerMapViewModelFactory;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapsViewModel.class)
    abstract ViewModel bindRecipeDetailViewModel(MapsViewModel mapsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(SGPowerMapViewModelFactory factory);
}
