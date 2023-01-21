package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum SwerveDriveState implements ISubsystemState<SwerveDrive> {
    NEUTRAL((s) -> s.neutral()),
    DRIVE((s) -> {
        if ((RobotContainer.Buttons.Creep.getButton())) {
            s.creep();
        } else {
            s.drive();
        }
    });

    SubsystemState<SwerveDrive> state;

    SwerveDriveState(Consumer<SwerveDrive> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    @Override
    public SubsystemState<SwerveDrive> getState() {
        return state;
    }

    @Override
    public SwerveDrive getSubsystem() {
        return Robot.swerveDrive;
    }
}