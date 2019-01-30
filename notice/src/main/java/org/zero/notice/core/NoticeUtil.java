package org.zero.notice.core;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2018/12/24
 */
@Component
public class NoticeUtil {

    @Resource
    private HttpClient feiGeHttpClient;

    public void sendNotice(String title, String content, String remark) throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        params.put("uid", "284");
        params.put("key", "notice");
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
