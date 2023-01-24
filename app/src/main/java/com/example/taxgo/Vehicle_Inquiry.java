package com.example.taxgo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Vehicle_Inquiry#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Vehicle_Inquiry extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    ArrayList<Vehicle_Inquiry_DataModal> Data_holder;

    String server_url = "http://192.168.1.105/project/fatch_vehicle_data.php";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleInquiryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Vehicle_Inquiry newInstance(String param1, String param2) {
        Vehicle_Inquiry fragment = new Vehicle_Inquiry();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Vehicle_Inquiry() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_vehicle_inquiry, container, false);

        recyclerView = view.findViewById(R.id.veh_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Data_holder = new ArrayList<>();



        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                Data_holder.add(new Vehicle_Inquiry_DataModal(
                                        product.getString("vehicle_class"),
                                        product.getString("purchase_type"),
                                        product.getString("ownership_status"),
                                        product.getString("price"),
                                        product.getString("seating_capacity"),
                                        product.getString("import_purchase_date"),
                                        product.getString("engine_size")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Vehicle_Inquiry_Adapter adapter = new Vehicle_Inquiry_Adapter((getContext()), Data_holder);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our string_request to queue
        My_Singleton.getInstance(getContext()).addToRequestQue(stringRequest);


        return view;
    }
}