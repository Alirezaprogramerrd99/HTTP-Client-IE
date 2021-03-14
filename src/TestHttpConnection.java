
import java.net.*;
import java.io.*;
import java.util.*;

public class TestHttpConnection {

    public static void main(String[] args){

        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "1");
        map.put("key2", "2");

        JSONFormat jsonFormat = JSONFormat.JSONConvert(map);

        System.out.println(jsonFormat.getJSONContent());


    }
}
