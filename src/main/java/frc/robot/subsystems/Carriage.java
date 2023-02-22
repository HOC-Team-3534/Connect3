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
        clamp.set(ControlMode.PercentOutput, 0.5);// TODO figure out how this
                                                  // works because unsure of its
                                                  // by encoder counts or by
                                                  // output
    }

    public void flipElement() {
        flipper.set(ControlMode.Position, 1024);// TODO find the output
                                                // needed to correctly run
                                                // most likely will use
                                                // encoder counts they want
                                                // it to be around 90
                                                // degrees flipping Thats why
                                                // its at 1024 which is 1/4 of
                                                // 4096
    }

    public void drop() {
        flipper.set(ControlMode.Position, 1024);// TODO this is 1/4 of 4096
                                                // don't know if it will work
                                                // need to test should
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
