package pers.lichen.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 基础线程机制：执行器(主要介绍三种)
 * 不推荐使用，容易造成资源耗尽，推荐使用${@link java.util.concurrent.ThreadPoolExecutor}
 * 
 * @author : lichen
 * @since : 2019-05-13 09:42
 */
public class ExecutorsDemo {

    public static void main(String[] args) throws InterruptedException {

        //缓存线程池,每个人物创建一个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        BaseThreadDemo.MyRunable myRunable = new BaseThreadDemo.MyRunable();
        for (int i = 0; i < 3; i++) {
            executorService.submit(myRunable);
        }
        Thread.sleep(1000);
        executorService.shutdown();
        System.out.println("——————————————————————");

        //固定大小线程池，线程总数有限制
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            executorService1.submit(myRunable);
        }
        Thread.sleep(1000);
        executorService1.shutdown();
        System.out.println("——————————————————————");

        //只创建一个线程
        //固定大小线程池，线程总数有限制
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            executorService2.submit(myRunable);
        }
        Thread.sleep(1000);
        executorService2.shutdown();
        System.out.println("——————————————————————");


    }
}
