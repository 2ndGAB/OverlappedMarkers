package com.example.alain.overlappedmarkers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.clustering.MarkerClusterer;
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    private ScaleBarOverlay mScaleBarOverlay;
    MyRadiusMarkerClusterer markers = null;
    Drawable clusterIconD = null;
    Bitmap clusterIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this.getApplicationContext();

        setContentView(R.layout.activity_main);

        final DisplayMetrics dm = context.getResources().getDisplayMetrics();

        MapView map = (MapView) findViewById(R.id.mapview);

        mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);

        map.getOverlays().add(this.mScaleBarOverlay);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(12);

        markers = new MyRadiusMarkerClusterer(context);
        markers.setMinNumOfMarkersInCluster(5);
        clusterIconD = getResources().getDrawable(R.drawable.ic_icon_bg_white, context.getTheme());

        clusterIcon = ((BitmapDrawable)clusterIconD).getBitmap();
        markers.setIcon(clusterIcon);
        markers.getTextPaint().setColor(Color.DKGRAY);
        markers.getTextPaint().setTextSize(50.0f);
        markers.mAnchorU = Marker.ANCHOR_RIGHT;
        markers.mAnchorV = Marker.ANCHOR_BOTTOM;
        markers.mTextAnchorV = 0.45f;


        map.getOverlays().add(markers);

        GeoPoint point = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(point);

        Marker myMarker = new Marker(map);

        myMarker.setPosition(point);
        myMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        myMarker.setIcon(getDrawable(R.drawable.ic_icon_bg_white_1));
        myMarker.setTitle("Marker 1");

        markers.add(myMarker);

        //map.getOverlays().add(myMarker);

        point = new GeoPoint(48.86, 2.30);

        myMarker = new Marker(map);

        myMarker.setPosition(point);
        myMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        myMarker.setIcon(getDrawable(R.drawable.ic_icon_bg_white_2));
        myMarker.setTitle("Marker 2");

        markers.add(myMarker);
        //map.getOverlays().add(myMarker);

        markers.invalidate();
        map.invalidate();

    }

}
