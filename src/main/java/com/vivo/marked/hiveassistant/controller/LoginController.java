package com.vivo.marked.hiveassistant.controller;

import com.vivo.marked.hiveassistant.common.CommonVO;
import com.vivo.marked.hiveassistant.common.Contains;
import com.vivo.marked.hiveassistant.common.DateUtil;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.controller
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:02
 * @Description: 登录、注册、注销、主页
 */
@Controller
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public CommonVO index(@RequestBody UserInfo user) {
        LOGGER.info("userName:{}----passWord:{}", user.getLoginName(), user.getPassword());
        try {
            if (StringUtils.isAnyBlank(user.getLoginName(), user.getPassword())) {
                throw new IllegalArgumentException("登录名或密码为空！");
            }
            UserInfo userInfo = userService.findUserByLoginName(user.getLoginName(), user
                    .getPassword());
            if (userInfo != null) {
                request.getSession().setAttribute(Contains.CURRUSER_SESSION, userInfo);
            } else {
                throw new IllegalArgumentException("用户名或密码不正确");
            }
            return CommonVO.success();
        } catch (IllegalArgumentException e) {
            return CommonVO.badParams(e.getMessage());
        }
    }

    @PostMapping("/regist")
    @ResponseBody
    public CommonVO regist(@RequestBody UserInfo userInfo) {
        try {
            if (userInfo == null || StringUtils.isAnyBlank(userInfo.getLoginName(), userInfo.getPassword(), userInfo.getEmail())) {
                throw new IllegalArgumentException("填写的信息不完整");
            }
            LOGGER.info("userName:{}----passWord:{}---email:{}", userInfo.getLoginName()
                    , userInfo.getPassword(), userInfo.getEmail());
            userInfo.setIsAdmin("N");
            userInfo.setCreateTime(DateUtil.getStringDate());
            userService.regist(userInfo);
            return CommonVO.success();
        } catch (IllegalArgumentException e) {
            return CommonVO.badParams(e.getMessage());
        }
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index.do")
    public String index() {
        return "index";
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("/logout.do")
    public String logout() {
        request.getSession().invalidate();
        return "redirect:./login.html";
    }
}
