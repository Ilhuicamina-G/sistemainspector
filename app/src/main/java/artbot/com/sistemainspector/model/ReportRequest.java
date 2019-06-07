package artbot.com.sistemainspector.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReportRequest extends StringRequest {

    private static final String URL = "http://yobusco.org/android/report.php";
    private Map<String,String> params;

    public ReportRequest(String id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL,listener,null);
        params=new HashMap<>();
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() { return params;}
}
