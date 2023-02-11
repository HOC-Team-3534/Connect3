package frc.robot.subsystems;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

// The clamp to grab the game element and place it on the grid
public class Carriage extends BaseSubsystem<CarriageState>{

    public Carriage(CarriageState neutralState) {
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

    public Object flip() {
        return null;
    }

    public Object unflip() {
        return null;
    }
}
