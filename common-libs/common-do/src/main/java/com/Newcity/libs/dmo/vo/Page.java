package com.Newcity.libs.dmo.vo;

import com.Newcity.lib.utils.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.Newcity.lib.utils.CommonUtils;

import java.util.*;

/**
 * Created by dev on 2017/4/6.
 */
public class Page {
    private final String PN_KEY = "pn";

    private int pageSize = 20;

    private int currentPageNum = -1;

    private int rowcount = -1;

    private int startIndex = -1;

    private Map<String,Object> params = new HashMap<String,Object>();

    public Page(){
    }

    public Page(JSONObject object ){
        String _pn = object.getString(PN_KEY);
        if(!CommonUtils.isBlank( _pn )){
            if(_pn.indexOf("/") != -1){
                String[] arr = _pn.split("/");
                pageSize = StrUtil.getInt(arr[0]);
                currentPageNum = StrUtil.getInt( arr[1] );
                if (currentPageNum > 0) {
                    startIndex = pageSize * (currentPageNum - 1);
                } else {
                    startIndex = 0;
                }
            }else{
                currentPageNum = StrUtil.getInt(_pn , -1  );
            }
        }

        int _pagesize = StrUtil.getInt( object.getString("pageSize") , -9 );
        if(_pagesize != -9 ){
            pageSize = _pagesize;
        }
        Set<Map.Entry<String, Object>> set = object.entrySet();
        for(Map.Entry<String, Object> entry : set){
            params.put(entry.getKey(), entry.getValue());
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public void put(String key , Object value){
        if(params == null){
            params = new HashMap();
        }
        this.params.put(key, value);
    }

    public void removeOtherKey(List keys){
        if(params != null){
            Iterator it=params.keySet().iterator();
            while(it.hasNext()){
                String key=(String)it.next();
                if(!keys.contains(key)){
                    it.remove();
                }
            }
        }
    }

    public Object get(String key){
        if(this.params ==null){
            return null;
        }
        return this.params.get(key);
    }

    public Object[] getArray(String key){
        if(this.params == null){
            return null;
        }
        JSONArray array = (JSONArray)this.params.get(key);
        if( array ==null){
            return null;
        }
        return array.toArray();
    }


    public String getString(String key){
        return (String)this.params.get(key);
    }

    public int getInt(String key){
        Integer value = (Integer)this.get(key);
        if(value==null){
            return -1;
        }
        return value.intValue();
    }

    public long getLong(String key){
        Long value = (Long)this.get(key);
        if(value==null){
            return -1;
        }
        return value.longValue();
    }

    public String getTimestamp(String key){
        return (String)this.params.get(key);
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
