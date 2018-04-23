package br.com.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class URL {

    private URL() {}

    public static List<Integer> decodeIntList(String s) {
/*
        List<Integer> list = new ArrayList<>();
        for (String str : s.split(",")) {
            list.add(Integer.parseInt(str));
        }
        return Arrays.asList();
*/
        return Arrays.asList(s.split(","))
                .stream()
                .map(x -> Integer.parseInt(x))
                .collect(Collectors.toList());
    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
