package pers.lichen.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程协作
 *
 * @author : lichen
 * @since : 2019-05-14 09:24
 */
public class ThreadCollaborationDemo {

    public static String a;
    public static void main(String[] args){
        //Demo1:join()示例
//        JoinDemo joinDemo = new JoinDemo();
//        joinDemo.test();

        //Demo2:Wait()和Notify()示例
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        waitNotifyDemo.test();
    }

    /**
     * Join示例
     * 一个线程中调用另外一个线程的join方法，会挂起当前线程，直到被join的线程执行完成
     *
     * @author : lichen
     * @since : 2019-05-14 09:35
     */
    static class JoinDemo{
        void test(){
            ThreadA a = new ThreadA();
            ThreadB b = new ThreadB(a);
            System.out.println("start B");
            b.start();
            System.out.println("start A");
            a.start();
        }

        static class ThreadA extends Thread{
            @Override
            public void run(){
                System.out.println("执行A");
            }
        }

        static class ThreadB extends Thread{
            ThreadA a;
            public ThreadB(ThreadA a){
                this.a = a;
            }
            @Override
            public void run(){
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行B");
            }
        }
    }

    /**
     * Wait()和Notify()示例
     * 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException
     * 调用wait()进行挂起，通过notify()或notifyAll()进行唤醒
     * @author : lichen
     * @since : 2019-05-14 09:40
     */
    static class WaitNotifyDemo{

        public void test(){
            ExecutorService executorService = Executors.newCachedThreadPool();
            WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
            System.out.println("调用A");
            executorService.execute(() -> waitNotifyDemo.A());
            System.out.println("调用B");
            executorService.execute(() -> waitNotifyDemo.B());
            executorService.shutdown();
        }

        synchronized void A(){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("运行A");
        }

        synchronized void B(){
            System.out.println("运行B");
            notify();
        }
    }
}
