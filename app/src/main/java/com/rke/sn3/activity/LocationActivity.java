package com.rke.sn3.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rke.sn3.R;
import com.rke.sn3.adapter.PlaceArrayAdapter;
import com.rke.sn3.common.GpsTracker;
import com.rke.sn3.common.SN3Application;
import com.rke.sn3.model.PlaceModel;
import com.rke.sn3.util.GeocodingLocation;
import com.rke.sn3.util.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.security.AccessController.getContext;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMapClickListener {

    private OkHttpClient mClient = new OkHttpClient();
    private WebSocket mWebSocket = null;
    private SN3Application appl;
    private SupportMapFragment mapFragment;
    private View mFragView;
    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 10001;
    private boolean mPermissionDenied = false;
    private Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(LocationActivity.this);
        setContentView(R.layout.activity_location);
        appl = SN3Application.Me;
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        findViewById(R.id.imgViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.imgCheckOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LocationActivity.this);
                final EditText edittext = new EditText(LocationActivity.this);
                alert.setMessage("Enter Client ID");

                alert.setView(edittext);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        appl.customerId = edittext.getText().toString().trim();
                        if (appl.customerId.isEmpty()) {
                            Toast.makeText(LocationActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                        } else {
                            initSocket();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));


        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);


        enableMyLocation();

    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }
    private void buildAlertMessageNoGps() {
        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }
    private boolean checkMapReady() {
        if (mMap == null) {
            return false;
        }
        return true;
    }

    private void enableMyLocation() {
        Set<String> requiredPermissions = new HashSet<>();
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requiredPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                && ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requiredPermissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }
        mPermissionDenied = requiredPermissions.isEmpty();
        if (!mPermissionDenied) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(requiredPermissions.toArray(new String[requiredPermissions.size()]), LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

        statusCheck();

        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMapClickListener(this);
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            new GpsTracker(getApplicationContext(), new GpsTracker.GpsTrackerListener() {
                @Override
                public void onLocationUpdate(Location location) {
                    if (location != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
                    }
                    else {
                        Log.e("location ---","NULL");
                    }
                }
            });
        }

    }

    private void processMessage(String message) {
        String response = "";
        try {
            response = new String(message.getBytes("ISO-8859-1"), "UTF-8");
            JSONObject jsonObject = new JSONObject(response);
            String driverId             = jsonObject.getString("driverId");
            String woId                 = jsonObject.getString("workOrderId");
            String latitude             = jsonObject.getString("latitude");
            String longitude            = jsonObject.getString("longitude");
            LatLng location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addMark(location, driverId, woId);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMark(LatLng latLng, String driverId, String woId) {
        if (checkMapReady()) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(driverId);
            markerOptions.snippet(woId);
            //   markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map));
            markerOptions.infoWindowAnchor((float) 0, (float) 0);
            markerOptions.position(latLng);
            mMap.clear();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, mMap.getCameraPosition().zoom));
            if(marker!=null){
                marker.remove();
            }
            marker = mMap.addMarker(markerOptions);
            if (marker != null) {
                marker.setTag(woId);
            }

        }
    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }


    void initSocket() {
        Request request = new Request.Builder().url("wss://apidev.sn3.com.sa:8445/location-streams/v1.0/locations").addHeader("Authorization","Bearer eyJ4NXQiOiJOVGRtWmpNNFpEazNOalkwWXpjNU1tWm1PRGd3TVRFM01XWXdOREU1TVdSbFpEZzROemM0WkEiLCJraWQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZ19SUzI1NiIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJhZG1pbiIsImF1dCI6IkFQUExJQ0FUSU9OIiwiYXVkIjoiTmVlcHVaWTF6R2dNNHcyWTNZUEpzWVVQNmdrYSIsIm5iZiI6MTY2MTk1NDA3OSwiYXpwIjoiTmVlcHVaWTF6R2dNNHcyWTNZUEpzWVVQNmdrYSIsInNjb3BlIjoiZGVmYXVsdCIsImlzcyI6Imh0dHBzOlwvXC8xOTIuMTY4LjIzNC4yMDo5NDQzXC9vYXV0aDJcL3Rva2VuIiwiZXhwIjoxNzYxOTU0MDc4LCJpYXQiOjE2NjE5NTQwNzksImp0aSI6IjEwZjc2NTlkLWQ2NDMtNGYzZi1hNzE3LTYyYjczNjExYjI1NSJ9.mG6mPR8BRy5pdiiLCXbo8mt_9wV2LWwELVdZOTT9eK8gYDy1rN2f-g7kETHXy7WrxpQhppRNn9XHvsF-aPpxI5nFDoxi0UjUjH0OMIuTvBZf8GN5HQZn6EF_HQ_z097GoJfIlzat8VbkuRQuW1IDGdrTXMikaWji8SB5BsIm2Kfln3uLzpsK7nHNx83oTDs5572jYJk24176QGMU0zqZoOLyDNMkY52ah0mWXqOzSrKQm0b3HSbst0Of_o5nkDBLbgMqdulMhsrkd17L-tEP_isE7vJqWNVmX9RUm-1BX--q3DLOLnvWCR84EaHNVXU7Y15hH08m_-G1arEZrJdmjQ").build();
        EchoWebSocketListener webSocketListener = new EchoWebSocketListener();
        mWebSocket = mClient.newWebSocket(request, webSocketListener);
        mClient.dispatcher().executorService().shutdown();
    }

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int CLOSE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.v("onOpen response ", response.toString());
            webSocket.send(
                    "{" +
                            "\"action\":\"" + "customer" + "\", " +
                            "\"customerId\":\"" + appl.customerId + "\", " +
                            "}");
        }
        @Override
        public void onMessage(WebSocket webSocket, String message) {
            Log.v("Receive Message ", message);
            processMessage(message);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Log.v("Receive ByteString ", bytes.toString());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(CLOSE_STATUS, null);
            Log.v("Closing Socket : ", code + " / " + reason);
            mWebSocket = null;
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
            Log.v("Error Socket: ", throwable.getMessage());
            mWebSocket = null;
        }
    }

}
