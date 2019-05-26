package pers.lichen.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：JDK实现，在${@link java.util.concurrent.locks}
 * 
 * @author : lichen
 * @since : 2019-05-14 09:06
 */
public class ReentrantLockDemo {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();
        MyLock myLock = new MyLock();
        executorService.execute(()->myLock.fun1());
        executorService.execute(()->myLock.fun1());
        executorService.shutdown();
    }


    public static class MyLock{
        private ReentrantLock lock = new ReentrantLock();

        public void fun1(){
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.print(i+" ");
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
