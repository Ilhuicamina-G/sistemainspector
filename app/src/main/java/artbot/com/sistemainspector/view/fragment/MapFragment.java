package artbot.com.sistemainspector.view.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artbot.com.sistemainspector.R;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private View view;
    private GoogleMap gMap;
    private MapView mapView;

    private String zonaResult = "";
    private String direccionResult = "";
    private String coordenadasResult = "";
    private LocationManager locationManager;
    private Location currentLocation;

    private float lat;
    private float lng;

    private double lat_actual;
    private double lng_actual;

    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    private  String NameUser;
    private int idUser;
    private int idResult;

    private EditText editText;
    private String comentario;

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);



    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            idResult = getArguments().getInt("id",0);
            idUser = getArguments().getInt("idUser",0);
            NameUser = getArguments().getString("nameUser","");


            zonaResult = getArguments().getString("zona","");
            direccionResult = getArguments().getString("direccion","");
            coordenadasResult = getArguments().getString("coordenadas","");

            String[] parts = coordenadasResult.split(",");
            String part1 = parts[0];
            String part2 = parts[1];

            lat = Float.parseFloat(part1);
            lng = Float.parseFloat(part2);
            //Toast.makeText(getContext(),part1,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);



        Button button = (Button) view.findViewById(R.id.validarUbicacion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Ubicacion actual"+ lat_actual + "," + lng_actual,Toast.LENGTH_SHORT).show();
                List<LatLng> pts = new ArrayList<>();
                boolean contains1 = false;
                switch (zonaResult){
                    case "Zona 1":


                        pts.add(new LatLng(18.64303, -91.84439));
                        pts.add(new LatLng(18.64196, -91.84137));
                        pts.add(new LatLng(18.63456, -91.8353));
                        pts.add(new LatLng(18.63432, -91.83461));
                        pts.add(new LatLng(18.63123, -91.83195));
                        pts.add(new LatLng(18.63013, -91.83006));
                        pts.add(new LatLng(18.63036, -91.829));
                        pts.add(new LatLng(18.6302, -91.82792));
                        pts.add(new LatLng(18.63021, -91.82689));
                        pts.add(new LatLng(18.63016, -91.82605));
                        pts.add(new LatLng(18.63003, -91.82539));
                        pts.add(new LatLng(18.6294, -91.82517));
                        pts.add(new LatLng(18.62818, -91.82655));
                        pts.add(new LatLng(18.62786, -91.82677));
                        pts.add(new LatLng(18.62766, -91.8261));
                        pts.add(new LatLng(18.62842, -91.82457));
                        pts.add(new LatLng(18.62981, -91.82423));
                        pts.add(new LatLng(18.63089, -91.82285));
                        pts.add(new LatLng(18.63321, -91.82332));
                        pts.add(new LatLng(18.63431, -91.82159));
                        pts.add(new LatLng(18.63689, -91.82042));
                        pts.add(new LatLng(18.63794, -91.81888));
                        pts.add(new LatLng(18.64469, -91.81908));
                        pts.add(new LatLng(18.64326, -91.8227));
                        pts.add(new LatLng(18.64307, -91.82295));
                        pts.add(new LatLng(18.64616, -91.82734));
                        pts.add(new LatLng(18.64697, -91.82623));
                        pts.add(new LatLng(18.64889, -91.82226));
                        pts.add(new LatLng(18.65111, -91.82463));
                        pts.add(new LatLng(18.64894, -91.82689));
                        pts.add(new LatLng(18.64858, -91.82776));
                        pts.add(new LatLng(18.65006, -91.82908));
                        pts.add(new LatLng(18.64909, -91.83001));
                        pts.add(new LatLng(18.64873, -91.83068));
                        pts.add(new LatLng(18.64631, -91.83821));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);

                        break;
                    case "Zona 2":

                        pts.add(new LatLng(18.64303, -91.84439));
                        pts.add(new LatLng(18.64631, -91.83821));
                        pts.add(new LatLng(18.64871, -91.8307));
                        pts.add(new LatLng(18.64907, -91.83004));
                        pts.add(new LatLng(18.65004, -91.8291));
                        pts.add(new LatLng(18.64856, -91.82778));
                        pts.add(new LatLng(18.64892, -91.82691));
                        pts.add(new LatLng(18.65034, -91.82552));
                        pts.add(new LatLng(18.65109, -91.82465));
                        pts.add(new LatLng(18.65256, -91.82615));
                        pts.add(new LatLng(18.65403, -91.82476));
                        pts.add(new LatLng(18.65535, -91.82206));
                        pts.add(new LatLng(18.65489, -91.82164));
                        pts.add(new LatLng(18.65569, -91.82042));
                        pts.add(new LatLng(18.65677, -91.81694));
                        pts.add(new LatLng(18.65751, -91.81719));
                        pts.add(new LatLng(18.6582, -91.81461));
                        pts.add(new LatLng(18.65849, -91.81393));
                        pts.add(new LatLng(18.65793, -91.81339));
                        pts.add(new LatLng(18.65908, -91.8095));
                        pts.add(new LatLng(18.66119, -91.81063));
                        pts.add(new LatLng(18.66251, -91.80882));
                        pts.add(new LatLng(18.66293, -91.80738));
                        pts.add(new LatLng(18.66344, -91.80648));
                        pts.add(new LatLng(18.66356, -91.80584));
                        pts.add(new LatLng(18.66334, -91.80554));
                        pts.add(new LatLng(18.66238, -91.80509));
                        pts.add(new LatLng(18.66229, -91.80483));
                        pts.add(new LatLng(18.66262, -91.80367));
                        pts.add(new LatLng(18.66258, -91.80347));
                        pts.add(new LatLng(18.66592, -91.80403));
                        pts.add(new LatLng(18.66561, -91.8103));
                        pts.add(new LatLng(18.66522, -91.81101));
                        pts.add(new LatLng(18.66409, -91.83046));
                        pts.add(new LatLng(18.66494, -91.83833));
                        pts.add(new LatLng(18.66494, -91.84009));
                        pts.add(new LatLng(18.66455, -91.84089));
                        pts.add(new LatLng(18.66384, -91.84103));
                        pts.add(new LatLng(18.66251, -91.84291));
                        pts.add(new LatLng(18.66026, -91.84523));
                        pts.add(new LatLng(18.65229, -91.85115));
                        pts.add(new LatLng(18.65046, -91.85145));
                        pts.add(new LatLng(18.6478, -91.8502));
                        pts.add(new LatLng(18.64822, -91.84905));
                        pts.add(new LatLng(18.64761, -91.84885));
                        pts.add(new LatLng(18.64723, -91.84809));
                        pts.add(new LatLng(18.64828, -91.84695));
                        pts.add(new LatLng(18.64881, -91.84763));
                        pts.add(new LatLng(18.65021, -91.8483));
                        pts.add(new LatLng(18.65107, -91.84653));
                        pts.add(new LatLng(18.6496, -91.84576));
                        pts.add(new LatLng(18.65018, -91.84458));
                        pts.add(new LatLng(18.65331, -91.84619));
                        pts.add(new LatLng(18.65392, -91.84551));
                        pts.add(new LatLng(18.65049, -91.84373));
                        pts.add(new LatLng(18.65113, -91.84245));
                        pts.add(new LatLng(18.65479, -91.84447));
                        pts.add(new LatLng(18.65533, -91.84375));
                        pts.add(new LatLng(18.65031, -91.84117));
                        pts.add(new LatLng(18.64994, -91.8421));
                        pts.add(new LatLng(18.6504, -91.84247));
                        pts.add(new LatLng(18.64912, -91.8453));
                        pts.add(new LatLng(18.64586, -91.84746));
                        pts.add(new LatLng(18.64568, -91.84712));
                        pts.add(new LatLng(18.64496, -91.84757));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);

                        break;
                    case "Zona 3":

                        pts.add(new LatLng(18.64009, -91.81895));
                        pts.add(new LatLng(18.63794, -91.81888));
                        pts.add(new LatLng(18.63689, -91.82042));
                        pts.add(new LatLng(18.63553, -91.82086));
                        pts.add(new LatLng(18.63401, -91.82109));
                        pts.add(new LatLng(18.63382, -91.82131));
                        pts.add(new LatLng(18.63353, -91.82132));
                        pts.add(new LatLng(18.63301, -91.82175));
                        pts.add(new LatLng(18.63269, -91.82171));
                        pts.add(new LatLng(18.6326, -91.8216));
                        pts.add(new LatLng(18.63236, -91.82172));
                        pts.add(new LatLng(18.63228, -91.82216));
                        pts.add(new LatLng(18.63201, -91.82272));
                        pts.add(new LatLng(18.63186, -91.8228));
                        pts.add(new LatLng(18.63066, -91.8227));
                        pts.add(new LatLng(18.63043, -91.82244));
                        pts.add(new LatLng(18.63022, -91.82238));
                        pts.add(new LatLng(18.63012, -91.82176));
                        pts.add(new LatLng(18.63, -91.8216));
                        pts.add(new LatLng(18.6299, -91.82135));
                        pts.add(new LatLng(18.6299, -91.82101));
                        pts.add(new LatLng(18.62978, -91.82075));
                        pts.add(new LatLng(18.62944, -91.82049));
                        pts.add(new LatLng(18.62939, -91.82031));
                        pts.add(new LatLng(18.62922, -91.82024));
                        pts.add(new LatLng(18.62898, -91.82));
                        pts.add(new LatLng(18.62886, -91.81998));
                        pts.add(new LatLng(18.62885, -91.81968));
                        pts.add(new LatLng(18.63034, -91.81774));
                        pts.add(new LatLng(18.63111, -91.81657));
                        pts.add(new LatLng(18.63158, -91.81547));
                        pts.add(new LatLng(18.63172, -91.81495));
                        pts.add(new LatLng(18.63173, -91.8146));
                        pts.add(new LatLng(18.63157, -91.81418));
                        pts.add(new LatLng(18.6314, -91.81405));
                        pts.add(new LatLng(18.63145, -91.81384));
                        pts.add(new LatLng(18.63137, -91.81349));
                        pts.add(new LatLng(18.63149, -91.81282));
                        pts.add(new LatLng(18.63134, -91.81216));
                        pts.add(new LatLng(18.6313, -91.81088));
                        pts.add(new LatLng(18.6319, -91.81));
                        pts.add(new LatLng(18.63255, -91.80921));
                        pts.add(new LatLng(18.63276, -91.8085));
                        pts.add(new LatLng(18.63287, -91.80775));
                        pts.add(new LatLng(18.63344, -91.80689));
                        pts.add(new LatLng(18.63342, -91.80619));
                        pts.add(new LatLng(18.63405, -91.80518));
                        pts.add(new LatLng(18.63445, -91.80544));
                        pts.add(new LatLng(18.63444, -91.80598));
                        pts.add(new LatLng(18.63454, -91.80648));
                        pts.add(new LatLng(18.6349, -91.80657));
                        pts.add(new LatLng(18.63597, -91.80981));
                        pts.add(new LatLng(18.63623, -91.81195));
                        pts.add(new LatLng(18.63648, -91.81186));
                        pts.add(new LatLng(18.6364, -91.81043));
                        pts.add(new LatLng(18.63672, -91.8075));
                        pts.add(new LatLng(18.63654, -91.8064));
                        pts.add(new LatLng(18.63608, -91.80599));
                        pts.add(new LatLng(18.63596, -91.80545));
                        pts.add(new LatLng(18.63604, -91.80455));
                        pts.add(new LatLng(18.6366, -91.80433));
                        pts.add(new LatLng(18.63669, -91.80389));
                        pts.add(new LatLng(18.63816, -91.80403));
                        pts.add(new LatLng(18.63852, -91.80266));
                        pts.add(new LatLng(18.63951, -91.80184));
                        pts.add(new LatLng(18.64057, -91.80152));
                        pts.add(new LatLng(18.64102, -91.80093));
                        pts.add(new LatLng(18.64038, -91.80014));
                        pts.add(new LatLng(18.64032, -91.79932));
                        pts.add(new LatLng(18.64049, -91.79875));
                        pts.add(new LatLng(18.64034, -91.79814));
                        pts.add(new LatLng(18.64055, -91.79797));
                        pts.add(new LatLng(18.64085, -91.79794));
                        pts.add(new LatLng(18.64102, -91.79777));
                        pts.add(new LatLng(18.64146, -91.79707));
                        pts.add(new LatLng(18.64151, -91.79637));
                        pts.add(new LatLng(18.64103, -91.79511));
                        pts.add(new LatLng(18.64046, -91.79458));
                        pts.add(new LatLng(18.63988, -91.79434));
                        pts.add(new LatLng(18.63973, -91.79435));
                        pts.add(new LatLng(18.64044, -91.79487));
                        pts.add(new LatLng(18.64051, -91.79516));
                        pts.add(new LatLng(18.64008, -91.79534));
                        pts.add(new LatLng(18.63964, -91.79531));
                        pts.add(new LatLng(18.63651, -91.79152));
                        pts.add(new LatLng(18.63503, -91.78771));
                        pts.add(new LatLng(18.63693, -91.78755));
                        pts.add(new LatLng(18.63744, -91.78684));
                        pts.add(new LatLng(18.63724, -91.78317));
                        pts.add(new LatLng(18.63885, -91.78314));
                        pts.add(new LatLng(18.63868, -91.78359));
                        pts.add(new LatLng(18.63875, -91.7851));
                        pts.add(new LatLng(18.63969, -91.78543));
                        pts.add(new LatLng(18.64017, -91.78593));
                        pts.add(new LatLng(18.64042, -91.78592));
                        pts.add(new LatLng(18.64077, -91.78614));
                        pts.add(new LatLng(18.64144, -91.7864));
                        pts.add(new LatLng(18.64116, -91.78671));
                        pts.add(new LatLng(18.64151, -91.7869));
                        pts.add(new LatLng(18.64152, -91.78707));
                        pts.add(new LatLng(18.64275, -91.78807));
                        pts.add(new LatLng(18.64256, -91.78859));
                        pts.add(new LatLng(18.64256, -91.78911));
                        pts.add(new LatLng(18.64317, -91.78972));
                        pts.add(new LatLng(18.64235, -91.78942));
                        pts.add(new LatLng(18.64189, -91.7895));
                        pts.add(new LatLng(18.64136, -91.78994));
                        pts.add(new LatLng(18.64115, -91.79051));
                        pts.add(new LatLng(18.64107, -91.79143));
                        pts.add(new LatLng(18.64111, -91.79275));
                        pts.add(new LatLng(18.64228, -91.7927));
                        pts.add(new LatLng(18.64343, -91.79396));
                        pts.add(new LatLng(18.6428, -91.79755));
                        pts.add(new LatLng(18.64433, -91.80094));
                        pts.add(new LatLng(18.64972, -91.80574));
                        pts.add(new LatLng(18.64787, -91.81173));
                        pts.add(new LatLng(18.64715, -91.81137));
                        pts.add(new LatLng(18.64377, -91.81072));
                        pts.add(new LatLng(18.64244, -91.81147));
                        pts.add(new LatLng(18.64051, -91.81185));
                        pts.add(new LatLng(18.63785, -91.81231));
                        pts.add(new LatLng(18.63791, -91.81286));
                        pts.add(new LatLng(18.63818, -91.81336));
                        pts.add(new LatLng(18.63984, -91.81574));
                        pts.add(new LatLng(18.63966, -91.81609));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);

                        break;
                    case "Zona 4":

                        pts.add(new LatLng(18.64616, -91.82734));
                        pts.add(new LatLng(18.64307, -91.82295));
                        pts.add(new LatLng(18.64326, -91.8227));
                        pts.add(new LatLng(18.64477, -91.81906));
                        pts.add(new LatLng(18.64009, -91.81895));
                        pts.add(new LatLng(18.63966, -91.81609));
                        pts.add(new LatLng(18.63984, -91.81574));
                        pts.add(new LatLng(18.63818, -91.81336));
                        pts.add(new LatLng(18.63791, -91.81286));
                        pts.add(new LatLng(18.63785, -91.81231));
                        pts.add(new LatLng(18.64183, -91.81162));
                        pts.add(new LatLng(18.64244, -91.81147));
                        pts.add(new LatLng(18.64377, -91.81072));
                        pts.add(new LatLng(18.64715, -91.81137));
                        pts.add(new LatLng(18.64787, -91.81173));
                        pts.add(new LatLng(18.64972, -91.80574));
                        pts.add(new LatLng(18.64433, -91.80094));
                        pts.add(new LatLng(18.6428, -91.79755));
                        pts.add(new LatLng(18.64343, -91.79396));
                        pts.add(new LatLng(18.64228, -91.7927));
                        pts.add(new LatLng(18.64111, -91.79275));
                        pts.add(new LatLng(18.64107, -91.79143));
                        pts.add(new LatLng(18.64115, -91.79051));
                        pts.add(new LatLng(18.64136, -91.78994));
                        pts.add(new LatLng(18.64189, -91.7895));
                        pts.add(new LatLng(18.64235, -91.78942));
                        pts.add(new LatLng(18.64317, -91.78972));
                        pts.add(new LatLng(18.65746, -91.8024));
                        pts.add(new LatLng(18.65778, -91.80256));
                        pts.add(new LatLng(18.66259, -91.80345));
                        pts.add(new LatLng(18.66264, -91.80365));
                        pts.add(new LatLng(18.66231, -91.80481));
                        pts.add(new LatLng(18.6624, -91.80507));
                        pts.add(new LatLng(18.66336, -91.80552));
                        pts.add(new LatLng(18.66358, -91.80582));
                        pts.add(new LatLng(18.66346, -91.80646));
                        pts.add(new LatLng(18.66295, -91.80736));
                        pts.add(new LatLng(18.66253, -91.8088));
                        pts.add(new LatLng(18.66121, -91.81061));
                        pts.add(new LatLng(18.6591, -91.80948));
                        pts.add(new LatLng(18.65795, -91.81337));
                        pts.add(new LatLng(18.65851, -91.81391));
                        pts.add(new LatLng(18.65822, -91.81459));
                        pts.add(new LatLng(18.65753, -91.81717));
                        pts.add(new LatLng(18.65679, -91.81692));
                        pts.add(new LatLng(18.65571, -91.8204));
                        pts.add(new LatLng(18.65521, -91.82123));
                        pts.add(new LatLng(18.65491, -91.82162));
                        pts.add(new LatLng(18.65537, -91.82203));
                        pts.add(new LatLng(18.65405, -91.82474));
                        pts.add(new LatLng(18.65258, -91.82613));
                        pts.add(new LatLng(18.64889, -91.82226));
                        pts.add(new LatLng(18.64697, -91.82623));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);

                        break;
                    case "Zona 5":

                        pts.add(new LatLng(18.64257, -91.78916));
                        pts.add(new LatLng(18.64256, -91.78859));
                        pts.add(new LatLng(18.64275, -91.78807));
                        pts.add(new LatLng(18.64152, -91.78707));
                        pts.add(new LatLng(18.64151, -91.7869));
                        pts.add(new LatLng(18.64116, -91.78671));
                        pts.add(new LatLng(18.64144, -91.7864));
                        pts.add(new LatLng(18.64077, -91.78614));
                        pts.add(new LatLng(18.64042, -91.78592));
                        pts.add(new LatLng(18.64017, -91.78593));
                        pts.add(new LatLng(18.63969, -91.78543));
                        pts.add(new LatLng(18.63875, -91.7851));
                        pts.add(new LatLng(18.63868, -91.78359));
                        pts.add(new LatLng(18.63885, -91.78314));
                        pts.add(new LatLng(18.63498, -91.78318));
                        pts.add(new LatLng(18.63492, -91.78121));
                        pts.add(new LatLng(18.63513, -91.77889));
                        pts.add(new LatLng(18.63535, -91.77849));
                        pts.add(new LatLng(18.63558, -91.77826));
                        pts.add(new LatLng(18.63558, -91.77747));
                        pts.add(new LatLng(18.63612, -91.7752));
                        pts.add(new LatLng(18.64641, -91.77964));
                        pts.add(new LatLng(18.64671, -91.77973));
                        pts.add(new LatLng(18.64737, -91.77971));
                        pts.add(new LatLng(18.65614, -91.78358));
                        pts.add(new LatLng(18.6572, -91.78051));
                        pts.add(new LatLng(18.66779, -91.7869));
                        pts.add(new LatLng(18.66735, -91.78898));
                        pts.add(new LatLng(18.66757, -91.78995));
                        pts.add(new LatLng(18.66642, -91.79718));
                        pts.add(new LatLng(18.66659, -91.79737));
                        pts.add(new LatLng(18.66662, -91.79762));
                        pts.add(new LatLng(18.66592, -91.80345));
                        pts.add(new LatLng(18.66594, -91.80401));
                        pts.add(new LatLng(18.65778, -91.80256));
                        pts.add(new LatLng(18.65746, -91.8024));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);

                        break;
                    case "Zona 6":
                        
                        pts.add(new LatLng(18.66779, -91.7869));
                        pts.add(new LatLng(18.6572, -91.78051));
                        pts.add(new LatLng(18.65614, -91.78358));
                        pts.add(new LatLng(18.64737, -91.77971));
                        pts.add(new LatLng(18.64671, -91.77973));
                        pts.add(new LatLng(18.64641, -91.77964));
                        pts.add(new LatLng(18.63612, -91.7752));
                        pts.add(new LatLng(18.63808, -91.77078));
                        pts.add(new LatLng(18.64166, -91.76894));
                        pts.add(new LatLng(18.67276, -91.73606));
                        pts.add(new LatLng(18.67752, -91.72224));
                        pts.add(new LatLng(18.68541, -91.7283));
                        pts.add(new LatLng(18.67964, -91.74314));
                        pts.add(new LatLng(18.67203, -91.77065));
                        pts.add(new LatLng(18.67069, -91.77933));

                        contains1 = PolyUtil.containsLocation(lat_actual, lng_actual, pts, true);
                        break;
                    default:

                        break;

                }



                if(!contains1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle("Alerta")
                            .setMessage("El ambulante se encuentra fuera de zona establecida.")
                            .setPositiveButton("Reportar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    createLoginDialogo();
                                }
                            })
                    .setNegativeButton("Omitir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();
                }else{
                    Toast.makeText(getContext(),"Todo en orden ",Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        checkIfGPSEnable();
        //Toast.makeText(getContext(),String.valueOf(lng_actual),Toast.LENGTH_SHORT).show();

    }

    public AlertDialog createLoginDialogo() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.alert_dialog_report, null);

        editText = (EditText) v.findViewById(R.id.comentarioReportEdittextMap);

        builder.setView(v);


        builder.setPositiveButton("Generar reporte", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        comentario = editText.getText().toString();
                        if (!comentario.isEmpty()){
                            //Toast.makeText(getContext(),comentario+" "+ idResult+" "+ idUser + " " + NameUser,Toast.LENGTH_LONG).show();
                            String URL = "http://www.carmen.gob.mx/sistema-ambulante/estilos/android/generateReport.php";
                            ejecutarServicio(URL);
                        }else{
                            Toast.makeText(getContext(),"Ingrese un comentario",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });




        builder.create();


        return builder.show();
    }

    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"Reporte enviado",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString() + comentario,Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("id_master",String.valueOf(idUser));
                parametros.put("nombre_master",NameUser);
                parametros.put("id_comerciante",String.valueOf(idResult));
                parametros.put("comentario",comentario);
                parametros.put("reporte","Fuera de zona");
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);



    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void checkIfGPSEnable(){
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isGPSEnable(){
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0){
                return false;
            }else{
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "No tienes permisos.", Toast.LENGTH_SHORT).show();
            return;
        }
        gMap.setMyLocationEnabled(true);
        gMap.getUiSettings().setMyLocationButtonEnabled(true);



        //currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //lng_actual = currentLocation.getLatitude();

        //Toast.makeText(getContext(),String.valueOf(currentLocation.getLatitude()),Toast.LENGTH_SHORT).show();


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,0,this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,0,this);

        gMap.addPolygon(new PolygonOptions()
        .clickable(false)
        .add(
                new LatLng(18.64303, -91.84439),
                new LatLng(18.64196, -91.84137),
                new LatLng(18.63456, -91.8353),
                new LatLng(18.63432, -91.83461),
                new LatLng(18.63123, -91.83195),
                new LatLng(18.63013, -91.83006),
                new LatLng(18.63036, -91.829),
                new LatLng(18.6302, -91.82792),
                new LatLng(18.63021, -91.82689),
                new LatLng(18.63016, -91.82605),
                new LatLng(18.63003, -91.82539),
                new LatLng(18.6294, -91.82517),
                new LatLng(18.62818, -91.82655),
                new LatLng(18.62786, -91.82677),
                new LatLng(18.62766, -91.8261),
                new LatLng(18.62842, -91.82457),
                new LatLng(18.62981, -91.82423),
                new LatLng(18.63089, -91.82285),
                new LatLng(18.63321, -91.82332),
                new LatLng(18.63431, -91.82159),
                new LatLng(18.63689, -91.82042),
                new LatLng(18.63794, -91.81888),
                new LatLng(18.64469, -91.81908),
                new LatLng(18.64326, -91.8227),
                new LatLng(18.64307, -91.82295),
                new LatLng(18.64616, -91.82734),
                new LatLng(18.64697, -91.82623),
                new LatLng(18.64889, -91.82226),
                new LatLng(18.65111, -91.82463),
                new LatLng(18.64894, -91.82689),
                new LatLng(18.64858, -91.82776),
                new LatLng(18.65006, -91.82908),
                new LatLng(18.64909, -91.83001),
                new LatLng(18.64873, -91.83068),
                new LatLng(18.64631, -91.83821)
        )
        .strokeColor(Color.argb(255, 214, 186, 219)) //Linea
        .fillColor(Color.argb(100, 214, 186, 219))); //Relleno

        gMap.addPolygon(new PolygonOptions()
                .clickable(false)
                .add(
                        new LatLng(18.64303, -91.84439),
                        new LatLng(18.64631, -91.83821),
                        new LatLng(18.64871, -91.8307),
                        new LatLng(18.64907, -91.83004),
                        new LatLng(18.65004, -91.8291),
                        new LatLng(18.64856, -91.82778),
                        new LatLng(18.64892, -91.82691),
                        new LatLng(18.65034, -91.82552),
                        new LatLng(18.65109, -91.82465),
                        new LatLng(18.65256, -91.82615),
                        new LatLng(18.65403, -91.82476),
                        new LatLng(18.65535, -91.82206),
                        new LatLng(18.65489, -91.82164),
                        new LatLng(18.65569, -91.82042),
                        new LatLng(18.65677, -91.81694),
                        new LatLng(18.65751, -91.81719),
                        new LatLng(18.6582, -91.81461),
                        new LatLng(18.65849, -91.81393),
                        new LatLng(18.65793, -91.81339),
                        new LatLng(18.65908, -91.8095),
                        new LatLng(18.66119, -91.81063),
                        new LatLng(18.66251, -91.80882),
                        new LatLng(18.66293, -91.80738),
                        new LatLng(18.66344, -91.80648),
                        new LatLng(18.66356, -91.80584),
                        new LatLng(18.66334, -91.80554),
                        new LatLng(18.66238, -91.80509),
                        new LatLng(18.66229, -91.80483),
                        new LatLng(18.66262, -91.80367),
                        new LatLng(18.66258, -91.80347),
                        new LatLng(18.66592, -91.80403),
                        new LatLng(18.66561, -91.8103),
                        new LatLng(18.66522, -91.81101),
                        new LatLng(18.66409, -91.83046),
                        new LatLng(18.66494, -91.83833),
                        new LatLng(18.66494, -91.84009),
                        new LatLng(18.66455, -91.84089),
                        new LatLng(18.66384, -91.84103),
                        new LatLng(18.66251, -91.84291),
                        new LatLng(18.66026, -91.84523),
                        new LatLng(18.65229, -91.85115),
                        new LatLng(18.65046, -91.85145),
                        new LatLng(18.6478, -91.8502),
                        new LatLng(18.64822, -91.84905),
                        new LatLng(18.64761, -91.84885),
                        new LatLng(18.64723, -91.84809),
                        new LatLng(18.64828, -91.84695),
                        new LatLng(18.64881, -91.84763),
                        new LatLng(18.65021, -91.8483),
                        new LatLng(18.65107, -91.84653),
                        new LatLng(18.6496, -91.84576),
                        new LatLng(18.65018, -91.84458),
                        new LatLng(18.65331, -91.84619),
                        new LatLng(18.65392, -91.84551),
                        new LatLng(18.65049, -91.84373),
                        new LatLng(18.65113, -91.84245),
                        new LatLng(18.65479, -91.84447),
                        new LatLng(18.65533, -91.84375),
                        new LatLng(18.65031, -91.84117),
                        new LatLng(18.64994, -91.8421),
                        new LatLng(18.6504, -91.84247),
                        new LatLng(18.64912, -91.8453),
                        new LatLng(18.64586, -91.84746),
                        new LatLng(18.64568, -91.84712),
                        new LatLng(18.64496, -91.84757)
                )
                .strokeColor(Color.argb(255, 215, 243, 247)) //Linea
                .fillColor(Color.argb(100, 215, 243, 247))); //Relleno

        gMap.addPolygon(new PolygonOptions()
                .clickable(false)
                .add(
                        new LatLng(18.64009, -91.81895),
                        new LatLng(18.63794, -91.81888),
                        new LatLng(18.63689, -91.82042),
                        new LatLng(18.63553, -91.82086),
                        new LatLng(18.63401, -91.82109),
                        new LatLng(18.63382, -91.82131),
                        new LatLng(18.63353, -91.82132),
                        new LatLng(18.63301, -91.82175),
                        new LatLng(18.63269, -91.82171),
                        new LatLng(18.6326, -91.8216),
                        new LatLng(18.63236, -91.82172),
                        new LatLng(18.63228, -91.82216),
                        new LatLng(18.63201, -91.82272),
                        new LatLng(18.63186, -91.8228),
                        new LatLng(18.63066, -91.8227),
                        new LatLng(18.63043, -91.82244),
                        new LatLng(18.63022, -91.82238),
                        new LatLng(18.63012, -91.82176),
                        new LatLng(18.63, -91.8216),
                        new LatLng(18.6299, -91.82135),
                        new LatLng(18.6299, -91.82101),
                        new LatLng(18.62978, -91.82075),
                        new LatLng(18.62944, -91.82049),
                        new LatLng(18.62939, -91.82031),
                        new LatLng(18.62922, -91.82024),
                        new LatLng(18.62898, -91.82),
                        new LatLng(18.62886, -91.81998),
                        new LatLng(18.62885, -91.81968),
                        new LatLng(18.63034, -91.81774),
                        new LatLng(18.63111, -91.81657),
                        new LatLng(18.63158, -91.81547),
                        new LatLng(18.63172, -91.81495),
                        new LatLng(18.63173, -91.8146),
                        new LatLng(18.63157, -91.81418),
                        new LatLng(18.6314, -91.81405),
                        new LatLng(18.63145, -91.81384),
                        new LatLng(18.63137, -91.81349),
                        new LatLng(18.63149, -91.81282),
                        new LatLng(18.63134, -91.81216),
                        new LatLng(18.6313, -91.81088),
                        new LatLng(18.6319, -91.81),
                        new LatLng(18.63255, -91.80921),
                        new LatLng(18.63276, -91.8085),
                        new LatLng(18.63287, -91.80775),
                        new LatLng(18.63344, -91.80689),
                        new LatLng(18.63342, -91.80619),
                        new LatLng(18.63405, -91.80518),
                        new LatLng(18.63445, -91.80544),
                        new LatLng(18.63444, -91.80598),
                        new LatLng(18.63454, -91.80648),
                        new LatLng(18.6349, -91.80657),
                        new LatLng(18.63597, -91.80981),
                        new LatLng(18.63623, -91.81195),
                        new LatLng(18.63648, -91.81186),
                        new LatLng(18.6364, -91.81043),
                        new LatLng(18.63672, -91.8075),
                        new LatLng(18.63654, -91.8064),
                        new LatLng(18.63608, -91.80599),
                        new LatLng(18.63596, -91.80545),
                        new LatLng(18.63604, -91.80455),
                        new LatLng(18.6366, -91.80433),
                        new LatLng(18.63669, -91.80389),
                        new LatLng(18.63816, -91.80403),
                        new LatLng(18.63852, -91.80266),
                        new LatLng(18.63951, -91.80184),
                        new LatLng(18.64057, -91.80152),
                        new LatLng(18.64102, -91.80093),
                        new LatLng(18.64038, -91.80014),
                        new LatLng(18.64032, -91.79932),
                        new LatLng(18.64049, -91.79875),
                        new LatLng(18.64034, -91.79814),
                        new LatLng(18.64055, -91.79797),
                        new LatLng(18.64085, -91.79794),
                        new LatLng(18.64102, -91.79777),
                        new LatLng(18.64146, -91.79707),
                        new LatLng(18.64151, -91.79637),
                        new LatLng(18.64103, -91.79511),
                        new LatLng(18.64046, -91.79458),
                        new LatLng(18.63988, -91.79434),
                        new LatLng(18.63973, -91.79435),
                        new LatLng(18.64044, -91.79487),
                        new LatLng(18.64051, -91.79516),
                        new LatLng(18.64008, -91.79534),
                        new LatLng(18.63964, -91.79531),
                        new LatLng(18.63651, -91.79152),
                        new LatLng(18.63503, -91.78771),
                        new LatLng(18.63693, -91.78755),
                        new LatLng(18.63744, -91.78684),
                        new LatLng(18.63724, -91.78317),
                        new LatLng(18.63885, -91.78314),
                        new LatLng(18.63868, -91.78359),
                        new LatLng(18.63875, -91.7851),
                        new LatLng(18.63969, -91.78543),
                        new LatLng(18.64017, -91.78593),
                        new LatLng(18.64042, -91.78592),
                        new LatLng(18.64077, -91.78614),
                        new LatLng(18.64144, -91.7864),
                        new LatLng(18.64116, -91.78671),
                        new LatLng(18.64151, -91.7869),
                        new LatLng(18.64152, -91.78707),
                        new LatLng(18.64275, -91.78807),
                        new LatLng(18.64256, -91.78859),
                        new LatLng(18.64256, -91.78911),
                        new LatLng(18.64317, -91.78972),
                        new LatLng(18.64235, -91.78942),
                        new LatLng(18.64189, -91.7895),
                        new LatLng(18.64136, -91.78994),
                        new LatLng(18.64115, -91.79051),
                        new LatLng(18.64107, -91.79143),
                        new LatLng(18.64111, -91.79275),
                        new LatLng(18.64228, -91.7927),
                        new LatLng(18.64343, -91.79396),
                        new LatLng(18.6428, -91.79755),
                        new LatLng(18.64433, -91.80094),
                        new LatLng(18.64972, -91.80574),
                        new LatLng(18.64787, -91.81173),
                        new LatLng(18.64715, -91.81137),
                        new LatLng(18.64377, -91.81072),
                        new LatLng(18.64244, -91.81147),
                        new LatLng(18.64051, -91.81185),
                        new LatLng(18.63785, -91.81231),
                        new LatLng(18.63791, -91.81286),
                        new LatLng(18.63818, -91.81336),
                        new LatLng(18.63984, -91.81574),
                        new LatLng(18.63966, -91.81609)
                )
                .strokeColor(Color.argb(255, 170, 211, 189)) //Linea
                .fillColor(Color.argb(100, 170, 211, 189))); //Relleno

        gMap.addPolygon(new PolygonOptions()
                .clickable(false)
                .add(
                        new LatLng(18.64616, -91.82734),
                        new LatLng(18.64307, -91.82295),
                        new LatLng(18.64326, -91.8227),
                        new LatLng(18.64477, -91.81906),
                        new LatLng(18.64009, -91.81895),
                        new LatLng(18.63966, -91.81609),
                        new LatLng(18.63984, -91.81574),
                        new LatLng(18.63818, -91.81336),
                        new LatLng(18.63791, -91.81286),
                        new LatLng(18.63785, -91.81231),
                        new LatLng(18.64183, -91.81162),
                        new LatLng(18.64244, -91.81147),
                        new LatLng(18.64377, -91.81072),
                        new LatLng(18.64715, -91.81137),
                        new LatLng(18.64787, -91.81173),
                        new LatLng(18.64972, -91.80574),
                        new LatLng(18.64433, -91.80094),
                        new LatLng(18.6428, -91.79755),
                        new LatLng(18.64343, -91.79396),
                        new LatLng(18.64228, -91.7927),
                        new LatLng(18.64111, -91.79275),
                        new LatLng(18.64107, -91.79143),
                        new LatLng(18.64115, -91.79051),
                        new LatLng(18.64136, -91.78994),
                        new LatLng(18.64189, -91.7895),
                        new LatLng(18.64235, -91.78942),
                        new LatLng(18.64317, -91.78972),
                        new LatLng(18.65746, -91.8024),
                        new LatLng(18.65778, -91.80256),
                        new LatLng(18.66259, -91.80345),
                        new LatLng(18.66264, -91.80365),
                        new LatLng(18.66231, -91.80481),
                        new LatLng(18.6624, -91.80507),
                        new LatLng(18.66336, -91.80552),
                        new LatLng(18.66358, -91.80582),
                        new LatLng(18.66346, -91.80646),
                        new LatLng(18.66295, -91.80736),
                        new LatLng(18.66253, -91.8088),
                        new LatLng(18.66121, -91.81061),
                        new LatLng(18.6591, -91.80948),
                        new LatLng(18.65795, -91.81337),
                        new LatLng(18.65851, -91.81391),
                        new LatLng(18.65822, -91.81459),
                        new LatLng(18.65753, -91.81717),
                        new LatLng(18.65679, -91.81692),
                        new LatLng(18.65571, -91.8204),
                        new LatLng(18.65521, -91.82123),
                        new LatLng(18.65491, -91.82162),
                        new LatLng(18.65537, -91.82203),
                        new LatLng(18.65405, -91.82474),
                        new LatLng(18.65258, -91.82613),
                        new LatLng(18.64889, -91.82226),
                        new LatLng(18.64697, -91.82623)
                )
                .strokeColor(Color.argb(255, 167, 206, 227)) //Linea
                .fillColor(Color.argb(100, 167, 206, 227))); //Relleno

        gMap.addPolygon(new PolygonOptions()
                .clickable(false)
                .add(
                        new LatLng(18.64257, -91.78916),
                        new LatLng(18.64256, -91.78859),
                        new LatLng(18.64275, -91.78807),
                        new LatLng(18.64152, -91.78707),
                        new LatLng(18.64151, -91.7869),
                        new LatLng(18.64116, -91.78671),
                        new LatLng(18.64144, -91.7864),
                        new LatLng(18.64077, -91.78614),
                        new LatLng(18.64042, -91.78592),
                        new LatLng(18.64017, -91.78593),
                        new LatLng(18.63969, -91.78543),
                        new LatLng(18.63875, -91.7851),
                        new LatLng(18.63868, -91.78359),
                        new LatLng(18.63885, -91.78314),
                        new LatLng(18.63498, -91.78318),
                        new LatLng(18.63492, -91.78121),
                        new LatLng(18.63513, -91.77889),
                        new LatLng(18.63535, -91.77849),
                        new LatLng(18.63558, -91.77826),
                        new LatLng(18.63558, -91.77747),
                        new LatLng(18.63612, -91.7752),
                        new LatLng(18.64641, -91.77964),
                        new LatLng(18.64671, -91.77973),
                        new LatLng(18.64737, -91.77971),
                        new LatLng(18.65614, -91.78358),
                        new LatLng(18.6572, -91.78051),
                        new LatLng(18.66779, -91.7869),
                        new LatLng(18.66735, -91.78898),
                        new LatLng(18.66757, -91.78995),
                        new LatLng(18.66642, -91.79718),
                        new LatLng(18.66659, -91.79737),
                        new LatLng(18.66662, -91.79762),
                        new LatLng(18.66592, -91.80345),
                        new LatLng(18.66594, -91.80401),
                        new LatLng(18.65778, -91.80256),
                        new LatLng(18.65746, -91.8024)
                )
                .strokeColor(Color.argb(255, 244, 225, 231)) //Linea
                .fillColor(Color.argb(150, 244, 225, 231))); //Relleno

        gMap.addPolygon(new PolygonOptions()
                .clickable(false)
                .add(
                        new LatLng(18.66779, -91.7869),
                        new LatLng(18.6572, -91.78051),
                        new LatLng(18.65614, -91.78358),
                        new LatLng(18.64737, -91.77971),
                        new LatLng(18.64671, -91.77973),
                        new LatLng(18.64641, -91.77964),
                        new LatLng(18.63612, -91.7752),
                        new LatLng(18.63808, -91.77078),
                        new LatLng(18.64166, -91.76894),
                        new LatLng(18.67276, -91.73606),
                        new LatLng(18.67752, -91.72224),
                        new LatLng(18.68541, -91.7283),
                        new LatLng(18.67964, -91.74314),
                        new LatLng(18.67203, -91.77065),
                        new LatLng(18.67069, -91.77933)
                )
                .strokeColor(Color.argb(255, 240, 234, 162)) //Linea
                .fillColor(Color.argb(100, 240, 234, 162))); //Relleno




        // Add a marker in Sydney and move the camera
        LatLng carmen = new LatLng(lat, lng);
        gMap.addMarker(new MarkerOptions().position(carmen).title("Asignado"));
        gMap.setMinZoomPreference(13);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(carmen)
                .zoom(15)
                .build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onLocationChanged(Location location) {

        lat_actual=location.getLatitude();
        lng_actual=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
