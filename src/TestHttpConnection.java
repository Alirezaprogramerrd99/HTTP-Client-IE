
import java.net.*;
import java.io.*;
import java.util.*;

public class TestHttpConnection {

    public static void main(String[] args){

        try{
            URL url = new URL("http://www.javatpoint.com/java-tutorial");
            HttpURLConnection huc = (HttpURLConnection)url.openConnection();
            //HttpRequest httpRequest = new HttpRequest()

            int i = 0;

            while(huc.getHeaderFieldKey(i) != null) {
                System.out.println(huc.getHeaderFieldKey(i) + " = " + huc.getHeaderField(i));

            }


            huc.disconnect();
        }catch(Exception e){System.out.println(e);}
    }
}
