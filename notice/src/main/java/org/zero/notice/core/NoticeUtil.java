package org.zero.notice.core;

import org.springframework.stereotype.Component;
import org.zero.notice.response.FeiGeListResponseVo;

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

    public void batchSendNotice(String title, String content, String remark) {
        FeiGeListResponseVo list = list();
        list.getList().forEach(l -> {
            try {
                sendNotice(l.getName() + "：" + title, content, remark, String.valueOf(l.getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendNotice(String title, String content, String remark, String uid) throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        params.put("uid", uid);
        params.put("key", "notice");
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }

    private FeiGeListResponseVo list() {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        String jsonStr = feiGeHttpClient.get("/api/userlist", params);
        return JsonUtil.readValue(jsonStr, FeiGeListResponseVo.class);
    }
}
