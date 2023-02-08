package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;
// Intake on the front with the swerve drive treds on the wood bot
//We are using 2 Talon SR's one for the top and one for the bottom bar

public class Intake extends BaseSubsystem<IntakeState> {
    final static WPI_TalonFX topMotor = new WPI_TalonFX(9);
    // WPI_TalonSRX botMotor;

    public Intake() {
        super(IntakeState.NEUTRAL);
        // topMotor.setInverted(true);
        SmartDashboard.putNumber("intake percent power", 0.5);
        // TODO Auto-generated constructor stub
    }

    public void intake() {
        var power = SmartDashboard.getNumber("intake percent power", 0);
        topMotor.set(ControlMode.PercentOutput, power);
        // botMotor.set(ControlMode.PercentOutput, 0.75);
    }

    public void extake() {
        var power = SmartDashboard.getNumber("intake percent power", 0);
        topMotor.set(ControlMode.PercentOutput, -power);
        // botMotor.set(ControlMode.PercentOutput, -0.75);
    }

    @Override
    public void neutral() {
        topMotor.set(ControlMode.PercentOutput, 0.0);
        // botMotor.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
