package spring.boot.common.function.util;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static CloseableHttpClient getCloseableHttpClient(){
        return HttpClients.createDefault();
    }

    public static List<NameValuePair> getQueryParam(Map<String,String> queryMap){
        List<NameValuePair> queryParam = new ArrayList<>();
        Iterator it = queryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry parmEntry = (Map.Entry) it.next();
            queryParam.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
        }
        return queryParam;
    }


    public static URI create(String scheme,String host, int port, String path) throws URISyntaxException {
        return new URI(scheme, null, host, port, path, null, null);
    }
    public static URI create(String scheme,String host, String path) throws URISyntaxException {
        return new URI(scheme, null, host, -1, path, null, null);
    }
    public static URI create(String scheme,String host, String path,int port,String query) throws URISyntaxException {
        return new URI(scheme, null, host, port, path, query, null);
    }
    public static URI create(String scheme,String host, String path,String query) throws URISyntaxException {
        return new URI(scheme, null, host, -1, path, query, null);
    }




}
