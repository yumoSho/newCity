package com.Newcity.libs.dmo.constant;

/**
 * 全局业务常量
 */
public class Constant {
    /**
     * 性别  -- 男
     */
    public static final short GENDER_MAN = 1;
    /**
     * 性别  -- 女
     */
    public static final short GENDER_WOMAN = 2;

    /**
     * 发送验证码业务类型  --  登录
     */
    public static final short VC_STEP_LOGIN = 1;
    /**
     * 发送验证码业务类型  --  修改密码
     */
    public static final short VC_STEP_MODIFY_PASSWORD = 2;
    /**
     * 发送验证码业务类型  --  换绑手机号验证
     */
    public static final short VC_REPLACE_BINDING_VALIDATE = 3;
    /**
     * 发送验证码业务类型  --  换绑手机号
     */
    public static final short VC_REPLACE_BINDING_CONFIRM = 4;

    /**
     * 需要登录的业务类型
     */
    public static final short[] NEED_LOGIN_BUSINESSTYPES = {VC_REPLACE_BINDING_VALIDATE,VC_REPLACE_BINDING_CONFIRM};
    /**
     * 所有的业务类型
     */
    public static final short[] NOT_NEED_LOGIN_BUSINESSTYPES = {VC_STEP_LOGIN,VC_STEP_MODIFY_PASSWORD};

    /**
     * 推送类型  --  邮箱
     */
    public static final String EMAIL = "EMAIL";



    public static final short DELETE = 1;

    /*****************************用户类型*****************************************/
    public static final Integer USER_FREEZE = 1;//冻结
    public static final Integer USER_DEPARTURE = 2;//离职





}
