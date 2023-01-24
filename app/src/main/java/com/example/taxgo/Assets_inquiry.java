package com.example.taxgo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Assets_inquiry extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_inquiry);

        tabLayout = findViewById(R.id.asset_inq_tabLayout);
        viewPager = findViewById(R.id.asset_inq_VP);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Property_Inquiry(), "PROPERTY");
        vpAdapter.addFragment(new Vehicle_Inquiry(), "VEHICLE");
        viewPager.setAdapter(vpAdapter);



    }
}