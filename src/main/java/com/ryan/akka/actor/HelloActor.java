package com.ryan.akka.actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rayn on 2016/11/18.
 * @email liuwei412552703@163.com.
 */
public class HelloActor extends UntypedActor {
    private static final Logger LOG = LoggerFactory.getLogger(HelloActor.class);


    public HelloActor(String name, int age) {
        LOG.info("测试构造方法初始化：{} - {}", name, age);
    }

    @Override
    public void preStart() throws Exception {
        LOG.info("初始化时调用 preStart() 方法。");
    }

    /**
     * 接收 Akka 消息进行处理
     *
     * @param message
     * @throws Throwable
     */
    @Override
    public void onReceive(Object message) throws Throwable {
        if (null != message) {

        } else {
            unhandled(message);
            getContext().stop(getSelf());
        }

        LOG.info("接收到消息 : {}", message);
        sender().tell("我接收到了消息，thx.", self());
    }

    @Override
    public void postStop() throws Exception {

        LOG.info("调用到 postStop() 方法。");

    }
}
