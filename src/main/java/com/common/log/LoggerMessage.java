package com.common.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/9/27 3:14 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggerMessage {
    private String body;
    private String timestamp;
    private String threadName;
    private String className;
    private String level;
    private String exception;
    private String cause;
}
