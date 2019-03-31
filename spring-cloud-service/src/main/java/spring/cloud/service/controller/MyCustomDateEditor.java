package spring.cloud.service.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class MyCustomDateEditor extends PropertyEditorSupport {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
// Treat empty String as null value.
            setValue(null);
        } else {
            try {
                setValue(this.dateAdapter(text));
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("出错日志：" + ex.getMessage());
            }
        }
    }


    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (value != null ? dateFormat.format(value) : "");
    }

    /**
     * 字符串转日期适配方法
     *
     * @param dateStr 日期字符串
     * @throws Exception
     */
    public static Date dateAdapter(String dateStr) throws Exception {
        Date date = null;
        String temp = dateStr;//缓存原始数据
        if (!(null == dateStr || "".equals(dateStr))) {
            //判断是不是日期字符串，如Wed May 28 08:00:00 CST 2014
            if (dateStr.contains("CST")) {
                date = new Date(dateStr);
            } else {
                dateStr = dateStr.replace("年", "-").replace("月", "-").replace("日", "").replaceAll("/", "-").replaceAll("\\.", "-").trim();
                String fm = "";
                //确定日期格式
                if (Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}.*").matcher(dateStr).matches()) {
                    fm = "yyyy-MM-dd";
                } else if (Pattern.compile("^[0-9]{4}-[0-9]{1}-[0-9]+.*||^[0-9]{4}-[0-9]+-[0-9]{1}.*").matcher(dateStr).matches()) {
                    fm = "yyyy-M-d";
                } else if (Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{2}.*").matcher(dateStr).matches()) {
                    fm = "yy-MM-dd";
                } else if (Pattern.compile("^[0-9]{2}-[0-9]{1}-[0-9]+.*||^[0-9]{2}-[0-9]+-[0-9]{1}.*").matcher(dateStr).matches()) {
                    fm = "yy-M-d";
                }
                //确定时间格式
                if (Pattern.compile(".*[ ][0-9]{2}").matcher(dateStr).matches()) {
                    fm += " HH";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}").matcher(dateStr).matches()) {
                    fm += " HH:mm";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(dateStr).matches()) {
                    fm += " HH:mm:ss";
                } else if (Pattern.compile(".*[ ][0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{0,3}").matcher(dateStr).matches()) {
                    fm += " HH:mm:ss:sss";
                }
                if (!"".equals(fm)) {
                    try {
                        date = new SimpleDateFormat(fm).parse(dateStr);
                    } catch (ParseException e) {
                        throw new Exception("参数字符串" + dateStr + "无法被转换为日期格式！");
                    }
                }
            }
        }
        return date;
    }

}
