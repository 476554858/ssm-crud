package com.atguigu.crud.bean;

import javax.validation.constraints.Pattern;

public class User {
    private String uid;
    
    // @Pattern(regexp="((^[a-z0-9_-]{3,16}$))",message="用户名必须是3-16位中文或英文和数字的组合")
    
    @Pattern(regexp="((^[a-z0-9_-]{3,16}$))",message="用户名必须是3-16位中文或英文和数字的组合")
    private String username;
    
    @Pattern(regexp="((^[a-z0-9_-]{3,16}$))",message="密码必须是3-16位中文或英文和数字的组合")
    private String password;
    
    @Pattern(regexp="(^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$)",message="邮箱格式不正确")
    private String email;

    private String code;

    private Boolean state;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", email=" + email + ", code=" + code + ", state="
				+ state + "]";
	}
    
    
}