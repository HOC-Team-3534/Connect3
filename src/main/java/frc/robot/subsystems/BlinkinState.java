package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum BlinkinState implements ISubsystemState<Blinkin> {
    NEUTRAL((s) -> s.neutral()),
    CONE((s) -> s.cone()),
    CUBE((s) -> s.cube()),
    DTM((s) -> s.DTM()),
    REDALLIANCE((s) -> s.redAlliance()),
    BLUEAllIANCE((s) -> s.blueAlliance());

    SubsystemState<Blinkin> state;

    BlinkinState(Consumer<Blinkin> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    public SubsystemState<Blinkin> getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public Blinkin getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.state;
    }
}
