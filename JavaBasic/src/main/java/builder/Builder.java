package builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {

    private final Supplier<T> constructor;

    private final List<Consumer<T>> dInjects = new ArrayList<>();

    public Builder(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    public static <T> Builder<T> builder(Supplier<T> constructor) {
        return new Builder<>(constructor);
    }

    public <P1> Builder<T> with(Builder.DInjectConsumer<T, P1> consumer, P1 p1){
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        dInjects.add(c);
        return this;
    }

    public T build() {
        T instance = constructor.get();
        dInjects.forEach(dInject -> dInject.accept(instance));
        return instance;
    }

    @FunctionalInterface
    public interface DInjectConsumer<T,P1> {
        void accept (T t, P1 p1);
    }
}
