package frc.robot.subsystems;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum FlipperState implements ISubsystemState<Flipper> {
    ;

    @Override
    public SubsystemState<Flipper> getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Flipper getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.flipper;
    }
    
}
