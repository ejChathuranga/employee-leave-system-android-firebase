package com.example.ej.employeeleavesystem;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.ej.employeeleavesystem.emp_leave_tabs.SectionPageAdapter;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_approve;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_lead_requests;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_new;
import com.example.ej.employeeleavesystem.emp_leave_tabs.emp_leave_home_old;

public class emp_leave_home extends AppCompatActivity {
    private static final String  TAG = "emp_leave_home";

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_leave_home);
        Log.d("TAG","onCreate: Loading..");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout  = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
//        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        mSectionPageAdapter.addFragment(new emp_leave_home_approve(), "Approved");
        mSectionPageAdapter.addFragment(new emp_leave_home_new(), "New");
        mSectionPageAdapter.addFragment(new emp_leave_home_old(), "Old");
        mSectionPageAdapter.addFragment(new emp_leave_home_lead_requests(), "Request");
        mViewPager.setAdapter(mSectionPageAdapter);
    }
}
//
//public class emp_leave_home extends AppCompatActivity {
//
//    /**
//     * The {@link android.support.v4.view.PagerAdapter} that will provide
//     * fragments for each of the sections. We use a
//     * {@link FragmentPagerAdapter} derivative, which will keep every
//     * loaded fragment in memory. If this becomes too memory intensive, it
//     * may be best to switch to a
//     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
//     */public static Context mContext;
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//    /**
//     * The {@link ViewPager} that will host the section contents.
//     */
//    private ViewPager mViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_emp_leave_home);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mContext = getBaseContext();
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_emp_leave_home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch(position){
//                case 0:
//                    emp_leave_home_new tab1 = new emp_leave_home_new();
//                    return tab1;
//                case 1:
//                    emp_leave_home_approve tab2 = new emp_leave_home_approve();
//                    return tab2;
//                case 2:
//                    emp_leave_home_old tab3 = new emp_leave_home_old();
//                    return tab3;
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 3;
//        }
//
//        public CharSequence getPageTitle(int position){
//            switch (position){
//                case 0:
////                    return "New";
//                    return (mContext.getString(R.string.tab_text_1));
//                case 1:
//                    return "Approve";
//                case 2:
//                    return "Old";
//
//            }
//            return null;
//        }
//    }
//}
