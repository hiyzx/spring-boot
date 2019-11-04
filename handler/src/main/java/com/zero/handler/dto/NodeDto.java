package com.zero.handler.dto;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2019/8/26
 */
@Data
public class NodeDto {

    public static final String START_NODE = "START_END";

    public static final String END_NODE = "END";

    private String preNode;

    private String currentNode;

    private String nextNode;

    public NodeDto toEnd(String currentNode){
        NodeDto rtn = new NodeDto();
        rtn.setCurrentNode(currentNode);
        rtn.setNextNode(END_NODE);
        return rtn;
    }
}
