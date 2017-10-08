package com.ramazan.jsf.spring.security.controller;

import com.ramazan.jsf.spring.security.domain.MenuProperties;
import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramazan Karagoz on 8.10.2017.
 */
@Component
@Scope("session")
public class MenuView {

    private MenuModel model;
    private String url;
    private List<MenuProperties> menuList;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //dummy menu
        MenuProperties menu = new MenuProperties("Stok", "/pages/material-list.xhtml", "Malzeme Listesi");
        MenuProperties menu2 = new MenuProperties("Hello", "/pages/hello.xhtml", "Hello");
        menuList = new ArrayList<>();
        menuList.add(menu);
        menuList.add(menu2);

        for (MenuProperties menuProperties : menuList)
        {
            DefaultSubMenu firstSubmenu = new DefaultSubMenu(menuProperties.getName());
            DefaultMenuItem item = new DefaultMenuItem(menuProperties.getSubMenu());
            item.setCommand("#{menuView.sa}");
            url=menuProperties.getUrl();
            firstSubmenu.addElement(item);
            model.addElement(firstSubmenu);
        }


    }

    public void sa(MenuActionEvent event) {
        for (MenuProperties menuProperties:menuList) {
            if (menuProperties.getSubMenu().equals(event.getMenuItem().getValue().toString()))
            {
                setUrl(menuProperties.getUrl());
            }
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
