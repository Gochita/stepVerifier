package services;

import reactor.core.publisher.Flux;

public class UppercaseConvert {
    private final Flux<String> source;

    public UppercaseConvert(Flux<String> source) {
        this.source = source;
    }

    public Flux<String> getUpperCase() {
        return source
                .map(String::toUpperCase);
    }
}
