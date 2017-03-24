package com.example.jhon.venue.Fragment.MapFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.jhon.venue.Map.Location;
import com.example.jhon.venue.R;

/**
 * Created by John on 2017/3/6.
 */

public class MapFragment extends Fragment {
    private static MapFragment fragment = null;
    private View view;

    private MapView mMapView = null;
    private AMap aMap=null;

    Location location;

    public static Fragment newInstance() {
        if (fragment == null) {
            synchronized (MapFragment.class) {
                if (fragment == null) {
                    fragment = new MapFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            Log.i("sys", "MF onCreateView() null");
            view = inflater.inflate(R.layout.map_main, null);
            mMapView = (MapView) view.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
            if (aMap == null) {
                aMap = mMapView.getMap();
                aMap.clear();
            }
        }else {
            if (view.getParent() != null) {
                Log.i("sys", "MF odfsfdsdnull");
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
        setUpMapIfNeeded();
        return view;
    }

    private void setUpMapIfNeeded() {
        setUpMap();
        //定位
        location=Location.newInstance(getContext(),mMapView);
        location.setLocType(2);
    }

    private void setUpMap() {
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions marker=new MarkerOptions();
                marker.position(latLng);
                aMap.addMarker(marker);
//                            aMap.addMarkers(list,false);//加多个Marker（标记）到地图上，并设置是否移动到屏幕中间
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                marker.setFlat(true);//设置当前marker是否平贴在地图上。
                Toast.makeText(getContext(),""+marker.getId(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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

    //以下为marker的自定义布局的一种实现方法
    //Marker marker = aMap.addMarker(new MarkerOptions()
//        .anchor(0.5f, 0.5f)
//        .position(new LatLng(lat.get(i)[0], lat.get(i)[1]))
//        .snippet("nnn").title(i + "").snippet(i + "")
//        .draggable(true));
//    String imageUrl = "http://img01.taobaocdn.com/bao/uploaded/i3/13215023749568975/T1UKWCXvpXXXXXXXXX_!!0-item_pic.jpg_230x230.jpg";
//    View view = LayoutInflater.from(getActivity()).inflate(R.layout.marker, null);
//    ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//    mAbImageDownloader.display(imageView, imageUrl);
//    marker.setIcon(BitmapDescriptorFactory.fromView(view));
}
