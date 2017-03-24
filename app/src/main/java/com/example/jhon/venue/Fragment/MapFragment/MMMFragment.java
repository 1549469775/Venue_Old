package com.example.jhon.venue.Fragment.MapFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.*;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.jhon.venue.Bean.ActicalListUtil;
import com.example.jhon.venue.Bean.Article;
import com.example.jhon.venue.Bean.Loc;
import com.example.jhon.venue.Bean.LocUtil;
import com.example.jhon.venue.Map.Location;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/14.
 */

public class MMMFragment extends SupportMapFragment {

    private View view;
    private Context context;

    private MapView mMapView = null;
    private AMap aMap=null;
    private FloatingActionButton fab_seach;

    private ArrayList<MarkerOptions> list=new ArrayList<>();
    private List<Loc.Results> list_result;

    Location location;

    public static MMMFragment newInstance() {

        Bundle args = new Bundle();

        MMMFragment fragment = new MMMFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view=layoutInflater.inflate(R.layout.map_main, viewGroup,false);
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(bundle);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.clear();
        }
        setUpMapIfNeeded();
        fab_seach= (FloatingActionButton) view.findViewById(R.id.fab_seach);
        fab_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    private void setUpMapIfNeeded() {
        setUpMap();
        //定位
        location=Location.newInstance(context,mMapView);
        location.setLocType(2);
    }

    private void setUpMap() {
        init();
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                init();
//                aMap.addMarkers(list,true);
//                            aMap.addMarkers(list,false);//加多个Marker（标记）到地图上，并设置是否移动到屏幕中间
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                marker.setFlat(true);//设置当前marker是否平贴在地图上.
                Toast.makeText(context,list_result.get(Integer.parseInt(marker.getTitle())).getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void init(){
        Loc loc=new Loc();
        final List<Loc.Results> ll=new ArrayList<>();
        for (int i=0;i< ActicalListUtil.getList().size();i++){
            Loc.Results results=new Loc.Results();
            results.setLatitude(ActicalListUtil.getList().get(i).getLatitude());
            results.setLongtitude(ActicalListUtil.getList().get(i).getLongtitude());
            ll.add(results);
        }

        loc.setResultses(ll);
        LocUtil.setLoc(loc);
        list_result= LocUtil.getLoc().getResultses();
        for (int i=0;i<ll.size();i++){
            MarkerOptions marker=new MarkerOptions();
            marker.position(new LatLng(list_result.get(i).getLatitude(),list_result.get(i).getLongtitude()));
            marker.title(i+"");
            this.list.add(marker);
        }
        aMap.addMarkers(list,true);
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(context,list_result.get(Integer.parseInt(marker.getTitle())).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                LinearLayout linearLayout=new LinearLayout(context);
                linearLayout.setBackgroundColor(Color.BLUE);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return linearLayout;
            }

            @Override
            public View getInfoContents(Marker marker) {
                TextView tv=new TextView(context);
                tv.setText(marker.getId());
                return tv;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(Constants.MAP_FRAGMENT);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        Log.i("sys", "mf onResume");
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        Log.i("sys", "mf onPause");
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("sys", "mf onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        Log.i("sys", "mf onDestroy");
        super.onDestroy();
        mMapView.onDestroy();
    }

}
