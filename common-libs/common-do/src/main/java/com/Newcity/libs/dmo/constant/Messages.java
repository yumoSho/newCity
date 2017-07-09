package com.Newcity.libs.dmo.constant;

import java.text.MessageFormat;

/**
 * 消息常量
 */
public class Messages {
    public static final MessageFormat LOGIN_MESSAGE = new MessageFormat("您的登录验证码是:{0}，有效期{1}分钟，请在页面中提交验证码完成验证；若非本人操作，请忽略！");
    public static final MessageFormat MODIFY_PASSWORD_MESSAGE = new MessageFormat("修改密码的短信验证码为:{0}，若非本人发送，请尽快修改密码以确保您账户安全！");
    public static final MessageFormat REPLACE_BINDING_VALIDATE_MESSAGE = new MessageFormat("您的短信验证码为:{0}，有效期{1}分钟，请在页面中提交验证码完成验证；若非本人操作，请忽略！");
    public static final MessageFormat REPLACE_BINDING_CONFIRM_MEWSSAGE = new MessageFormat("您的短信验证码为:{0}，有效期{1}分钟，请在页面中提交验证码完成绑定；若非本人操作，请忽略！");
    public static final MessageFormat EMAIL_MESSAGE_OPINION = new MessageFormat("尊敬的用户{0}:\n{1}，谢谢您的反馈与支持");

    public static String buildMessageByBusiness(Integer businessType, String... params) {
        String message = null;
        switch (businessType.shortValue()) {
            case Constant.VC_STEP_LOGIN:
                message = Messages.LOGIN_MESSAGE.format(new Object[]{params[0], params[1]});
                break;
            case Constant.VC_STEP_MODIFY_PASSWORD:
                message = Messages.MODIFY_PASSWORD_MESSAGE.format(new Object[]{params[0]});
                break;
            case Constant.VC_REPLACE_BINDING_VALIDATE:
                message = Messages.REPLACE_BINDING_VALIDATE_MESSAGE.format(new Object[]{params[0], params[1]});
                break;
            case Constant.VC_REPLACE_BINDING_CONFIRM:
                message = Messages.REPLACE_BINDING_CONFIRM_MEWSSAGE.format(new Object[]{params[0], params[1]});
                break;
        }
        return message;
    }
}
