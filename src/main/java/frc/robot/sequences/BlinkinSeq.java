package frc.robot.sequences;

import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum BlinkinSeqPhase implements ISequencePhase {
    NEUTRAL,
    CONE,
    CUBE,
    DTM,
    REDALLIANCE,
    BLUEAllIANCE;

    SequencePhase phase;

    BlinkinSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class BlinkinSeq extends BaseSequence<BlinkinSeqPhase> {
    public BlinkinSeq(BlinkinSeqPhase neutralPhase,
                      BlinkinSeqPhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
