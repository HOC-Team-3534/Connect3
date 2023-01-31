package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum IntakeState implements ISubsystemState<Intake> {
    NEUTRAL((s) -> s.neutral()),
    INTAKE((s) -> s.intake()),
    EXTAKE((s) -> s.extake());

    SubsystemState<Intake> state;

    IntakeState(Consumer<Intake> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    public SubsystemState<Intake> getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public Intake getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.intake;
    }
}
