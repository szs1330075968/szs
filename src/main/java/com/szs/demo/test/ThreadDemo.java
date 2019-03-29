package com.szs.demo.test;

/**
 * @author sunzs
 * created on 2018/8/31
 */
public class ThreadDemo implements Runnable{

    private  Thread t;
    private  String threadName;

    ThreadDemo( String name){
        this.threadName = name;
        System.out.println("Creating"+threadName);
    }

    @Override
    public void run() {
        System.out.println("Running"+threadName);
        try {
            for (int i=4;i>0;i--){
                System.out.println("Tread:"+threadName+"  i:"+i);
                Thread.sleep(50);
            }
        }catch (Exception e){
            System.out.println("interrup:"+threadName);
        }

        System.out.println("Thread"+threadName+"exiting");
    }

    public void start(){
        System.out.println("starting:"+threadName);
        if (t==null){
            t=new Thread("Thread-1");
            t.run();
        }
  }

}
