package frc.robot.autons;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;

enum DriveForwardPhase implements ISequencePhase {
    Neutral,
    Drive(0, SwerveDriveState.DRIVE_AUTONOMOUSLY);

    SequencePhase phase;

    DriveForwardPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    DriveForwardPhase(int pathIndex, ISubsystemState... states) {
        phase = new SequencePhase(pathIndex, states);
    }

    public SequencePhase getPhase() {
        return phase;
    }
}

public class DriveForward extends BaseAutonSequence<DriveForwardPhase> {
    public DriveForward(DriveForwardPhase neutralPhase,
                        DriveForwardPhase startPhase,
                        BaseDriveSubsystem driveSubsystem) {
        super(neutralPhase, startPhase, driveSubsystem);
    }

    @Override
    public void process() {
        switch (getPhase()) {
            case Drive:
                if (this.getPlannerFollower().isFinished()) {
                    setNextPhase(DriveForwardPhase.Neutral);
                }
                break;

            case Neutral:
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