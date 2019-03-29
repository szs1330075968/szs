package com.szs.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author sunzs
 * created on 2019/2/26
 */
@Slf4j
public class test {


    public static void main(String[] args) {
        Integer id = 1791;
        Long timeStamp = System.currentTimeMillis();
        String secretKey = "0513a668c9d9dad9d4bde6229a07320a";
        try {
            String signTemp = id+""+timeStamp+secretKey;
            String sign =  DigestUtils.md5Hex(signTemp.getBytes("UTF-8"));
        }catch (Exception e){
            log.error("签名失败");
        }

    }


}
