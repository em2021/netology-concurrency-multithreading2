import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private int counter = 0;

    @Override
    public String call() {
        try {
            int limit = new Random().nextInt(15);
            for (int i = 0; i < limit; i++) {
                System.out.printf("%s %s. %s%n", "Я Callable задача из потока", Thread.currentThread().getName(), "Всем привет!");
                counter++;
                Thread.sleep(2000);
            }
        } catch (Exception e) {

        }
        return String.format("%s %s: %d", "Кол-во выведенных сообщений от задачи из потока",
                Thread.currentThread().getName(),
                counter);
    }
}