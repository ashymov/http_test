package kg.megacom;

import kg.megacom.http.ApacheClient;
import kg.megacom.http.OkHttpHelper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

//        ApacheClient.getInstance().testPost();
        try {
//            OkHttpHelper.getInstance().get();
//            OkHttpHelper.getInstance().post();
            OkHttpHelper.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
