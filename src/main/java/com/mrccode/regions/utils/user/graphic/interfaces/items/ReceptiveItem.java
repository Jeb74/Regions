package com.mrccode.regions.utils.user.graphic.interfaces.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.fromCSSHexString;

public final class ReceptiveItem extends ItemStack {

    private final int fakeID;

    private Consumer<ReceptiveItem> update;

    public ReceptiveItem (
                    Reason reason,
                    String displayName,
                    String hexNameColor,
                    TextDecoration nameDecoration,
                    String description,
                    String hexDescColor,
                    TextDecoration descDecoration
            ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName, fromCSSHexString(hexNameColor), nameDecoration));
        meta.lore(
                Arrays.stream(description.split("\n")).map(
                        l -> text(l, fromCSSHexString(hexDescColor), descDecoration)
                ).collect(Collectors.toList())
        );
    }

    public ReceptiveItem (
                    Reason reason,
                    String displayName,
                    String hexNameColor,
                    TextDecoration nameDecoration,
                    String description,
                    String hexDescColor
            ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName, fromCSSHexString(hexNameColor), nameDecoration));
        meta.lore(
                Arrays.stream(description.split("\n")).map(
                        l -> text(l, fromCSSHexString(hexDescColor))
                ).collect(Collectors.toList())
        );
    }

    public ReceptiveItem (
                    Reason reason,
                    String displayName,
                    String hexNameColor,
                    TextDecoration nameDecoration,
                    String description
            ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName, fromCSSHexString(hexNameColor), nameDecoration));
        meta.lore(
                Arrays.stream(description.split("\n")).map(
                        Component::text
                ).collect(Collectors.toList())
        );
    }

    public ReceptiveItem (
                    Reason reason,
                    String displayName,
                    String hexNameColor,
                    TextDecoration nameDecoration
            ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName, fromCSSHexString(hexNameColor), nameDecoration));
        meta.addItemFlags(
                ItemFlag.HIDE_ITEM_SPECIFICS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_UNBREAKABLE
        );
    }

    public ReceptiveItem (
            Reason reason,
            String displayName,
            String hexNameColor
    ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName, fromCSSHexString(hexNameColor)));
        meta.addItemFlags(
                ItemFlag.HIDE_ITEM_SPECIFICS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_UNBREAKABLE
        );
    }

    public ReceptiveItem (
                    Reason reason,
                    String displayName
            ) {
        fakeID = reason.getValue();
        ItemMeta meta = this.getItemMeta();
        meta.displayName(text(displayName));
        meta.addItemFlags(
                ItemFlag.HIDE_ITEM_SPECIFICS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_UNBREAKABLE
        );
    }

    public ReceptiveItem (
            Reason reason
    ) {
        fakeID = reason.getValue();
        this.getItemMeta().addItemFlags(
                ItemFlag.HIDE_ITEM_SPECIFICS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_UNBREAKABLE
        );
    }

    public ReceptiveItem setMaterial(Material material) {
        this.setType(material);
        return this;
    }

    public void setUpdate(Consumer<ReceptiveItem> updateMode) {
        if (update == null) {
            update = updateMode;
        }
    }

    public void update() {
        if (update != null) update.accept(this);
    }

    public int getID() {
        return fakeID;
    }

}
