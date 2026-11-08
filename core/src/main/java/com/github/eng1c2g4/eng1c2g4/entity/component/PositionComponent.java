package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public record PositionComponent(Vector2 position) implements Component {}
