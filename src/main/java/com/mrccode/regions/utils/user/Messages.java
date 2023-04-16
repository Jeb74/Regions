package com.mrccode.regions.utils.user;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.fromCSSHexString;

public interface Messages {
    static Component generateSelectionMessage(int value) {
        Component defaultText = text("[", fromCSSHexString("#747572"));
        defaultText = defaultText.append(text("Regions", fromCSSHexString("#82AAFF"), TextDecoration.ITALIC));
        defaultText = defaultText.append(text("] ", fromCSSHexString("#747572")));
        if (value > 0) {
            defaultText = defaultText.append(
                    text(
                            "You have successfully completed a Selection.",
                            fromCSSHexString("#23D0EF")
                    )
            );
            if (value > 1) {
                defaultText = defaultText.append(
                        text(
                                " You have " + value + " pending selections to deal with!",
                                fromCSSHexString("#23D0EF")
                        )
                );
            }
        }
        else {
            value = Math.abs(value);
            defaultText = defaultText.append(
                    text(
                            "You have initialized a Selection!",
                            fromCSSHexString("#23D0EF")
                    )
            );
            if (value > 1) {
                defaultText = defaultText.append(
                        text(
                                " You have " + (value - 1) + " pending selections to deal with!",
                                fromCSSHexString("#23D0EF")
                        )
                );
            }
        }
        return defaultText;
    }
}
