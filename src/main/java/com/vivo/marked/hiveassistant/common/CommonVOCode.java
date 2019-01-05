package com.vivo.marked.hiveassistant.common;

/**
 * @author Tian Guangxin
 * @date 2018/03/01
 * @since 1.0
 */
public class CommonVOCode {
    public static final int SUCCESS = 0;

    /**参数错误*/
    public static final int BAD_PARAMS = 20000;

    /**服务器错误*/
    public static final int SERVER_ERROR = 10000;

    /**用户没找到*/
    public static final int USER_NOT_FOUND = 20001;

    /**用户登录态过期*/
    public static final int USER_SESSION_EXPIRED = 20002;

    /**用户提交信息包含敏感词*/
    public static final int CONTAIN_SENSITIVITY = 20003;

    /**用户操作被禁止*/
    public static final int OPERATION_FORBIDDEN = 20004;

    /**操作失败*/
    public static final int OPERATION_FAILURE = 20005;

}
