package org.zero.entertainment.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2019/7/16
 */
public class TranslateTest {

    public static void main(String[] args){
        List<String> lines = FileUtil.readLines("C:\\Users\\GVT\\Desktop\\模板-新.txt", "UTF-8");
        List<String> newLines = new ArrayList<>(lines.size());
        for (String line : lines) {
            if(line.contains("PARSE_MODEL_DETAIL_COLUMN")){
                String[] split = line.split(" ");
                String source = split[22];
                String translate = translate(source);
                System.out.println(source + "  " + translate);
                newLines.add(line.replace(source, translate));
            }else{
                newLines.add(line);
            }
        }
        FileUtil.writeLines(newLines, "C:\\Users\\GVT\\Desktop\\模板-翻译.txt", "UTF-8");
    }

    private static String translate(String source){
        Map<String, Object> params = new HashMap<>();
        params.put("q", source);
        params.put("from", "zh");
        params.put("to", "en");
        params.put("appid", "20190716000318561");
        int value = RandomUtil.randomInt();
        params.put("salt", value);
        params.put("sign", encodeByMD5("20190716000318561" + source + value + "06CB2hy9ZB39HXwOwHiS"));
        String s = HttpUtil.get("http://api.fanyi.baidu.com/api/trans/vip/translate", params);
        JSONObject jsonObject = JSONUtil.parseObj(s);
        JSONObject trans_result = (JSONObject) jsonObject.getJSONArray("trans_result").get(0);
        return trans_result.getStr("dst");
    }

    public static String encodeByMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("UTF-8"));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}
