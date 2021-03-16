
import java.net.*;
import java.io.*;
import java.util.*;

public class TestHttpConnection {

    public static void main(String[] args){

        HttpRequest httpRequest = new HttpRequest("https://www-users.cs.umn.edu/~kumar001/dmbook/firsted.php");

        try {  // must write class to do this printing works..
            HttpResponse httpResponse = httpRequest.request();
            System.out.println(httpResponse.getStatus());

            Map<String, List<String>> serverHeaders = httpResponse.getHeaders();

            for (String key : serverHeaders.keySet()) {
                System.out.println(key + ":");

                List<String> values = serverHeaders.get(key);

                for (String aValue : values) {
                    System.out.print("\t" + aValue);
                }
                System.out.println();
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }


    }
}
