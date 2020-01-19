/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of SpruceUI.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.spruceui;

import com.mojang.blaze3d.systems.RenderSystem;
import me.lambdaurora.spruceui.accessor.DrawableHelperAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Represents an object which can show a tooltip.
 *
 * @author LambdAurora
 * @version 1.0.2
 * @since 1.0.0
 */
public interface Tooltipable
{
    /**
     * Gets the tooltip.
     *
     * @return The tooltip to show.
     */
    @NotNull Optional<Text> get_tooltip();

    /**
     * Sets the tooltip.
     *
     * @param tooltip The tooltip to show.
     */
    void set_tooltip(@Nullable Text tooltip);

    /**
     * Renders a tooltip.
     * <p>
     * X and Y tooltips are often mouse coordinates.
     *
     * @param client The client instance.
     * @param helper The draw helper.
     * @param text   The tooltip text to render.
     * @param x      The X coordinate of the tooltip.
     * @param y      The Y coordinate of the tooltip.
     */
    static void render(@NotNull MinecraftClient client, @NotNull DrawableHelper helper, List<String> text, int x, int y)
    {
        if (!text.isEmpty()) {
            DrawableHelperAccessor helper_accessor = (DrawableHelperAccessor) helper;

            RenderSystem.disableRescaleNormal();
            RenderSystem.disableDepthTest();
            int i = 0;

            for (String string : text) {
                int j = client.textRenderer.getStringWidth(string);
                if (j > i) {
                    i = j;
                }
            }

            int text_x = x + 12;
            int text_y = y - 12;
            int n = 8;
            if (text.size() > 1) {
                n += 2 + (text.size() - 1) * 10;
            }

            if (text_x + i > client.getWindow().getScaledWidth()) {
                text_x -= 28 + i;
            }

            if (text_y + n + 6 > client.getWindow().getScaledHeight()) {
                text_y = client.getWindow().getScaledHeight() - n - 6;
            }

            helper.setBlitOffset(300);
            client.getItemRenderer().zOffset = 300.0F;
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y - 4, text_x + i + 3, text_y - 3, -267386864, -267386864);
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y + n + 3, text_x + i + 3, text_y + n + 4, -267386864, -267386864);
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y - 3, text_x + i + 3, text_y + n + 3, -267386864, -267386864);
            helper_accessor.spruceui_fill_gradient(text_x - 4, text_y - 3, text_x - 3, text_y + n + 3, -267386864, -267386864);
            helper_accessor.spruceui_fill_gradient(text_x + i + 3, text_y - 3, text_x + i + 4, text_y + n + 3, -267386864, -267386864);
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y - 3 + 1, text_x - 3 + 1, text_y + n + 3 - 1, 1347420415, 1344798847);
            helper_accessor.spruceui_fill_gradient(text_x + i + 2, text_y - 3 + 1, text_x + i + 3, text_y + n + 3 - 1, 1347420415, 1344798847);
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y - 3, text_x + i + 3, text_y - 3 + 1, 1347420415, 1347420415);
            helper_accessor.spruceui_fill_gradient(text_x - 3, text_y + n + 2, text_x + i + 3, text_y + n + 3, 1344798847, 1344798847);
            MatrixStack matrix_stack = new MatrixStack();
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
            matrix_stack.translate(0.0D, 0.0D, client.getItemRenderer().zOffset);
            Matrix4f matrix4f = matrix_stack.peek().getModel();

            for (int line_index = 0; line_index < text.size(); ++line_index) {
                String line = text.get(line_index);
                if (line != null) {
                    client.textRenderer.draw(line, (float) text_x, (float) text_y, -1, true, matrix4f, immediate, false, 0, 15728880);
                }

                if (line_index == 0) {
                    text_y += 2;
                }

                text_y += 10;
            }

            immediate.draw();
            helper.setBlitOffset(0);
            client.getItemRenderer().zOffset = 0.0F;
            RenderSystem.enableDepthTest();
            RenderSystem.enableRescaleNormal();
        }
    }
}
