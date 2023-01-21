package frc.robot.subsystems;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum SusanState implements ISubsystemState<Susan> {
    ;

    @Override
    public SubsystemState<Susan> getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Susan getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.susan;
    }
}
