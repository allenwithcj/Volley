package com.wy.lwl.volley;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lwl on 2017/8/3.
 */

public class ThreadPoolManager {
    //请求队列 无限阻塞 阻塞式队列
    private LinkedBlockingQueue<Runnable> qunune = new LinkedBlockingQueue<>();
    //线程池
    private ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolManager instance;

    public static ThreadPoolManager getInstance(){
        if (instance == null){
            synchronized (ThreadPoolManager.class){
                if(instance == null){
                    instance = new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    private ThreadPoolManager(){
        threadPoolExecutor = new ThreadPoolExecutor(4,20,20, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(4),rejectedExecutionHandler);
        //开启传送带
        threadPoolExecutor.execute(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                Runnable runnable = null;
                try {
                    //当队列中没有请求会一直阻塞
                    runnable = qunune.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(runnable != null){
                    //执行请求
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };

    /**
     * 添加请求
     * @param runnable
     */
    public void execute(Runnable runnable){
        if(runnable != null){
            qunune.add(runnable);
        }
    }

    public RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                qunune.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };



}
