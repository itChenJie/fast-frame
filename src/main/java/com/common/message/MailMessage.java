package com.common.message;

import org.basis.framework.message.IMessage;
import org.basis.framework.message.bean.MessageAccount;
import org.basis.framework.message.bean.MessageDetailsInfo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * 邮件发送
 * @Author ChenWenJie
 * @Data 2021/10/26 2:31 下午
 **/
@Service
public class MailMessage implements IMessage {
    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendMessage(MessageAccount messageAccount, MessageDetailsInfo detailsInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(messageAccount.getAccount());
        message.setFrom(detailsInfo.getReceiveAccount().getAccount());
        message.setSubject(detailsInfo.getSubject());
        message.setText(detailsInfo.getBody());
        message.setSentDate(detailsInfo.getTime());
        javaMailSender.send(message);
        return true;
    }

    @Override
    public boolean receiveMessage(MessageAccount messageAccount) {
        return false;
    }
}
