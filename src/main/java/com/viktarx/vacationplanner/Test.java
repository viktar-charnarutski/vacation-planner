package com.viktarx.vacationplanner;

import java.util.concurrent.*;

/**
 *
 */
public class Test extends RecursiveTask<Long> {
    final int a, c;
    long total;

    public Test(int a, int c, long total) {
        this.a = a;
        this.c = c;
        this.total = total;
    }

    @Override
    protected Long compute() {
        if (a <= 3) return total;
        else {
            Test t = new Test(a - 1, c, total++);
            t.fork();
            total += t.join();
            return total;
        }
    }

    public static void main(String[] args) {
        ForkJoinTask<Long> task = new Test(200, 100, 0);
        ForkJoinPool pool = new ForkJoinPool();
        long x = pool.invoke(task);
        System.out.println(x);
    }

}
