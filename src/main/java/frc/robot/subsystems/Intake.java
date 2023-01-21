package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;
// Intake on the front with the swerve drive treds on the wood bot
//We are using 2 Talon SR's one for the top and one for the bottom bar

public class Intake extends BaseSubsystem<IntakeState> {
    WPI_TalonSRX topMotor;
    WPI_TalonSRX botMotor;

    public Intake(IntakeState neutralState) {
        super(neutralState);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void neutral() {
        // set all the motors to 0 movement(Josh took a guess on how to do it on all of them)
        topMotor.set(ControlMode.PercentOutput, 0.0);
        botMotor.set(ControlMode.PercentOutput, 0.0);
    }

    public void intake() {
        topMotor.set(ControlMode.PercentOutput, 0.75);
        botMotor.set(ControlMode.PercentOutput, 0.75);
    }

    public void extake() {
        topMotor.set(ControlMode.PercentOutput, -0.75);
        botMotor.set(ControlMode.PercentOutput, -0.75);
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
