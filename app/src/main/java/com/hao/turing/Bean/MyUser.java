package com.hao.turing.Bean;

import cn.bmob.v3.BmobUser;

/**
 * @Description: 用户类
 * @Date: 12/14/2017.
 */
public class MyUser extends BmobUser{
    private Integer age;//年龄
    private String nick;//昵称
    private Boolean sex;//性别

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
