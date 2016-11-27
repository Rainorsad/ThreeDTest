package com.example.yao.threedtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;

/**
 * Created by Yao on 2016/10/28.
 */
public class DituDingweiActivity extends AppCompatActivity{
    private MapView mapView;
    private BaiduMap map;
    private Marker marker;
    private TextView tv_1,tv_2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_ditudingwei);
        mapView = (MapView) findViewById(R.id.mapview);
        tv_1 = (TextView) findViewById(R.id.map_tv_1);
        tv_2 = (TextView) findViewById(R.id.map_tv_2);
        map = mapView.getMap();
        map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星图

        /**
         * 定位
         */
        map.setMyLocationEnabled(true);//开启定位
//        MyLocationData data = new MyLocationData.Builder().accuracy()

//        /**
//         * 室内地图开启
//         */
//        map.setIndoorEnable(true);
//        map.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
//            @Override
//            public void onBaseIndoorMapMode(boolean b, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
//                if (b){
//                    // 进入室内图
//                    // 通过获取回调参数 mapBaseIndoorMapInfo 来获取室内图信息，包含楼层信息，室内ID等
//                }else {
//                    // 移除室内图
//                }
//            }
//        });

        /**
         * 开启定位以及拖拽事件
         */
//        map.setBaiduHeatMapEnabled(true);//开启交通图
//        LatLng point = new LatLng(39.963175,116.400244);
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
//        OverlayOptions options = new MarkerOptions().position(point).zIndex(9).draggable(true).icon(bitmap);
//        marker = (Marker) map.addOverlay(options);
//        map.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(Marker marker) {
//                //拖拽中
//            }
//
//            @Override
//            public void onMarkerDragEnd(Marker marker) {
//                //拖拽结束
//            }
//
//            @Override
//            public void onMarkerDragStart(Marker marker) {
//                //开始拖拽
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onResume();
    }
}
