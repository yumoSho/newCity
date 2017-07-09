package com.Newcity.lib.utils;

/**
 * Created by dev on 2017/4/11.
 */
public class FormatUtils {

    /**
     * 序列化Id
     *
     * @return
     */
    public static String idFormat(Long number, String parentsId) {
        String id = (number+1)+"";
        if (id == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(parentsId);
        if (id != null && id.length() != 4) {
            for (int i = 0; i < (3 - id.length()); i++) {
                sb.append("0");
            }
            sb.append(id);
        }
        return sb.toString();
    }



}
