package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.BearSwerveHelper;
import frc.robot.Constants.DRIVE;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;
import frc.statebasedcontroller.subsystem.requiredadditions.swervedrive.StateBasedSwerveDrivetrainModel;
import frc.robot.RobotContainer.Axes;

public class SwerveDrive extends BaseDriveSubsystem<SwerveDriveState> {
    static StateBasedSwerveDrivetrainModel dt = BearSwerveHelper.createBearSwerve();

    public SwerveDrive() {
        super(dt, DRIVE.KINEMATICS, SwerveDriveState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    protected void drive() {
        setModuleStates(Axes.Drive_ForwardBackward.getAxis(), Axes.Drive_LeftRight.getAxis(), Axes.Drive_Rotation.getAxis());
    }

    protected void creep() {
        setModuleStatesCreep(Axes.Drive_ForwardBackward.getAxis(), Axes.Drive_LeftRight.getAxis(), Axes.Drive_Rotation.getAxis());
    }

    @Override
    public void neutral() {
        // TODO Auto-generated method stub
        setModuleStates(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        setModuleStates(new ChassisSpeeds(0.0, 0.0, 0.0));
        return true;
    }
}