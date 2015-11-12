package com.ambimmort.nisp3.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hedingwei on 8/24/15.
 */
@Service
public class SolrService {


    private final static String url = "http://kk.ambimmort.com/solr/kk_core/update?wt=json";

    public void save(String document){

        DataOutputStream wr = null;
        try {
            byte[] postData       = document.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = url;
            URL url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            wr = new DataOutputStream( conn.getOutputStream());
            wr.write( postData );
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }




}
