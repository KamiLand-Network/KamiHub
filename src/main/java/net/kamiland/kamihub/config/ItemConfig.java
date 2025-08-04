package net.kamiland.kamihub.config;

import lombok.Getter;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemConfig extends Config {

    @Getter
    private final Map<Short, ItemStack> itemMap = new HashMap<>();
    private final Map<ItemStack, String[]> itemActionMap = new HashMap<>();

    public ItemConfig(KamiHub plugin) {
        super(plugin, "items.yml");
    }

    @Override
    public void load() {
        super.load();
        itemMap.clear();
        itemActionMap.clear();
        for (Map<?, ?> im : config.getMapList("items")) {
            NamespacedKey namespacedKey = NamespacedKey.fromString(im.get("id").toString());
            if (namespacedKey == null) {
                plugin.getSLF4JLogger().error("Invalid item id: {}", im.get("id"));
                continue;
            }
            short slot = Short.parseShort(im.get("slot").toString());
            var tmpName = im.get("name");
            Component name = MessageUtil.getMessage(tmpName != null ? tmpName.toString() : "");
            Object loreObj = im.get("lore");
            List<Component> lore = new ArrayList<>();
            if (loreObj instanceof List<?> rawList) {
                lore = rawList.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .map(MessageUtil::getMessage)
                        .toList();
            }
            ItemStack is = Registry.ITEM.get(namespacedKey).createItemStack();
            List<Component> finalLore = lore;
            is.editMeta(itemMeta -> {
                itemMeta.itemName(name);
                itemMeta.lore(finalLore);
            });
            itemMap.put(slot, is);
            if (im.get("click-actions") != null) {
                Object clickActionsObj = im.get("click-actions");
                if (clickActionsObj instanceof Collection<?>) {
                    String[] actions = ((Collection<?>) clickActionsObj).stream()
                            .filter(String.class::isInstance)
                            .map(String.class::cast)
                            .toArray(String[]::new);
                    itemActionMap.put(is, actions);
                }
            }
        }
    }

    @NotNull
    public String[] getActions(ItemStack is) {
        return itemActionMap.getOrDefault(is, new String[0]);
    }

}
