import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int NUMBER = 16;
        final int MAX = 1000;
        System.out.println("Задача 3. Калькулятор массива");

        Random random = new Random();
        List<Integer> integerList = new ArrayList<>(NUMBER);
        for (int i = 0; i < NUMBER; i++)
            integerList.add(random.nextInt(MAX));

        //integerList.stream().forEach(System.out::println);

        long currentTimeMillisBegin, currentTimeMillisEnd;
        int result = 0;

        System.out.println("+=");
        currentTimeMillisBegin = System.currentTimeMillis();
        result = 0;
        for (Integer value: integerList)
            result += value;
        currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(currentTimeMillisEnd - currentTimeMillisBegin);

        System.out.println("ForkJoinPool");
        currentTimeMillisBegin = System.currentTimeMillis();
        result = 0;
        ArrayCalculator arrayCalculator = new ArrayCalculator(0, integerList.size() , integerList);
        result = arrayCalculator.compute();
        currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(currentTimeMillisEnd - currentTimeMillisBegin);



    }
}
