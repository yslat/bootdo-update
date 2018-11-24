package com.bootdo.sqlserver.account.controller;

import com.bootdo.common.domain.PageDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.sqlserver.account.domain.AccountsInfoDO;
import com.bootdo.sqlserver.account.service.AccountsInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/accounts/agent")
public class AccountsInfoController {
    String prefix = "sqlserver/account";
    @Autowired
    private AccountsInfoService accountsInfoService;
    @RequiresPermissions("accounts:agent:agent")
    @GetMapping()
    String accounts() {
        return prefix + "/account";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("accounts:agent:agent")
    public PageDO<AccountsInfoDO> list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
       // params.put ( "offset",1 );
        //params.put ( "limit" ,10);
        Query query = new Query(params);
        PageDO<AccountsInfoDO> page = accountsInfoService.list(query);
        return page;
        //List<AccountsInfoDO> notifyList = accountsInfoService.list(query);
       //int total = accountsInfoService.count(query);
        // pageUtils = new PageUtils(notifyList, total);
        //return pageUtils;
    }
}
