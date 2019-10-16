package spring.cloud.common.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class TestIo {

    public static void main(String[] args) throws Exception {

        /**
         * https://jingyan.baidu.com/article/3a2f7c2e07783b26aed61154.html
         */
        URL url = new URL("http://www.google.com/intl/en_ALL/images/logo.gif");
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "gif", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        /**
         * 流的转换与复用，请参见 https://www.cnblogs.com/yixiu868/p/8144670.html
         *
         */

        /**
         * 有时候我们需要对同一个InputStream对象使用多次。比如，客户端从服务器获取数据 ，
         * 利用HttpURLConnection的getInputStream()方法获得Stream对象，这时既要把数据显示到前台（第一次读取），
         * 又想把数据写进文件缓存到本地（第二次读取）。
         * 但第一次读取InputStream对象后，第二次再读取时可能已经到Stream的结尾了（EOFException）或者Stream已经close掉了。
         * 而InputStream对象本身不能复制，因为它没有实现Cloneable接口。此时，可以先把InputStream转化成ByteArrayOutputStream，后面要使用InputStream对象时，再从ByteArrayOutputStream转化回来就好了。代码实现如下：
         */

    }
}
