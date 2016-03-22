package com.pluto.fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by Pluto on 2016/3/14.
 */
public class BaseFragment extends Fragment {

    /**
     * This interface must be implemented by activities that contain this
     * com.pluto.fragment to allow an interaction in this com.pluto.fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
