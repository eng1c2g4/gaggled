package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public record VelocityComponent(Vector2 velocity, Vector2 acceleration) implements Component {}
