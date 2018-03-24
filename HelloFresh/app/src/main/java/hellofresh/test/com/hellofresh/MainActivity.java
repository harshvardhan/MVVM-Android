package hellofresh.test.com.hellofresh;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            boolean hasLoggedIn = getSharedPreferences(getString(R.string.PREFS), Context.MODE_PRIVATE).getBoolean(getString(R.string.HAS_LOGGED_IN), false);
            if (hasLoggedIn)
                navigationController.navigateToRecipeList();
            else
                navigationController.navigateToLogin();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
