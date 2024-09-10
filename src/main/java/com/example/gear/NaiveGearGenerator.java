package com.example.gear;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;
import net.runelite.api.EquipmentInventorySlot;

import java.util.List;
import java.util.Map;

public class NaiveGearGenerator implements GearSetupGenerator {
    @Override
    public GearSetup generateGearSetup(Map<EquipmentInventorySlot, List<CollectionLogItem>> unlockedItems) {
        CollectionLogItem headItems = getFirst(unlockedItems.get(EquipmentInventorySlot.HEAD));
        CollectionLogItem capeItems = getFirst(unlockedItems.get(EquipmentInventorySlot.CAPE));
        CollectionLogItem amuletItems = getFirst(unlockedItems.get(EquipmentInventorySlot.AMULET));
        CollectionLogItem weaponItems = getFirst(unlockedItems.get(EquipmentInventorySlot.WEAPON));
        CollectionLogItem bodyItems = getFirst(unlockedItems.get(EquipmentInventorySlot.BODY));
        CollectionLogItem shieldItems = getFirst(unlockedItems.get(EquipmentInventorySlot.SHIELD));
        CollectionLogItem legsItems = getFirst(unlockedItems.get(EquipmentInventorySlot.LEGS));
        CollectionLogItem glovesItems = getFirst(unlockedItems.get(EquipmentInventorySlot.GLOVES));
        CollectionLogItem bootsItems = getFirst(unlockedItems.get(EquipmentInventorySlot.BOOTS));
        CollectionLogItem ringItems = getFirst(unlockedItems.get(EquipmentInventorySlot.RING));
        CollectionLogItem ammoItems = getFirst(unlockedItems.get(EquipmentInventorySlot.AMMO));
        return new GearSetup(headItems, capeItems, amuletItems, weaponItems, bodyItems, shieldItems, legsItems, glovesItems, bootsItems, ringItems, ammoItems);
    }

    private CollectionLogItem getFirst(List<CollectionLogItem> items) {
        if (items.isEmpty()) {
            return null;
        }
        return items.get(0);
    }


}
