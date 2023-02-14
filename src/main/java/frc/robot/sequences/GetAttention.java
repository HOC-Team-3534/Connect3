package frc.robot.sequences;

import com.pathplanner.lib.PathPlanner;

import frc.robot.Robot;
import frc.robot.RobotContainer.Buttons;
import frc.robot.subsystems.LightsState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum GetAttentionPhase implements ISequencePhase {
    NEUTRAL(LightsState.NEUTRAL),
    CONE(LightsState.CONE),
    CUBE(LightsState.CUBE),
    DTM(LightsState.DTM);

    SequencePhase phase;

    GetAttentionPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }

    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class GetAttention extends BaseSequence<GetAttentionPhase> {
    public GetAttention(GetAttentionPhase neutralPhase,
                        GetAttentionPhase startPhase) {
        super(neutralPhase, startPhase);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        if (Buttons.ConeLights.getButton())
            setNextPhase(GetAttentionPhase.CONE);
        else if (Buttons.CubeLights.getButton())
            setNextPhase(GetAttentionPhase.CUBE);
        else if (SequenceProcessor.drive.getPhase().name().equals("DTM"))
            setNextPhase(GetAttentionPhase.DTM);
        else
            setNextPhase(GetAttentionPhase.NEUTRAL);
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }
}
