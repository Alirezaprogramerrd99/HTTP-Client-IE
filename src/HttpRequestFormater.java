import java.util.HashMap;

public class HttpRequestFormater {

    public static void addHeader(HttpRequest request, String key, String value){
        request.setHeader(key, value);
    }

    public static void addParam(HttpRequest request, String key, String value){
        request.setParams(key, value);
    }


}
