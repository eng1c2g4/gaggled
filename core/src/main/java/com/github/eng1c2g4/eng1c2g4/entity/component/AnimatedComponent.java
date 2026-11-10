package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A component that can be attached to an entity with a {@link VelocityComponent}
 * in which allows directional animation frames to be referenced by a system.
 * @param time current animation time
 * @param left left direction animation
 * @param right right direction animation
 * @param up up direction animation
 * @param down down direction animation
 */
public record AnimatedComponent(
        AtomicReference<Float> time,
        Animation<TextureRegion> left,
        Animation<TextureRegion> right,
        Animation<TextureRegion> up,
        Animation<TextureRegion> down
) implements Component {

    /**
     * Create a new animated component from a given sprite sheet.
     * This function takes in a 4x4 sprite sheet in the order:
     * <li>down</li>
     * <li>left</li>
     * <li>right</li>
     * <li>up</li>
     *
     * @param spriteSheet 4x4 sprite sheet
     * @return new animated component
     */
    public static AnimatedComponent fromSpriteSheet(Texture spriteSheet) {
        List<Animation<TextureRegion>> animations = Arrays.stream(TextureRegion.split(
                spriteSheet,
                spriteSheet.getWidth() / 4,
                spriteSheet.getHeight() / 4
        )).map(frames -> new Animation<>(0.1f, frames))
        .toList();
        return new AnimatedComponent(
                new AtomicReference<>(0.0f),
                animations.get(1),
                animations.get(2),
                animations.get(3),
                animations.get(0)
        );
    }

}
