package io.github.plugindustry.industrialworld.behavior;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.industrialworld.recipe.LithicReductionRecipe;
import io.github.plugindustry.wheelcore.i18n.I18n;
import io.github.plugindustry.wheelcore.manager.RecipeRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LithicReductionBehavior extends Behavior {
    private static LithicReductionBehavior INSTANCE;

    private LithicReductionBehavior() {}

    public static Behavior getInstance() {
        if (INSTANCE == null) INSTANCE = new LithicReductionBehavior();
        return INSTANCE;
    }

    private static LithicReductionRecipe matchRecipe(InventoryGui gui) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        for (char row = 'b'; row <= 'v'; row += 5) {
            List<ItemStack> rows = new ArrayList<>();
            for (char col = row; col < row + 5; ++col) {
                GuiElement element = gui.getElement(col);
                if (element instanceof StaticGuiElement itemElement) rows.add(itemElement.getRawItem());
                else rows.add(null);
            }
            inputs.add(rows);
        }
        return RecipeRegistry.matchSpecificRecipe(LithicReductionRecipe.class, inputs);
    }

    private static void openLithicReduction(Player player) {
        InventoryGui gui = new InventoryGui(IndustrialWorld.getInstance(), I18n.getLocalePlaceholder(
                new NamespacedKey(IndustrialWorld.getInstance(), "inv/lithic_reduction/title")),
                new String[]{"abcdefaaa", "aghijkaaa", "almnopaRa", "aqrstuaaa", "avwxyzaaa", "aaaaaaaaa"});
        gui.addElement('a', new ItemStack(Material.GRAY_STAINED_GLASS_PANE), "");
        gui.addElement('R', new ItemStack(Material.AIR), click -> {
            ItemStack resultItem = ((StaticGuiElement) click.getElement()).getRawItem();
            if (resultItem.getType() != Material.AIR) {
                click.getWhoClicked().getWorld().dropItem(click.getWhoClicked().getLocation(), resultItem);
                click.getGui().close();
            }
            return true;
        });
        for (char row = 'b'; row <= 'v'; row += 5)
            for (char col = row; col < row + 5; ++col) {
                final char finalCol = col;
                gui.addElement(col, new ItemStack(Material.FLINT), click -> {
                    gui.removeElement(finalCol);
                    LithicReductionRecipe recipe = matchRecipe(gui);
                    if (recipe != null) ((StaticGuiElement) gui.getElement('R')).setItem(recipe.getResult());
                    gui.draw();
                    return true;
                });
            }
        gui.show(player);
    }

    @Override
    public void onActivate() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInteract(PlayerInteractEvent event) {
                Player player = event.getPlayer();
                if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
                RayTraceResult result = player.getWorld()
                        .rayTraceEntities(player.getEyeLocation(), player.getLocation().getDirection(), 5,
                                entity -> entity != player);
                if (result != null && result.getHitEntity() instanceof Item itemEntity) {
                    ItemStack item = itemEntity.getItemStack();
                    if (event.hasItem() && Objects.requireNonNull(event.getItem()).getType() == Material.FLINT &&
                        item.getType() == Material.FLINT && item.getAmount() == 1 &&
                        itemEntity.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.STONE) {
                        event.setCancelled(true);
                        itemEntity.remove();
                        openLithicReduction(player);
                    }
                }
            }
        }, IndustrialWorld.getInstance());
    }
}