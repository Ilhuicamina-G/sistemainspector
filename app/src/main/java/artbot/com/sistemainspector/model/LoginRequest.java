package artbot.com.sistemainspector.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String URL = "http://www.carmen.gob.mx/sistema-ambulante/estilos/android/login.php";
    private Map<String,String> params;

    public LoginRequest(String email, String pass, Response.Listener<String> listener) {
        super(Request.Method.POST, URL,listener,null);
        params=new HashMap<>();
        params.put("email",email);
        params.put("pass",pass);
    }

    @Override
    public Map<String, String> getParams() { return params;}
}
