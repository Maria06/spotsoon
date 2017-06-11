package com.test.spotsoon.activity;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.spotsoon.R;
import com.test.spotsoon.fragments.OneFragment;
import com.test.spotsoon.fragments.ThreeFragment;
import com.test.spotsoon.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout,tabLayouttop;
    private ViewPager viewPager,viewPagertop;
    private ArrayList<Bitmap> drawable = new ArrayList<Bitmap>();
    private static String[] IMAGE_NAME = {"default_image", "default_image1", "default_image2"};
    private int items=3;
    private ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    private TextView tabOne,tabTwo,tabThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
        viewPagertop = (ViewPager) findViewById(R.id.viewpagertop);
        viewPagertop.setAdapter(imageFragmentPagerAdapter);

        tabLayouttop = (TabLayout) findViewById(R.id.tab_layout);
        tabLayouttop.setupWithViewPager(viewPagertop, true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.menu1);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {

        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("      VIDEOS");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.video, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("       IMAGES");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.image, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("   MILESTONE");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.milestone, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public  class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
        public ImageFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return items;
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return fragment.newInstance(position);
        }
    }

    public static class SwipeFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.swipe_fragment, container, false);
            ImageView imageView = (ImageView) swipeView.findViewById(R.id.imageView2);
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
            String imageFileName = IMAGE_NAME[position];
            int imgResId = getResources().getIdentifier(imageFileName, "drawable", "com.test.spotsoon");
            imageView.setImageResource(imgResId);
            return swipeView;
        }

         SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
    }

}
