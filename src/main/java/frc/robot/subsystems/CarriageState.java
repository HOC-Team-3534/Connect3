package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum CarriageState implements ISubsystemState<Carriage> {
    NEUTRAL((s) -> s.neutral()),
    FLIP((s) -> s.flipElement()),
    CLAMP((s) -> s.clampElement()),
    DROP((s) -> s.drop()),
    RETRACT((s) -> {
    });

    SubsystemState<Carriage> state;

    CarriageState(Consumer<Carriage> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    @Override
    public SubsystemState<Carriage> getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public Carriage getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.carriage;
    }
}
