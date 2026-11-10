package com.github.eng1c2g4.eng1c2g4.entity.component;

import com.badlogic.ashley.core.Component;

public record PlayerInputComponent() implements Component {

    public static final PlayerInputComponent INSTANCE = new PlayerInputComponent();

}
