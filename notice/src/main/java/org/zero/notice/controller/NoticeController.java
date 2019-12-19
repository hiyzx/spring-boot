package org.zero.notice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.notice.core.NoticeUtil;
import org.zero.notice.response.FeiGeListUserInfoVo;
import org.zero.notice.task.WeatherTask;
import org.zero.notice.task.WeekReportTask;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class NoticeController {

    @Autowired
    private WeatherTask weatherTask;
    @Autowired
    private WeekReportTask weekReportTask;
    @Autowired
    private NoticeUtil noticeUtil;

    @GetMapping("/notice")
    public String notice(@RequestParam Integer type) throws IOException {
        switch (type) {
            case 1:
                weatherTask.sendNoticeWeatherMorningQZ();
                break;
            case 2:
                weatherTask.sendNoticeNight();
                break;
            case 3:
                weekReportTask.sendNoticeAfternoon();
                break;
            default:
                log.info("{} type is valid", type);
                throw new RuntimeException("非法type");
        }
        return "success";
    }

    @GetMapping("/list")
    public List<FeiGeListUserInfoVo> list() throws IOException {
        return noticeUtil.list();
    }
}
