package com.example.gear;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;

import java.util.List;

public class NaiveGearGenerator implements GearSetupGenerator {
    @Override
    public GearSetup generateGearSetup(PlayerInfo player) {
        CollectionLogItem headItems = getFirst(player.getHeadItems());
        CollectionLogItem capeItems = getFirst(player.getCapeItems());
        CollectionLogItem amuletItems = getFirst(player.getAmuletItems());
        CollectionLogItem weaponItems = getFirst(player.getWeaponItems());
        CollectionLogItem bodyItems = getFirst(player.getBodyItems());
        CollectionLogItem shieldItems = getFirst(player.getShieldItems());
        CollectionLogItem legsItems = getFirst(player.getLegsItems());
        CollectionLogItem glovesItems = getFirst(player.getGlovesItems());
        CollectionLogItem bootsItems = getFirst(player.getBootsItems());
        CollectionLogItem ringItems = getFirst(player.getRingItems());
        CollectionLogItem ammoItems = getFirst(player.getAmmoItems());
        return new GearSetup(headItems, capeItems, amuletItems, weaponItems, bodyItems, shieldItems, legsItems, glovesItems, bootsItems, ringItems, ammoItems);
    }

    private CollectionLogItem getFirst(List<CollectionLogItem> items) {
        if (items.isEmpty()) {
            return null;
        }
        return items.get(0);
    }


}
