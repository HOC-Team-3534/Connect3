package frc.robot.subsystems;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.ISubsystem;

public enum ElevatorState implements ISubsystemState<Elevator> {
    ;

    @Override
    public SubsystemState<Elevator> getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Elevator getSubsystem() {
        return Robot.elevator;
    }
}
