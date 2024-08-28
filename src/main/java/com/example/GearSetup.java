package com.example;

import com.example.enums.AttackType;
import com.example.enums.WeaponStyle;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Prayer;

import java.util.Map;

// TODO: Field with each item...
// TODO: weaponStyle *must* be determined uniquely by the weapon + selected option
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
        return 0;
    }
}
