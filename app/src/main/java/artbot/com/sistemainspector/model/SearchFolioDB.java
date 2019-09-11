package artbot.com.sistemainspector.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchFolioDB extends StringRequest {

    private static final String URL = "http://www.carmen.gob.mx/sistema-ambulante/estilos/android/scan.php";
    private Map<String,String> params;

    public SearchFolioDB(String folio, Response.Listener<String> listener){
        super(Request.Method.POST, URL,listener,null);
        params=new HashMap<>();
        params.put("id", folio);
    }

    @Override
    public Map<String, String> getParams() { return params;}

}
