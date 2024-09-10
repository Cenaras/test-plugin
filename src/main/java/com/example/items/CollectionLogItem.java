package com.example.items;

import net.runelite.api.EquipmentInventorySlot;

import java.io.Serializable;

public interface CollectionLogItem extends Serializable {

    void serialize();

    EquipmentInventorySlot getSlot();

    int getStabBonus();
    int slashBonus();
    int crushBonus();
    int mageBonus();
    int rangeBonus();
    int stabDefense();
    int slashDefense();
    int crushDefense();
    int mageDefense();
    int rangeDefense();
    int strBonus();
    int rngStrBonus();
    int magicStrBonus();
    int prayBonus();


    // TODO: Have a single StandardItemObject that implements and just uses an Enum to disginguish since all items have same attributes.
    // Just reduces code burden of parsing every item the same way...

}
