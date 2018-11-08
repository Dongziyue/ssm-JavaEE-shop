package project.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.demo.model.User;
import project.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController  extends BaseController{
    private UserService userService; //Service 层的属性

    @Autowired//自动装配，实例化
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("checkEmail")//通过Map映射传出是否邮箱存在
    @ResponseBody
    public Map<String , Boolean> checkEmail(String email){
        Map<String ,Boolean> data = new HashMap<>();
        data.put("isEmailExisted",false);
        if (userService.queryUserByEmail(email) != null){
            data.put("isEmailExisted",true);
        }
        return data;
    }

    @RequestMapping("signUp")
    public String signUp(User user){//UserServiceImpl 的signUp方法是boolean类型
        if (userService.signUp(user)){
            return "redirect:/index.jsp";
        }
        request.setAttribute("message","邮箱已存在");
        return "/sign-up.jsp";
    }

    @RequestMapping("signIn")//登陆
    public String signIn(User user){
        user = userService.signIn(user);//Service层的登陆方法，通过上面登录层的属性
        if (user != null){
            session.setAttribute("user",user);
            return "redirect:/portal/home.jsp";
        }
        request.setAttribute("message","邮箱或密码错误.");
        return "/sign-in.jsp";
    }

    @RequestMapping("signOut")
    public String signOut(){
        session.invalidate();//清空 session层
        return "redirect:index.jsp";
    }
}
