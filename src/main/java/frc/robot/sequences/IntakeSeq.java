package frc.robot.sequences;

import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.IntakeState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum IntakeSeqPhase implements ISequencePhase {
    NEUTRAL(IntakeState.NEUTRAL),
    INTAKE(IntakeState.INTAKE),
    EXTAKE(IntakeState.EXTAKE);

    SequencePhase phase;

    IntakeSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class IntakeSeq extends BaseSequence<IntakeSeqPhase> {
    public IntakeSeq(IntakeSeqPhase neutralPhase, IntakeSeqPhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        if (Buttons.Intake.getButton())
            setNextPhase(IntakeSeqPhase.INTAKE);
        else if (Buttons.Extake.getButton())
            setNextPhase(IntakeSeqPhase.EXTAKE);
        else
            setNextPhase(IntakeSeqPhase.NEUTRAL);
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
