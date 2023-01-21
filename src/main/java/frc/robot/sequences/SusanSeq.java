package frc.robot.sequences;

import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
//Probably using a Talon SR and PLG
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum SusanSeqPhase implements ISequencePhase {
    NEUTRAL,
    ROTATE;// something to tell the subsystem and we might need to change it to clockwise
           // and counterclockwise

    SequencePhase phase;

    SusanSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class SusanSeq {
}
