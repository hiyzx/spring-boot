package org.zero.notice.core;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.stereotype.Component;
import org.zero.notice.response.FeiGeListResponseVo;
import org.zero.notice.response.FeiGeListUserInfoVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2018/12/24
 */
@Component
public class NoticeUtil {

    @Resource
    private HttpClient feiGeHttpClient;
    private List<FeiGeListUserInfoVo> feiGeListUserInfoVs = new ArrayList<>();

    public void sendNotice(String title, String content, String remark, Integer uid) throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        params.put("uid", String.valueOf(uid));
        params.put("key", "notice");
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }

    public List<FeiGeListUserInfoVo> list() {
        if (CollectionUtil.isEmpty(feiGeListUserInfoVs)) {
            Map<String, String> params = new HashMap<>(5);
            params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
            String jsonStr = feiGeHttpClient.get("/api/userlist", params);
            FeiGeListResponseVo feiGeListResponseVo = JsonUtil.readValue(jsonStr, FeiGeListResponseVo.class);
            feiGeListUserInfoVs = feiGeListResponseVo.getList();
        }
        return feiGeListUserInfoVs;
    }
}
