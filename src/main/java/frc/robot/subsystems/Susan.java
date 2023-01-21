package frc.robot.subsystems;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Susan extends BaseSubsystem<SusanState> {
    public Susan(SusanState neutralState) {
        super(neutralState);
        // TODO Auto-generated constructor stub
    }
    // The turn table within the robot to turn the cone

    @Override
    public void neutral() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
