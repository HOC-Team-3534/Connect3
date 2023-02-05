package frc.robot.sequences;

import frc.robot.Robot;
import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum DrivePhase implements ISequencePhase {
    NEUTRAL,
    DRIVE(SwerveDriveState.DRIVE),
    DTM(SwerveDriveState.DTM),
    DTM_Complete(SwerveDriveState.DRIVE);

    SequencePhase phase;

    DrivePhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class Drive extends BaseSequence<DrivePhase> {
    public Drive(DrivePhase neutralPhase, DrivePhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case DRIVE:
                if (Buttons.DTM.getButton())
                    break;
            case DTM:
                if (Robot.swerveDrive.getPathPlannerFollower().isFinished()) {
                    setNextPhase(DrivePhase.DTM_Complete);
                }
                if (!Buttons.DTM.getButton()) {
                    setNextPhase(DrivePhase.DRIVE);
                }
                break;

            case DTM_Complete:
                if (!Buttons.DTM.getButton()) {
                    setNextPhase(DrivePhase.DRIVE);
                }
                break;

            case NEUTRAL:
                break;

            default:
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
