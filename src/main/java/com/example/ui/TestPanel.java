package com.example.ui;

import com.example.CollectionLog;
import com.example.dps.DPSCalculator;
import com.example.dps.MeleeDPSCalculator;
import com.example.entities.MonsterInfo;
import com.example.entities.PlayerInfo;
import com.example.gear.GearSetup;
import com.example.gear.NaiveGearGenerator;
import com.example.items.CollectionLogItem;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;


public class TestPanel extends PluginPanel  {

    private final Map<String, Integer> nameVersionToId = new HashMap<>();

    // TODO: Only pass strings to SearchBar, move monsterInfo logic into this class.
    public TestPanel(Map<Integer, MonsterInfo> monsterInfo, PlayerInfo player, CollectionLog collectionLog) {
        super();

        monsterInfo.keySet().forEach(k -> {
            MonsterInfo monster = monsterInfo.get(k);
            String name = monster.getName();
            String version = monster.getVersion();
            String versionName;
            if (version == null || version.isEmpty()) {
                versionName = name + version;
            } else {
                versionName = name;
            }
            nameVersionToId.put(versionName, monster.getId());

        });
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        JComboBox comboBox = new SearchBar(monsterInfo).getComboBox();

        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;


        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox cb = (JComboBox) e.getSource();
                String nameVersion = (String) cb.getSelectedItem();
                int id = nameVersionToId.get(nameVersion);
                MonsterInfo selectedMonster = monsterInfo.get(id);
                System.out.println(selectedMonster.getName());
                // Invoke the DPS calc with the new monster info from the name,version -> id map

                GearSetup setup = new NaiveGearGenerator().generateGearSetup(collectionLog.getUnlockedItems());
                double dps = new MeleeDPSCalculator().calculateDPS(player, setup, selectedMonster);
                System.out.println("DPS:");
                System.out.println(dps);
            }
        });

        add(comboBox, c);
    }

}
