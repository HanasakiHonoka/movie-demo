package com.xzx.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {

    public static List removeDuplicate(List list) {
        return (List) list.stream().distinct().collect(Collectors.toList());
    }
}
