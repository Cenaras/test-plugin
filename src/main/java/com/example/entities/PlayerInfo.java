package com.example.entities;

import com.example.items.CollectionLogItem;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Skill;

import java.util.ArrayList;
import java.util.List;


/**
 * Information about the player stats and unlocks.
 */
@Getter
public class PlayerInfo {
    private final List<CollectionLogItem> headItems = new ArrayList<>();
    private final List<CollectionLogItem> capeItems = new ArrayList<>();
    private final List<CollectionLogItem> amuletItems = new ArrayList<>();
    private final List<CollectionLogItem> weaponItems = new ArrayList<>();
    private final List<CollectionLogItem> bodyItems = new ArrayList<>();
    private final List<CollectionLogItem> shieldItems = new ArrayList<>();
    private final List<CollectionLogItem> legsItems = new ArrayList<>();
    private final List<CollectionLogItem> glovesItems = new ArrayList<>();
    private final List<CollectionLogItem> bootsItems = new ArrayList<>();
    private final List<CollectionLogItem> ringItems = new ArrayList<>();
    private final List<CollectionLogItem> ammoItems = new ArrayList<>();


    // TODO: Read from player
    @Setter
    private int strBoost = 0;
    private int attBoost = 0;

    @Getter
    private int attLevel = 99;
    private int strLevel = 99;
    private int rngLevel = 99;
    private int mageLevel = 99;
    private int prayLevel = 99;


    /**
     * Updates the corresponding fields with unlocked items for faster access
     */
    public void initializeUnlockedItems(List<CollectionLogItem> unlockedItems) {
        for (CollectionLogItem item : unlockedItems) {
            switch (item.getSlot()) {
                case HEAD:
                    headItems.add(item);
                    break;
                case CAPE:
                    capeItems.add(item);
                    break;
                case AMULET:
                    amuletItems.add(item);
                    break;
                case WEAPON:
                    weaponItems.add(item);
                    break;
                case BODY:
                    bodyItems.add(item);
                    break;
                case SHIELD:
                    shieldItems.add(item);
                    break;
                case LEGS:
                    legsItems.add(item);
                    break;
                case GLOVES:
                    glovesItems.add(item);
                    break;
                case BOOTS:
                    bootsItems.add(item);
                    break;
                case RING:
                    ringItems.add(item);
                    break;
                case AMMO:
                    ammoItems.add(item);
                    break;
            }
        }
    }


    public void updateStats(int[] realSkillLevels) {
        this.attLevel = realSkillLevels[Skill.ATTACK.ordinal()];
        this.strLevel = realSkillLevels[Skill.STRENGTH.ordinal()];
        this.rngLevel = realSkillLevels[Skill.RANGED.ordinal()];
        this.mageLevel = realSkillLevels[Skill.MAGIC.ordinal()];
        this.prayLevel = realSkillLevels[Skill.PRAYER.ordinal()];
    }
}

