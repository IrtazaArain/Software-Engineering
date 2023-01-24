package com.example.taxgo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FAQ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQ extends Fragment {
    NavigationView navigation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CardView cardView1,cardView2,cardView3,cardView4,cardView5;
    ImageView arrow1,arrow2,arrow3,arrow4,arrow5,play1,play2,play3,play4,play5;
    Group hiddenGroup1,hiddenGroup2,hiddenGroup3,hiddenGroup4,hiddenGroup5;

    public FAQ() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Faq_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQ newInstance(String param1, String param2) {
        FAQ fragment = new FAQ();
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
            ((MainActivity) getActivity()).setActionBarTitle("FAQ");
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP && keycode == KeyEvent.KEYCODE_BACK){
                    Menu drawer_menu = navigation.getMenu();
                    MenuItem menuItem = drawer_menu.findItem(R.id.faq);
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
        View view = inflater.inflate(R.layout.fragment_faq_, container, false);

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

        if(!menuItem6.isChecked()){
        menuItem6.setChecked(true);

    }


        cardView1 = view.findViewById(R.id.base_cardview_1);
        arrow1 = view.findViewById(R.id.show1);
        hiddenGroup1 = view.findViewById(R.id.card_group_1);

        cardView2 = view.findViewById(R.id.base_cardview_2);
        arrow2 = view.findViewById(R.id.show2);
        hiddenGroup2 = view.findViewById(R.id.card_group_2);

        cardView3 = view.findViewById(R.id.base_cardview_3);
        arrow3 = view.findViewById(R.id.show3);
        hiddenGroup3 = view.findViewById(R.id.card_group_3);

        cardView4 = view.findViewById(R.id.base_cardview_4);
        arrow4 = view.findViewById(R.id.show4);
        hiddenGroup4 = view.findViewById(R.id.card_group_4);

        cardView5 = view.findViewById(R.id.base_cardview_5);
        arrow5 = view.findViewById(R.id.show5);
        hiddenGroup5 = view.findViewById(R.id.card_group_5);



        play1 = view.findViewById(R.id.play1);
        final MediaPlayer[] mediaPlayerQ1 = {new MediaPlayer()};

        play2 = view.findViewById(R.id.play2);
        final MediaPlayer[] mediaPlayerQ2 = {new MediaPlayer()};

        play3 = view.findViewById(R.id.play3);
        final MediaPlayer[] mediaPlayerQ3 = {new MediaPlayer()};

        play4 = view.findViewById(R.id.play4);
        final MediaPlayer[] mediaPlayerQ4 = {new MediaPlayer()};

        play5 = view.findViewById(R.id.play5);
        final MediaPlayer[] mediaPlayerQ5 = {new MediaPlayer()};


        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup1.getVisibility() == View.VISIBLE){
                    hiddenGroup1.setVisibility(View.GONE);
                    arrow1.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ1[0].stop();
                    mediaPlayerQ1[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    arrow1.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup1.setVisibility(View.VISIBLE);
                    play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ1[0] = new MediaPlayer();
                }
            }
        });
        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayerQ1[0].isPlaying()) {
                        mediaPlayerQ1[0].stop();
                        //mediaPlayerQ1[0].release();
                        play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ1[0] = new MediaPlayer();
                    }
                    else{

                        play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ2[0].stop();
                        mediaPlayerQ2[0] = new MediaPlayer();

                        play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ3[0].stop();
                        mediaPlayerQ3[0] = new MediaPlayer();

                        play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ4[0].stop();
                        mediaPlayerQ4[0] = new MediaPlayer();

                        play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ5[0].stop();
                        mediaPlayerQ5[0] = new MediaPlayer();

                        AssetFileDescriptor descriptor = getActivity().getAssets().openFd("ans1.mp3");
                        mediaPlayerQ1[0].setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mediaPlayerQ1[0].prepare();
                        mediaPlayerQ1[0].setVolume(3f, 3f);
                        mediaPlayerQ1[0].start();
                        play1.setImageResource(R.drawable.ic_baseline_pause_24);

                        mediaPlayerQ1[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayerQ1[0].stop();
                                mediaPlayerQ1[0].release();
                                play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                mediaPlayerQ1[0] = new MediaPlayer();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup2.getVisibility() == View.VISIBLE){
                    hiddenGroup2.setVisibility(View.GONE);
                    arrow2.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ2[0].stop();
                    mediaPlayerQ2[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    arrow2.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup2.setVisibility(View.VISIBLE);
                    play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ2[0] = new MediaPlayer();
                }
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayerQ2[0].isPlaying()) {
                        mediaPlayerQ2[0].stop();
                        //mediaPlayerQ2[0].release();
                        play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ2[0] = new MediaPlayer();
                    }
                    else{
                        play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ1[0].stop();
                        mediaPlayerQ1[0] = new MediaPlayer();

                        play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ3[0].stop();
                        mediaPlayerQ3[0] = new MediaPlayer();

                        play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ4[0].stop();
                        mediaPlayerQ4[0] = new MediaPlayer();

                        play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ5[0].stop();
                        mediaPlayerQ5[0] = new MediaPlayer();

                        AssetFileDescriptor descriptor = getActivity().getAssets().openFd("ans2.mp3");
                        mediaPlayerQ2[0].setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mediaPlayerQ2[0].prepare();
                        mediaPlayerQ2[0].setVolume(3f, 3f);
                        mediaPlayerQ2[0].start();
                        play2.setImageResource(R.drawable.ic_baseline_pause_24);

                        mediaPlayerQ2[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayerQ2[0].stop();
                                mediaPlayerQ2[0].release();
                                play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                mediaPlayerQ2[0] = new MediaPlayer();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup3.getVisibility() == View.VISIBLE){
                    hiddenGroup3.setVisibility(View.GONE);
                    arrow3.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ3[0].stop();
                    mediaPlayerQ3[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    arrow3.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup3.setVisibility(View.VISIBLE);
                    play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ3[0] = new MediaPlayer();
                }
            }
        });
        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayerQ3[0].isPlaying()) {
                        mediaPlayerQ3[0].stop();
                        //mediaPlayerQ1[0].release();
                        play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ3[0] = new MediaPlayer();
                    }
                    else{

                        play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ2[0].stop();
                        mediaPlayerQ2[0] = new MediaPlayer();

                        play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ1[0].stop();
                        mediaPlayerQ1[0] = new MediaPlayer();

                        play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ4[0].stop();
                        mediaPlayerQ4[0] = new MediaPlayer();

                        play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ5[0].stop();
                        mediaPlayerQ5[0] = new MediaPlayer();

                        AssetFileDescriptor descriptor = getActivity().getAssets().openFd("ans3.mp3");
                        mediaPlayerQ3[0].setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mediaPlayerQ3[0].prepare();
                        mediaPlayerQ3[0].setVolume(3f, 3f);
                        mediaPlayerQ3[0].start();
                        play3.setImageResource(R.drawable.ic_baseline_pause_24);

                        mediaPlayerQ3[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayerQ3[0].stop();
                                mediaPlayerQ3[0].release();
                                play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                mediaPlayerQ3[0] = new MediaPlayer();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup4.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    hiddenGroup4.setVisibility(View.GONE);
                    arrow4.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ4[0].stop();
                    mediaPlayerQ4[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    arrow4.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup4.setVisibility(View.VISIBLE);
                    play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ4[0] = new MediaPlayer();
                }
            }
        });

        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayerQ4[0].isPlaying()) {
                        mediaPlayerQ4[0].stop();
                        //mediaPlayerQ1[0].release();
                        play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ4[0] = new MediaPlayer();
                    }
                    else{

                        play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ5[0].stop();
                        mediaPlayerQ5[0] = new MediaPlayer();

                        play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ3[0].stop();
                        mediaPlayerQ3[0] = new MediaPlayer();

                        play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ2[0].stop();
                        mediaPlayerQ2[0] = new MediaPlayer();

                        play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ1[0].stop();
                        mediaPlayerQ1[0] = new MediaPlayer();


                        AssetFileDescriptor descriptor = getActivity().getAssets().openFd("ans4.mp3");
                        mediaPlayerQ4[0].setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mediaPlayerQ4[0].prepare();
                        mediaPlayerQ4[0].setVolume(3f, 3f);
                        mediaPlayerQ4[0].start();
                        play4.setImageResource(R.drawable.ic_baseline_pause_24);

                        mediaPlayerQ4[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayerQ4[0].stop();
                                mediaPlayerQ4[0].release();
                                play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                mediaPlayerQ4[0] = new MediaPlayer();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup4.getVisibility() == View.VISIBLE){
                    hiddenGroup4.setVisibility(View.GONE);
                    arrow4.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ4[0].stop();
                    mediaPlayerQ4[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    arrow4.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup4.setVisibility(View.VISIBLE);
                    play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ4[0] = new MediaPlayer();
                }
            }
        });

        play5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayerQ5[0].isPlaying()) {
                        mediaPlayerQ5[0].stop();
                        //mediaPlayerQ1[0].release();
                        play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ5[0] = new MediaPlayer();
                    }
                    else{

                        play4.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ4[0].stop();
                        mediaPlayerQ4[0] = new MediaPlayer();

                        play3.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ3[0].stop();
                        mediaPlayerQ3[0] = new MediaPlayer();

                        play2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ2[0].stop();
                        mediaPlayerQ2[0] = new MediaPlayer();

                        play1.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        mediaPlayerQ1[0].stop();
                        mediaPlayerQ1[0] = new MediaPlayer();


                        AssetFileDescriptor descriptor = getActivity().getAssets().openFd("ans5.mp3");
                        mediaPlayerQ5[0].setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mediaPlayerQ5[0].prepare();
                        mediaPlayerQ5[0].setVolume(3f, 3f);
                        mediaPlayerQ5[0].start();
                        play5.setImageResource(R.drawable.ic_baseline_pause_24);

                        mediaPlayerQ5[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayerQ5[0].stop();
                                mediaPlayerQ5[0].release();
                                play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                mediaPlayerQ5[0] = new MediaPlayer();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenGroup5.getVisibility() == View.VISIBLE){
                    hiddenGroup5.setVisibility(View.GONE);
                    arrow5.setImageResource(android.R.drawable.arrow_down_float);
                    mediaPlayerQ5[0].stop();
                    mediaPlayerQ5[0] = new MediaPlayer();
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    arrow5.setImageResource(android.R.drawable.arrow_up_float);
                    hiddenGroup5.setVisibility(View.VISIBLE);
                    play5.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayerQ5[0] = new MediaPlayer();
                }
            }
        });

        return view;
    }
}