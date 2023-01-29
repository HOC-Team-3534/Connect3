package frc.robot.sequences;

public class SequenceProcessor {
    public static Drive drive;
    public static ElevatorSeq elevator;
    public static FlipperSeq flipper;
    public static GyroReset gyroReset;
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
        drive.start();
        // driveCharacterization.start();
        drive.process();
        // driveCharacterization.process();
    }
}
