import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {

    public enum ExecutorServiceMethodName {
        INVOKE_ALL("invokeAll"), INVOKE_ANY("invokeAny");

        private final String name;

        ExecutorServiceMethodName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) throws Exception {

        execute(ExecutorServiceMethodName.INVOKE_ALL);
        System.out.println();
        execute(ExecutorServiceMethodName.INVOKE_ANY);
    }

    public static void execute(ExecutorServiceMethodName methodName) throws Exception {
        System.out.println("Демонстрация выполнения задач через " + methodName);
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            callables.add(new MyCallable());
        }
        System.out.println("Создаю пул потоков...");
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("Запускаю выполнение задач...");
        if (methodName.equals(ExecutorServiceMethodName.INVOKE_ALL)) {
            final List<Future<String>> tasksResults = threadPool.invokeAll(callables);
            for (Future<String> f : tasksResults) {
                System.out.println(f.get());
            }
        } else {
            final String taskResult = threadPool.invokeAny(callables);
            System.out.println(taskResult);
        }
        System.out.println("Останавливаю пул потоков...");
        threadPool.shutdown();
    }
}