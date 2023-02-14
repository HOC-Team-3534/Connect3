package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants.ELEVATOR;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Elevator extends BaseSubsystem<ElevatorState> {
    WPI_TalonFX elevatorMotor = new WPI_TalonFX(14);

    public Elevator() {
        super(ElevatorState.NEUTRAL);
        elevatorMotor.configMotionCruiseVelocity(ELEVATOR.kElevatorCruiseVelocity / ELEVATOR.kElevatorCountsToInches / 10.0);
        elevatorMotor.configMotionAcceleration(ELEVATOR.kElevatorAcceleration / ELEVATOR.kElevatorCountsToInches / 10.0);
        elevatorMotor.configMotionSCurveStrength(1);
        elevatorMotor.config_kP(0, 0);// TODO config and find the values by
                                      // tuning
        elevatorMotor.config_kI(0, 0);
        elevatorMotor.config_kD(0, 0);// TODO config and find the values by
                                      // tuning
        elevatorMotor.config_kF(0, 0);// TODO config and find the values by
                                      // tuning
        elevatorMotor.setSelectedSensorPosition(0);
    }

    public void changeHeight(ELEVATOR.Height height) {
        elevatorMotor.set(ControlMode.MotionMagic,
                          height.height / ELEVATOR.kElevatorCountsToInches);
    }

    public boolean isCorrectElevatorHeight() {
        return elevatorMotor.isMotionProfileFinished();
    }

    @Override
    public void neutral() {
        elevatorMotor.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean abort() {
        elevatorMotor.set(ControlMode.PercentOutput, 0.0);
        return true;
    }
    // Uses falcon and is what is used to move the game element to certain
    // position
}
