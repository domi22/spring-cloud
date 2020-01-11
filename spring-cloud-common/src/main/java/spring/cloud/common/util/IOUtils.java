package spring.cloud.common.util;

import java.io.*;

public class IOUtils {

    /**
     * 字节流（图片，音，视频）
     * @param is
     * @param path
     * @return
     */
    public static Boolean videoStreamOut(InputStream is,String path){
        Boolean isDone = true;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            dis =new DataInputStream(is);
            dos = new DataOutputStream(new FileOutputStream(path));
            byte[] by = new byte[1024];
            int len = dis.read(by);
            while(len!=-1){
                dos.write(by);
                len = dis.read(by);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isDone = false;
        }catch (IOException e) {
            e.printStackTrace();
            isDone = false;
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isDone;
    }

    /**
     * 字节流（二进制文件）
     * @param is
     * @param path
     * @return
     */
    public static Boolean byteStreamOut(InputStream is,String path){
        Boolean isDone = true;
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(path);
            int a;
            while ((a = is.read()) != -1) {
                //二进制a转为char输出 System.out.println((char)a);
                fo.write(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isDone = false;
        }catch (IOException e) {
            e.printStackTrace();
            isDone = false;
        }finally{
            try {
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isDone;
    }

    /**
     * 字符流（包含中文）
     * @param r
     * @param path
     * @return
     */
    public static Boolean characterStreamOut(Reader r,String path){
        Boolean isDone = true;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(r);
            bw = new BufferedWriter(new FileWriter(path));
            String line = br.readLine();
            while(line!=null){
                bw.write(line);
                bw.flush();
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            isDone = false;
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isDone;
    }
}
