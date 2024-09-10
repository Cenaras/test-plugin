package com.example.entities;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Skill;


/**
 * Information about the player stats and unlocks.
 */
@Getter
public class PlayerInfo {


    @Setter
    private int strBoost = 0;
    private int attBoost = 0;

    @Getter
    private int attLevel = 99;
    private int strLevel = 99;
    private int rngLevel = 99;
    private int mageLevel = 99;
    private int prayLevel = 99;


    public void updateStats(int[] realSkillLevels) {
        this.attLevel = realSkillLevels[Skill.ATTACK.ordinal()];
        this.strLevel = realSkillLevels[Skill.STRENGTH.ordinal()];
        this.rngLevel = realSkillLevels[Skill.RANGED.ordinal()];
        this.mageLevel = realSkillLevels[Skill.MAGIC.ordinal()];
        this.prayLevel = realSkillLevels[Skill.PRAYER.ordinal()];
    }
}

