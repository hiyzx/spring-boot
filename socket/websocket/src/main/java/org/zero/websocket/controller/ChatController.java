package org.zero.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yezhaoxing
 * @since 2018/11/27
 */
@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}
