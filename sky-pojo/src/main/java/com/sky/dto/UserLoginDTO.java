package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
public class UserLoginDTO implements Serializable {
// code 在微信小程序中，用户登录时需要获取一个临时登录凭证code，然后将该code发送到后台服务器,
// 后台服务器使用该code向微信服务器发起请求，获取用户的openid和session_key等信息，从而完成用户登录。
//因此，这个code属性表示用户登录时获取的临时登录凭证code，用于向微信服务器发起请求获取用户信息。
// 在UserLoginDTO类中，code属性作为数据传输对象，用于将用户登录时传递的code值传递给后台服务器。
    private String code;

}
