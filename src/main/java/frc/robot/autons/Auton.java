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
                  Path.Drive_Forward_1),
    Far_Left_Place2(new FarLeftPlace2(FarLeftPlace2Phase.NEUTRAL,
                                      FarLeftPlace2Phase.PLACECONE,
                                      Robot.swerveDrive),
                    Path.Far_Left_Place_2),
    Far_Right_Place2(new FarRightPlace2(FarRightPlace2Phase.NEUTRAL,
                                        FarRightPlace2Phase.PLACECONE,
                                        Robot.swerveDrive),
                     Path.Far_Right_Place_2),
    Far_Left_Place2_Pick_Up(new FarLeftPlace2PickUp(FarLeftPlace2PickUpPhase.NEUTRAL,
                                                    FarLeftPlace2PickUpPhase.PLACECONE,
                                                    Robot.swerveDrive),
                            Path.Far_Left_Place_2_Pick_Up);

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
