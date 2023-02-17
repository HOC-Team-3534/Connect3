package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

// The clamp to grab the game element and place it on the grid also flipper
public class Carriage extends BaseSubsystem<CarriageState> {
    WPI_TalonSRX clamp = new WPI_TalonSRX(18);
    WPI_TalonSRX flipper = new WPI_TalonSRX(19);

    public Carriage() {
        super(CarriageState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    public void clampElement() {
        clamp.set(ControlMode.PercentOutput, 0.5);// TODO find the output needed
                                                  // to correctly run most
                                                  // likely will use encoder
                                                  // counts
    }

    public void flipElement() {
        flipper.set(ControlMode.PercentOutput, 0.5);// TODO find the output
                                                    // needed to correctly run
                                                    // most likely will use
                                                    // encoder counts they want
                                                    // it to be around 90
                                                    // degrees flipping
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
}
