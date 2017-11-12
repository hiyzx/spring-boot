package com.zero.util;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author yezhaoxing
 * @date 2017/11/10
 */
@Component
public class DocumentUtil {

    private static final String ACCESS_KEY_ID = "7914f998dbc54f548300d3db442a1f5a";
    private static final String SECRET_ACCESS_KEY = "68837af26d2144e38109059bbdf8006c";

    @Resource
    private HttpClient localHttpClient;

    private DocClient getDocClient() {
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        return new DocClient(config);
    }

    public void createDocument(File file, String title, String format, String notification, String access,
                               String targetType) {
        createNotification("abcde", "http://120.78.210.235:8080/customer/order/list.json?sessionId=b9513bbb-9f53-41eb-9aa8-f95e68927052&page=1&pageSize=5");

        CreateDocumentResponse resp = getDocClient().createDocument(file, title, format, "abcde", access,
                targetType);
        System.out.println("documentId: " + resp.getDocumentId());
    }


    public void createNotification(String name, String endpoint) {
        getDocClient().createNotification(name, endpoint);
    }

    public void getDocument(String documentId) {
        GetDocumentResponse resp = getDocClient().getDocument(documentId);
        System.out.println(resp);
    }

    public String getDownloadUrl(String documentId) {
        String res = localHttpClient.get(String.format("/v2/document/%s?download&expireInSeconds=-1&https=false", documentId));
        return res;
    }
}
