package services;

import reactor.core.publisher.Flux;

public class LowerCaseConvert {
    private final Flux<String> source;

    public LowerCaseConvert(Flux<String> source) {
        this.source = source;
    }

    public Flux<String> getLowerCase() {
        return source
                .map(String::toLowerCase);
    }
}
