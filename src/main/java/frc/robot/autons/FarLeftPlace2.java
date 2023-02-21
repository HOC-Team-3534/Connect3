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

enum FarLeftPlace2Phase implements ISequencePhase {
    NEUTRAL,
    PLACECONE(ElevatorState.HIGH),
    DROPCONE(ElevatorState.STAY, CarriageState.DROP),
    DRIVE1(0, SwerveDriveState.DRIVE_AUTONOMOUSLY, IntakeState.INTAKE,
           ElevatorState.LOW, CarriageState.RETRACT),
    PLACECUBE(ElevatorState.HIGH, IntakeState.NEUTRAL),
    DROPCUBE(ElevatorState.STAY, CarriageState.DROP),
    REORIENT(ElevatorState.LOW, CarriageState.RETRACT);

    SequencePhase phase;

    FarLeftPlace2Phase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    FarLeftPlace2Phase(int pathIndex, ISubsystemState... states) {
        phase = new SequencePhase(pathIndex, states);
    }

    public SequencePhase getPhase() {
        // TODO Auto-generated method stub
        return phase;
    }
}

public class FarLeftPlace2 extends BaseAutonSequence<FarLeftPlace2Phase> {
    public FarLeftPlace2(FarLeftPlace2Phase neutralPhase,
                         FarLeftPlace2Phase startPhase,
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
                    setNextPhase(FarLeftPlace2Phase.DROPCONE);
                break;

            case DROPCONE:
                if (this.getTimeSinceStartOfPhase() > 500)
                    setNextPhase(FarLeftPlace2Phase.DRIVE1);
                break;

            case DRIVE1:
                if (this.getPlannerFollower().isFinished())
                    setNextPhase(FarLeftPlace2Phase.PLACECUBE);
                break;

            case PLACECUBE:
                if (Robot.elevator.isCorrectElevatorHeight())
                    setNextPhase(FarLeftPlace2Phase.DROPCUBE);
                break;

            case DROPCUBE:
                if (this.getTimeSinceStartOfPhase() > 500)
                    setNextPhase(FarLeftPlace2Phase.REORIENT);
                break;

            case REORIENT:
                if (this.getTimeSinceStartOfPhase() > 1000)
                    setNextPhase(FarLeftPlace2Phase.NEUTRAL);
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
