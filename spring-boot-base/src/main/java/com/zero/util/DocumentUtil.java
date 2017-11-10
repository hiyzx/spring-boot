package com.zero.util;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentResponse;

import java.io.File;

/**
 * @author yezhaoxing
 * @date 2017/11/10
 */
public class DocumentUtil {

    private static final String ACCESS_KEY_ID = "7914f998dbc54f548300d3db442a1f5a";
    private static final String SECRET_ACCESS_KEY = "68837af26d2144e38109059bbdf8006c";

    private static DocClient getDocClient() {
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        return new DocClient(config);
    }

    public static void createDocument(File file, String title, String format, String notification, String access,
                                      String targetType) {
        createNotification("abcd", "http://120.78.210.235:8080/customer/monitor/version");

        CreateDocumentResponse resp = getDocClient().createDocument(file, title, format, "abcd", access,
                targetType);
        System.out.println("documentId: " + resp.getDocumentId());
    }


    public static void createNotification(String name, String endpoint) {
        getDocClient().createNotification(name, endpoint);
    }

    public static void getDocument(String documentId) {
        GetDocumentResponse resp = getDocClient().getDocument(documentId);
        System.out.println(resp);
    }
}
