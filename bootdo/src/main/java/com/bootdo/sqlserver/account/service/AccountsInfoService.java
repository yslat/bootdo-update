package com.bootdo.sqlserver.account.service;

import com.bootdo.common.domain.PageDO;
import com.bootdo.sqlserver.account.domain.AccountsInfoDO;

import java.util.List;
import java.util.Map;


public interface AccountsInfoService {
    PageDO<AccountsInfoDO> list(Map<String, Object> map);
    int count(Map<String, Object> map);
}
