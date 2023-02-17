package frc.robot.sequences;

import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.CarriageState;
import frc.robot.subsystems.IntakeState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum FlipSeqPhase implements ISequencePhase {
    NEUTRAL(IntakeState.NEUTRAL, CarriageState.NEUTRAL),
    FLIP(IntakeState.RUNBOTTOM, CarriageState.FLIP);// TODO Probably need a to
                                                    // add a delay inbetween the
                                                    // two running

    SequencePhase phase;

    FlipSeqPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class FlipSeq extends BaseSequence<FlipSeqPhase> {
    public FlipSeq(FlipSeqPhase neutralPhase, FlipSeqPhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        if (Buttons.FlipElement.getButton())
            setNextPhase(FlipSeqPhase.FLIP);
        else
            setNextPhase(FlipSeqPhase.NEUTRAL);
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
