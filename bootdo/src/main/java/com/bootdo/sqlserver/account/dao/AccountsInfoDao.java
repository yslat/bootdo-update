package com.bootdo.sqlserver.account.dao;

import com.bootdo.sqlserver.account.domain.AccountsInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
@Mapper
public interface AccountsInfoDao {
	List <AccountsInfoDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
}
