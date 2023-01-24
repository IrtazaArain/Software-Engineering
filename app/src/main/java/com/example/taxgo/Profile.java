package com.example.taxgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText Name,Email,CNIC,Number;

    Button button;
    NavigationView navigation;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
            ((MainActivity) getActivity()).setActionBarTitle("Profile");
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP && keycode == KeyEvent.KEYCODE_BACK){
                    Menu drawer_menu = navigation.getMenu();
                    MenuItem menuItem = drawer_menu.findItem(R.id.view_profile);
                    menuItem.setChecked(false);
                    getActivity().onBackPressed();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        Name = view.findViewById(R.id.person_name);
        Email = view.findViewById(R.id.person_email);
        CNIC = view.findViewById(R.id.person_cnic);
        Number = view.findViewById(R.id.person_number);

        Intent i = getActivity().getIntent();
        String mName = i.getStringExtra("name");
        String mEmail = i.getStringExtra("email");
        String mCnic = i.getStringExtra("cnic");
        String mNumber = i.getStringExtra("number");

        Name.setText(mName);
        Email.setText(mEmail);
        CNIC.setText(mCnic);
        Number.setText(mNumber);

        navigation = (NavigationView)getActivity().findViewById(R.id.nav_view);

        Menu drawer_menu = navigation.getMenu();

        MenuItem menuItem;

        menuItem = drawer_menu.findItem(R.id.home);

        MenuItem menuItem1 = drawer_menu.findItem(R.id.home);
        MenuItem menuItem2 = drawer_menu.findItem(R.id.transaction);
        MenuItem menuItem3 = drawer_menu.findItem(R.id.History);
        MenuItem menuItem4 = drawer_menu.findItem(R.id.about_us);
        MenuItem menuItem5 = drawer_menu.findItem(R.id.view_profile);
        MenuItem menuItem6 = drawer_menu.findItem(R.id.faq);

        menuItem.setChecked(false);
        menuItem1.setChecked(false);
        menuItem2.setChecked(false);
        menuItem3.setChecked(false);
        menuItem4.setChecked(false);
        menuItem5.setChecked(false);
        menuItem6.setChecked(false);

        if(!menuItem5.isChecked()){



            menuItem5.setChecked(true);

        }

        button = view.findViewById(R.id.update_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}