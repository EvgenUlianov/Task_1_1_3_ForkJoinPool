import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ArrayCalculator  extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private final List<Integer> integerList;

    public ArrayCalculator(int start, int end, List<Integer> integerList) {
        this.start = start;
        this.end = end;
        this.integerList = integerList;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(start + " - " + end);
        return switch (diff) {
            case 0 -> 0;
            case 1 -> integerList.get(start);
            case 2 -> integerList.get(start) + integerList.get(start + 1);
            default -> forkTasksAndGetResult();
        };
    }
    private int forkTasksAndGetResult(){
        final int middle = (end - start) / 2 + start;
        // Создаем задачу для левой части диапазона
        ArrayCalculator task1 = new ArrayCalculator(start, middle, integerList);

        // Создаем задачу для правой части диапазона
        ArrayCalculator task2 = new ArrayCalculator(middle, end, integerList);



        // Запускаем обе задачи в пуле
        invokeAll(task1, task2);
        // Суммируем результаты выполнения обоих задач
        return task1.join()+ task2.join();
    }
}

