package com.pluto.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.pluto.adapter.HomeFragmentViewPagerAdapter;
import com.pluto.adapter.PopupWindowGridViewAdapter;
import com.pluto.bean.HomeTabProductInfo;
import com.pluto.gifttalk.R;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this com.pluto.fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this com.pluto.fragment.
 */
public class HomeFragment extends BaseFragment {

    public static final String TAB_URL = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";

    // the com.pluto.fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private PopupWindow popupWindow;
    private GridView mGridView;
    private PopupWindowGridViewAdapter popupWindowGridViewAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    //使用黄油刀进行控件的初始化
    @Bind(R.id.tl_fg_home)
    TabLayout mTabLayout;
    @Bind(R.id.vp_fg_home)
    ViewPager mViewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private HomeFragmentViewPagerAdapter pagerAdapter;

    @Bind(R.id.ib_fg_home)
    ImageButton imageButtonDown;
    @Bind(R.id.fl_popup_window_home)
    FrameLayout frameLayout;
    @Bind(R.id.ib_popup_window_home)
    ImageButton imageButtonUp;


    /**
     * Use this factory method to create a new instance of
     * this com.pluto.fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of com.pluto.fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this com.pluto.fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        //绑定黄油刀
        ButterKnife.bind(this, view);

        imageButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = mViewPager.getHeight();
                View popView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window_home, null);
                popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT
                        , height);

                mGridView = (GridView) popView.findViewById(R.id.mgv_popup_window_home);
                popupWindowGridViewAdapter = new PopupWindowGridViewAdapter(getActivity(), titleList);
                mGridView.setAdapter(popupWindowGridViewAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        frameLayout.setVisibility(View.INVISIBLE);
                        mTabLayout.setVisibility(View.VISIBLE);
                        imageButtonDown.setVisibility(View.VISIBLE);
                        popupWindow.dismiss();
                        mViewPager.setCurrentItem(position);
                    }
                });

                popupWindow.setContentView(popView);
                popupWindow.setOutsideTouchable(true);

                popupWindow.showAsDropDown(mTabLayout, 0, 0, Gravity.CENTER);
                frameLayout.setVisibility(View.VISIBLE);
                mTabLayout.setVisibility(View.INVISIBLE);
                imageButtonDown.setVisibility(View.INVISIBLE);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.setFocusable(true);
                        return false;
                    }
                });
                popupWindow.setFocusable(false);
            }
        });

        imageButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.INVISIBLE);
                mTabLayout.setVisibility(View.VISIBLE);
                imageButtonDown.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
            }
        });
//        setUpViewPager();
        setUpTabLayout();
//        HttpUtils.requestGet(TAB_URL,requestCallBack , 1);
        OkHttpTools.newInstance().okGet(TAB_URL, HomeTabProductInfo.class ,new IOkCallBack<HomeTabProductInfo>() {
            @Override
            public void onSuccess(HomeTabProductInfo result , int requestCode) {
            List<HomeTabProductInfo.DataEntity.ChannelsEntity> channelsEntities =
                    result.getData().getChannels();
            for(int i = 0 ;i < channelsEntities.size(); i++){
                titleList.add(channelsEntities.get(i).getName());
                if(i == 0){
                    fragmentList.add(HandPickFragment.newInstance(null , null));
                }else {
                    fragmentList.add(HomeOthersFragment.newInstance(channelsEntities.get(i).getId() + "" , null));
                }
            }
            setUpViewPager();
            }
        } , 1);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        frameLayout.setVisibility(View.INVISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        imageButtonDown.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        frameLayout.setVisibility(View.INVISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        imageButtonDown.setVisibility(View.VISIBLE);
    }

    private void setUpTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /**
     * 设置ViewPager
     */
    private void setUpViewPager() {

        pagerAdapter = new HomeFragmentViewPagerAdapter(getActivity().getSupportFragmentManager()
                , fragmentList, titleList);
        mViewPager.setAdapter(pagerAdapter);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
