package spring.cloud.service.controller;

public class Test {

    public static void main(String[] args) {

        int a = 5;
        int b = 5;
        String naem = "niaho";
        String naem2 = "niaho";
        String naem4 = "n5aho";
//        String naem3 = new String("niaho");
//        System.out.println(a==b);
//        System.out.println(naem==naem2);
//        System.out.println(naem.equals(naem2));
//        System.out.println(naem==naem3);
//        System.out.println(naem.equals(naem3));
        if (naem.charAt(2) == naem4.charAt(2)) {
            String s = String.valueOf("21111");
            System.out.println(s);
        } else {
            String s = String.valueOf("21134567811");
            System.out.println(s);
        }

//        Map map = new HashMap();
//        map.put("nihao","bi");
//        List<Map> list = new ArrayList<>();
//        list.add(map);
//        list.clear();
//        map.clear();
//        System.out.println("list");
//        String name = "12";
//        String name2= null;
//        Set set = new TreeSet();
//        set.add("122");
//        set.add(name2);
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

//        int i1 = name2.compareTo(name);
//        int compare = Integer.compare(1, 2);
//        int compare1 = Boolean.compare(true, false);
//        Thread thread1 = new Thread();
//        Thread thread2 = new Thread();
//        Thread thread3 = new Thread();
//        thread3.interrupt();
////        thread3.sleep(500L);
////        thread3.join();
//        Thread thread0 = Thread.currentThread();
//        list.add(thread0);
//        list.add(thread1);
//        list.add(thread2);
//        list.add(thread3);
//        String name = "123";
////        BeanUtils.copyProperties();
//        char[] chars = name.toCharArray();
//
//        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(200L);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("threadName is :" + Thread.currentThread().getName());
//                }
//            }).start();
//        }


//        for (Thread thread : list) {
//            if (thread == thread3) {
//                if (thread.isAlive()) {
//                    System.out.println("当前线程去强锁");
////                    thread.stop();
//                    if (!thread.isAlive()) {
//                        System.out.println("当前线程已经不存活了");
//                    }
//                }
//            } else {
//                System.out.println(thread.getName() + ":" + thread.getId());
//            }
//        }

    }
}
