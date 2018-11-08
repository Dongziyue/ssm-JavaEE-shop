package project.demo.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dao.GenericDao;
import project.demo.dao.UserDao;
import project.demo.dao.UserInfoDao;
import project.demo.model.User;
import project.demo.model.UserInfo;
import project.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private HttpServletRequest request;

    @Override
    @Autowired
    void setGenericDao(GenericDao<User, Integer> genericDao) {
        super.genericDao = genericDao;
        this.userDao = (UserDao) genericDao;
    }

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao){
        this.userInfoDao = userInfoDao;
    }

    @Autowired
    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public User queryUserByEmail(String email) {
        return userDao.queryOne("queryUserByEmail",email);
    }

    @Override
    public boolean signUp(User user) {
        if (queryUserByEmail(user.getEmail())!= null){
            return false;
        }
        StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
        user.setPassword(strongPasswordEncryptor.encryptPassword(user.getPassword()));
        userDao.create(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        int userId = user.getId();
        UserInfo userInfo = new UserInfo(createTime,userId);
        userInfoDao.create(userInfo);
        return true;

    }

    @Override
    public User signIn(User user) {
        String plainPassword = user.getPassword();
        user = queryUserByEmail(user.getEmail());
        if (user!= null){
            String encryptedPassword = user.getPassword();
            StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
            if (strongPasswordEncryptor.checkPassword(plainPassword,encryptedPassword)){
                UserInfo userInfo = user.getUserInfo();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String updateTime = simpleDateFormat.format(new Date());//
                String updateIp = request.getRemoteAddr();//获取IP地址
                userInfo.setUpdateTime(updateTime);//设置更新时间
                userInfo.setUpdateIp(updateIp);//设置IP地址
                userInfoDao.modify("signInUpdate",userInfo);

                user.setUserInfo(userInfo);//？？？为啥还设置
                return user;
            }
        }
        return null;
    }

}
