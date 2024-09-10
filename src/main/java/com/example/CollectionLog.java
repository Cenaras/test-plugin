package com.example;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;
import com.example.items.StandardItem;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.game.ItemManager;
import net.runelite.http.api.item.ItemEquipmentStats;
import net.runelite.http.api.item.ItemStats;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionLog implements Serializable {
    // Look at
    // https://github.com/evansloan/collection-log/blob/master/src/main/java/com/evansloan/collectionlog/CollectionLogManager.java

    private static final String unlockedItemsPath = "./src/main/java/com/example/data/unlocks.ser";
    private static final long serialVersionUID = 1L;

    @Inject
    private Client client;

    @Inject
    private ItemManager manager;

    @Getter
    private Map<EquipmentInventorySlot, List<CollectionLogItem>> unlockedItems = new HashMap<>();


    // TODO: Not all items come directly from CollectionLog (e.g., bowfa, faceguard, avernic) consider this...
    public void updateAllCollectionLogItems(PlayerInfo playerInfo) {
        List<CollectionLogItem> obtainedItems = new ArrayList<>();

        Widget collectionLogContainer = client.getWidget(ComponentID.COLLECTION_LOG_ENTRY_ITEMS);
        if (collectionLogContainer == null) {
            return;
        }
        Widget[] itemsInPage = collectionLogContainer.getDynamicChildren();
        for (Widget item : itemsInPage) {
            int itemId = item.getItemId();
            boolean obtained = item.getOpacity() == 0;
            String itemName = manager.getItemComposition(item.getItemId()).getMembersName();

            // Collect equippable items that are unlocked
            ItemStats itemStats = manager.getItemStats(itemId, true);
            if (!(itemStats == null) && itemStats.isEquipable() && obtained) {

                ItemEquipmentStats equipment = itemStats.getEquipment();
                int itemSlotId = equipment.getSlot();
                EquipmentInventorySlot slot = EquipmentInventorySlot.values()[itemSlotId];

                CollectionLogItem unlockedItem = new StandardItem(slot, itemName, equipment);

                unlockedItems
                        .computeIfAbsent(slot, k -> new ArrayList<>())
                        .add(unlockedItem);
            }
        }
    }


    // TODO: Make sure that if we add new items from collection log, then the currently unlocked items do not disappear

    public void saveToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(unlockedItemsPath))) {
            oos.writeObject(this.unlockedItems);
        } catch (Exception e) {
            System.err.println("TODO");
            System.err.println(e.toString());
        }
    }

    public void loadFromDisk() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(unlockedItemsPath))) {
            Map<EquipmentInventorySlot, List<CollectionLogItem>> newUnlocksItems = (Map<EquipmentInventorySlot, List<CollectionLogItem>>) ois.readObject();
            this.unlockedItems = newUnlocksItems;
        }
    }

}
