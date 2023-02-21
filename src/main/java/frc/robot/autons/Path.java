package frc.robot.autons;

import frc.pathplanner.PathPlannerFollower;
import frc.robot.Constants.Drive.AUTO;

public enum Path {
    Drive_Forward_1("Drive Forward Path"),
    Far_Right_Place_2("Far Right Place 2"),
    Far_Left_Place_2("Far Left Place 2");

    String pathName;
    PathPlannerFollower path;

    Path() {
        this.pathName = this.name();
    }

    Path(String pathName) {
        this.pathName = pathName;
    }

    public void loadPath() {
        path = new PathPlannerFollower(pathName, AUTO.kMaxSpeedMetersPerSecond,
                                       AUTO.kMaxAccelerationMetersPerSecondSquared);
    }

    public PathPlannerFollower getPath() {
        return path;
    }
}
