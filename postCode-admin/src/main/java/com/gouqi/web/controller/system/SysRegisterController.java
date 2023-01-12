package com.gouqi.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gouqi.common.core.controller.BaseController;
import com.gouqi.common.core.domain.AjaxResult;
import com.gouqi.common.core.domain.model.RegisterBody;
import com.gouqi.common.utils.StringUtils;
import com.gouqi.framework.web.service.SysRegisterService;
import com.gouqi.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author gouqi
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
