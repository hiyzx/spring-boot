package org.zero.notice.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @since 2018/10/11
 */
@Data
public class CiBaResponse {

    private String sid;

    private String tts;

    private String content;

    private String note;

    private String love;

    private String translation;

    private String picture;

    private String picture2;

    private String caption;

    private String dateline;

    private String s_pv;

    private String sp_pv;

    private List<Tags> tags;

    private String fenxiang_img;
}

@Data
class Tags {
    private String id;

    private String name;
}