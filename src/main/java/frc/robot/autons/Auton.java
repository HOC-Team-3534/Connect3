package frc.robot.autons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import frc.robot.Robot;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;

public enum Auton {
    Drive_Forward(new DriveForward(DriveForwardPhase.Neutral,
                                   DriveForwardPhase.Drive, Robot.swerveDrive),
                  Path.Drive_Forward_1);

    BaseAutonSequence<? extends ISequencePhase> auton;
    List<Path> paths;

    Auton(BaseAutonSequence<? extends ISequencePhase> auton, Path... paths) {
        this.auton = auton;
        this.paths = Arrays.asList(paths);
    }

    public BaseAutonSequence<? extends ISequencePhase> getAuton() {
        auton.setPathPlannerFollowers(paths.stream().map(path -> path.getPath()).collect(Collectors.toList()));
        return auton;
    }
}
