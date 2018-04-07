package hellofresh.test.com.hellofresh.ui.list;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentDataBindingComponent;
import hellofresh.test.com.hellofresh.databinding.RecipeListFragmentBinding;
import hellofresh.test.com.hellofresh.di.Injectable;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.ui.common.RecipeListAdapter;
import hellofresh.test.com.hellofresh.util.AutoClearedValue;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipeListFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

    @Inject
    public  ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public NavigationController navigationController;

    public FragmentDataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    public AutoClearedValue<RecipeListFragmentBinding> binding;

    AutoClearedValue<RecipeListAdapter> adapter;

    private RecipeListViewModel recipeListViewModel;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecipeListFragmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_list_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipeListViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeListViewModel.class);
        initRecyclerView();
        RecipeListAdapter rvAdapter = new RecipeListAdapter(dataBindingComponent, true,
                recipe -> navigationController.navigateToRecipeDetail(recipe.id));
        //binding.get().recipeList.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.get().recipeList.setAdapter(rvAdapter);
        adapter = new AutoClearedValue<>(this, rvAdapter);

        binding.get().setCallback(() -> recipeListViewModel.refresh());

        //call for API
        recipeListViewModel.setQuery();
    }

    private void initRecyclerView() {

        recipeListViewModel.getRecipes().observe(this, result -> {
            binding.get().setRecipeResource(result);
            binding.get().setResultCount((result == null || result.data == null)
                    ? 0 : result.data.size());
            adapter.get().replace(result == null ? null : result.data);
            binding.get().executePendingBindings();
        });
    }
}
