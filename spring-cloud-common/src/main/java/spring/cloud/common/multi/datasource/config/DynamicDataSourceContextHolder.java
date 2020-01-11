package spring.cloud.common.multi.datasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xx
 * @Date: 2018/8/15 10:49
 * @Description: 数据源上下文
 */
public class DynamicDataSourceContextHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    public static final String DEFAULT_SOURCE = "default";

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 线程级别的私有变量  todo 用栈结构代理list
     */
    private static final ThreadLocal<List<String>> SOURCE = new ThreadLocal<>();

    public static String getDataSourceRouterKey () {
        List<String> oldSources = SOURCE.get();
        String source = CollectionUtils.isEmpty(oldSources) ? DEFAULT_SOURCE : oldSources.get(oldSources.size() - 1);
        return source;
    }

    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        logger.info("切换至{}数据源", dataSourceRouterKey);
        List<String> oldSources = SOURCE.get();
        List<String> sources = CollectionUtils.isEmpty(oldSources) ? new ArrayList<>() : oldSources;
        sources.add(dataSourceRouterKey);
        SOURCE.set(sources);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey () {
        List<String> oldSources = SOURCE.get();
        if (oldSources != null) {
            int size = oldSources.size();
            if (size > 1) {
                oldSources.remove(size - 1);
            }
        }
    }

    public static void finalRemove() {
        SOURCE.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        List<String> oldSources = SOURCE.get();
        return CollectionUtils.isEmpty(oldSources) ? false : oldSources.contains(dataSourceId);
    }

}
