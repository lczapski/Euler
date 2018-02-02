package utils;

import java.util.function.Consumer;

@FunctionalInterface
public interface DoIt<T> {

    void doIt(T t);

    static <T> DoIt<Consumer<T>> forEvery(T ... vals) {
        return (Consumer<T> doIt) -> {
            doForEvery(doIt, vals);
        };
    }

    static <T> void doForEvery(Consumer<T> doIt, T ... vals) {
        for (T i : vals) {
            doIt.accept(i);
        }
    }
}
