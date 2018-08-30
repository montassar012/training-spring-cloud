package com.firststep.spring.cloud.demoreactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class OperatorsTest {

    @Test
    public  void map(){

        Flux.range(1,5)
                .map(i->i*10)
               // .log()
                .subscribe(System.out::println);
    }

    @Test
    public  void flatMap(){

        Flux.range(1,5)
                .flatMap(i-> Flux.range(i*10,2))
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public  void flatMapMany(){

        Mono.just(3).flatMapMany(i->Flux.range(1,i))

                .subscribe(System.out::println);
    }

    @Test
    public void concat() throws InterruptedException {

      Flux<Integer> oneToFive=  Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));

      Flux<Integer> sixToTen=  Flux.range(6,5)
              .delayElements(Duration.ofMillis(400));

      Flux.concat(oneToFive,sixToTen).log().subscribe(System.out::println);

     // oneToFive.concatWith(sixToTen).subscribe();

      Thread.sleep(4000);


    }

    @Test
    public void merge() throws InterruptedException {

        Flux<Integer> oneToFive=  Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));

        Flux<Integer> sixToTen=  Flux.range(6,5)
                .delayElements(Duration.ofMillis(400));

        Flux.merge(oneToFive,sixToTen).log().subscribe(System.out::println);

        // oneToFive.mergeWith(sixToTen).subscribe();

        Thread.sleep(4000);


    }

    @Test
    public void zip() throws InterruptedException {

        Flux<Integer> oneToFive=  Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));

        Flux<Integer> sixToTen=  Flux.range(6,5)
                .delayElements(Duration.ofMillis(400));

        Flux.zip(oneToFive,sixToTen).subscribe(System.out::println);

        // oneToFive.zipWith(sixToTen).subscribe();

        Thread.sleep(4000);


    }
}
