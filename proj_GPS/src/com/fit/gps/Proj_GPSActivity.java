package com.fit.gps;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Proj_GPSActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);
       // String provider=LocationManager.GPS_PROVIDER;
//        String provider = LocationManager.GPS_PROVIDER;
//        String provider = "gps";
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //��Ҫ�󺣰�
        criteria.setAltitudeRequired(false); 
      //��Ҫ��λ
        criteria.setBearingRequired(false);
      //�����л���
        criteria.setCostAllowed(true);
      //�����к���
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);
        
        Location location = locationManager.getLastKnownLocation(provider);
        //����λ��
        updateWithNewLocation(location);
        //�������һ��
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
}
private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
                updateWithNewLocation(location);
        }
        public void onProviderDisabled(String provider) {
                updateWithNewLocation(null);
        }
        public void onProviderEnabled(String provider) {
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
};
	private void updateWithNewLocation(Location location) {
        String latLongString;
        TextView myLocationText;
        
        myLocationText = (TextView) findViewById(R.id.myLocation);
        System.out.println(myLocationText);

        if (location != null) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                
                Geocoder geocoder=new Geocoder(this); 
//                Geocoder geocoder = new Geocoder(this, Locale.CHINA);
                List places = null;
                
                try {
//                        Thread.sleep(2000);
                        places = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
//                        Thread.sleep(2000);
                        Toast.makeText(Proj_GPSActivity.this, places.size()+"", Toast.LENGTH_LONG).show();
                        System.out.println(places.size()+""); 
                } catch (Exception e) {  
                        e.printStackTrace();
                }
                
                String placename = "";
                if (places != null && places.size() > 0) {
                        // placename=((Address)places.get(0)).getLocality();
                        //һ�µ���Ϣ������嵽ĳ����
                        //����getAddressLine(0)��ʾ���ң�getAddressLine(1)��ʾ��ȷ��ĳ������getAddressLine(2)��ʾ��ȷ������Ľ�
                        placename = ((Address) places.get(0)).getAddressLine(0) + ", " + System.getProperty("line.separator")
                                        + ((Address) places.get(0)).getAddressLine(1) + ", "
                                        + ((Address) places.get(0)).getAddressLine(2);
                }

                latLongString = "γ��:" + lat + "\n����:" + lng;
                Toast.makeText(Proj_GPSActivity.this, placename, Toast.LENGTH_LONG).show();
        } else {
                latLongString = "�޷���ȡ������Ϣ";
        }
        myLocationText.setText("����ǰ��λ����:\n" + latLongString);
}

}