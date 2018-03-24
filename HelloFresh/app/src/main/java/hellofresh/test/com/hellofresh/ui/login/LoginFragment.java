package hellofresh.test.com.hellofresh.ui.login;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentDataBindingComponent;
import hellofresh.test.com.hellofresh.databinding.LoginFragmentBinding;
import hellofresh.test.com.hellofresh.di.Injectable;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.ui.list.RecipeListViewModel;
import hellofresh.test.com.hellofresh.util.AutoClearedValue;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends LifecycleFragment implements Injectable {

    private static final String USER_EMAIL_ID = "rrmartin@got.com";
    private static final String USER_PASSWORD = "BurnThemAll";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    NavigationController navigationController;

    FragmentDataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<LoginFragmentBinding> binding;

    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginFragmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        loginViewModel.getIsAuthenticated().observe(this, isAuthenticated -> {
            if (isAuthenticated == null)
                return;

            if (isAuthenticated){
                //Authenticated
                navigationController.navigateToRecipeList();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.PREFS), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.HAS_LOGGED_IN), true);
                editor.apply();
            }
            else
            {
                //Wrong username or password
                binding.get().userEmailLayout.setError("Retry");
                binding.get().userPasswordLayout.setError("Retry");
            }
        });

        //binding.get().userEmail.setText(LoginFragment.USER_EMAIL_ID);
        //binding.get().userPassword.setText(LoginFragment.USER_PASSWORD);

        binding.get().loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.get().userEmail.getText().toString();
                String password = binding.get().userPassword.getText().toString();
                //Validate the input and authenticate the user
                if(validate(email, password))
                    loginViewModel.authenticate(email, password);
            }
        });
    }

    private boolean validate(String email, String password) {
        // Reset errors.
        binding.get().userEmailLayout.setError(null);
        binding.get().userPasswordLayout.setError(null);

        if (TextUtils.isEmpty(email)) {
            binding.get().userEmailLayout.setError("Email is required");
            return false;
        } else if (!isEmailValid(email)) {
            binding.get().userEmailLayout.setError("Enter a valid email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            binding.get().userPasswordLayout.setError("Password is required");
            return false;
        } else if (!isPasswordValid(password)) {
            binding.get().userPasswordLayout.setError("Password must contain at least 6 characters");
            return false;
        }

        return true;
    }

    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Check password with minimum requirement here(it should be minimum 6 characters)
    public static boolean isPasswordValid(String password){
        return password.length() >= 6;
    }
}
