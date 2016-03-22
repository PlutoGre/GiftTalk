package com.pluto.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.pluto.adapter.ExpandAdapter;
import com.pluto.adapter.HandPickRecyclerViewAdapter;
import com.pluto.bean.HandPickContentProductInfo;
import com.pluto.bean.HandPickRecyclerViewInfo;
import com.pluto.bean.HandPickViewPagerProductInfo;
import com.pluto.gifttalk.R;
import com.pluto.gifttalk.WebActivity;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;
import com.pluto.http.UrlConfig;
import com.pluto.tools.DateFormatTool;
import com.pluto.tools.LogTool;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HandPickFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HandPickFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HandPickFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public HandPickFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.elv_fg_hand_pick)
    ExpandableListView mExpandableListView;

    private ExpandAdapter expandAdapter;
    private HeaderViewHolder headerViewHolder;
    private List<String> mViewPagerUrlList = new ArrayList<>();

    //分组数据
    private Map<String, List<HandPickContentProductInfo.DataEntity.ItemsEntity>> itemsMap =
            new HashMap<>();
    private List<String> groupTitleList = new ArrayList<>();

    //横向的RecyclerView
    private HandPickRecyclerViewAdapter recyclerViewAdapter;
    private List<HandPickRecyclerViewInfo.DataEntity.SecondaryBannersEntity> recyclerViewList =
            new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HandPickFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HandPickFragment newInstance(String param1, String param2) {
        HandPickFragment fragment = new HandPickFragment();
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
        View view = inflater.inflate(R.layout.fragment_hand_pick, container, false);
        ButterKnife.bind(this, view);
        //为ExpandListView添加头部
        setupHeaderView();
        OkHttpTools.newInstance().okGet(UrlConfig.HAND_PICK_VIEW_PAGER_URL, HandPickViewPagerProductInfo.class
                , new IOkCallBack<HandPickViewPagerProductInfo>() {
            @Override
            public void onSuccess(HandPickViewPagerProductInfo resultInfo, int requestCode) {
                List<HandPickViewPagerProductInfo.DataEntity.BannersEntity> banners = resultInfo.getData().getBanners();
                if (mViewPagerUrlList.isEmpty()) {
                    for (int i = 0; i < banners.size(); i++) {
                        mViewPagerUrlList.add(banners.get(i).getImage_url());
                    }
                }
                //参数一：CBViewHolderCreator创建者对象
                //参数二：数据源
                headerViewHolder.mConvenientBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new BannerViewHolder();
                    }
                }, mViewPagerUrlList)
                        .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }
        }, 1);

        setupExpandableListView();
        getHttpData();

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity() , WebActivity.class);
                intent.putExtra("url" , itemsMap.get(groupTitleList.get(groupPosition)).get(childPosition).getContent_url());
                intent.putExtra("image_url" , itemsMap.get(groupTitleList.get(groupPosition)).get(childPosition).getCover_webp_url() );
                intent.putExtra("title" , itemsMap.get(groupTitleList.get(groupPosition)).get(childPosition).getTitle());
                startActivity(intent);
                return true;
            }
        });

        return view;
    }

    /**
     * 请求网络获得数据
     */
    private void getHttpData() {
        OkHttpTools.newInstance().okGet(UrlConfig.HAND_PICK_LIST_VIEW, HandPickContentProductInfo.class
                , new IOkCallBack<HandPickContentProductInfo>() {
            @Override
            public void onSuccess(HandPickContentProductInfo resultInfo, int requestCode) {
                List<HandPickContentProductInfo.DataEntity.ItemsEntity> itemsEntityList = resultInfo.getData().getItems();

                groupTitleList.clear();
                itemsMap.clear();

                for (int i = 0; i < itemsEntityList.size(); i++) {
                    HandPickContentProductInfo.DataEntity.ItemsEntity itemsEntity = itemsEntityList.get(i);
                    String key = DateFormatTool.formatDate(itemsEntity.getPublished_at() * 1000L);
                    Log.d("heyang", "onSuccess: ------------>" + key);
                    List<HandPickContentProductInfo.DataEntity.ItemsEntity> itemsEntities = itemsMap.get(key);

                    if (itemsEntities != null) {
                        itemsEntities.add(itemsEntity);
                        Log.d("heyang", "onSuccess: ------------>" + itemsEntities.get(0).getCover_image_url());
                    } else {
                        groupTitleList.add(key);
                        itemsEntities = new ArrayList<>();
                        itemsEntities.add(itemsEntity);
                        itemsMap.put(key, itemsEntities);
                    }
                }
                Log.d("heyang", "onSuccess: ------------>" + groupTitleList.size());
                //注意：一定要展开Group，不然child无法显示出来
                for (int i = 0; i < groupTitleList.size(); i++) {
                    mExpandableListView.expandGroup(i);
                }
                expandAdapter.notifyDataSetChanged();

            }
        }, 3);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(headerViewHolder != null){
            headerViewHolder.mConvenientBanner.startTurning(3000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(headerViewHolder != null){
            headerViewHolder.mConvenientBanner.stopTurning();
        }
    }

    /**
     * 为ExpandListView添加头部
     */
    private void setupHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.expand_listview_header, null);
        headerViewHolder = new HeaderViewHolder(headerView);

        //指定一个布局管理器
        headerViewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()
                ,LinearLayoutManager.HORIZONTAL , false ));
        //创建适配器并绑定适配器
        recyclerViewAdapter = new HandPickRecyclerViewAdapter(getActivity() ,recyclerViewList);
        headerViewHolder.mRecyclerView.setAdapter(recyclerViewAdapter);
        //请求数据
        OkHttpTools.newInstance().okGet(UrlConfig.HAND_PICK_HORIZONTAL_LIST_VIEW_URL
                , HandPickRecyclerViewInfo.class, new IOkCallBack<HandPickRecyclerViewInfo>() {
            @Override
            public void onSuccess(HandPickRecyclerViewInfo resultInfo, int requestCode) {
                List<HandPickRecyclerViewInfo.DataEntity.SecondaryBannersEntity> secondary_banners =
                        resultInfo.getData().getSecondary_banners();
                recyclerViewList.clear();
                recyclerViewList.addAll(secondary_banners);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        }, 2);

        mExpandableListView.addHeaderView(headerView);
    }

    class BannerViewHolder implements Holder<String> {

        private ImageView mBannerImageView;

        @Override
        public View createView(Context context) {
            mBannerImageView = new ImageView(getActivity());
            mBannerImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return mBannerImageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(getActivity()).load(data).into(mBannerImageView);
        }
    }

    class HeaderViewHolder {
        @Bind(R.id.cb_elv_header)
        ConvenientBanner mConvenientBanner;
        @Bind(R.id.rv_elv_header)
        RecyclerView mRecyclerView;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 设置ExpandableListView
     */
    private void setupExpandableListView() {
        expandAdapter = new ExpandAdapter(getActivity(), itemsMap, groupTitleList);
        mExpandableListView.setAdapter(expandAdapter);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
