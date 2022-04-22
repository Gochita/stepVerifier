package services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@org.springframework.stereotype.Service
public class Service {
    public Mono<String> buscarUno(){
        return Mono.just("Pedro");
    }
    public Flux<String> buscarTodos(){
        return Flux.just("Pedro" , "María", "Jesús", "Carmen");
    }
    public Flux<String> buscarTodosLento(){
        return Flux.just("Pedro" , "María", "Jesús", "Carmen").delaySequence(Duration.ofSeconds(20));
    }
    public Flux<String> buscarTodosFiltro() {
        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
        return source;
    }
}
