package com.example.gear;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;
import net.runelite.api.EquipmentInventorySlot;

import java.util.List;
import java.util.Map;

public interface GearSetupGenerator {
    GearSetup generateGearSetup(Map<EquipmentInventorySlot, List<CollectionLogItem>> unlockedItems);
}
