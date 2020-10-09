package com.lzb.admin.bean;


import lombok.Data;

import javax.persistence.Id;


@Data
public class Admin {
    @Id
    private Long id;
    private String loginacct;
    private String username;
    private String password;
    private String email;
    private String createtime;
    private Role role;
}
