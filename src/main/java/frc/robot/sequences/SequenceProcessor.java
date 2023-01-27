package frc.robot.sequences;

import frc.robot.subsystems.IntakeState;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.RobotContainer.Buttons;
import frc.robot.sequences.IntakeSeq;

public class SequenceProcessor {
    public static Drive drive;
    public static ElevatorSeq elevator;
    public static FlipperSeq flipper;
    public static GyroReset gyroReset;
    public static SusanSeq susan;
    public static IntakeSeq intakeSeq;
    public static SteerCharacterization steerCharacterization;
    public static DriveCharacterization driveCharacterization;
    public static SendableChooser<Characterization> steerOrDrive = new SendableChooser<Characterization>();

    enum Characterization {
        Drive,
        Steer;
    }

    public SequenceProcessor() {
        drive = new Drive(DrivePhase.NEUTRAL, DrivePhase.DRIVE);
        intakeSeq = new IntakeSeq(IntakeSeqPhase.NEUTRAL, IntakeSeqPhase.NEUTRAL);
        steerCharacterization = new SteerCharacterization(SteerCharacterizationPhase.NEUTRAL,
                                                          SteerCharacterizationPhase.STEER);
        driveCharacterization = new DriveCharacterization(DriveCharacterizationPhase.NEUTRAL,
                                                          DriveCharacterizationPhase.DRIVE);
        steerOrDrive.setDefaultOption("Steer Characterization", Characterization.Steer);
        steerOrDrive.addOption("Drive Characterization", Characterization.Drive);
    }

    public void process() {
        // drive.start();
        if (Buttons.Characterize.getButton()) {
            switch (steerOrDrive.getSelected()) {
                case Drive:
                    driveCharacterization.start();
                    break;

                case Steer:
                    steerCharacterization.start();
                    break;

                default:
                    break;
            }
        }
        // drive.process();
        driveCharacterization.process();
        steerCharacterization.process();
    }
}
