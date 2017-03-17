package com.example.jhon.venue.Fragment.ReadFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.Adapter.Read_VP_Adapter;
import com.example.jhon.venue.Fragment.FragmentText;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/7.
 */

public class ReadFragment extends Fragment {

    private View view;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static ReadFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ReadFragment fragment = new ReadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.read_main,container,false);
        initViewPager();
        return view;
    }

    public void initViewPager() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_found);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_found);

        List<String> titles = new ArrayList<>();
        titles.add("热门");
        titles.add("人物");
        titles.add("地点");
        titles.add("故事");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(Read_Page_Fragment.newInstance());
        fragments.add(Read_Person_Fragment.newInstance());
        fragments.add(Read_Location_Fragment.newInstance());
        fragments.add(Read_Story_Fragment.newInstance());

        mViewPager.setOffscreenPageLimit(3);

        Read_VP_Adapter mReadVPAdapter = new Read_VP_Adapter(getActivity().getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mReadVPAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mReadVPAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if (position == 2) {
//                    fab.show();
//                } else {
//                    fab.hide();
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
