package frc.robot.sequences;

import frc.robot.subsystems.IntakeState;
import frc.robot.sequences.IntakeSeq;

public class SequenceProcessor {
    public static Drive drive;
    public static ElevatorSeq elevator;
    public static FlipperSeq flipper;
    public static GyroReset gyroReset;
    public static SusanSeq susan;
    public static IntakeSeq intakeSeq;


    public SequenceProcessor() {
        drive = new Drive(DrivePhase.NEUTRAL, DrivePhase.DRIVE);
        intakeSeq = new IntakeSeq(IntakeSeqPhase.NEUTRAL,IntakeSeqPhase.NEUTRAL);
    }

    public static void process() {
        drive.start();
        drive.process();
    }
}
