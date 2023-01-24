package com.example.taxgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.log_out).setOnMenuItemClickListener(menuItem -> {
            logout();
            return true;
        });

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);

        loadFragment(new Home(), "Home");
        toolbar.setTitle("Home");
        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment;
                switch (id)
                {
                    case R.id.home:
                        fragment = new Home();
                        loadFragment(fragment, "Home");
                        break;
                    case R.id.transaction:
                        fragment = new Payment();
                        loadFragment(fragment, "Payment");
                        break;
                    case R.id.History:
                        fragment = new History();
                        loadFragment(fragment, "History");
                        break;
                    case R.id.view_profile:
                        fragment = new Profile();
                        loadFragment(fragment, "Profile");
                        break;
                    case R.id.faq:
                        fragment = new FAQ();
                        loadFragment(fragment, "FAQ");
                        break;
                    case R.id.about_us:
                        fragment = new About();
                        loadFragment(fragment, "About");
                        break;
                }

            return true;
            }
        });
    }

    private void logout() {
        SharedPreferences preferences = this.getSharedPreferences(Login.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(this, Login.class));
        this.finish();
    }

    public void setActionBarTitle(String title) {
        toolbar.setTitle(title);
    }

    private void loadFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        toolbar.setTitle(title);
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}