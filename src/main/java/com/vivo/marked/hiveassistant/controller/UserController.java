package com.vivo.marked.hiveassistant.controller;

import com.vivo.marked.hiveassistant.common.CommonVO;
import com.vivo.marked.hiveassistant.common.Contains;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.UserInfoExtend;
import com.vivo.marked.hiveassistant.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.controller
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-27 15:39
 * @Description: 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/userList.do")
    @ResponseBody
    public CommonVO getUsers(@RequestParam(value = "loginName", required = false, defaultValue = "") String loginName
            , @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
            , @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        LOGGER.info("lognName:{}",loginName);
        UserInfo user = (UserInfo) request.getSession().getAttribute(Contains.CURRUSER_SESSION);
        try {
            //用户列表
            List<UserInfoExtend> userInfos = userService.findAllByloginName(loginName, page-1, limit, user);
            //用户人数
            int count = userService.countByLoginNameLike(loginName, user);
            return CommonVO.success(userInfos, count);
        } catch (IllegalArgumentException e) {
            LOGGER.info("获取用户列表失败");
            return CommonVO.badParams(e);
        }

    }
}
