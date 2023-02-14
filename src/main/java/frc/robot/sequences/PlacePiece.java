package frc.robot.sequences;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Robot;
import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.CarriageState;
import frc.robot.subsystems.ElevatorState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum PlacePiecePhase implements ISequencePhase {
    NEUTRAL(ElevatorState.NEUTRAL, CarriageState.NEUTRAL),
    DECIDE(ElevatorState.NEUTRAL, CarriageState.NEUTRAL),
    MID(ElevatorState.MID, CarriageState.NEUTRAL),
    HIGH(ElevatorState.HIGH, CarriageState.NEUTRAL),
    DROP(ElevatorState.STAY, CarriageState.DROP),
    RETRACT(ElevatorState.LOW, CarriageState.RETRACT);

    SequencePhase phase;

    PlacePiecePhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        // TODO Auto-generated method stub
        return null;
    }
}

public class PlacePiece extends BaseSequence<PlacePiecePhase> {
    public PlacePiece(PlacePiecePhase neutralPhase,
                      PlacePiecePhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case DECIDE:
                if (Buttons.PlaceHigh.getButton())
                    setNextPhase(PlacePiecePhase.HIGH);
                else if (Buttons.PlaceMid.getButton())
                    setNextPhase(PlacePiecePhase.MID);
                else if (Robot.swerveDrive.isAtEndPose())
                    setNextPhase(PlacePiecePhase.DROP);
                break;

            case HIGH:
                if (Robot.elevator.isCorrectElevatorHeight()
                    && Robot.swerveDrive.isAtEndPose())
                    setNextPhase(PlacePiecePhase.DROP);
                else if (!Buttons.DTM.getButton())
                    setNextPhase(PlacePiecePhase.RETRACT);
                break;

            case MID:
                if (Robot.elevator.isCorrectElevatorHeight()
                    && Robot.swerveDrive.isAtEndPose())
                    setNextPhase(PlacePiecePhase.DROP);
                else if (!Buttons.DTM.getButton())
                    setNextPhase(PlacePiecePhase.RETRACT);
                break;

            case DROP:
                if (!Buttons.DTM.getButton())
                    setNextPhase(PlacePiecePhase.RETRACT);
                break;

            case RETRACT:
                if (this.getTimeSinceStartOfPhase() > 500
                    && Robot.elevator.isCorrectElevatorHeight())
                    setNextPhase(PlacePiecePhase.NEUTRAL);
                break;

            case NEUTRAL:
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
