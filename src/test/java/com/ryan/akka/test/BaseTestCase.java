package com.ryan.akka.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rayn on 2016/11/18.
 * @email liuwei412552703@163.com.
 */
public class BaseTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTestCase.class);



    @Test
    public void testLog() throws Exception {
        LOG.info("Test Out Log.");


    }
}
