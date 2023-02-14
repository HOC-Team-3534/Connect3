package frc.robot.sequences;

import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;

import frc.robot.Robot;
import frc.robot.RobotContainer.Buttons;

public class SequenceProcessor {
    public static Drive drive;
    public static IntakeSeq intakeSeq;
    public static DriveCharacterization driveCharacterization;
    public static GetAttention getAttention;
    public static PlacePiece placePiece;

    public SequenceProcessor() {
        drive = new Drive(DrivePhase.NEUTRAL, DrivePhase.DRIVE);
        intakeSeq = new IntakeSeq(IntakeSeqPhase.NEUTRAL,
                                  IntakeSeqPhase.NEUTRAL);
        driveCharacterization = new DriveCharacterization(DriveCharacterizationPhase.NEUTRAL,
                                                          DriveCharacterizationPhase.DRIVE);
        getAttention = new GetAttention(GetAttentionPhase.NEUTRAL,
                                        GetAttentionPhase.NEUTRAL);
        placePiece = new PlacePiece(PlacePiecePhase.NEUTRAL,
                                    PlacePiecePhase.DECIDE);
    }

    public void process() {
        if (Buttons.ResetPoseToZero.getButton()) {
            Robot.swerveDrive.resetPose();
        }
        if (Buttons.ResetWithLimelight.getButton()) {
            Robot.swerveDrive.updatePoseWithVision();
        }
        if (Buttons.DTM.getButton()) {
            placePiece.start();
        }
        getAttention.start();
        drive.start();
        // if (Buttons.Characterize.getButton())
        // driveCharacterization.start();
        drive.process();
        getAttention.process();
        placePiece.process();
        // driveCharacterization.process();
    }
}
