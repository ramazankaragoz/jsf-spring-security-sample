package com.ramazan.jsf.spring.security.controller;

import com.ramazan.jsf.spring.security.domain.MenuProperties;
import com.sun.faces.component.visit.FullVisitContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramazan Karagoz on 8.10.2017.
 */
@Component
@Scope("session")
public class MenuView implements Serializable{

    private MenuModel model;
    private String url;
    private List<MenuProperties> menuList;
    private DefaultMenuItem item;

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
            item = new DefaultMenuItem(menuProperties.getSubMenu());
            item.setCommand("#{menuView.sa}");
            url=menuProperties.getUrl();
            //item.setUrl(url);
            item.setUpdate(":content");
            firstSubmenu.addElement(item);
            model.addElement(firstSubmenu);
        }


    }

    public void sa(MenuActionEvent event) {
        for (MenuProperties menuProperties:menuList) {
            if (menuProperties.getSubMenu().equals(event.getMenuItem().getValue().toString()))
            {
                setUrl(menuProperties.getUrl());
                //UIComponent component=findComponent("centerPanel");
                //RequestContext.getCurrentInstance().update("content");
                /*try {
                    component.encodeAll(FacesContext.getCurrentInstance());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        }
    }

    public UIComponent findComponent(final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if(component.getId().equals(id)){
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });

        return found[0];

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
