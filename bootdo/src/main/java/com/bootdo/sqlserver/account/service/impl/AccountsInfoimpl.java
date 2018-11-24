package com.bootdo.sqlserver.account.service.impl;

import com.bootdo.common.domain.PageDO;
import com.bootdo.sqlserver.account.dao.AccountsInfoDao;
import com.bootdo.sqlserver.account.domain.AccountsInfoDO;
import com.bootdo.sqlserver.account.service.AccountsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AccountsInfoimpl implements AccountsInfoService{
    @Autowired
    private AccountsInfoDao accountsInfoDao;
    @Override
    public PageDO<AccountsInfoDO> list(Map<String, Object> map) {
        int total = accountsInfoDao.count(map);
        List <AccountsInfoDO> accountsInfos = accountsInfoDao.list(map);
        PageDO<AccountsInfoDO> page = new PageDO<>();
        page.setTotal(total);
        page.setRows(accountsInfos);

        return page;
    }

    @Override
    public int count(Map<String, Object> map) {
        return accountsInfoDao.count(map);
    }
}
