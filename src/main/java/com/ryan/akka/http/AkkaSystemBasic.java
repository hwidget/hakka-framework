package com.ryan.akka.http;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.server.AllDirectives;
import akka.stream.ActorMaterializer;
import scala.runtime.BoxedUnit;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author Rayn
 * @Vendor liuwei412552703@163.com
 * Created by Rayn on 2017/1/13 16:18.
 */
public abstract class AkkaSystemBasic extends AllDirectives {

    protected final ActorSystem system;
    protected final Http http;
    protected final ActorMaterializer materializer;

    public AkkaSystemBasic() {
        this("route");

    }

    public AkkaSystemBasic(String name) {
        system = ActorSystem.create(name);
        http = Http.get(system);
        materializer = ActorMaterializer.create(system);
    }

    /**
     * 释放
     */
    public void release(CompletionStage<ServerBinding> binding) {
        binding.thenCompose(new Function<ServerBinding, CompletionStage<BoxedUnit>>() {
            @Override
            public CompletionStage<BoxedUnit> apply(ServerBinding serverBinding) {
                // trigger unbinding from the port
                return serverBinding.unbind();
            }
        }).thenAccept(new Consumer<BoxedUnit>() {
            @Override
            public void accept(BoxedUnit u) {
                System.exit(0);
            }
        });
    }
}
