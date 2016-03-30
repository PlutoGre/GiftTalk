package com.pluto.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pluto.adapter.SelectDetailCommentListViewAdapter;
import com.pluto.bean.SelectDetailCommentInfo;
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
 * {@link SelectDetailCommentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectDetailCommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectDetailCommentFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectDetailCommentFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.lv_fg_select_detail_comment)
    ListView mListView;
    private List<SelectDetailCommentInfo.DataEntity.CommentsEntity> commentsEntityList = new ArrayList<>();
    private SelectDetailCommentListViewAdapter commentListViewAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectDetailCommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectDetailCommentFragment newInstance(String param1, String param2) {
        SelectDetailCommentFragment fragment = new SelectDetailCommentFragment();
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
        View view = inflater.inflate(R.layout.fragment_select_detail_comment, container, false);
        ButterKnife.bind(this , view);

        commentListViewAdapter = new SelectDetailCommentListViewAdapter(getActivity() , commentsEntityList);
        mListView.setAdapter(commentListViewAdapter);

        String url = UrlConfig.SELECT_DETAIL_COMMENT_URL_1 + mParam1 + UrlConfig.SELECT_DETAIL_COMMENT_URL_2;
        OkHttpTools.newInstance().okGet(url, SelectDetailCommentInfo.class, new IOkCallBack<SelectDetailCommentInfo>() {
            @Override
            public void onSuccess(SelectDetailCommentInfo resultInfo, int requestCode) {
                commentsEntityList.addAll(resultInfo.getData().getComments());
                commentListViewAdapter.notifyDataSetChanged();
            }
        }, 1);

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
