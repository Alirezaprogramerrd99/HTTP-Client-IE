
import java.net.*;
import java.io.*;
import java.util.*;

public class TestHttpConnection {

    public static void main(String[] args){

       // HttpRequest httpRequest = new HttpRequest("https://www-users.cs.umn.edu/~kumar001/dmbook/firsted.php");

        HashMap<String, String> p = new HashMap<>();
        HashMap<String, String> h = new HashMap<>();

        HashMap<String, String> b = new HashMap<>();

        h.put("Content-Type", "application/json; utf-8");
        h.put("Accept", "application/json");

        b.put("name", "Upendra");

        b.put("job", "Programmer");

        HttpRequest httpRequest2 = new HttpRequest("https://reqres.in/api/users", HttpRequestMethod.POST,
                h, p, b);

        try {  // must write class to do this printing works..
            HttpResponse httpResponse = httpRequest2.request();
            //System.out.println(httpResponse.getStatus());

            Map<String, List<String>> serverHeaders = httpResponse.getHeaders();
            String body = httpResponse.getBody();

            for (String key : serverHeaders.keySet()) {
                System.out.println(key + ":");

                List<String> values = serverHeaders.get(key);

                for (String aValue : values) {
                    System.out.print("\t" + aValue);
                }
                System.out.println();
            }

            System.out.println("response body: ");
            System.out.println(body);
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }
}
