package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Intake extends BaseSubsystem<IntakeState> {
    WPI_TalonFX topMotor = new WPI_TalonFX(15);
    WPI_TalonFX botMotor = new WPI_TalonFX(16);

    public Intake() {
        super(IntakeState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    public void intake() {
        topMotor.set(ControlMode.PercentOutput, 1.0);
        botMotor.set(ControlMode.PercentOutput, 1.0);
    }

    public void extake() {
        topMotor.set(ControlMode.PercentOutput, -1.0);
        botMotor.set(ControlMode.PercentOutput, -1.0);
    }

    public void runBottomMotor() {
        botMotor.set(ControlMode.PercentOutput, 1.0);
    }

    @Override
    public void neutral() {
        topMotor.set(ControlMode.PercentOutput, 0.0);
        botMotor.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
