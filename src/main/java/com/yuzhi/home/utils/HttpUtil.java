package com.yuzhi.home.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuzhi.home.config.BaseException;
import okhttp3.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @Description：http请求工具类-json格式
 * @Author：song
 * @Date：2022/06/13
 */
public class HttpUtil {

    public static JSONObject doGetObject(String url) throws IOException {
        JSONObject jsonObject = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            jsonObject = JSON.parseObject(result);
            return jsonObject;
        } catch (IOException e) {
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doGet(String url) throws BaseException {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doPostXml(String url, String xml) throws BaseException {
        try {
            MediaType JSON = MediaType.parse("application/xml; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, xml);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doPost(String url) throws BaseException {
        try {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doPost(String url, JSONObject jo) throws BaseException {
        try {
            MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, jo.toJSONString());
            Request request = new Request.Builder().url(url).header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    /**
     * 基层治理综合信息平台post请求
     *
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws BaseException
     */
    public static String doPost(String url, JSONObject headers, JSONObject params) throws BaseException {
        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, params.toJSONString());
            Request request = new Request.Builder().url(url).header("Content-Type", "application/json;charset=utf-8")
                    .header("auth", headers.getString("auth"))
                    .header("Authorization", headers.getString("Authorization"))
                    .header("Cookie", headers.getString("Cookie"))
                    .post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doPost(String url, String json) throws BaseException {
        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).header("Content-Type", "application/json;charset=utf-8")
                    .post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("请求失败:" + e.getMessage());
        }
    }

    public static String doPostWaitLongger(String url, String json) throws Exception {
        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(url).header("Content-Type", "application/json;charset=utf-8")
                    .post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new Exception("请求失败:" + e.getMessage());
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                throw new BaseException("请求失败:" + e.getMessage());
            }
        }
        return ip;
    }

    public static void main(String[] args) {
        String url = "http://10.45.14.83/api/issue-business-server/issue/findIssuePage";

        JSONObject hander = new JSONObject();
        hander.put("auth", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");
        hander.put("Authorization", "Basic dXNlcmNlbnRlcjoxMTg2MDQ1ZDU1OTlkZTZlZjJjYTI4MjM0N2E1NWNhMg==");
        hander.put("Referer", "http://10.45.14.83/province-header/0.1.0/tq-zjgrid-issuecenter/areaEvent/eventListHave");
        hander.put("Cookie", "-admin-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("searchKeyWords", "");
        jsonObject.put("issueMark", 6);
        jsonObject.put("status", 300);
        jsonObject.put("sidx", "last_deal_date");
        jsonObject.put("sord", "desc");
        jsonObject.put("rows", 10000);
        jsonObject.put("page", 1);
        jsonObject.put("orgId", "1372022184028995586");
        jsonObject.put("departmentDimension", 1);

        // 请求接口
        String responseJson = HttpUtil.doPost(url, hander, jsonObject);
        JSONObject jsonObject2 = JSONObject.parseObject(responseJson);
        JSONObject data = jsonObject2.getJSONObject("data");
        System.out.println(jsonObject2.toJSONString());
        String rows = jsonObject2.getJSONObject("data").getString("rows");
    }

}
