package com.Newcity.libs.parameter.json;


import com.Newcity.libs.dmo.constant.ResultCode;

/**
 * Created by dev on 2017/5/3.
 */
public class JsonResult {
    //返回状态
    private int result;
    //返回信息
    private String message;
    //返回数据
    private Object datas;

    public JsonResult(){
    }

    public JsonResult(int result, String message){
        this.result = result;
        this.message = message;
    }

    public JsonResult(int result, String message, Object datas){
        this.result = result;
        this.message = message;
        this.datas = datas;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public JsonResult setup(Object datas){
        this.datas = datas;
        return this;
    }

    public JsonResult setup(ResultCode resultCode){
        this.result = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public JsonResult setup(ResultCode resultCode,String message){
        this.result = resultCode.getCode();
        this.message = message;
        return this;
    }

    public JsonResult setup(ResultCode resultCode,Object datas){
        this.setup(resultCode);
        this.datas = datas;
        return this;
    }

    public JsonResult setup(ResultCode resultCode,String message,Object datas){
        this.setup(resultCode);
        this.message = message;
        this.datas = datas;
        return this;
    }
}

