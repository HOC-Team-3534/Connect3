package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Blinkin extends BaseSubsystem<BlinkinState> {
    WPI_TalonSRX blinkSrx = new WPI_TalonSRX(14);// Should I make static?

    public Blinkin(BlinkinState neutralState) {
        super(neutralState);
        // TODO Auto-generated constructor stub
    }

    public void DTM() {
        blinkSrx.set(ControlMode.PercentOutput, 0.77);
    }

    public void cone() {
        blinkSrx.set(ControlMode.PercentOutput, 0.69);
    }

    public void cube() {
        blinkSrx.set(ControlMode.PercentOutput, 0.91);
    }

    public void blueAlliance() {
        blinkSrx.set(ControlMode.PercentOutput, 0.87);
    }

    public void redAlliance() {
        blinkSrx.set(ControlMode.PercentOutput, 0.61);
    }

    public void neutral() {
        // This sets it to blue need to have a chooser in smart dashboard to see what
        // color we are
        blinkSrx.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
