package com.example.taxgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CardView button,button1,button2,button3,button4;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getActivity()!=null) {
            ((MainActivity) getActivity()).setActionBarTitle("Home");
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP && keycode == KeyEvent.KEYCODE_BACK){
                    getActivity().finishAffinity();
                    getActivity().finish();
                    System.exit(0);
                    return true;
                }

                return false;
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        NavigationView navigation = (NavigationView)getActivity().findViewById(R.id.nav_view);

        Menu drawer_menu = navigation.getMenu();

        MenuItem menuItem;

        MenuItem menuItem1 = drawer_menu.findItem(R.id.home);
        MenuItem menuItem2 = drawer_menu.findItem(R.id.transaction);
        MenuItem menuItem3 = drawer_menu.findItem(R.id.History);
        MenuItem menuItem4 = drawer_menu.findItem(R.id.about_us);
        MenuItem menuItem5 = drawer_menu.findItem(R.id.view_profile);
        MenuItem menuItem6 = drawer_menu.findItem(R.id.faq);

        menuItem1.setChecked(false);
        menuItem2.setChecked(false);
        menuItem3.setChecked(false);
        menuItem4.setChecked(false);
        menuItem5.setChecked(false);
        menuItem6.setChecked(false);

        if(!menuItem1.isChecked()){
            menuItem1.setChecked(true);

        }

        button = view.findViewById(R.id.cardView2);
        button1 = view.findViewById(R.id.cardView4);
        button2 = view.findViewById(R.id.cardView5);
        button3 = view.findViewById(R.id.cardview3);
        button4 = view.findViewById(R.id.cardView6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicle_tax();
            }
            public void vehicle_tax() {
                Intent intent = new Intent(getActivity(), Vehicle.class);
                startActivity(intent);
            }
        });

        
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculate();
            }
                public void Calculate() {
                    Intent intent = new Intent(getActivity(), Calculator.class);
                    startActivity(intent);
                }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                property_tax();
            }
            public void property_tax() {
                Intent intent = new Intent(getActivity(), Property.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub_Appointment();
            }
            public void sub_Appointment() {
                Intent intent = new Intent(getActivity(), Appointment.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assets_inquiry();
            }
            public void assets_inquiry() {
                Intent intent = new Intent(getActivity(), Assets_inquiry.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
