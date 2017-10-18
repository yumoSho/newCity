package com.Newcity.libs.dmo.vo;

import com.Newcity.lib.utils.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.Newcity.lib.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by dev on 2017/4/6.
 *  排序属性放在params中属性名为order
 */
public class Page<E> {
    private final String PN_KEY = "pn";//分页简化查询方式
    private final String ORDER = "order";

    private int pageSize = 20;//每页显示的数量

    private int currentPageNum = -1;//当前页码

    private int records = -1;//总记录数

    private int total = 0;//总页数

    private int startIndex = -1;//从哪开始查

    private Map<String,Object> params = new HashMap<String,Object>();//查询条件

    private List<E> data;//返回参数

    private String order;

    public Page(){
    }

    /**
     * 简化分页查询
     * @param object
     */
    public Page(JSONObject object ){
        String _order = object.getString(ORDER);
        String _pn = object.getString(PN_KEY);
        if(!CommonUtils.isBlank( _pn )){
            if(_pn.indexOf("/") != -1){
                String[] arr = _pn.split("/");
                pageSize = StrUtil.getInt(arr[0]);
                currentPageNum = StrUtil.getInt( arr[1] );
                calculateBegin();
            }else{
                currentPageNum = StrUtil.getInt(_pn , -1  );
            }
        }
        if(StrUtil.isNull(_order)){
           order = "create_time";
        }else{
            order = _order;
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

    /**
     * jqgrid分页方式
     * @param request
     */
    public Page(HttpServletRequest request){
        currentPageNum = Integer.parseInt(request.getParameter("page")); // 取得当前页数,注意这是jqgrid自身的参数
        pageSize = Integer.parseInt(request.getParameter("rows")); // 取得每页显示行数，,注意这是jqgrid自身的参数
        calculateBegin();
    }

    /**
     * 计算从哪开始查
     */
    private void calculateBegin(){
        if (currentPageNum > 0) {
            startIndex = pageSize * (currentPageNum - 1);
        } else {
            startIndex = 0;
        }
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecords() {
        return records;
    }

    //通过总记录数获取总页数
    public void setRecords(int records) {
        this.records = records;
        this.total = records %  this.pageSize > 0 ? records/this.pageSize + 1 : records/this.pageSize;
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

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        //添加返回数据是清空查询条件
        this.params = null;

        this.data = data;
    }

    public int getTotal() {
        return total;
    }

}
