package com.example.dps;

import com.example.GearSetup;
import com.example.entities.MonsterInfo;
import com.example.entities.PlayerInfo;

public interface DPSCalculator {

    double calculateDPS(PlayerInfo player, GearSetup setup, MonsterInfo monster);
}
