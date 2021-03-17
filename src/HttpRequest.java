import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
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

    void setHeader(String header, String val){
        headers.put(header, val);
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(String param, String val) {
        params.put(param, val);
    }

    public HashMap<String, String> getBody() {
        return this.body;
    }

    public void setBody(HashMap<String, String> body) {

        if(HttpRequestMethod.GET != getHttpRequestMethod())
        this.body = body;
    }

    public void setBody(String name, String val) {

        if (HttpRequestMethod.GET != getHttpRequestMethod())
            body.put(name, val);

    }


    public void setHeadersProperty(HttpURLConnection con) {

        for (Map.Entry<String, String> entry : headers.entrySet()) {

            con.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public HttpResponse request() throws HttpException, IOException {

        JSONFormat bodyJsonFormat = null;
        DataOutputStream out = null;
        //---------------------------------- connection.
        URL url = new URL(getUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(getHttpRequestMethod().name());        // gives back the request method of user.

        con.setDoInput(true);       // true indicates the server returns response

        if (getHttpRequestMethod() != HttpRequestMethod.GET) {
            // convert to JSON method.
             bodyJsonFormat = JSONFormat.JSONConvert(body);
            //*** should I check params size or null condition ?!
            con.setDoOutput(true);
            out = new DataOutputStream(con.getOutputStream());

        } else {
            con.setDoOutput(false); // false indicates this is a GET request
        }

        String parameters = ParameterStringBuilder.getParamsString(params);  // sets all current requests params in to the string.

        String Headers = HeaderStringBuilder.getHeadersString(headers); //*** may be redundant!!!
        setHeadersProperty(con);                    // it will set all the headers for request.

        if (params.size() > 0) {
                   // for sending to the server.
            out.writeBytes(parameters);             // sending parameters to Server.
            out.flush();
        }

        if (bodyJsonFormat != null) {           // sending body(JSON Format) to the server.

            out.writeBytes(bodyJsonFormat.getJSONContent());
            out.flush();
        }

        if (out != null) {

            out.close();
        }

        //----------------------------------- check the server response part -------------------------------
        int statusCode = con.getResponseCode();
        String responseBody = readResponseContent(con);
        System.out.println(responseBody);
        HttpResponse response = new HttpResponse(statusCode, con.getHeaderFields(), responseBody);
        validateResponse(response, statusCode);

        return response;
    }

    private void validateResponse(HttpResponse response, int responseCode) throws HttpException {

        if (responseCode <= 499 && responseCode >= 400)
            throw new HttpException("Client error!", response);
        else if (responseCode <= 599 && responseCode >= 500)
            throw new HttpException("Server error!", response);
    }

    private String readResponseContent(HttpURLConnection connection) throws IOException{

        String responseLine = null;
        StringBuilder responseMassage = new StringBuilder();

        try(BufferedReader br = new BufferedReader(

                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            while ((responseLine = br.readLine()) != null) {

                responseMassage.append(responseLine.trim());
                responseMassage.append("\n");   // ***
            }

        }
        return responseMassage.toString();
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