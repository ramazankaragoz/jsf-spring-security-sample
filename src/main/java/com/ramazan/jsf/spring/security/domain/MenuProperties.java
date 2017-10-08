package com.ramazan.jsf.spring.security.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

/**
 * Created by Ramazan Karagoz on 8.10.2017.
 */
public class MenuProperties {
    private String name;
    private String url;
    private String subMenu;

    public MenuProperties()
    {}

    public MenuProperties(String name, String url, String subMenu) {
        this.name = name;
        this.url = url;
        this.subMenu = subMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }
}
