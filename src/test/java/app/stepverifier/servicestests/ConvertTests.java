package app.stepverifier.servicestests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import services.LowerCaseConvert;
import services.UppercaseConvert;

@SpringBootTest
public class ConvertTests {

    final TestPublisher<String> testPublisher = TestPublisher.create();

    @Test
    void testUpperCase(){
        UppercaseConvert uppercaseConvert = new UppercaseConvert(testPublisher.flux());
        StepVerifier.create(uppercaseConvert.getUpperCase())
                .then(() -> testPublisher.emit("cositas", "Gochi"))
                .expectNext("COSITAS", "GOCHI")
                .verifyComplete();
    }
    @Test
    void testLowerCase(){
        LowerCaseConvert lowerCaseConvert = new LowerCaseConvert(testPublisher.flux());
        StepVerifier.create(lowerCaseConvert.getLowerCase())
                .then(() -> testPublisher.emit("COSITAS", "GOchI"))
                .expectNext("cositas", "gochi")
                .verifyComplete();
    }
}
