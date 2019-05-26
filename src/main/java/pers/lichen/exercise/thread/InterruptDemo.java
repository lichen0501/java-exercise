package pers.lichen.exercise.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 打断线程
 * 只有线程在阻塞、休眠、等待的时候才会被外界打断，从而提前结束执行
 * 
 * @author : lichen
 * @since : 2019-05-13 10:21
 */
public class InterruptDemo {

    public static void main(String[] args){
        //demo1:sleep方法抛出InterruptedException异常
//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.interrupt();

        //demo2:interrupt方法判断线程的中断状态
//        MyThread myThread1 = new MyThread();
//        myThread1.start();
//        myThread1.interrupt();

        //demo3:Executors.shutdownNow()立即结束，使用shutdown()方法会等待线程都执行完毕之后再关闭
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executorService.shutdownNow();

        //demo4:通过FutureTask取消来中断线程
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        Future<?> future = executorService1.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        });
        future.cancel(true);
        executorService1.shutdown();
    }

    /**
     * 通过sleep方法抛出InterruptedException异常结束线程
     *
     * @author : lichen
     * @since : 2019-05-13 10:30
     */
    public static class MyThread extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过interrupt方法判断线程的中断状态标记结束线程
     *
     * @author : lichen
     * @since : 2019-05-13 10:29
     */
    public static class MyThread1 extends Thread{
        @Override
        public void run(){
            while (!interrupted()){
                System.out.println("没有被中断");
            }
            System.out.println("线程被终端");
        }
    }
}
