package com.ryan.akka.test;

import akka.http.javadsl.model.*;
import akka.http.javadsl.model.headers.Authorization;
import akka.http.scaladsl.model.IllegalUriException;
import akka.util.ByteString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rayn
 * @Vendor liuwei412552703@163.com
 * Created by Rayn on 2017/1/13 16:46.
 */
public class AkkaHttpRequest {
    private static final Logger LOG = LoggerFactory.getLogger(AkkaHttpRequest.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Uri
     *
     * @throws Exception
     */
    @Test(expected = IllegalUriException.class)
    public void testUri() throws Exception {
        Uri uri = Uri.create("http://user:ö@host");

    }

    /**
     * @throws Exception
     */
    @Test
    public void testHttpRequest() throws Exception {
        // construct a simple GET request to `homeUri`
        Uri homeUri = Uri.create("/home");
        HttpRequest request1 = HttpRequest.create().withUri(homeUri);

        // construct simple GET request to "/index" using helper methods
        HttpRequest request2 = HttpRequest.GET("/index");

        // construct simple POST request containing entity
        ByteString data = ByteString.fromString("abc");
        HttpRequest postRequest1 = HttpRequest.POST("/receive").withEntity(data);

        // customize every detail of HTTP request
        Authorization authorization = Authorization.basic("user", "pass");
        HttpRequest complexRequest = HttpRequest.PUT("/user")
                .withEntity(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8, "abc"))
                .addHeader(authorization)
                .withProtocol(HttpProtocols.HTTP_1_0);


    }
}
