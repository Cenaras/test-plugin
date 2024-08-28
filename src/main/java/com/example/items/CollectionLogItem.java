package com.example.items;

import net.runelite.api.EquipmentInventorySlot;

public interface CollectionLogItem {

    // TODO: Give items defense bonus as well?

    void serialize();

    EquipmentInventorySlot getSlot();

    // TODO: Have a single StandardItemObject that implements and just uses an Enum to disginguish since all items have same attributes.
    // Just reduces code burden of parsing every item the same way...

}
