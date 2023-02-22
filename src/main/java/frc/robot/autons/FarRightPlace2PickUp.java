package frc.robot.autons;

import frc.robot.Robot;
import frc.robot.subsystems.CarriageState;
import frc.robot.subsystems.ElevatorState;
import frc.robot.subsystems.IntakeState;
import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;

enum FarRightPlace2PickUpPhase implements ISequencePhase {
    NEUTRAL,
    PLACECONE(ElevatorState.HIGH),
    DROPCONE(ElevatorState.STAY, CarriageState.DROP),
    DRIVE1(0, SwerveDriveState.DRIVE_AUTONOMOUSLY, IntakeState.INTAKE,
           ElevatorState.LOW, CarriageState.RETRACT),
    PLACECUBE(ElevatorState.HIGH, IntakeState.NEUTRAL),
    DROPCUBE(ElevatorState.STAY, CarriageState.DROP),
    DRIVE2(1, SwerveDriveState.DRIVE_AUTONOMOUSLY, IntakeState.INTAKE,
           ElevatorState.LOW, CarriageState.RETRACT),
    REORIENT(ElevatorState.LOW, CarriageState.RETRACT, IntakeState.NEUTRAL);

    SequencePhase phase;

    FarRightPlace2PickUpPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    FarRightPlace2PickUpPhase(int pathIndex, ISubsystemState... states) {
        phase = new SequencePhase(pathIndex, states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class FarRightPlace2PickUp extends BaseAutonSequence<FarRightPlace2PickUpPhase> {
    public FarRightPlace2PickUp(FarRightPlace2PickUpPhase neutralPhase,
                                FarRightPlace2PickUpPhase startPhase,
                                BaseDriveSubsystem driveSubsystem) {
        super(neutralPhase, startPhase, driveSubsystem);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case NEUTRAL:
                break;

            case PLACECONE:
                if (Robot.elevator.isCorrectElevatorHeight())
                    setNextPhase(FarRightPlace2PickUpPhase.DROPCONE);
                break;

            case DROPCONE:
                if (this.getTimeSinceStartOfPhase() > 500)
                    setNextPhase(FarRightPlace2PickUpPhase.DRIVE1);
                break;

            case DRIVE1:
                if (this.getPlannerFollower().isFinished())
                    setNextPhase(FarRightPlace2PickUpPhase.PLACECUBE);
                break;

            case PLACECUBE:
                if (Robot.elevator.isCorrectElevatorHeight())
                    setNextPhase(FarRightPlace2PickUpPhase.DROPCUBE);
                break;

            case DROPCUBE:
                if (this.getTimeSinceStartOfPhase() > 500)
                    setNextPhase(FarRightPlace2PickUpPhase.DRIVE2);
                break;

            case DRIVE2:
                if (this.getPlannerFollower().isFinished())
                    setNextPhase(FarRightPlace2PickUpPhase.REORIENT);
                break;

            case REORIENT:
                if (this.getTimeSinceStartOfPhase() > 1000)
                    setNextPhase(FarRightPlace2PickUpPhase.NEUTRAL);
                break;
        }
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
