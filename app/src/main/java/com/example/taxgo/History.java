package com.example.taxgo;

import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link History#newInstance} factory method to
 * create an instance of this fragment.
 */
public class History extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView memail;
    MaterialAlertDialogBuilder builder;

    ShimmerFrameLayout shimmerFrameLayout;

    NavigationView navigation;

    RecyclerView recyclerView;

    FloatingActionButton btn;

    ArrayList<History_DataModal> Data_holder;

    String server_url = "http://192.168.1.105/project/test2.php";

    public History() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static History newInstance(String param1, String param2) {
        History fragment = new History();
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
            ((MainActivity) getActivity()).setActionBarTitle("History");
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP && keycode == KeyEvent.KEYCODE_BACK){
                    Menu drawer_menu = navigation.getMenu();
                    MenuItem menuItem = drawer_menu.findItem(R.id.History);
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
       View  view =  inflater.inflate(R.layout.fragment_history, container, false);

       shimmerFrameLayout = view.findViewById(R.id.history_shimmer);
       shimmerFrameLayout.startShimmer();

       navigation = getActivity().findViewById(R.id.nav_view);

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

       if(!menuItem3.isChecked()){
           menuItem3.setChecked(true);

       }

       Data_holder = new ArrayList<>();

       recyclerView = view.findViewById(R.id.His_rv);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       memail = view.findViewById(R.id.email);


       //SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
       //String email = sharedPreferences.getString("useremail","irtazaarain14@gmail.com");

       //memail.setText(email);
        String email = "irtazaarain14@gmail.com";
        memail.setText(email);

       StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,response -> {
           builder = new MaterialAlertDialogBuilder(this.getActivity(), R.style.MyAlertDialogStyle)
                   .setTitle("Server Response")
                   .setMessage("Response :"+response);
           colloct(view);
           builder.create();
           builder.show();
           }
           , error -> {
           Toast.makeText(requireActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
           error.printStackTrace();

       }){
           @Override
           protected Map<String, String> getParams() {
               Map <String,String> Params = new HashMap<>();
               Params.put("email",email);
               return Params;
           }
       };

       My_Singleton.getInstance(requireActivity()).addToRequestQue(stringRequest);



       btn = view.findViewById(R.id.floatingActionButton);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   printPDF();
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }
           }
       });

       return  view;
    }

    public void colloct(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(view.GONE);
                            recyclerView.setVisibility(view.VISIBLE);
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                Data_holder.add(new History_DataModal(
                                        product.getString("date"),
                                        product.getString("time"),
                                        product.getString("method"),
                                        product.getString("amount")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            History_Adapter adapter = new History_Adapter((getContext()), Data_holder);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> {

                });

        //adding our string_request to queue
        My_Singleton.getInstance(getContext()).addToRequestQue(stringRequest);
    }
    public void printPDF() throws FileNotFoundException {

        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> amount = new ArrayList<>();

        description.add("Total Income");
        description.add("TAX Income");

        amount.add("363753");
        amount.add("363753");

        String date = new SimpleDateFormat("dd/LLL/yyyy", Locale.getDefault()).format(new Date()).toString();
        String time = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date()).toString();
        String period="08-Jan-2023 - 10-Jan-2024";
        String medium="Online";
        String due_date="15-Jan-2024";
        String valid_upto="10-Jan-2024";


        String pdf_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdf_path, "Transaction_Report.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        float columnWidth[] = {360};
        Table table = new Table(columnWidth);
        table.addCell(new Cell().add(new Paragraph("Transaction Report").setFontSize(20).setBold()).setBorder(Border.NO_BORDER));

        float columnWidth1[] = {120, 220};
        Table table1 = new Table(columnWidth1);

        table1.addCell(new Cell().add(new Paragraph("TAX Year:").setFontSize(14).
                setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(date).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Document Date: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(date).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Time: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(time).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Period: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(period).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Medium: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(medium).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Due Date: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(due_date).setFontSize(14).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Valid Upto: ").setFontSize(14).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph(valid_upto).setFontSize(14).setBorder(Border.NO_BORDER)));

        float columnWidth2[] = {350, 200};
        Table table2 = new Table(columnWidth2);

        table2.addCell(new Cell().add(new Paragraph("Description")
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setBackgroundColor(WebColors.getRGBColor("#FFFFFF"))
                .setFontColor(WebColors.getRGBColor("#3ab54c"))));
        table2.addCell(new Cell().add(new Paragraph("  Amount (Rs.)")
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setBackgroundColor(WebColors.getRGBColor("#FFFFFF"))
                .setFontColor(WebColors.getRGBColor("#3ab54c"))));

        for (int i=0; i<description.size(); i++) {
            table2.addCell(new Cell().add(new Paragraph(description.get(i))));
            table2.addCell(new Cell().add(new Paragraph(amount.get(i)).setTextAlignment(TextAlignment.RIGHT)));
        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(table1);
        document.add(new Paragraph("\n"));
        document.add(table2);
        document.close();

        Toast.makeText(getActivity(), "Successfully Created", Toast.LENGTH_SHORT).show();


    }
}