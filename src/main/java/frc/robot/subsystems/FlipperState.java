package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;

public enum FlipperState implements ISubsystemState<Flipper> {
    NEUTRAL((s)-> s.neutral()),
    FLIP((s)-> s.flip()),
    UNFLIP((s)-> s.unflip());


    SubsystemState<Flipper> state;

	FlipperState(Consumer<Flipper> processFunction) {
		this.state = new SubsystemState<>(this, processFunction);
	}

    @Override
    public SubsystemState<Flipper> getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public Flipper getSubsystem() {
        // TODO Auto-generated method stub
        return Robot.flipper;
    }
    
}
