package com.xy.sharding.algorithm;

import java.util.Collection;  
import java.util.LinkedHashSet;  
  
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;  
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;  
import com.google.common.collect.Range;  
  
/** 
 * user���ֿ���߼����� 
 * @author liuyazhuang 
 * 
 */  
public class StudentSingleKeyDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer>{  
  
    /** 
     * sql �йؼ��� ƥ���Ϊ =��ʱ�򣬱���·�ɺ��� 
     */  
	@Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {  
        for (String each : availableTargetNames) {  
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {  
                return each;  
            }  
        }  
        throw new IllegalArgumentException();  
    }  
  
    /** 
     * sql �йؼ��� ƥ���Ϊ in ��ʱ�򣬱���·�ɺ��� 
     */
	@Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {  
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());  
        for (Integer value : shardingValue.getValues()) {  
            for (String tableName : availableTargetNames) {  
                if (tableName.endsWith(value % 2 + "")) {  
                    result.add(tableName);  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * sql �йؼ��� ƥ���Ϊ between��ʱ�򣬱���·�ɺ��� 
     */  
	@Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,  
            ShardingValue<Integer> shardingValue) {  
        Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());  
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();  
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {  
            for (String each : availableTargetNames) {  
                if (each.endsWith(i % 2 + "")) {  
                    result.add(each);  
                }  
            }  
        }  
        return result;  
    }  
  
}  