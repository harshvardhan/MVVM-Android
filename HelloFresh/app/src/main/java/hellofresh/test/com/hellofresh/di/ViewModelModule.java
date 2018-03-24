package hellofresh.test.com.hellofresh.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import hellofresh.test.com.hellofresh.ui.details.RecipeDetailsViewModel;
import hellofresh.test.com.hellofresh.ui.list.RecipeListViewModel;
import hellofresh.test.com.hellofresh.ui.login.LoginViewModel;
import hellofresh.test.com.hellofresh.viewmodel.HelloFreshViewModelFactory;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeListViewModel.class)
    abstract ViewModel bindRecipeListViewModel(RecipeListViewModel recipeListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailsViewModel.class)
    abstract ViewModel bindRecipeDetailViewModel(RecipeDetailsViewModel recipeDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(HelloFreshViewModelFactory factory);
}
