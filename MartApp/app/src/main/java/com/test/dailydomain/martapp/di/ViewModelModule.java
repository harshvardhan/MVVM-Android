package com.test.dailydomain.martapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.test.dailydomain.martapp.ui.list.ProductListViewModel;
import com.test.dailydomain.martapp.viewmodel.MartAppViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindRecipeListViewModel(ProductListViewModel productListViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MartAppViewModelFactory factory);
}
