package com.xzx.util;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestApiUtil {

    public static String sendToRestApi(String url, Object obj){
        String res = null;
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        try {
            MultiValueMap<String, Object> params = MapConvertUtil.obj2Map(obj);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity = client.postForEntity(url, requestEntity, String.class);
            res = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
