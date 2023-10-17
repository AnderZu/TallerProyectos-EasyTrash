package com.lmmt.easytrash;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lmmt.easytrash.databinding.ActivityMapsBinding;

import java.sql.DriverManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //Add a marker in Sydney and move the camera
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng sitio1 = new LatLng(-12.068151096438912, -75.21493826124146);
        mMap.addMarker(new MarkerOptions().position(sitio1).title("jr ayacucho 798"));
        CameraPosition cameraPosition = CameraPosition.builder().target(sitio1).zoom(19).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void DActualizar(View view) {
        Toast.makeText(MapsActivity.this, "Direccion Actualizada", Toast.LENGTH_SHORT).show();
    }

    public void MPerfil(View view) {
        Intent j = new Intent(MapsActivity.this, Perfil.class);
        startActivity(j);
    }

    public void MDireccion(View view) {
        Intent k = new Intent(MapsActivity.this, MapsActivity.class);
        startActivity(k);
    }

    public void MHorarios(View view) {
        Intent l = new Intent(MapsActivity.this, Horarios.class);
        startActivity(l);
    }

    @Override
    public void onClick(View v) {

    }
}