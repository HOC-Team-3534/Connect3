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

enum DriveCharacterizationPhase implements ISequencePhase {
    NEUTRAL,
    DRIVE(SwerveDriveState.CHARACTERIZE_DRIVE),
    COMPLETED(SwerveDriveState.COMPLETED_CHARACTERIZATION);

    SequencePhase phase;

    DriveCharacterizationPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class DriveCharacterization extends BaseSequence<DriveCharacterizationPhase> {
    SendableChooser<DriveCharacterizationPhase> steerOrDrive = new SendableChooser<DriveCharacterizationPhase>();

    public DriveCharacterization(DriveCharacterizationPhase neutralPhase,
                                 DriveCharacterizationPhase startPhase) {
        super(neutralPhase, startPhase);
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case DRIVE:
                if (getTimeSinceStartOfPhase() > DRIVE.Config.DriveCharacterization.QUASIASTIC_DURATION * 1000
                    || Buttons.CancelCharacterize.getButton()) {
                    setNextPhase(DriveCharacterizationPhase.COMPLETED);
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
