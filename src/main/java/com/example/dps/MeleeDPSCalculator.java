package com.example.dps;

// https://oldschool.runescape.wiki/w/Damage_per_second/Melee

import com.example.enums.AttackType;
import com.example.GearSetup;
import com.example.entities.MonsterInfo;
import com.example.entities.PlayerInfo;
import com.example.enums.WeaponStyle;

// TODO: Interface and different implementations based on range mage and melee
// TODO: Not PlayerInfo but create a class that represents an actual equipment setup and let PlayerInfo represent player unlocks and levels
public class MeleeDPSCalculator implements DPSCalculator {

    // Pass to overall getDPSMethod and let it pass downwards...

    private int effectiveStrengthLevel(PlayerInfo player, GearSetup setup) {
        int accStr = 0;
        accStr += player.getStrLevel();
        accStr += player.getStrBoost();
        accStr *= setup.getStrModifier();
        if (setup.getWeaponStyle() == WeaponStyle.AGGRESSIVE) {
            accStr += 3;
        } else if (setup.getWeaponStyle() == WeaponStyle.CONTROLLED) {
            accStr += 1;
        }

        accStr += 8;
        // If all void, multiply with 1.1
        return accStr;
    }

    private int maxHit(PlayerInfo player, GearSetup setup) {
        int maxHit = 0;
        maxHit += effectiveStrengthLevel(player, setup);
        // * (equipment str bonus + 64)
        maxHit += 320;
        maxHit /= 640;
        // * target specific multipliers


        return maxHit;
    }

    private int effectiveAttackLevel(PlayerInfo player, GearSetup setup) {
        int accAtt = 0;
        accAtt += player.getAttLevel();
        accAtt += player.getAttBoost();
        accAtt *= setup.getAttModifier();

        if (setup.getWeaponStyle() == WeaponStyle.ACCURATE) {
            accAtt += 3;
        } else if (setup.getWeaponStyle() == WeaponStyle.CONTROLLED) {
            accAtt += 1;
        }
        accAtt += 8;
        // If all void, multiply with 1.1
        return accAtt;
    }

    private int attackRoll(PlayerInfo player, GearSetup setup) {
        int attackRoll = 0;
        attackRoll += effectiveAttackLevel(player, setup);
        // * (equipment attack bonus + 64)
        // * target specific gear bonus
        return attackRoll;
    }

    // TODO: I think we skip step 5 since we are not hitting players.

    private int defenseRoll(AttackType style, MonsterInfo monster) {
        return (monster.getDefenseLevel() + 9) * (monster.getStyleDefenseBonus(style) + 64);
    }

    private int hitChance(PlayerInfo player, GearSetup setup, MonsterInfo monster) {
        int attRoll = attackRoll(player, setup);
        int defRoll = defenseRoll(setup.getAttackType(), monster);
        if (attRoll > defRoll) {
            return 1 - ((defRoll + 2) / (2 * (attRoll + 1)));
        } else {
            return attRoll / (2 * (defRoll + 1));
        }
    }


    @Override
    public double calculateDPS(PlayerInfo player, GearSetup setup, MonsterInfo monster) {
        int maxHit = maxHit(player, setup);
        int avgDmgPrAttack = hitChance(player, setup, monster) * ((maxHit / 2) + (1 / (maxHit + 1)));

        return avgDmgPrAttack / (setup.getWeaponAttackSpeed() * 0.6);

    }
}
