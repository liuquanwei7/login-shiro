package com.lqw.loginshiro.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @AUTHOR: liuquanwei
 * @DATE: 2019/5/25
 * @TIME: 21:49
 */


@Controller
public class UserController {
    @RequestMapping("/index")
    public String hello(Model model) {
        model.addAttribute("name","liu");
        return "test";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    //登录逻辑请求
    @RequestMapping("/Login")
    public String login(String name,String password,Model model) {
        //使用Shiro编写认证操作

        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            subject.login(token);
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误不存在");
            return "login";
        }
    }
}
