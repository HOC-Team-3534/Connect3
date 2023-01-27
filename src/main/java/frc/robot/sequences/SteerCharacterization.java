package frc.robot.sequences;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DRIVE;
import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum SteerCharacterizationPhase implements ISequencePhase {
    NEUTRAL,
    STEER(SwerveDriveState.CHARACTERIZE_STEER),
    COMPLETED(SwerveDriveState.COMPLETED_CHARACTERIZATION);

    SequencePhase phase;

    SteerCharacterizationPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class SteerCharacterization extends BaseSequence<SteerCharacterizationPhase> {
    SendableChooser<DriveCharacterizationPhase> steerOrDrive = new SendableChooser<DriveCharacterizationPhase>();

    public SteerCharacterization(SteerCharacterizationPhase neutralPhase,
                                 SteerCharacterizationPhase startPhase) {
        super(neutralPhase, startPhase);
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case STEER:
                if (getTimeSinceStartOfPhase() > DRIVE.Config.SteerCharacterization.QUASIASTIC_DURATION
                    || Buttons.CancelCharacterize.getButton()) {
                    setNextPhase(SteerCharacterizationPhase.COMPLETED);
                }
                break;

            case COMPLETED:
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
