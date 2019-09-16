package com.eden.orchid.impl.themes.menus

import com.eden.common.util.EdenUtils
import com.eden.orchid.api.OrchidContext
import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.options.annotations.Option
import com.eden.orchid.api.theme.menus.MenuItem
import com.eden.orchid.api.theme.menus.OrchidMenu
import com.eden.orchid.api.theme.menus.OrchidMenuFactory
import java.util.ArrayList

@Description("A generic inner menu, which holds a menu inside it.", name = "Submenu")
class SubmenuMenuItem : OrchidMenuFactory("submenu") {

    @Option
    @Description("The text of the root menu item.")
    lateinit var title: String

    @Option
    @Description("A new menu to nest under this menu item.")
    lateinit var menu: OrchidMenu

    override fun getMenuItems(context: OrchidContext): List<MenuItem> {
        val menuItems = ArrayList<MenuItem>()

        if (!EdenUtils.isEmpty(title) && !menu.isEmpty) {
            menuItems.add(
                MenuItem.Builder(context)
                    .children(menu.getMenuItems(this.page))
                    .title(title)
                    .build()
            )
        }

        return menuItems
    }
}
