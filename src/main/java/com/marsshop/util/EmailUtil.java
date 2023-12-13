package com.marsshop.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public final class EmailUtil {
    private String emailHost = "smtp.qq.com";       //发送邮件的主机
    private String transportType = "smtp";           //邮件发送的协议
    private String fromEmail = "2788265709@qq.com";  //发件人邮箱
    private String authCode = "vfncpsqzzfendfbb";    //发件人邮箱授权码
    private EmailUtil(){}
    public static EmailUtil build(String emServer, String emAddress, String emPassCode) {
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.emailHost = emServer;
        emailUtil.fromEmail = emAddress;
        emailUtil.authCode = emPassCode;
        return emailUtil;
    }

    /**
     * 发送邮件
     * @param targetEmail 目标邮件地址
     * @param title 主题
     * @param content 内容
     * @return
     */
    public boolean sendEmail(String targetEmail, String title, String content) {
        try {
            //初始化默认参数
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", transportType);
            props.setProperty("mail.host", emailHost);
            props.setProperty("mail.user", fromEmail.substring(0, fromEmail.indexOf("@")));
            props.setProperty("mail.from", fromEmail);
            //获取Session对象
            Session session = Session.getInstance(props, null);
            //开启后有调试信息
            session.setDebug(true);

            //通过MimeMessage来创建Message接口的子类
            MimeMessage message = new MimeMessage(session);
            //下面是对邮件的基本设置
            //设置发件人：
            //设置发件人第一种方式：直接显示：antladdie <antladdie@163.com>
            //InternetAddress from = new InternetAddress(sender_username);
            //设置发件人第二种方式：发件人信息拼接显示：蚂蚁小哥 <antladdie@163.com>
            String formName = MimeUtility.encodeWord("E诚尚品") + " <" + fromEmail + ">";
            InternetAddress from = new InternetAddress(formName);
            message.setFrom(from);

            //设置收件人：
            InternetAddress to = new InternetAddress(targetEmail);
            message.setRecipient(Message.RecipientType.TO, to);

//        //设置抄送人(两个)可有可无抄送人：
//        List<InternetAddress> addresses = Arrays.asList(new InternetAddress("1457034247@qq.com"), new InternetAddress("575814158@qq.com"));
//        InternetAddress[] addressesArr = (InternetAddress[]) addresses.toArray();
//        message.setRecipients(Message.RecipientType.CC, addressesArr);

            //设置密送人 可有可无密送人：
            //InternetAddress toBCC = new InternetAddress(toEmail);
            //message.setRecipient(Message.RecipientType.BCC, toBCC);

            //设置邮件主题
            message.setSubject(title);

            //设置邮件内容,这里我使用html格式，其实也可以使用纯文本；纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            //保存上面设置的邮件内容
            message.saveChanges();

            //获取Transport对象
            Transport transport = session.getTransport();
            //smtp验证，就是你用来发邮件的邮箱用户名密码（若在之前的properties中指定默认值，这里可以不用再次设置）
            transport.connect(null, null, authCode);
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients()); // 发送
        } catch (UnsupportedEncodingException | javax.mail.MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
