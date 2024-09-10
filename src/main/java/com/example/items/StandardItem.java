package com.example.items;

import lombok.Getter;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.http.api.item.ItemEquipmentStats;

@Getter
public class StandardItem implements CollectionLogItem {
    private final EquipmentInventorySlot slot;
    private final String name;

    private final int stabBonus;
    private final int slashBonus;
    private final int crushBonus;
    private final int mageBonus;
    private final int rangeBonus;
    private final int stabDefense;
    private final int slashDefense;
    private final int crushDefense;
    private final int mageDefense;
    private final int rangeDefense;
    private final int strBonus;
    private final int rngStrBonus;
    private final int magicStrBonus;
    private final int prayBonus;

    @Override
    public void serialize() {
    }

    @Override
    public EquipmentInventorySlot getSlot() {
        return this.slot;
    }

    @Override
    public int slashBonus() {
        return this.slashBonus;
    }

    @Override
    public int crushBonus() {
        return this.crushBonus;
    }

    @Override
    public int mageBonus() {
        return this.mageBonus;
    }

    @Override
    public int rangeBonus() {
        return this.rangeBonus;
    }

    @Override
    public int stabDefense() {
        return this.stabBonus;
    }

    @Override
    public int slashDefense() {
        return this.slashDefense;
    }

    @Override
    public int crushDefense() {
        return this.crushDefense;
    }

    @Override
    public int mageDefense() {
        return this.mageDefense;
    }

    @Override
    public int rangeDefense() {
        return this.rangeDefense;
    }

    @Override
    public int strBonus() {
        return this.strBonus;
    }

    @Override
    public int rngStrBonus() {
        return this.rngStrBonus;
    }

    @Override
    public int magicStrBonus() {
        return this.magicStrBonus;
    }

    @Override
    public int prayBonus() {
        return this.prayBonus;
    }

    public StandardItem(EquipmentInventorySlot slot, String name, ItemEquipmentStats equipment) {
        this.slot = slot;
        this.name = name;
        this.stabBonus = equipment.getAstab();
        this.slashBonus = equipment.getAslash();
        this.crushBonus = equipment.getAcrush();
        this.mageBonus = equipment.getAmagic();
        this.rangeBonus = equipment.getArange();
        this.stabDefense = equipment.getDstab();
        this.slashDefense = equipment.getDslash();
        this.crushDefense = equipment.getDcrush();
        this.mageDefense = equipment.getDmagic();
        this.rangeDefense = equipment.getDrange();
        this.strBonus = equipment.getStr();
        this.rngStrBonus = equipment.getRstr();
        this.magicStrBonus = equipment.getMdmg();
        this.prayBonus = equipment.getPrayer();
    }


}
