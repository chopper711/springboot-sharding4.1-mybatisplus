package com.example.demo.config.sharding;

import com.example.demo.module.util.DateUtil;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 按创建时间年份分库
 *
 * @author Chopper
 */
public class CreateTimeShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long create_time = preciseShardingValue.getValue();
        String value = DateUtil.toString(create_time, "yyyy");
        //data2019,data2020
        return "data" + value;
    }

    @Override
    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        Collection<String> collect = new ArrayList<>();
        Range<Integer> valueRange = rangeShardingValue.getValueRange();

        //开始年份结束年份
        String start = DateUtil.toString(valueRange.lowerEndpoint().longValue(), "yyyy");
        String end = DateUtil.toString(valueRange.upperEndpoint().longValue(), "yyyy");
        //循环增加区间的查询条件
        for (Integer i = Integer.valueOf(start); i <= Integer.valueOf(end); i++) {
            collect.add("data" + i);
        }
        return collect;
    }
}

