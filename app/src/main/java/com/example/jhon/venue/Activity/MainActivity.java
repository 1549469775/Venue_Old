package com.example.jhon.venue.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jhon.venue.Fragment.FoundFragment.FoundFragment;
import com.example.jhon.venue.Fragment.MapFragment.MMMFragment;
import com.example.jhon.venue.Fragment.MapFragment.MapFragment;
import com.example.jhon.venue.Fragment.Message.MessageFragment;
import com.example.jhon.venue.Fragment.PersonFragment.PersonFragment;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Map.MapDownload;
import com.example.jhon.venue.Model.ActicalOperation;
import com.example.jhon.venue.R;
import com.example.jhon.venue.Fragment.ReadFragment.ReadFragment;

/*
* 目前解决地图在fragment中白屏的方法
* 关闭Instant Run的方法
* 打开Android Studio设置，在输入框中输入Instant Run，出来以下界面，然后把☑️去掉即可。
* */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab_submit;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private MapFragment fragment_world;
    private FoundFragment foundFragment;
    private ReadFragment readFragment;
    private MessageFragment messageFragment;
    private PersonFragment personFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        if (fragment_world == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment_world = (MapFragment) MapFragment.newInstance();
            fragmentTransaction.add(R.id.main_framelayout, fragment_world);
            fragmentTransaction.commit();
        }

        setUpBottomView();

        fab_submit= (FloatingActionButton) findViewById(R.id.fab_submit);
        fab_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Activity_Up.class));
            }
        });
    }

    private void addFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (foundFragment != null) {
            fragmentTransaction.hide(foundFragment);
        }
        if (readFragment != null) {
            fragmentTransaction.hide(readFragment);
        }
        if (messageFragment != null) {
            fragmentTransaction.hide(messageFragment);
        }
        if (personFragment != null) {
            fragmentTransaction.hide(personFragment);
        }
        if (fragment_world != null) {
            fragmentTransaction.hide(fragment_world);
        }
    }

    private void setUpBottomView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                addFragment();
                switch (item.getItemId()) {
                    case R.id.menu_map:
//                        start(1);
//                        bottomNavigationView.setItemBackgroundResource(R.color.write);
                        if (fragment_world == null) {
                            fragment_world = (MapFragment) MapFragment.newInstance();
                            fragmentTransaction.add(R.id.main_framelayout, fragment_world);
                        } else {
                            fragmentTransaction.show(fragment_world);
                        }
                        break;
                    case R.id.menu_found:
                        fab_submit.show();
//                        start(2);
//                        bottomNavigationView.setItemBackgroundResource(R.color.blue_primary);
                        if (foundFragment == null) {
                            foundFragment = FoundFragment.newInstance();
                            fragmentTransaction.add(R.id.main_framelayout, foundFragment);
                        } else {
                            fragmentTransaction.show(foundFragment);
                        }
                        break;
                    case R.id.menu_read:
                        fab_submit.show();
//                        start(3);
//                        bottomNavigationView.setItemBackgroundResource(R.color.colorPurpleDark);
                        if (readFragment == null) {
                            readFragment = ReadFragment.newInstance();
                            fragmentTransaction.add(R.id.main_framelayout, readFragment);
                        } else {
                            fragmentTransaction.show(readFragment);
                        }
                        break;
                    case R.id.menu_news:
                        fab_submit.show();
//                        start(4);
//                        bottomNavigationView.setItemBackgroundResource(R.color.md_yellow_700);
                        if (messageFragment == null) {
                            messageFragment = MessageFragment.newInstance();
                            fragmentTransaction.add(R.id.main_framelayout, messageFragment);
                        } else {
                            fragmentTransaction.show(messageFragment);
                        }
                        break;
                    case R.id.menu_person:
                        fab_submit.hide();
//                        start(5);
//                        bottomNavigationView.setItemBackgroundResource(R.color.orange_primary_dark);
                        if (personFragment == null) {
                            personFragment = PersonFragment.newInstance();
                            fragmentTransaction.add(R.id.main_framelayout, personFragment);
                        } else {
                            fragmentTransaction.show(personFragment);
                        }
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
//        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView= (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.app_bar_search) {
            ActicalOperation.getActicalPage(1,new JudgeInterface() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                }
            });
//            startActivity(new Intent(MainActivity.this,DetailActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //    private Animator animation;
//
//    public void start(int position){
//        animation= ViewAnimationUtils.createCircularReveal(
//                bottomNavigationView,
//                bottomNavigationView.getMeasuredWidth()*(position/5),
//                bottomNavigationView.getHeight()/2,
//                0,//开始半径
//                (float)Math.hypot(bottomNavigationView.getWidth(),bottomNavigationView.getHeight()));//jieshu半径
//        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        animation.setDuration(1000);
//        animation.start();
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentTransaction = null;
    }


}
