package com.basecolon.nidhojj.ashley.component;

import com.badlogic.ashley.core.Component;

public class SwordPosition extends Component {
    public final Sword pos;

    public SwordPosition(Sword pos) {
        this.pos = pos;
    }

    public static enum Sword {
        HIGH,
        MID,
        LOW;
    }
}
