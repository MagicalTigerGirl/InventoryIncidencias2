package com.example.inventoryincidencias.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.ui.login.LoginFragment;
import com.example.inventoryincidencias.ui.preferences.UserPrefManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.inventoryincidencias.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.contentMain.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // OPCION 2: MOSRTAR LOS NIVELES DE FRAGMENTS PARA QUE NO SE MUESTRE LA FLECHA
        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.dashBoardFragment);
        topLevelDestination.add(R.id.sectionFragment);
        topLevelDestination.add(R.id.dependencyListFragment);

        // Con este método gestionamos la BARRA DE ACCIÓN, cuando hay varios niveles de navegación
        // OPCIÓN 1
        /*appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.SplashFragment, R.id.dashBoardFragment, R.id.LoginFragment).setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/

        // OPCION 2
        appBarConfiguration =
                new AppBarConfiguration.Builder(topLevelDestination).setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // A continuación se configura las opciones del componente NavigationView
        setUpNavigationView();

        // Se inicializa firebase
        FirebaseApp.initializeApp(this);
    }

    /**
     * Este método configura los eventos del menú de NavigationView a través del listener
     */
    private void setUpNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dependencyListFragment:
                        navController.navigate(R.id.dependencyListFragment);
                        break;
                    case R.id.sectionFragment:
                        navController.navigate(R.id.sectionFragment);
                        break;
                    case R.id.settingInventoryFragment:
                        navController.navigate(R.id.settingInventoryFragment);
                        break;
                    case R.id.settingsFragment:
                        navController.navigate(R.id.settingsFragment);
                        break;
                    case R.id.action_logout:
                        // Al añadir el menú y no ser parte del Splash, ni Login, ni SignUp estos Fragments no deben ser fragment de MainActivity.
                        // Son Activity ya independientes
                        //startActivity(MainActivity.this, LoginActivity.class);
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    @Override
    public void onBackPressed() {
        // binding.drawerLayout.isOpen()
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        else // Dejamos que el sistema operativo decida qué hacer
            super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        FirebaseAuth.getInstance().signOut();
    }
}