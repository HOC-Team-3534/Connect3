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
    }),
    CHARACTERIZE_DRIVE((s) -> s.characterizeDrive()),
    COMPLETED_CHARACTERIZATION((s) -> {
        s.neutral();
        if (s.getStateFirstRunThrough()) {
            s.printData();
        }
    }),
    DRIVE_AUTONOMOUSLY((s) -> {
        if (s.getStateFirstRunThrough()) {
            // TODO check if the start of the path is near current odometry for
            // safety
        }
        if (s.getPathPlannerFollower() != null) {
            s.setModuleStatesAutonomous();
        } else {
            System.out.println("DRIVE PATH NOT SET. MUST CREATE PATHPLANNERFOLLOWER IN AUTON AND SET IN SWERVEDRIVE SUBSYSTEM");
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