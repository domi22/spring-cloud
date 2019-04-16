package spring.boot.common.function.threadpool.task;

import java.io.*;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 如何设计一个任务:
 * 1- 是否具备可取消操作
 * 2- 是否能响应中断，中断是实现取消的最佳实践
 * 3- call方法中要允许线程的退出
 */
public class FileLoadTask<V> implements CancellableTask<V> {
    private InputStream input;
    private OutputStream output;
    private String filename;

    public FileLoadTask(String filename) {
        this.filename = filename;
    }

    /**
     * 转存文件方法
     */
    public synchronized void download() throws FileNotFoundException, IOException {
        File file = new File(filename);
        File saveFile = null;
        String[] names = filename.split("/");
        String saveName = names[names.length - 1];
        saveFile = new File("tmp/" + saveName);
        //System.out.println(saveFile.getAbsolutePath());
        input = new FileInputStream(file);
        output = new FileOutputStream(saveFile);
        // 进行转存
        int len = 0;
        byte[] buffer = new byte[1024];
        while (-1 != (len = input.read(buffer, 0, buffer.length))) {
            output.write(buffer, 0, len);
        }
        input.close();
        output.close();
    }


    @Override
    public void cancel() {
        try {
            if (null != input)
                input.close();
            if (null != output)
                output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RunnableFuture<V> newTask() {
        //重写FutureTask的cancel()方法，首先调用自定义的 cancel方法，停掉阻塞的任务
        return new FutureTask<V>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                FileLoadTask.this.cancel();
                return super.cancel(mayInterruptIfRunning);
            }
        };
    }

    @Override
    public V call() throws Exception {
        //1- 中断是取消任务的最合理方式
        //2- 也可以通过Future来取消任务
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                download();
//                Thread.sleep(2000);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            throw new Exception("各种异常原样抛出",e.getCause());
//        } finally {
//            Thread.currentThread().interrupt();
//        }
        Thread.sleep(5000);
        return null;
    }
}
