package Java8.Function;

public class best {

    public static void main(String[] args) {
        retryFunction(best::caseValue, "daq", 20);
    }

    public static void caseValue (String s) {
        System.out.println("模拟更新");
        Integer i = Integer.valueOf(s);
    }

    public static <T, R> R retryFunction(ThrowExceptionFunction<T, R> function, T t, int time) {
        while (true) {
            try {
                return function.apply(t);
            } catch (Exception e) {
                time --;
                if (time <= 0) throw new RuntimeException(e);
            }
        }
    }
    public static <T, U, R> R retryFunction(ThrowExceptionBiFunction<T, U, R> function, T t, U u, int time) {
        while (true) {
            try {
                return function.apply(t, u);
            } catch (Exception e) {
                time --;
                if (time <= 0) throw new RuntimeException(e);
            }
        }
    }

    public static <T> void retryFunction(ThrowExceptionConsumer<T> function, T t, int time) {
        while (true) {
            try {
                function.accept(t);
            } catch (Exception e) {
                time --;
                if (time <= 0) throw new RuntimeException(e);
            }
        }
    }

    @FunctionalInterface
    public interface ThrowExceptionFunction<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    public interface ThrowExceptionBiFunction<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    @FunctionalInterface
    public interface ThrowExceptionConsumer<T> {
        void accept(T t) throws Exception;
    }

}
