package com.pluto.gifttalk;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pluto.fragment.CategoryFragment;
import com.pluto.fragment.HomeFragment;
import com.pluto.fragment.ProfileFragment;
import com.pluto.fragment.SelectFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private HomeFragment homeFragment;
    private SelectFragment selectFragment;
    private CategoryFragment categoryFragment;
    private ProfileFragment profileFragment;

    @Bind(R.id.toolbar_main)
    Toolbar toolbar;
    @Bind(R.id.ib_main_sign_in)
    ImageButton ibLeft;
    @Bind(R.id.iv_main_logo_title)
    ImageView ivLogo;
    @Bind(R.id.ib_main_search)
    ImageButton ibRight;

    @Bind(R.id.tv_main_title)
    TextView tvLogo;


    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();

        homeFragment = HomeFragment.newInstance(null, null);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_main_fragment, homeFragment);
        fragmentTransaction.commit();
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                hideAllFragment(fragmentTransaction1);
                switch (checkedId) {
                    case R.id.rb_main_home:
                        if (homeFragment == null) {
                            homeFragment = HomeFragment.newInstance(null, null);
                            fragmentTransaction1.add(R.id.fl_main_fragment, homeFragment);
                        } else {
                            fragmentTransaction1.show(homeFragment);
                        }
                        toolbar.setVisibility(View.VISIBLE);
                        ibLeft.setVisibility(View.VISIBLE);
                        ibRight.setVisibility(View.VISIBLE);
                        ivLogo.setVisibility(View.VISIBLE);
                        tvLogo.setVisibility(View.GONE);
                        break;
                    case R.id.rb_main_select:
                        if (selectFragment == null) {
                            selectFragment = SelectFragment.newInstance(null, null);
                            fragmentTransaction1.add(R.id.fl_main_fragment, selectFragment);
                        } else {
                            fragmentTransaction1.show(selectFragment);
                        }
                        toolbar.setVisibility(View.VISIBLE);
                        ibLeft.setVisibility(View.GONE);
                        ibRight.setVisibility(View.GONE);
                        ivLogo.setVisibility(View.GONE);
                        tvLogo.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_main_category:
                        if (categoryFragment == null) {
                            categoryFragment = CategoryFragment.newInstance(null, null);
                            fragmentTransaction1.add(R.id.fl_main_fragment, categoryFragment);
                        } else {
                            fragmentTransaction1.show(categoryFragment);
                        }
                        toolbar.setVisibility(View.GONE);
                        break;
                    case R.id.rb_main_profile:
                        if (profileFragment == null) {
                            profileFragment = ProfileFragment.newInstance(null, null);
                            fragmentTransaction1.add(R.id.fl_main_fragment, profileFragment);
                        } else {
                            fragmentTransaction1.show(profileFragment);
                        }
                        toolbar.setVisibility(View.GONE);
                        break;
                }
                fragmentTransaction1.commit();
            }
        });

        ibRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_main_bottom);
    }

    /**
     * 隐藏所有fragment
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null)
            fragmentTransaction.hide(homeFragment);
        if (selectFragment != null)
            fragmentTransaction.hide(selectFragment);
        if (categoryFragment != null)
            fragmentTransaction.hide(categoryFragment);
        if (profileFragment != null)
            fragmentTransaction.hide(profileFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
