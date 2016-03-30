package com.pluto.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pluto.adapter.CategoryGiftRightListViewAdapter;
import com.pluto.adapter.CategoryGiftTitleListViewAdapter;
import com.pluto.bean.CategoryGiftInfo;
import com.pluto.gifttalk.R;
import com.pluto.gifttalk.SelectGiftGodActivity;
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
 * {@link CategoryGiftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryGiftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryGiftFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CategoryGiftFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.lv_category_gift_title)
    ListView lvTitle;
    @Bind(R.id.lv_category_gift_detail)
    ListView lvDetail;

    @Bind(R.id.btn_select_gift_god)
    Button btnSelectGiftGod;

    private int titlePosition = -1;
    private int detailPosition = -1;


    private CategoryGiftTitleListViewAdapter titleListViewAdapter;
    private List<CategoryGiftInfo.DataEntity.CategoriesEntity> categoriesEntityList = new ArrayList<>();

    private CategoryGiftRightListViewAdapter rightListViewAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryGiftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryGiftFragment newInstance(String param1, String param2) {
        CategoryGiftFragment fragment = new CategoryGiftFragment();
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
        View view = inflater.inflate(R.layout.fragment_category_gift, container, false);
        ButterKnife.bind(this , view);

        titleListViewAdapter = new CategoryGiftTitleListViewAdapter(getActivity() , categoriesEntityList);
        lvTitle.setAdapter(titleListViewAdapter);
        OkHttpTools.newInstance().okGet(UrlConfig.CATEGORY_GIFT_URL, CategoryGiftInfo.class
                , new IOkCallBack<CategoryGiftInfo>() {
            @Override
            public void onSuccess(CategoryGiftInfo resultInfo, int requestCode) {
                categoriesEntityList.addAll(resultInfo.getData().getCategories());
                titleListViewAdapter.notifyDataSetChanged();

                rightListViewAdapter.notifyDataSetChanged();
            }
        }, 1);
//        lvTitle.setItemChecked(0 , true);

        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("heyang", "onItemClick: -----------" + categoriesEntityList.size());
                Log.d("heyang", "onItemClick: -----------" + position);
//                lvTitle.setItemChecked(position , true);
                titleListViewAdapter.setSelectPosition(position);
                lvDetail.setSelection(position);
                titleListViewAdapter.notifyDataSetChanged();
//                if(position >= 1){
//                    lvTitle.setSelection(position - 1);
//                }else {
//                    lvTitle.setSelection(position);
//                }
                lvTitle.smoothScrollToPositionFromTop(position , 150);
//                titlePosition = position;
            }
        });

        lvDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int firstVisiblePosition = lvDetail.getFirstVisiblePosition();
//                lvTitle.setSelectionFromTop(firstVisiblePosition, 40);
                lvTitle.smoothScrollToPositionFromTop(firstVisiblePosition , 150);
                titleListViewAdapter.setSelectPosition(firstVisiblePosition);
                titleListViewAdapter.notifyDataSetChanged();
                if(firstVisiblePosition >= 1){
                    lvTitle.setSelection(firstVisiblePosition - 1);
                }else {
                    lvTitle.setSelection(firstVisiblePosition);
                }
                return false;
            }
        });

        rightListViewAdapter = new CategoryGiftRightListViewAdapter(getActivity() , categoriesEntityList);
        lvDetail.setAdapter(rightListViewAdapter);

        btnSelectGiftGod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , SelectGiftGodActivity.class);
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
