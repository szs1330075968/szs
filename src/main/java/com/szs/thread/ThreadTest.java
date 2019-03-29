package com.szs.thread;


import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.NamedThreadFactory;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sunzs
 * created on 2018/11/8
 */
@Slf4j
public class ThreadTest {

    @Resource
    private ThreadPoolExecutor executor =new ThreadPoolExecutor(2,100,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),new NamedThreadFactory("测试线程"));
    //@Autowired
    //private ThreadPoolExecutor executor;
    public ThreadTest() {
       // executor =  new ThreadPoolExecutor(100,100,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),new NamedThreadFactory("测试线程"));
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                }catch (Exception e){
                    log.error("shibai"+e.getMessage(),e);
                }
            }
        });
    }

    public static void main(String[] args) throws Exception{
        for (int i = 1;i<=10;i++){
            Thread.sleep(1000);
            ThreadTest threadTest = new ThreadTest();
        }
    }
}
