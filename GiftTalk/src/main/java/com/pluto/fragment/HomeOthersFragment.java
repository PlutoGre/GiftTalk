package com.pluto.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pluto.adapter.HomeOthersListViewAdapter;
import com.pluto.bean.HomeOthersListVIewInfo;
import com.pluto.gifttalk.MainActivity;
import com.pluto.gifttalk.R;
import com.pluto.gifttalk.WebActivity;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;
import com.pluto.http.UrlConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeOthersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeOthersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeOthersFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeOthersFragment() {
        // Required empty public constructor
    }

    //其它界面的ListView
    @Bind(R.id.lv_fg_home_others)
    ListView mListView;
    private List<HomeOthersListVIewInfo.DataEntity.ItemsEntity> itemsEntityList = new ArrayList<>();
    private HomeOthersListViewAdapter listViewAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeOthersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeOthersFragment newInstance(String param1, String param2) {
        HomeOthersFragment fragment = new HomeOthersFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_others, container, false);
        ButterKnife.bind(this, view);
        String url = UrlConfig.HOME_OTHERS_URL_1 + mParam1 + UrlConfig.HOME_OTHER_URL_2;
        OkHttpTools.newInstance().okGet(url, HomeOthersListVIewInfo.class, new IOkCallBack<HomeOthersListVIewInfo>() {
            @Override
            public void onSuccess(HomeOthersListVIewInfo resultInfo, int requestCode) {
                List<HomeOthersListVIewInfo.DataEntity.ItemsEntity> items = resultInfo.getData().getItems();
                itemsEntityList.addAll(items);
                listViewAdapter.notifyDataSetChanged();
            }
        }, 1);

        listViewAdapter = new HomeOthersListViewAdapter(getActivity() , itemsEntityList);
        mListView.setAdapter(listViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url" , itemsEntityList.get(position).getContent_url());
                intent.putExtra("title" , itemsEntityList.get(position).getTitle());
                intent.putExtra("image_url" , itemsEntityList.get(position).getCover_webp_url());
                startActivity(intent);
            }
        });
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
