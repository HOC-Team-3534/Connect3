package frc.robot.sequences;

import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;

import frc.robot.Robot;
import frc.robot.RobotContainer.Buttons;

public class SequenceProcessor {
    public static Drive drive;
    public static ElevatorSeq elevator;
    public static FlipperSeq flipper;
    public static SusanSeq susan;
    public static IntakeSeq intakeSeq;
    public static DriveCharacterization driveCharacterization;

    public SequenceProcessor() {
        drive = new Drive(DrivePhase.NEUTRAL, DrivePhase.DRIVE);
        intakeSeq = new IntakeSeq(IntakeSeqPhase.NEUTRAL,
                                  IntakeSeqPhase.NEUTRAL);
        driveCharacterization = new DriveCharacterization(DriveCharacterizationPhase.NEUTRAL,
                                                          DriveCharacterizationPhase.DRIVE);
    }

    public void process() {
        if (Buttons.ResetGyro.getButton()) {
            Robot.swerveDrive.resetPose();
        }
        drive.start();
        // driveCharacterization.start();
        drive.process();
        // driveCharacterization.process();
    }
}
