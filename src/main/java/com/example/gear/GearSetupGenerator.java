package com.example.gear;

import com.example.entities.PlayerInfo;
import com.example.items.CollectionLogItem;

import java.util.List;

public interface GearSetupGenerator {

    GearSetup generateGearSetup(PlayerInfo player);

}
