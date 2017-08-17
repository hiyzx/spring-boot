package com.zero.util;

/**
 * @author yezhaoxing
 * @date 2017/08/17
 */
public class NumberUtil {

    public static Long moveByte(long oldHistory, long moveAmonut) {
        long moveResult = oldHistory << moveAmonut;
        return Long.parseLong(toFullBinaryString(moveResult), 2) + 1;
    }

    /**
     * 读取
     */
    private static String toFullBinaryString(long num) {
        final int size = 42;
        char[] chs = new char[size];
        for (int i = 0; i < size; i++) {
            chs[size - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }
}
