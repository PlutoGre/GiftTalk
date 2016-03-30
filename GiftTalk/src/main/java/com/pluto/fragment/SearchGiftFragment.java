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
import android.widget.GridView;

import com.pluto.adapter.CategoryGiftDetailAdapter;
import com.pluto.bean.CategoryGiftDetailInfo;
import com.pluto.gifttalk.R;
import com.pluto.gifttalk.SelectDetailActivity;
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
 * {@link SearchGiftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchGiftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchGiftFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchGiftFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.gv_fg_search_gift_select)
    GridView mGridView;
    private List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> dataEntity1List =
            new ArrayList<>();
    private CategoryGiftDetailAdapter gridViewAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchGiftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchGiftFragment newInstance(String param1, String param2) {
        SearchGiftFragment fragment = new SearchGiftFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_gift, container, false);
        ButterKnife.bind(this, view);

        gridViewAdapter = new CategoryGiftDetailAdapter(getActivity(), dataEntity1List);
        mGridView.setAdapter(gridViewAdapter);

        OkHttpTools.newInstance().okGet(mParam1, CategoryGiftDetailInfo.class
                , new IOkCallBack<CategoryGiftDetailInfo>() {
            @Override
            public void onSuccess(CategoryGiftDetailInfo resultInfo, int requestCode) {
                List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> items = resultInfo.getData().getItems();
                if (items == null) {
                    return;
                } else {
                    dataEntity1List.addAll(items);
                    gridViewAdapter.notifyDataSetChanged();
                }
            }
        }, 1);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SelectDetailActivity.class);
                intent.putExtra("id", dataEntity1List.get(position).getId() + "");
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
