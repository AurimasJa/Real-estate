package edu.ktu.myfirstapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ktu.myfirstapplication.databinding.ActivityMapsActivity2newBinding;

public class MapsActivity2new extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<SkelbimaiList> list;
    private Geocoder geocoder;

    private ActivityMapsActivity2newBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMapsActivity2newBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Bundle extras = getIntent().getExtras();
//        if(extras != null) {
//            list = (ArrayList<SkelbimaiList>) getIntent().getSerializableExtra("list");
//        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng address = getLocationFromAddress(this, "Kaunas");
        mMap.addMarker(new MarkerOptions().position(address).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(address));

//        try {
//
//            List<Address> addresses = geocoder.getFromLocationName("Kaunas", 1);
//            Address address = addresses.get(0);
//            LatLng point = new LatLng(address.getLatitude(), address.getLongitude());
//            MarkerOptions markerOptions = new MarkerOptions().position(point).title(address.getLocality());
//            mMap.addMarker(markerOptions);
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        for(int i = 0; i < arrayList.size(); i++)
//        {
//            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
//        }

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }
}