package com.javarush.task.task34.task3404;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.function.DoubleBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);
        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);
        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        s = "10-2^(2-1+1)";
        System.out.print(s + " expected output 6 4 actually ");
        solution.recursion(s, 0);
        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);
        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);
        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);
        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);
        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);
        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        s = "-(-22+22*2)";
        System.out.print(s + " expected output -22 4 actually ");
        solution.recursion(s, 0);
        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);
        s = "-(-2^(-2))+2+(-(-2^(-2)))";
        System.out.print(s + " expected output 2.5 10 actually ");
        solution.recursion(s, 0);
        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);
        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);
        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);
        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);
        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);
        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);
        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recursion(s, 0);
        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);
        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);
    }

    public void recursion(final String expression, int countOperation) {


        //implement
        String temp = new String(expression);
        boolean complete = false;
// operation's patterns
        Pattern mindetect=Pattern.compile("[^\\d)?]-|^-");//патерн для определения унарных минусов, сказочная дурь
        Pattern patPar = Pattern.compile("\\(([^()]*)\\)");//внутренние скобки
        Pattern patrem = Pattern.compile("\\((-?[\\d.]+)\\)");//поиск готовых цифр в скобках для раскрытия
        Pattern pow = Pattern.compile("(-?[\\d.]+)(\\^)(-?[\\d.]+)");//степень удаленно из начала (?:-|\+)
        Pattern sin = Pattern.compile("()(sin|cos|tan)(-?[\\d.]+)");//тригонометрия
        Pattern mul = Pattern.compile("(-?[\\d.]+)([*/])(-?[\\d.]+)");//умножение деление
        Pattern additive = Pattern.compile("(-?[\\d.]+)?([+M])(-?[\\d.]+)");//сложение вычитание
        // Pattern unar=Pattern.compile("()(M)([\\d.]+)");
        Pattern plusser = Pattern.compile("()(--)([\\d.]+)");//поиск двойных плюсов после различных операций
        Pattern round = Pattern.compile("(-?[\\d.]+)");//поиск готовых цифр для округления


        if(countOperation == 0){//добавляем унарные минусы в качестве операций возможно не потреб-ся. переименовываем все - в М
            Matcher matcher = mindetect.matcher(temp);
            //  while (matcher.find())countOperation++;
            temp = temp.replaceAll("-","M");//теперь все необработанные - это M
        }
        temp = temp.replaceAll(" +", "");//выпиливаем пробелы
        // System.out.println(temp);
        String calctemp = temp;
        int start = 0;
        int end = calctemp.length();
        Matcher mathPar = patPar.matcher(temp);//поехали! скобки
        if (mathPar.find()) {

            calctemp = mathPar.group(1);
            start = mathPar.start()+1;
            end = mathPar.end()-1;

        }


        String result = calc(calctemp, sin);//тригонометрия
        if (!result.equals("")) {
            countOperation++;
            temp = temp.substring(0,start)+result+(temp.length()==end?"":temp.substring(end));
            recursion(temp, countOperation);
            return;
        }

        result = calc(calctemp, pow);//степень
        if (!result.equals("")) {
            countOperation++;
            temp = temp.substring(0,start)+result+(temp.length()==end?"":temp.substring(end));
            recursion(temp, countOperation);
            return;
        }

        result = calc(calctemp, mul);
        if (!result.equals("")) {
            countOperation++;
            temp = temp.substring(0,start)+result+(temp.length()==end?"":temp.substring(end));
            recursion(temp, countOperation);
            return;
        }
        result = calc(calctemp, plusser);
        if (!result.equals("")) {
            temp = temp.substring(0,start)+result+(temp.length()==end?"":temp.substring(end));
            recursion(temp, countOperation);
            return;
        }

        result = calc(calctemp, additive);
        if (!result.equals("")) {
            countOperation++;
            temp = temp.substring(0,start)+result+(temp.length()==end?"":temp.substring(end));
            recursion(temp, countOperation);
            return;
        }
      /*  result=calc(calctemp,unar);
        if (!result.equals("")) {
            countOperation++;
            temp = temp.replaceFirst(Pattern.quote(calctemp), result);
            recursion(temp, countOperation);
            return;
        }*/
        mathPar = patrem.matcher(temp);
        if (mathPar.find()) {
            temp = temp.substring(0,start-1)+mathPar.group(1)+temp.substring(end+1);;
            recursion(temp, countOperation);
            return;
        }
        NumberFormat nf = new DecimalFormat("#.##");
        Double d = Double.parseDouble(temp);
        System.out.println(String.format("%s %d", nf.format(d),countOperation).replace(",","."));

    }

    private String calc(String temp, Pattern pattern) {

        String result = "";
        String temporar = new String(temp);
        Matcher matcher = pattern.matcher(temporar);

        if (matcher.find()) {
            result = temporar.replaceFirst(pattern.pattern(), numerate(matcher));
        }
        return result;
    }

    private String numerate(Matcher matcher) {
        HashMap<String, DoubleBinaryOperator> hashMap = new HashMap();
        hashMap.put("*", (DoubleBinaryOperator) (double a, double b) -> a * b);
        hashMap.put("/", (DoubleBinaryOperator) (double a, double b) -> a / b);
        hashMap.put("M", (DoubleBinaryOperator) (double a, double b) -> a - b);
        hashMap.put("+", (DoubleBinaryOperator) (double a, double b) -> a + b);
        hashMap.put("++", (DoubleBinaryOperator) (double a, double b) -> b);
        hashMap.put("M-", (DoubleBinaryOperator) (double a, double b) -> b);
        hashMap.put("^", (DoubleBinaryOperator) (double a, double b) -> Math.pow(a, b));
        hashMap.put("cos", (DoubleBinaryOperator) (double a, double b) -> Math.cos(Math.toRadians(b)));
        hashMap.put("sin", (DoubleBinaryOperator) (double a, double b) -> Math.sin(Math.toRadians(b)));
        hashMap.put("tan", (DoubleBinaryOperator) (double a, double b) -> Math.tan(Math.toRadians(b)));
        String left = "0";
        String right = "0";
        String prefix = "";
        boolean unar;
        try {
            left = matcher.group(1).equals("") ? "0" : matcher.group(1);
        } catch (Exception e) {
        }
        try {
            right = matcher.group(3).equals("") ? "0" : matcher.group(3);
        } catch (Exception e) {
        }
        ;
        Double dleft = Double.parseDouble(left);
        Double dright = Double.parseDouble(right);
        Double result = hashMap.get(matcher.group(2)).applyAsDouble(dleft, dright);
        //if (dleft < 0 && result >= 0) prefix = "+";
       /* if(dleft ==0&& result<0) prefix="-";
        if(dleft ==0&& result<0) prefix="-";
*/
        NumberFormat nf = new DecimalFormat("#.##");

        return String.format("%s", nf.format(result)).replace(",",".");
    }
    public Solution() {
        //don't delete
    }
}