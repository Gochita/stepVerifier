package app.stepverifier.servicestests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import services.Service;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
public class ServicioTest {


     public Service service = new Service();

    @Test
    void testMono(){
        Mono<String> uno = service.buscarUno();
        StepVerifier.create(uno).expectNext("Pedro").verifyComplete();
    }

    @Test
    void testVarios(){
        Flux<String> uno = service.buscarTodos();
        StepVerifier.create(uno).expectNext("Pedro")
                .expectNext("María")
                .expectNext("Jesús")
                .expectNext("Carmen")
                .verifyComplete();
    }

    @Test
    void testVariosLento(){
        Flux<String> uno = service.buscarTodosLento();
        StepVerifier.create(uno)
                .expectNext("Pedro")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("María")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Jesús")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Carmen")
                .thenAwait(Duration.ofSeconds(1)).verifyComplete();
    }

    @Test
    void testTodosFiltro() {
        Flux<String> source = service.buscarTodosFiltro();
        StepVerifier
                .create(source)
                .expectNext("JOHN")
                .expectNextMatches(name -> name.startsWith("MA"))
                .expectNext("CLOE", "CATE")
                .expectComplete()
                .verify();
    }
}
