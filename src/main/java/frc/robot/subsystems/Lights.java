package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Lights extends BaseSubsystem<LightsState> {
    Spark spark = new Spark(0);

    public Lights() {
        super(LightsState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    public void green() {
        spark.set(0.77);
    }

    public void yellow() {
        spark.set(0.69);
    }

    public void purple() {
        spark.set(0.91);
    }

    public void neutral() {
        // This sets it to blue need to have a chooser in smart dashboard to see
        // what
        // color we are
        switch (DriverStation.getAlliance()) {
            case Blue:
                spark.set(0.87);
                break;

            case Red:
                spark.set(0.61);
                break;

            default:
                spark.set(0.0);
                break;
        }
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
