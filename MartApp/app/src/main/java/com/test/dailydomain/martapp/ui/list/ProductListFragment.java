package com.test.dailydomain.martapp.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.dailydomain.martapp.R;
import com.test.dailydomain.martapp.binding.FragmentDataBindingComponent;
import com.test.dailydomain.martapp.databinding.ProductListFragmentBinding;
import com.test.dailydomain.martapp.di.Injectable;
import com.test.dailydomain.martapp.ui.common.NavigationController;
import com.test.dailydomain.martapp.ui.common.ProductListAdapter;
import com.test.dailydomain.martapp.util.AutoClearedValue;
import com.test.dailydomain.martapp.vo.Resource;


import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

    @Inject
    public  ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public NavigationController navigationController;

    public FragmentDataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    public AutoClearedValue<ProductListFragmentBinding> binding;

    AutoClearedValue<ProductListAdapter> adapter;

    private ProductListViewModel productListViewModel;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProductListFragmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.product_list_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productListViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);
        initRecyclerView();
        ProductListAdapter rvAdapter = new ProductListAdapter(dataBindingComponent, true,
                null);
        //binding.get().productList.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.get().productList.setAdapter(rvAdapter);
        adapter = new AutoClearedValue<>(this, rvAdapter);

        binding.get().setCallback(() -> productListViewModel.refresh());

        //call for API
        productListViewModel.setQuery();
    }

    private void initRecyclerView() {

        productListViewModel.getProducts().observe(this, result -> {
            binding.get().setProductResource(result);
            binding.get().setResultCount((result == null || result.data == null)
                    ? 0 : result.data.size());
            adapter.get().replace(result == null ? null : result.data);
            binding.get().executePendingBindings();
        });
    }
}
