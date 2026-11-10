package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;

/**
 * A component in which scales the rendered texture of an entity.
 * @param scale the scale factor
 */
public record ScaledComponent(float scale) implements Component { }
