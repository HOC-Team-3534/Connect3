package frc.robot.sequences;

import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
//Probably using a Talon SR and a PLG 
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum FlipperSeqPhase implements ISequencePhase {
    NEUTRAL,
    FLIP;

    SequencePhase phase;

    FlipperSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class FlipperSeq {
}
