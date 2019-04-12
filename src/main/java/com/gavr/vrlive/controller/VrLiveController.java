package com.gavr.vrlive.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Administrator
 * @className VrLiveController
 * @description TODO
 * @date 2019/04/08
 */
@Controller
//@RequestMapping("/live")
public class VrLiveController {


    @RequestMapping("/index")
    public String liveIndex(){
        return "live/liveIndex";
    }

    @ResponseBody
    @RequestMapping("/sdcard/{address:.+}/liveStream/{name:.+}")
    public void test(@PathVariable String name, @PathVariable String address,
                     HttpServletResponse response, HttpServletRequest request) {
        sendGet(address, name, response);
    }

    public void sendGet(String address, String data, HttpServletResponse response) {
        try {
            response.setContentType("text/plain");
            OutputStream os = response.getOutputStream();
            CloseableHttpClient client = HttpClients.createDefault();
            try {
//                HttpGet httpGet = new HttpGet("http://"+ip+":8000/sdcard/liveStream/" + data);
                HttpGet httpGet = new HttpGet("http://"+address+":9090/live/" + data);
//                HttpGet httpGet = new HttpGet(address+"/" + data);
                CloseableHttpResponse resp = client.execute(httpGet);
                int statusCode = resp.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    HttpEntity httpEntity = resp.getEntity();
                    long contentLength = httpEntity.getContentLength();
                    response.setContentLength((int) contentLength);
                    InputStream is = httpEntity.getContent();
                    byte[] buffer = new byte[4096];
                    int r = 0;
                    while ((r = is.read(buffer)) > 0) {
                        os.write(buffer, 0, r);
                    }
                    os.flush();
                    os.close();
                }
            } finally {
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
