package org.zero.entertainment.core;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author yezhaoxing
 * @since 2018/09/04
 */
@Component
public class TextRecognitionUtil {

    @Resource
    private AipOcr aipOcr;

    public String recognitionUrl(String url){
        JSONObject res = aipOcr.basicGeneralUrl(url, new HashMap<>());
        return "";
    }
}
