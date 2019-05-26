package pers.lichen.exercise.thread;

import javax.security.auth.callback.Callback;
import java.util.Currency;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 基础线程
 *
 * @author : lichen
 * @since : 2019-05-13 09:09
 */
public class BaseThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRunable myRunable = new MyRunable();
        Thread threadRunable = new Thread(myRunable);
        threadRunable.start();

        MyCallable myCallable = new MyCallable();
        //返回值通过FetureTask进行封装(未来任务)
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread threadCallable = new Thread(futureTask);
        threadCallable.start();
        System.out.println("执行结果："+futureTask.get().toString());

        MyThread myThread = new MyThread();
        myThread.start();
    }

    /** 
     * 实现Runable接口实现线程
     * 
     * @author : lichen
     * @since : 2019-05-13 09:37
     */
    public static class MyRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("Runable实现任务:"+ Thread.currentThread().getName());
        }
    }

    /** 
     * 实现Callable接口实现线程
     * 
     * @author : lichen
     * @since : 2019-05-13 09:37
     */
    public static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable实现任务"+ Thread.currentThread().getName());
            return 100;
        }
    }

    /** 
     * 集成Thread类实现线程
     * 
     * @author : lichen
     * @since : 2019-05-13 09:37
     */
    public static class MyThread extends Thread{

        @Override
        public void run(){
            System.out.println("重写Thread类run方法");
        }
    }
}
