package com.lzb.admin.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private int menuid;
    private int pid;
    private String menuname;
    private String icon;
    private String url;
    private List<Menu> children =new ArrayList<>();
}
