package org.dav.learn.corejavabook;

import java.util.function.Function;

public class FunctionalInterfaces
{
    public static void main(String[] args)
    {
        Function<String, Integer> stringToInteger = Integer::valueOf;
        Function<String, String> backToString = stringToInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));
    }
}
