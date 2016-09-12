package com.airline.domain.portal;

import java.util.List;

/**
 * Created by JJ on 4/17/16.
 */
public class TreeMenuEntity {
    private String displayName;
    private String href;
    private List<TreeMenuEntity> subMenu;

    public List<TreeMenuEntity> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<TreeMenuEntity> subMenu) {
        this.subMenu = subMenu;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
