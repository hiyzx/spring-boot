package org.zero.entertainment.util;

import cn.hutool.http.HttpUtil;

/**
 * @author yezhaoxing
 * @date 2019/7/18
 */
public class HttpTest {

    public static void main(String[] args){
        String s = HttpUtil.get("http://tms.tepin.hk/Api/Supplier/Orders/findExpress?tokenid=black_horse&sign=4ae1a11a03b2c18a4c7589bd5b1ca5e0&time=20190718093322");
        System.out.println(123);
    }
}
