import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String url;
    private HttpRequestMethod httpRequestMethod;
    private HashMap<String, String> headers;
    private HashMap<String, String> params;
    private HashMap<String, String> body;


    public HttpRequest(String url) {
        this(url, HttpRequestMethod.GET, new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public HttpRequest(String url, HttpRequestMethod httpRequestMethod) {
        this(url, httpRequestMethod, new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public HttpRequest(String url, HttpRequestMethod httpRequestMethod, HashMap<String, 
            String> headers, HashMap<String, String> params, HashMap<String, String> body) {

        this.url = url;
        this.httpRequestMethod = httpRequestMethod;
        this.headers = headers;
        this.params = params;
        this.body = body;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpRequestMethod getHttpRequestMethod() {
        return this.httpRequestMethod;
    }

    public void setHttpRequestMethod(HttpRequestMethod httpRequestMethod) {
        this.httpRequestMethod = httpRequestMethod;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(String param, String val) { params.put(param, val); }

    public HashMap<String, String> getBody() {
        return this.body;
    }

    public void setBody(HashMap<String, String> body) {
        this.body = body;
    }

    public void setHeadersProperty(HttpURLConnection con){

        for (Map.Entry<String, String> entry: headers.entrySet()) {

            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public HttpResponse request() throws HttpException, IOException {

        String requestMassage = "";   // has the every thing in it.
        // connection.
        URL url = new URL(getUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(getHttpRequestMethod().name());   // gives back the request method of user.

        // TODO parameters.
        String parameters = ParameterStringBuilder.getParamsString(params);  // sets all current requests params in to the string.

        // TODO Headers.
        String Headers = HeaderStringBuilder.getHeadersString(headers); //*** may be redundant!!!
        setHeadersProperty(con); // it will set all the headers for request.

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());  // for sending to the server.

        out.writeBytes(requestMassage);

        //return new HttpResponse();
    }
}

/*

POST /api/ValidateTwitterFollowerCount HTTP/1.1
Host: myazurefunction.azurewebsites.net
Content-Type: application/json
cache-control: no-cache
Postman-Token: XXXXXXX-XXXXX-XXXXXX

{  // --> json data part...
    "followersCount" : 220,
    "tweettext":"#Stack Overflow rocks",
    "Name": "John Doe"
}

 */