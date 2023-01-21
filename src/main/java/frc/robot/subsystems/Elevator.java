package frc.robot.subsystems;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Elevator extends BaseSubsystem<ElevatorState>{

    public Elevator(ElevatorState neutralState) {
        super(neutralState);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void neutral() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
    // Uses falcon and is what is used to move the game element to certain position
}
