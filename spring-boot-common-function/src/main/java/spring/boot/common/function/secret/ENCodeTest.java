package spring.boot.common.function.secret;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码方式的测试类
 */
public class ENCodeTest {

    public static void main(String[] args) {


        String url = "taobao.comn?name=你好&age=27";
        try {
            String encode = URLEncoder.encode(url,"UTF-8");
            System.out.println("encode>>>" + encode);

            String decode = URLDecoder.decode(encode, "UTF-8");
            System.out.println("decode>>>" + decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*
        encode>>>taobao.comn%3Fname%3D%E4%BD%A0%E5%A5%BD%26age%3D27
        decode>>>taobao.comn?name=你好&age=27
         */







    }


}
