/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of SpruceUI.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.spruceui.option;

import me.lambdaurora.spruceui.SpruceSeparatorWidget;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a separator option.
 *
 * @author LambdAurora
 * @version 1.3.0
 * @since 1.0.1
 */
public class SpruceSeparatorOption extends SpruceOption
{
    private final boolean showTitle;
    private final Text    tooltip;

    public SpruceSeparatorOption(@NotNull String key, boolean showTitle, @Nullable Text tooltip)
    {
        super(key);
        this.showTitle = showTitle;
        this.tooltip = tooltip;
    }

    @Override
    public AbstractButtonWidget createButton(GameOptions options, int x, int y, int width)
    {
        SpruceSeparatorWidget separator = new SpruceSeparatorWidget(this.showTitle ? new TranslatableText(this.key) : null, x, y, width);
        separator.setTooltip(this.tooltip);
        return new SpruceSeparatorWidget.ButtonWrapper(separator, 20);
    }
}
