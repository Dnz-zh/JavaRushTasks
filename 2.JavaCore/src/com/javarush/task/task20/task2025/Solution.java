package com.javarush.task.task20.task2025;

/*
1) Для выполнены данной задачи обязательно к прочтению https://acmp.ru/article.asp?id_text=198
и в wiki https://ru.wikipedia.org/wiki/%D0%A7%D0%B8%D1%81%D0%BB%D0%BE_%D0%90%D1%80%D0%BC%D1%81%D1%82%D1%80%D0%BE%D0%BD%D0%B3%D0%B0
2) Закоменнтированный код можно попробовать оптимизировать (его не удаляю).
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
*/
/*
Алгоритмы-числа
*/
public class Solution {

    public static long[] getNumbers(long N) {
/*
        List<Long> result = new LinkedList<>();
        System.out.println(String.format(
            "Value N = %d, length = %d",
            N,
            String.valueOf(N).length()
            )
        );
        if (N < 100) {
            if (N < 10) {
                for (long i = 0; i < N - 1; i++) {
                    result.add(i + 1);
                }
            } else {
                for (long i = 0; i < 9; i++) {
                    result.add(i + 1);
                }
            }
            long[] res = new long[result.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = result.get(i);
            }
            return res;
        } else {
            for (long i = 0; i < 9; i++) {
                result.add(i + 1);
            }
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Set<Long>>> futures = new ArrayList<>();
        for (int i = 3; i <= String.valueOf(N).length(); i++) {
            long start = Double.valueOf(Math.pow(10, i - 1)).longValue();
            long tmp = Double.valueOf(Math.pow(10, i)).longValue();
            long st = start;
            long ed = st + tmp / (i - 1);
            for (int j = 1; j < i; j++) {
                if (N < ed) {
                    futures.add(exec.submit(new FindArmstrongNums(st, N)));
                } else {
                    if (ed < tmp) {
                        futures.add(exec.submit(new FindArmstrongNums(st, ed - 1)));
                    } else {
                        futures.add(exec.submit(new FindArmstrongNums(st, tmp - 1)));
                        break;
                    }
                    st = ed - 1;
                    ed += tmp / 4;
                }
            }
        }

        for (Future<Set<Long>> fs : futures) {
            try {
                result.addAll(fs.get());
            } catch(InterruptedException e) {
                return null;
            } catch(ExecutionException e) {
            } finally {
                exec.shutdown();
            }
        }
*/

        long[] gold = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371
            , 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725
            , 4210818, 9800817, 9926315, 24678050, 24678051, 88593477
            , 146511208, 472335975, 534494836, 912985153};
        List<Long> result = new LinkedList<>();
        for (long aGold : gold) {
            if (aGold >= N) {
                break;
            }
            result.add(aGold);
        }

        long[] res = new long[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }
/*
    private static class FindArmstrongNums implements Callable<Set<Long>> {

        private Map<String, Long> data = new HashMap<>();
        private Set<Long> results = new TreeSet<>();
        private long start;
        private long end;

        public FindArmstrongNums(long start, long end) {
            this.start = start;
            this.end = end;

        }

        @Override
        public Set<Long> call() {
            for (long i = start; i < end; i++) {
                char[] numbers = String.valueOf(i).toCharArray();
                Arrays.sort(numbers);
                if (data.containsKey(String.valueOf(numbers))) {
                    if (i == data.get(String.valueOf(numbers))) {
                        results.add(i);
                    }
                    continue;
                }
                long sum = 0;
                for (char number : numbers) {
                    sum += Math.pow(Character.getNumericValue(number), numbers.length);
                }
                if (sum == i) {
                    results.add(i);
                }
                data.put(String.valueOf(numbers), sum);
            }
            return results;
        }
    }
*/
    public static void main(String[] args) {
        long timeStart = new Date().getTime();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        long timeEnd = new Date().getTime();
        System.out.println("Estimate time (sec): " + (timeEnd - timeStart)/( 1000 * 1d));
    }
}
