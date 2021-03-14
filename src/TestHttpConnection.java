
import java.net.*;
import java.io.*;
import java.util.*;

public class TestHttpConnection {

    public static void main(String[] args){

        HttpRequest httpRequest = new HttpRequest("https://www.tutorialspoint.com/http/http_requests.htm");

        try {
            HttpResponse httpResponse = httpRequest.request();
            System.out.println(httpResponse.getStatus());
        }

        catch (Exception e){
            e.printStackTrace();
        }


    }
}
