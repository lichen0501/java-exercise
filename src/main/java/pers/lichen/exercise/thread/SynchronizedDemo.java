package pers.lichen.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步操作Demo，JVM实现，是关键字不是类
 *
 * @author : lichen
 * @since : 2019-05-13 11:01
 */
public class SynchronizedDemo {

    public static void main(String[] args){
        //demo1：synchronized代码块，只对同一个对象不同线程进行访问才会有同步操作
        //当不同线程进入同一个对象，进入同步代码块，后面线程要等待前面线程同步操作完成才执行同步代码
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        SynchronizedTask synchronizedTask = new SynchronizedTask();
//        executorService.execute(synchronizedTask);
//        executorService.execute(synchronizedTask);
//        executorService.shutdown();

        //demo2：synchronized代码块，只对同一个对象不同线程进行访问才会有同步操作
        //当不同线程进入同一个对象，进入同步代码块，后面线程要等待前面线程同步操作完成才执行同步代码
//        ExecutorService executorService1 = Executors.newCachedThreadPool();
//        SynchronizedTask synchronizedTask = new SynchronizedTask();
//        executorService1.execute(()->synchronizedTask.syncBlock());
//        executorService1.execute(()->synchronizedTask.syncBlock());
//        executorService1.shutdown();

        //demo3：synchronized代码块，对类进行同步，不同实例的多线程访问也会进行同步操作
        //当不同线程进入类的任意实例，进入同步代码块，后面线程要等待前面线程同步操作完成才执行同步代码
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
//        SynchronizedTask synchronizedTask1 = new SynchronizedTask();
//        SynchronizedTask synchronizedTask2 = new SynchronizedTask();
//        executorService2.execute(()->synchronizedTask1.syncClass());
//        executorService2.execute(()->synchronizedTask2.syncClass());
//        executorService2.shutdown();

        //demo4：synchronized代码块，对类进行同步，不同实例的多线程访问也会进行同步操作
        //当不同线程进入类的任意实例，进入同步代码块，后面线程要等待前面线程同步操作完成才执行同步代码
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        executorService3.execute(()->SynchronizedTask.sycnStaticFun());
        executorService3.execute(()->SynchronizedTask.sycnStaticFun());
        executorService3.shutdown();
    }

    /** 
     * 同步任务
     * 
     * @author : lichen
     * @since : 2019-05-13 11:16
     */
    public static class SynchronizedTask{

        /**
         * 同步代码块
         */
        public void syncBlock() {
            //同步操作,对当前实例同步
            synchronized (this){
                for (int i = 0; i < 3; i++) {
                    System.out.println("当前线程："+Thread.currentThread().getName()+" i值:"+i);
                }
            }
        }

        /**
         * 同步类
         */
        public void syncClass() {
            //同步操作,对当前实例同步
            synchronized (SynchronizedTask.class){
                for (int i = 0; i < 3; i++) {
                    System.out.println("当前线程："+Thread.currentThread().getName()+" i值:"+i);
                }
            }
        }

        /**
         * 同步静态方法
         */
        public static synchronized void sycnStaticFun(){
            for (int i = 0; i < 3; i++) {
                System.out.println("当前线程："+Thread.currentThread().getName()+" i值:"+i);
            }
        }
    }
}
