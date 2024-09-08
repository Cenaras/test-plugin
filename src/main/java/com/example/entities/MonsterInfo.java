package com.example.entities;

import com.example.enums.AttackType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MonsterInfo {

    // TODO: Keep monsters.json in memory rather than reading from disk all the time...
    private static final String monsterInfo = "./src/main/java/com/example/data/monsters.json";

    private int id;
    private String name;
    private String version;
    private String image;
    private int level;
    private int speed;
    private List<String> style;
    private int size;
    private String maxHit;
    private Skills skills;
    private Offensive offensive;
    private Defensive defensive;
    private List<String> attributes;
    private Weakness weakness;

    private static Map<Integer, MonsterInfo> monstersMap = null;

    // TODO: Separate class for JSON parsing formats


    public int getDefenseLevel() {
        return 0;
    }

    public int getStyleDefenseBonus(AttackType style) {
        return 0;
    }

    public static Map<Integer, MonsterInfo> parseMonsters() {
        String monsterContent;
        StringBuilder b = new StringBuilder();
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(monsterInfo).normalize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String l : lines) {
            b.append(l).append("\n");
        }
        monsterContent = b.toString();

        // TODO: More reflection! :)
        Gson gson = new Gson();
        Type monsterListType = new TypeToken<List<MonsterInfo>>(){}.getType();
        List<MonsterInfo> monsters = gson.fromJson(monsterContent, monsterListType);
        Map<Integer, MonsterInfo> monsterMap = new HashMap<>();
        for (MonsterInfo monster : monsters) {
            monsterMap.put(monster.getId(), monster);
        }

        return monsterMap;
    }

    public static MonsterInfo getMonsterInfo(int id) {
        if (monstersMap == null) {
            monstersMap = parseMonsters();
        }

        return monstersMap.get(id);
    }

}

class Skills {
    private int atk;
    private int def;
    private int hp;
    private int magic;
    private int ranged;
    private int str;

}
class Offensive {
    private int atk;
    private int magic;
    @SerializedName("magic_str")
    private int magicStr;
    private int ranged;
    @SerializedName("ranged_str")
    private int rangedStr;
    private int str;

}
class Defensive {
    private int crush;
    private int magic;
    private int heavy;
    private int standard;
    private int light;
    private int slash;
    private int stab;
}

class Weakness {
    private String element;
    private int severity;
}

