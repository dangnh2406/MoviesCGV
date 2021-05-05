package com.nguyenhaidang_dangnh2406.moviescgv.views.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.adapters.ViewPagerAdapter;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.GetCountingStar;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.AboutFragment;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.FavoriteFragment;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.MoviesFragment;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.SettingFragment;

public class MasterActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, GetCountingStar, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private ActionBar actionBar;
    private Button mLogin_btn;
    private Button button2;
    private BadgeDrawable mBadgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        initView();
        addFragment(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                actionBar.setTitle(R.string.fragment_name_movies);
                break;
            case 1:
                actionBar.setTitle(R.string.fragment_name_favorite);
                break;
            case 2:
                actionBar.setTitle(R.string.fragment_name_about);
                break;
            case 3:
                actionBar.setTitle(R.string.fragment_name_setting);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.master_drawer_nav);
        mLogin_btn = findViewById(R.id.drawer_login_btn);
        mViewPager = findViewById(R.id.master_viewpager);
        mTabLayout = findViewById(R.id.master_tablayout);
        button2 = findViewById(R.id.drawer_login_btn2);

        actionBar = getSupportActionBar();
        mActionBarDrawerToggle = new ActionBarDrawerToggle(MasterActivity.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLogin_btn.setOnClickListener(this);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        actionBar.setTitle(R.string.fragment_name_movies);
    }

    private void addFragment(ViewPager mViewPager) {
        mViewPagerAdapter.addFragment(MoviesFragment.newInstance(), getString(R.string.fragment_name_movies));
        mViewPagerAdapter.addFragment(FavoriteFragment.newInstance(), getString(R.string.fragment_name_favorite));
        mViewPagerAdapter.addFragment(AboutFragment.newInstance(), getString(R.string.fragment_name_about));
        mViewPagerAdapter.addFragment(SettingFragment.newInstance(), getString(R.string.fragment_name_setting));

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);

        mBadgeDrawable = mTabLayout.getTabAt(1).getOrCreateBadge();

    }

    @Override
    public void getCoutingStar(int stars) {
        mBadgeDrawable.setNumber(stars);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer_login_btn:
                Log.d("TAG", "TestBuyutu");
                break;
            case R.id.drawer_login_btn2:
                Log.d("TAG", "TestBuyutu");
                break;
            default:
                break;
        }
    }
}
