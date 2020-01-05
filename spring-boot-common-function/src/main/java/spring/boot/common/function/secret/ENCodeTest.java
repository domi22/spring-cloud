package spring.boot.common.function.secret;

import org.apache.commons.codec.CharEncoding;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;

/**
 * 编码方式的测试类
 */
public class ENCodeTest {

    public static void main(String[] args) throws Exception {
        /**
         * 编码方式
         */
        testUrlEncode();
        /**
         * 摘要算法
         */
        testMD5();
    }

    private static void testUrlEncode() {
        String url = "taobao.comn?name=你好&age=27";
        try {
            String encode = URLEncoder.encode(url,CharEncoding.UTF_8);
            System.out.println("encode>>>" + encode);

            String decode = URLDecoder.decode(encode, CharEncoding.UTF_8);
            System.out.println("decode>>>" + decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*
        encode>>>taobao.comn%3Fname%3D%E4%BD%A0%E5%A5%BD%26age%3D27
        decode>>>taobao.comn?name=你好&age=27
         */
    }

    private static void testMD5() {
        try {
            // SHA-1  MD5 SAH-256 SHA-512
            MessageDigest md = MessageDigest.getInstance("MD5");
            String name = "helloword";
            md.update(name.getBytes(CharEncoding.UTF_8));
            byte[] digest = md.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
