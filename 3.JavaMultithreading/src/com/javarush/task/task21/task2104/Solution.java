package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode

сам реализовал методы equals и hashCode, но лучше делать черз автогенерацию
в idea, код более элегантный (тут закомментирован). Единственное валидатор
не принимает вот это getClass() != o.getClass() (пункт 3 условия), ему нужно
вот это !(n instanceof Solution)
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (this == n) {
            return true;
        }

        if (!(n instanceof Solution)) {
            return false;
        }

        Solution other = (Solution) n;

        boolean resultFirst = false;
        boolean resultLast = false;

        if (other.first == null && this.first == null) {
            resultFirst = true;
        } else if (other.first != null && this.first != null) {
            resultFirst = other.first.equals(this.first);
        }

        if (other.last == null && this.last == null) {
            resultLast = true;
        } else if (other.last != null && this.last != null) {
            resultLast = other.last.equals(this.last);
        }

        return resultFirst && resultLast;
    }

    public int hashCode() {
        int firstHashCode = first == null ? 0 : first.hashCode();
        int lastHashCode = last == null ? 0 : last.hashCode();
        return 31 * firstHashCode + lastHashCode;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(first, solution.first) &&
            Objects.equals(last, solution.last);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, last);
    }
*/
    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        Solution solution = new Solution("Donald", "Duck");
        Solution solution1 = solution;
        System.out.println(solution.equals(new Object()));
        s.add(solution);
        System.out.println(s.contains(new Solution("Donald", "Duck")));
        System.out.println(s.contains(solution1));
        System.out.println(s.contains(new Solution(null, "Duck")));
    }
}
