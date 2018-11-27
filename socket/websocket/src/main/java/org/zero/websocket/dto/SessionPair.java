package org.zero.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;


@Data
@AllArgsConstructor
public class SessionPair {

    private Session session;

    private String taskId;

    private String type;

    private String uuid;
}