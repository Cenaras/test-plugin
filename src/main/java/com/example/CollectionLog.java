package com.example;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;
import com.example.items.StandardItem;
import com.google.common.collect.ImmutableList;
import net.runelite.api.*;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.game.ItemManager;
import net.runelite.http.api.item.ItemEquipmentStats;
import net.runelite.http.api.item.ItemStats;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CollectionLog {
    // Look at
    // https://github.com/evansloan/collection-log/blob/master/src/main/java/com/evansloan/collectionlog/CollectionLogManager.java

    @Inject
    private Client client;

    @Inject
    private ItemManager manager;

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
                CollectionLogItem obtainedItem = generateItem(itemStats, itemName);
                obtainedItems.add(obtainedItem);

            }
        }

        playerInfo.initializeUnlockedItems(obtainedItems);
        System.out.println("You have unlocked " + obtainedItems.size() + " items!");

    }


    // TODO: Parse using above and a single class for items...
    private CollectionLogItem generateItem(ItemStats itemStats, String itemName) {
        ItemEquipmentStats equipment = itemStats.getEquipment();
        int itemSlotId = equipment.getSlot();
        EquipmentInventorySlot slot = EquipmentInventorySlot.values()[itemSlotId];

        // TODO: Fix Java 11 update to 16 to use ENH Switch and Records

        return new StandardItem(slot, itemName, equipment);
    }

}
