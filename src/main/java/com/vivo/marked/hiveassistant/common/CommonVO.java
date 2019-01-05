package com.vivo.marked.hiveassistant.common;

/**
 * @author Tian Guangxin
 * @date 2018/03/01
 * @since 1.0
 */

public class CommonVO {

    private Integer code;

    private Object data;

    private String msg;

    private Integer count;

    public CommonVO() {
    }

    public CommonVO(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public CommonVO(Integer code, Object data, Integer count) {
        this.code = code;
        this.data = data;
        this.count = count;
    }

    public CommonVO(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static CommonVO success(Object data,Integer count) {
        return new CommonVO(CommonVOCode.SUCCESS, data, count);
    }

    public static CommonVO success() {
        return success(null,null);
    }

    public static CommonVO badParams(Object data) {
        return badParams(data,null);
    }

    public static CommonVO badParams(Object data,String msg) {
        return new CommonVO(CommonVOCode.BAD_PARAMS, data,msg);
    }

    public static CommonVO badParams() {
        return badParams(null);
    }

    public static CommonVO error(Object data){
        return new CommonVO(CommonVOCode.SERVER_ERROR, data);
    }

    public static CommonVO error(){
        return error(null);
    }

    public static CommonVO error(Integer code,Object data){
        return new CommonVO(code,data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
