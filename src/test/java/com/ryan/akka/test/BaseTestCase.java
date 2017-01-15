package com.ryan.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.ryan.akka.actor.HelloActor;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 如果要异步输出日志，需要配置如下：
 * --------->   -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
 * <p/>
 * <p/>
 * -Xms1G -Xmx1G (prevent heap resizing during the test)
 * -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -DAsyncLogger.WaitStrategy=busyspin (to use Async Loggers. The BusySpin wait strategy
 * reduces some jitter.)
 * classic mode: -Dlog4j2.enable.threadlocals=false -Dlog4j2.enable.direct.encoders=false
 * garbage-free mode: -Dlog4j2.enable.threadlocals=true -Dlog4j2.enable.direct.encoders=true
 * -XX:CompileCommand=dontinline,org.apache.logging.log4j.core.async.perftest.NoOpIdleStrategy::idle
 * -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime (to eyeball GC and
 * safepoint pauses)
 *
 * @author Rayn on 2016/11/18.
 * @email liuwei412552703@163.com.
 */
public class BaseTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTestCase.class);

    protected ActorSystem actorSystem = null;

    @Before
    public void setUp() throws Exception {
        actorSystem = ActorSystem.create("MyActorSystem1");


    }

    @Test
    public void testHelloActor() throws Exception {
        ActorRef actorRef = actorSystem.actorOf(Props.create(HelloActor.class, "test", 12), "HelloActor");

        for (int i = 0; i < 5; i++) {
            actorRef.tell("发送字符串消息测试." + i, actorRef);

        }

    }
}
