package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.Constants.ELEVATOR;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.ISubsystem;

public enum ElevatorState implements ISubsystemState<Elevator> {
    NEUTRAL((s) -> s.neutral()),
    MID((s) -> s.changeHeight(ELEVATOR.Height.MID)),
    HIGH((s) -> s.changeHeight(ELEVATOR.Height.HIGH)),
    STAY((s) -> {
    }),
    LOW((s) -> s.changeHeight(ELEVATOR.Height.LOW));

    SubsystemState<Elevator> state;

    ElevatorState(Consumer<Elevator> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    @Override
    public SubsystemState<Elevator> getState() {
        return this.state;
    }

    @Override
    public Elevator getSubsystem() {
        return Robot.elevator;
    }
}
