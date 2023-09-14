package com.admin.controller;

import com.common.message.sse.MessageVo;
import com.common.message.sse.SseEmitterService;
import io.swagger.annotations.ApiOperation;
import org.basis.framework.annotation.IgnoreSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sse")
public class SseEmitterController {

    @Resource
    private SseEmitterService sseEmitterService;


    @ApiOperation(value = "创建连接")
    @CrossOrigin
    @GetMapping("/createConnect")
    @IgnoreSecurity
    public SseEmitter createConnect(String clientId) {
        return sseEmitterService.createConnect(clientId);
    }

    @ApiOperation(value = "发送消息给所有客户端")
    @CrossOrigin
    @PostMapping("/broadcast")
    @IgnoreSecurity
    public void sendMessageToAllClient(@RequestBody(required = false) String msg) {
        sseEmitterService.sendMessageToAllClient(msg);
    }

    @ApiOperation(value = "给指定客户端发送消息")
    @CrossOrigin
    @PostMapping("/sendMessage")
    @IgnoreSecurity
    public void sendMessageToOneClient(@RequestBody(required = false) MessageVo messageVo) {
        if (messageVo.getClientId().isEmpty()) {
            return;
        }
        sseEmitterService.sendMessageToOneClient(messageVo.getClientId(), messageVo.getData());
    }

    @ApiOperation(value = "关闭连接")
    @CrossOrigin
    @GetMapping("/closeConnect")
    @IgnoreSecurity
    public void closeConnect(@RequestParam(required = true) String clientId) {
        sseEmitterService.closeConnect(clientId);
    }

}

