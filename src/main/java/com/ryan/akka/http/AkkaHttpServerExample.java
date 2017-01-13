package com.ryan.akka.http;


import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

/**
 * @Author Rayn
 * @Vendor liuwei412552703@163.com
 * Created by Rayn on 2017/1/13 15:50.
 */
public class AkkaHttpServerExample extends AllDirectives {

    public static void main(String[] args) throws Exception {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        //In order to access all directives we need an instance where the routes are define.
        AkkaHttpServerExample app = new AkkaHttpServerExample();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost("0.0.0.0", 8081), materializer);

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");

        // let it run until user presses return
        System.in.read();


    }

    /**
     * @return
     */
    private Route createRoute() {

        Route index = path("/", new Supplier<Route>() {
            @Override
            public Route get() {
                return complete("<h1>Index Page</h1>");
            }
        });

        Route hello = path("hello", new Supplier<Route>() {
            @Override
            public Route get() {
                return complete("<h1>Say hello to akka-http</h1>");
            }
        });

        Route hello1 = path("hello1", new Supplier<Route>() {
            @Override
            public Route get() {
                return complete("<h1>Say hello1 to akka-http</h1>");
            }
        });


        return route(index, hello, hello1);
    }
}
