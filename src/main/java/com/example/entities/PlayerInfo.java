package com.example.entities;

import com.example.items.CollectionLogItem;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Skill;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Information about the player stats and unlocks.
 */
@Getter
public class PlayerInfo implements Serializable {
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

    private static final String unlockedItemsPath = "./src/main/java/com/example/data/unlocks.ser";
    private static final long serialVersionUID = 1L;

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

    // TODO: Make sure that if we add new items from collection log, then the currently unlocked items do not disappear

    // Serialize method
    public void saveToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(unlockedItemsPath))) {
            oos.writeObject(this);
        } catch (Exception e) {
            System.err.println("TODO");
            System.err.println(e.toString());
        }
    }

    // Deserialize method
    public static PlayerInfo loadFromDisk() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(unlockedItemsPath))) {
            return (PlayerInfo) ois.readObject();
        }
    }


}

