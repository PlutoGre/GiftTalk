package com.pluto.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.pluto.adapter.CategoryStrategyHeadRecyclerViewAdapter;
import com.pluto.adapter.CategoryStrategyListViewAdapter;
import com.pluto.adapter.CategoryStrategyRecyclerItemViewAdapter;
import com.pluto.adapter.CategoryStrategyRecyclerViewAdapter;
import com.pluto.bean.CategoryStrategyHeadInfo;
import com.pluto.bean.CategoryStrategyRecyclerViewInfo;
import com.pluto.gifttalk.R;
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
 * {@link CategoryStrategyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryStrategyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryStrategyFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View headView;

    public CategoryStrategyFragment() {
        // Required empty public constructor
    }

//    @Bind(R.id.rv_fg_category_strategy)
//    RecyclerView mRecyclerView;
    @Bind(R.id.lv_fg_category_strategy)
    ListView mListView;
    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity>
            channelGroupsEntityList = new ArrayList<>();
//    private CategoryStrategyRecyclerViewAdapter strategyRecyclerViewAdapter;
    private CategoryStrategyListViewAdapter strategyListViewAdapter;

    private CategoryStrategyHeadRecyclerViewAdapter headRecyclerViewAdapter;
    private List<CategoryStrategyHeadInfo.DataEntity.CollectionsEntity> collectionsEntityList = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryStrategyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryStrategyFragment newInstance(String param1, String param2) {
        CategoryStrategyFragment fragment = new CategoryStrategyFragment();
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
        View view = inflater.inflate(R.layout.fragment_category_strategy, container, false);
        ButterKnife.bind(this, view);

        //添加头部布局
        addHeadView();

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL ,false));
//        strategyRecyclerViewAdapter = new CategoryStrategyRecyclerViewAdapter(getActivity() , headView , channelGroupsEntityList);
//        mRecyclerView.setAdapter(strategyRecyclerViewAdapter);

        strategyListViewAdapter = new CategoryStrategyListViewAdapter(getActivity() , channelGroupsEntityList);
        mListView.setAdapter(strategyListViewAdapter);

        OkHttpTools.newInstance().okGet(UrlConfig.CATEGORY_STRATEGY_URL, CategoryStrategyRecyclerViewInfo.class
                ,
                new IOkCallBack<CategoryStrategyRecyclerViewInfo>() {
                    @Override
                    public void onSuccess(CategoryStrategyRecyclerViewInfo resultInfo, int requestCode) {
                        List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity> channel_groups = resultInfo.getData().getChannel_groups();
                        channelGroupsEntityList.addAll(channel_groups);
                        strategyListViewAdapter.notifyDataSetChanged();
                    }
                }, 2);

        return view;
    }

    /**
     * 给ListView添加头部布局
     */
    private void addHeadView() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.category_strategy_head , null);
        ViewHolder holder = new ViewHolder(headView);

        holder.rvSpecial.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL ,false));
        headRecyclerViewAdapter = new CategoryStrategyHeadRecyclerViewAdapter(getActivity() ,collectionsEntityList);
        holder.rvSpecial.setAdapter(headRecyclerViewAdapter);

        OkHttpTools.newInstance().okGet(UrlConfig.CATEGORY_STRATEGY_HEAD_URL, CategoryStrategyHeadInfo.class
                , new IOkCallBack<CategoryStrategyHeadInfo>() {
            @Override
            public void onSuccess(CategoryStrategyHeadInfo resultInfo, int requestCode) {
                List<CategoryStrategyHeadInfo.DataEntity.CollectionsEntity> collections = resultInfo.getData().getCollections();
                collectionsEntityList.clear();
                collectionsEntityList.addAll(collections);
                headRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, 1);

//        holder.rvSpecial.addView(headView ,0);
        mListView.addHeaderView(headView);
    }

    class ViewHolder{

        @Bind(R.id.btn_category_strategy_head_all)
        Button btnAll;
        @Bind(R.id.rv_category_strategy_head_special)
        RecyclerView rvSpecial;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
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
