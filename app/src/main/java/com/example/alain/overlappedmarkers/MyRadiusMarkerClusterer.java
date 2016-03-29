package com.example.alain.overlappedmarkers;

import android.content.Context;
import android.graphics.Rect;

import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer;
import org.osmdroid.bonuspack.clustering.StaticCluster;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Alain on 03/02/2016.
 */
public class MyRadiusMarkerClusterer extends RadiusMarkerClusterer {

    public int getMinNumOfMarkersInCluster() {
        return minNumOfMarkersInCluster;
    }

    public void setMinNumOfMarkersInCluster(int minNumOfMarkersInCluster) {
        this.minNumOfMarkersInCluster = minNumOfMarkersInCluster;
    }

    int minNumOfMarkersInCluster = 1;   // Initialized to 1 to keep the same behavior as today

    public MyRadiusMarkerClusterer(Context ctx) {
        super(ctx);
    }

    @Override public ArrayList<StaticCluster> clusterer(MapView mapView) {

        ArrayList<StaticCluster> clusters = super.clusterer(mapView);

        ArrayList<StaticCluster> myClusters = new ArrayList<>();

        Iterator<StaticCluster> itClusters = clusters.iterator();

        while(itClusters.hasNext()) {
            StaticCluster cluster = itClusters.next();

            if (cluster.getSize() < minNumOfMarkersInCluster) {
                for (int i = 0; i < cluster.getSize(); i++) {
                    StaticCluster newCluster = new StaticCluster(cluster.getItem(i).getPosition());
                    newCluster.add(cluster.getItem(i));
                    myClusters.add(newCluster);
                }
            } else {
                myClusters.add(cluster);
            }
        }
        
        return myClusters;
    }
}
