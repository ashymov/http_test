package kg.megacom.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.megacom.models.Lot;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class ApacheClient {
    public static ApacheClient getInstance() {
        return new ApacheClient();
    }

    private final static CloseableHttpClient httpClient= HttpClients.createDefault();

    public void testGet(){

        HttpGet httpGet=new HttpGet("http://localhost:8080/lot/gett/1");

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine().getStatusCode());

            HttpEntity entity=response.getEntity();
            ObjectMapper om=new ObjectMapper();
            Lot lot = om.readValue(entity.getContent(),Lot.class);
            System.out.println(lot);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void testPost(){
        Lot lot=new Lot();
        lot.setName("Test");
        lot.setStartDate(new Date());

        HttpPost httpPost=new HttpPost("http://localhost:8080/lot/savee");
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Content-Type","application/json");
        ObjectMapper om=new ObjectMapper();
        StringEntity entity=null;
        try {
            entity =new StringEntity(om.writeValueAsString(lot));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);

        try {
            CloseableHttpResponse response= httpClient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity1=response.getEntity();
            lot=om.readValue(entity1.getContent(),Lot.class);
            System.out.println(lot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
