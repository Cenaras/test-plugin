package com.example.gear;

import com.example.enums.AttackType;
import com.example.enums.WeaponStyle;
import com.example.items.CollectionLogItem;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Prayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: Field with each item...
// TODO: weaponStyle *must* be determined uniquely by the weapon + selected option
// TODO: BuilderPattern to create? Instead of 11 arguments in ctor
@Getter
public class GearSetup {

    private final Map<Prayer, Double> strModifier = Map.of(Prayer.PIETY, 1.23);
    private final Map<Prayer, Double> attModifier = Map.of(Prayer.PIETY, 1.20);

    @Setter
    private AttackType attackType;
    @Setter
    private Prayer prayer;
    @Setter
    private WeaponStyle weaponStyle;

    private final CollectionLogItem headItems;
    private final CollectionLogItem capeItems;
    private final CollectionLogItem amuletItems;
    private final CollectionLogItem weaponItems;
    private final CollectionLogItem bodyItems;
    private final CollectionLogItem shieldItems;
    private final CollectionLogItem legsItems;
    private final CollectionLogItem glovesItems;
    private final CollectionLogItem bootsItems;
    private final CollectionLogItem ringItems;
    private final CollectionLogItem ammoItems;

    public GearSetup(
            CollectionLogItem headItems,
            CollectionLogItem capeItems,
            CollectionLogItem amuletItems,
            CollectionLogItem weaponItems,
            CollectionLogItem bodyItems,
            CollectionLogItem shieldItems,
            CollectionLogItem legsItems,
            CollectionLogItem glovesItems,
            CollectionLogItem bootsItems,
            CollectionLogItem ringItems,
            CollectionLogItem ammoItems
    ) {
        this.headItems = headItems;
        this.capeItems = capeItems;
        this.amuletItems = amuletItems;
        this.weaponItems = weaponItems;
        this.bodyItems = bodyItems;
        this.shieldItems = shieldItems;
        this.legsItems = legsItems;
        this.glovesItems = glovesItems;
        this.bootsItems = bootsItems;
        this.ringItems = ringItems;
        this.ammoItems = ammoItems;
    }


    public Double getStrModifier() {
        if (this.prayer == null) {
            return 1.0;
        }
        return this.strModifier.get(this.prayer);
    }

    public Double getAttModifier() {
        if (this.prayer == null) {
            return 1.0;
        }
        return this.attModifier.get(this.prayer);
    }


    public int getWeaponAttackSpeed() {
        return 4; // TODO
    }
}
