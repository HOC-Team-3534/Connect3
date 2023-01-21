package frc.robot.sequences;

import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

//Using a Falcon 
enum ElevatorSeqPhase implements ISequencePhase {
    NEUTRAL,;

    SequencePhase phase;

    ElevatorSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class ElevatorSeq {
}
