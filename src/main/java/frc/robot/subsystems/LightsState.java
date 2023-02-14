package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum LightsState implements ISubsystemState<Lights> {
    NEUTRAL((s) -> s.neutral()),
    CONE((s) -> s.yellow()),
    CUBE((s) -> s.purple()),
    DTM((s) -> s.green());

    SubsystemState<Lights> state;

    LightsState(Consumer<Lights> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    public SubsystemState<Lights> getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public Lights getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.lights;
    }
}
