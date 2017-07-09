package com.Newcity.lib.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommonUtils {

    public static final Predicate<Object> NOT_NULL_PREDICATE = null;

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\\\.][A-Za-z]{2,3}([\\\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证电话号码
     *
     * @param telPhone
     * @return
     */
    public static boolean isTelPhone(String telPhone) {
        String str = "^([0-9]{3,4}-?)[0-9]{7,8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(telPhone);
        return m.matches();
    }

    /**
     * 验证手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        String str = "^((12[0-9])|(13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 是否包含数字
     *
     * @param value
     * @return
     */
    public static boolean notHasNumber(String value) {
        String str = "^[^0-9]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * 是否是表情
     * @param ch
     * @return
     */
    public static boolean isEmoji(char ch) {
        return !((ch == 0x0) || (ch == 0x9) || (ch == 0xA) || (ch == 0xD)
                || ((ch >= 0x20) && (ch <= 0xD7FF))
                || ((ch >= 0xE000) && (ch <= 0xFFFD)) || ((ch >= 0x10000) && (ch <= 0x10FFFF)));
    }

    /**
     * 清除一个字符串中的emoji表情字符
     */
    public static String cleanEmoji(String s) {
        if (CommonUtils.isBlank(s)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < builder.length(); i++) {
            if (isEmoji(builder.charAt(i))) {
                builder.deleteCharAt(i);
                builder.insert(i, "");// 比字符串中直接替换字符要好，那样会产生很多字符串对象
            }
        }
        return builder.toString().trim();
    }

    public static String filterEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }

    public static boolean containsEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                //source = emojiMatcher.replaceAll("*");
                return true ;
            }
            return false;
        }
        return false;
    }

    /**
     * 是否是中文
     *
     * @param value
     * @return
     */
    public static boolean isChn(String value) {
        String str = "^[\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * 是否是URL
     *
     * @param value
     * @return
     */
    public static boolean isUrl(String value) {
        String str = "^(http|https)://[\\w\\.\\-]+(?:/|(?:/[\\w\\.\\-]+)*)?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean nullEquals(Object o1, Object o2) {
        if (o1 instanceof String && o2 instanceof String) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            if (isBlank(s1) && !isBlank(s2)) {
                return false;
            } else if (isBlank(s1) && isBlank(s2)) {
                return true;
            }
        } else {
            if (o1 == null && o2 != null) {
                return false;
            } else if (o1 == null) {
                return true;
            }
        }
        return o1.equals(o2);
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * 判断值是否为空，长度是否在min和max之间
     *
     * @param value
     * @param min
     * @param max
     * @return -1 : 为空 0 长度不否 1 合格
     */
    public static int isBlank(String value, int min, int max) {
        if (value == null || value.trim().length() == 0) {
            return -1;
        } else if (value.trim().length() >= min && value.trim().length() <= max) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean isBlank(Collection<?> value) {
        return value == null || value.size() == 0;
    }

    public static String getString(String value) {
        if (isBlank(value) || "null".equalsIgnoreCase(value)) {
            return null;
        }
        return value;
    }

    public static String getString(String value, String defaultValue) {
        if(isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    public static Double getDouble(String value) {
        if (value != null && value.trim().length() > 0) {
            return Double.parseDouble(value);
        } else {
            return null;
        }
    }

    public static Double getDouble(String value, Double defaultValue) {
        try {
            Double d = getDouble(value);
            if (d != null) {
                return d;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static Integer getInteger(Object value) {
        if (value instanceof BigDecimal) {
            return getInteger((BigDecimal) value);
        } else if (value instanceof String) {
            return getInteger((String) value);
        } else {
            return null;
        }
    }

    public static Integer getInteger(BigDecimal value) {
        if (value != null) {
            return value.intValue();
        }
        return null;
    }

    public static Integer getInteger(Double value) {
        if (value != null) {
            return value.intValue();
        }
        return null;
    }

    public static Integer getInteger(String value) {
        if (isBlank(value)) {
            return null;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static int getInteger(String value, int defaultValue) {
        try {
            Integer i = getInteger(value);
            if (i != null) {
                return i;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static Long getLong(String value) {
        if (isBlank(value)) {
            return null;
        } else {
            return Long.parseLong(value);
        }
    }

    public static boolean getBoolean(String value, boolean defaultValue) {
        if (value != null) {
            return "1".equals(value) || "true".equalsIgnoreCase(value);
        } else {
            return defaultValue;
        }
    }

    public static Boolean getBoolean(String value) {
        if (value != null) {
            return "1".equals(value) || "true".equalsIgnoreCase(value);
        } else {
            return null;
        }
    }

    public static void removeIfNull(Collection<?> list) {
        if (!isBlank(list)) {
            list.removeIf(NOT_NULL_PREDICATE);
        }
    }

    public static <T> boolean contains(T[] array, T e) {
        for (T t : array) {
            if (t.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public static Object setOtherNull(Object d, Set<String> includes) {
        Method[] m = d.getClass().getDeclaredMethods();
        for (int x = 0; x < m.length; x++) {
            if (m[x].getName().contains("set") && !includes.contains(m[x].getName())) {
                try {
                    String[] param=new String[]{null};
                    m[x].invoke(d, param);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(CommonUtils.isUrl("http://www.ddd.com"));
    }

}
