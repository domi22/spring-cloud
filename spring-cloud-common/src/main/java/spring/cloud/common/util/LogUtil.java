package spring.cloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    //定义一个枚举 info debug error warn

    public static void info(Class cz,String info){
        Logger logger = LoggerFactory.getLogger(cz);
        if (logger.isInfoEnabled()) {
            logger.info(info);
        }
    }


    /**
     * ERROR及其以上级别的log信息是一定会被输出的，所以只有logger.isDebugEnabled和logger.isInfoEnabled方法，而没有logger.isErrorEnabled方法。
     * @param cz
     * @param error
     */
    public static void error(Class cz,String error){
        Logger logger = LoggerFactory.getLogger(cz);
        if (logger.isErrorEnabled()) {
            logger.error(error);
        }
    }

    public static void debug(Class cz,String debug){
        Logger logger = LoggerFactory.getLogger(cz);
        if (logger.isDebugEnabled()) {
            logger.debug(debug);
        }
    }

    public static void debug(Class cz,String debug,Exception e){
        Logger logger = LoggerFactory.getLogger(cz);
        if (logger.isDebugEnabled()) {
            logger.debug(debug,e);
        }
    }

}
