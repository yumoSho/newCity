package com.Newcity.libs.dmo.constant;

/**
 * 集中设置返回状态吗信息，设置为公用的
 * 分区块设置返回值类型，化简了前后端的交流
 */
public class ResultCode {

    /**
     * code 1-500 不包含500之间为请求信息提示，若请求信息出错，则code为1-500之间
     */
    public static final ResultCode SUCCESS = new ResultCode(0, "请求成功！");
    public static final ResultCode SUCCESS_NEED_REDIRECT = new ResultCode(1, "请求成功,需要重新请求！");
    public static final ResultCode INVALID_PARAM = new ResultCode(2, "请检查请求参数！");
    public static final ResultCode INVALID_RESULT = new ResultCode(-1, "该条件没有返回结果！");
    /**
     *code 500-600 不包含100之间为服务器内部异常错误信息
     */
    public static final ResultCode SERVER_ERROR = new ResultCode(500, "网络不稳定请稍后再试！");
    /**
     * code 600- 为业务逻辑错误
     */
    public static final ResultCode FAIL_GENERATE_TOKEN = new ResultCode(600, "生成token失败！");
    public static final ResultCode TOKEN_IS_NULL = new ResultCode(601, "token为空！");
    public static final ResultCode INVALID_TOKEN = new ResultCode(602, "token已经失效！");
    public static final ResultCode NEED_LOGIN = new ResultCode(610, "请先登录！");
    public static final ResultCode LOGIN_FAILED = new ResultCode(611, "登录失败！");
    public static final ResultCode FAIL_SEND = new ResultCode(620, "发送验证码失败！");
    public static final ResultCode CODE_CHECK_FAILURE = new ResultCode(621, "校验验证码失败！");
    public static final ResultCode FAIL_MODIFY_PASSWORD = new ResultCode(630, "修改密码失败！");
    public static final ResultCode FAIL_MODIFY_PASSWORD_CODE = new ResultCode(631, "修改密码失败，验证失效！");
    public static final ResultCode FAIL_MODIFY_USER_INFO = new ResultCode(640, "修改个人信息失败！");
    public static final ResultCode FAIL_COMMON_OPERATION = new ResultCode(700, "操作失败！");
    public static final ResultCode FAIL_UPLOAD = new ResultCode(800, "上传失败！");
    public static final ResultCode FAIL_LOGOUT = new ResultCode(1000, "注销失败！");

    protected ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
