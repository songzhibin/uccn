package com.yuzhi.home.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuzhi.home.config.Config;
import com.yuzhi.home.model.Sm;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图床上传工具
 *
 * @author soso
 * @date 2019-1-11 09:32:10
 */
public class SmUtil {
    /**
     * @param multipartFile 表单名称。上传图片用到
     * @param ssl           是否使用 https 输出，强制开启
     * @param format        输出的格式。可选值有 json、xml。默认为 json
     * @return
     */
    public static Sm saveFile(MultipartFile multipartFile, boolean ssl, String format) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("smfile", getFile(multipartFile));
        map.put("ssl", ssl);
        map.put("format", format);

        HttpRequest httpRequest = HttpRequest.post(Config.SM_URL)
                .header("accept", "*/*")
                .header("connection", "Keep-Alive")
                .header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        String smStr = httpRequest.form(map).execute().body();

        return JSONUtil.toBean(smStr, Sm.class);
    }

    /**
     * @param multipartFile 表单名称。上传图片用到
     * @param format        输出的格式。可选值有 json、xml。默认为 json
     * @return
     */
    public static Sm saveFile(MultipartFile multipartFile, String format) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("smfile", getFile(multipartFile));
        map.put("format", format);

        String smStr = HttpUtil.post(Config.SM_URL, map);

        return JSONUtil.toBean(smStr, Sm.class);
    }

    /**
     * @param multipartFile 表单名称。上传图片用到
     * @param ssl           是否使用 https 输出，强制开启
     * @return
     */
    public static Sm saveFile(MultipartFile multipartFile, boolean ssl) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("smfile", getFile(multipartFile));
        map.put("ssl", ssl);

        String smStr = HttpUtil.post(Config.SM_URL, map);

        return JSONUtil.toBean(smStr, Sm.class);
    }

    /**
     * @param multipartFile 表单名称。上传图片用到
     * @return
     */
    public static Sm saveFile(MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("smfile", getFile(multipartFile));

        String smStr = HttpUtil.post(Config.SM_URL, map);

        return JSONUtil.toBean(smStr, Sm.class);
    }

    public static File getFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getName());
        try {
            FileUtil.writeFromStream(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static void main(String[] args) {
        HashMap<String, String> para = new HashMap<String, String>();
        HashMap<String, String> header = new HashMap<String, String>();
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("smfile", "D:\\myself\\photo\\4.jpg");

        JSONObject data = Image.upload(header, body);
        System.out.println(data);

    }
}
