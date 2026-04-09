package components;

import ecs.Component;

public class StateComponent implements Component {

    public enum State {
        IDLE,
        RUN,
        ATTACK
    }

    public State state = State.IDLE;

}