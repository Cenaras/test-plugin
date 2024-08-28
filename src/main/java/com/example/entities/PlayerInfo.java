package com.example.entities;

import com.example.items.CollectionLogItem;
import net.runelite.api.Skill;

import java.util.ArrayList;
import java.util.List;


/**
 * Information about the player stats and unlocks.
 */
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


    private int strLevel = 0;
    private int attLevel = 0;
    private int rngLevel = 0;
    private int mageLevel = 0;
    private int prayLevel = 0;


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


    public int getStrLevel() {
        return 0;
    }

    public int getStrBoost() {
        return 0;
    }

    public int getAttLevel() {
        return 0;
    }

    public int getAttBoost() {
        return 0;
    }


    public void updateStats(int[] realSkillLevels) {
        this.attLevel = realSkillLevels[Skill.ATTACK.ordinal()];
        this.strLevel = realSkillLevels[Skill.STRENGTH.ordinal()];
        this.rngLevel = realSkillLevels[Skill.RANGED.ordinal()];
        this.mageLevel = realSkillLevels[Skill.MAGIC.ordinal()];
        this.prayLevel = realSkillLevels[Skill.PRAYER.ordinal()];
    }
}

