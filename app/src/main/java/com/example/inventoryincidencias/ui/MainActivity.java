package com.example.inventoryincidencias.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.ui.preferences.UserPrefManager;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.inventoryincidencias.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.SplashFragment, R.id.dashBoardFragment, R.id.LoginFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Con esta opción accedo al fichero por defecto de las preferencias que se llaman com.moronlu18.inin_preferences.xml
            //SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

            // Acceder a las preferencias por defecto desde un fragment
            //SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.settingsFragment);
            return true;
        }

        if (id == R.id.action_logout) {
            new UserPrefManager(this).logout();
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.LoginFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}