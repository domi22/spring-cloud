package spring.boot.common.function.mapper;

import org.apache.ibatis.annotations.Param;

public interface DataSourceMapper {

    String getName();

    void addShangp(@Param("shangName")String shangName);

}
