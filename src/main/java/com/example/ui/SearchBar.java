package com.example.ui;

import com.example.entities.MonsterInfo;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SearchBar extends PlainDocument {

    private final Map<Integer, MonsterInfo> monsterMap;
    private final Map<String, Integer> nameVersionIdMap = new HashMap<>();
    @Getter
    private final JComboBox comboBox;
//    private boolean selecting = false;


    public SearchBar(Map<Integer, MonsterInfo> monsterMap) {
        this.monsterMap = monsterMap;

        this.monsterMap.keySet().forEach(k -> {
            MonsterInfo info = monsterMap.get(k);
            String name = info.getName();
            String version = info.getVersion();
            Integer id = info.getId();
//            String formattedName = !version.isBlank() ? name + "\n (" + version + ")" : name;
            this.nameVersionIdMap.put(name, id);
        });

        this.comboBox = createComboBox();


    }


    private JComboBox createComboBox() {
        JComboBox comboBox = new JComboBox(this.nameVersionIdMap.keySet().stream().sorted().toArray());

        comboBox.setEditable(true);

        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.setDocument(this);
        Dimension preferredSize = new Dimension(220, 25);
        comboBox.setPreferredSize(preferredSize);


        return comboBox;
    }


//    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
//
//        System.out.println("Insert String called with " + str);
//
//        // return immediately when selecting an item
//        if (selecting) return;
//        // insert the string into the document
//        super.insertString(offs, str, a);
//        // lookup and select a matching item
//        String item = lookupItem(getText(0, getLength()));
//        setSelectedItem(item);
//
//        // remove all text and insert the completed string
//        super.remove(0, getLength());
//        super.insertString(0, item, a);
//        // select the completed part
//
//        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
//        editor.setSelectionStart(offs+str.length());
//        editor.setSelectionEnd(getLength());
//    }
//
//    private void setSelectedItem(Object item) {
//        selecting = true;
//        this.comboBox.setSelectedItem(item);
//        selecting = false;
//    }
//
//    private String lookupItem(String pattern) {
//                System.out.println("PATTERN IS: " + pattern);
//
//        String[] bossNames = nameVersionIdMap.keySet().toArray(new String[0]);
////
////        System.out.println("PATTERN IS: " + pattern);
////
//        Optional<String> first = Arrays.stream(bossNames).filter(f -> f.startsWith(pattern)).findFirst();
//        if (first.isPresent()) {
//            return first.get();
//        } else {
//            throw new RuntimeException("BAD STUFF");
//        }
//    }
}
