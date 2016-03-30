package com.pluto.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pluto.adapter.HomeOthersListViewAdapter;
import com.pluto.adapter.SearchStrategyListViewAdapter;
import com.pluto.bean.HomeOthersListVIewInfo;
import com.pluto.bean.SearchStrategyInfo;
import com.pluto.gifttalk.R;
import com.pluto.gifttalk.WebActivity;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchStrategyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchStrategyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchStrategyFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchStrategyFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.lv_fg_search_strategy)
    ListView mListView;
    private List<SearchStrategyInfo.DataEntity.PostsEntity> itemsEntityList = new ArrayList<>();
    private SearchStrategyListViewAdapter listViewAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchStrategyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchStrategyFragment newInstance(String param1, String param2) {
        SearchStrategyFragment fragment = new SearchStrategyFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_strategy, container, false);
        ButterKnife.bind(this, view);
        Log.d("heyang3", "onCreateView: ------------->" + mParam1);
        OkHttpTools.newInstance().okGet(mParam1, SearchStrategyInfo.class, new IOkCallBack<SearchStrategyInfo>() {
            @Override
            public void onSuccess(SearchStrategyInfo resultInfo, int requestCode) {
                Log.d("heyang3", "onCreateView: ------------->" + resultInfo.getData().getPosts());
                List<SearchStrategyInfo.DataEntity.PostsEntity> items = resultInfo.getData().getPosts();
                if (items == null) {
                    return;
                } else {
                    itemsEntityList.addAll(items);
                    listViewAdapter.notifyDataSetChanged();
                }
            }
        }, 1);

        listViewAdapter = new SearchStrategyListViewAdapter(getActivity(), itemsEntityList);
        mListView.setAdapter(listViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                String url = "http://www.liwushuo.com/posts/" + itemsEntityList.get(position).getId() + "/content";
                intent.putExtra("url", url);
                intent.putExtra("title", itemsEntityList.get(position).getTitle());
                intent.putExtra("image_url", itemsEntityList.get(position).getCover_webp_url());
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
