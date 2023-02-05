package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.pathplanner.PathPlannerFollower;
import frc.robot.Robot;
import frc.robot.SwerveHelper;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Drive.AUTO;
import frc.robot.Constants.Drive.Calculated;
import frc.robot.Constants.Drive.Config;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveDrivetrainModel;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveModule;
import frc.robot.RobotContainer.Axes;
import frc.robot.RobotContainer.Buttons;

public class SwerveDrive extends BaseDriveSubsystem<SwerveDriveState> {
    final static boolean loadedConstants = SwerveHelper.loadSwerveConstants();
    final static WPI_TalonFX FL_drive = new WPI_TalonFX(1);
    final static WPI_TalonFX FL_steer = new WPI_TalonFX(3);
    final static CANCoder FL_cancoder = new CANCoder(2);
    final static SwerveModule fl = new SwerveModule(FL_drive, FL_steer,
                                                    FL_cancoder,
                                                    Rotation2d.fromDegrees(86.13));
    final static WPI_TalonFX FR_drive = new WPI_TalonFX(4);
    final static WPI_TalonFX FR_steer = new WPI_TalonFX(6);
    final static CANCoder FR_cancoder = new CANCoder(5);
    final static SwerveModule fr = new SwerveModule(FR_drive, FR_steer,
                                                    FR_cancoder,
                                                    Rotation2d.fromDegrees(3.86));
    final static WPI_TalonFX BL_drive = new WPI_TalonFX(7);
    final static WPI_TalonFX BL_steer = new WPI_TalonFX(9);
    final static CANCoder BL_cancoder = new CANCoder(8);
    final static SwerveModule bl = new SwerveModule(BL_drive, BL_steer,
                                                    BL_cancoder,
                                                    Rotation2d.fromDegrees(274.30));
    final static WPI_TalonFX BR_drive = new WPI_TalonFX(10);
    final static WPI_TalonFX BR_steer = new WPI_TalonFX(12);
    final static CANCoder BR_cancoder = new CANCoder(11);
    final static SwerveModule br = new SwerveModule(BR_drive, BR_steer,
                                                    BR_cancoder,
                                                    Rotation2d.fromDegrees(23.90));
    final static WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Config.PIGEON2_ID);
    final static SwerveDrivetrainModel dt = new SwerveDrivetrainModel(fl, fr,
                                                                      bl, br,
                                                                      pigeon2);
    static PIDController limelightPID = new PIDController(0.185, 0.0, 0.0);

    public SwerveDrive() {
        super(dt, Calculated.KINEMATICS, SwerveDriveState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    public void resetPose() {
        dt.setKnownPose(new Pose2d(0, 0, new Rotation2d()));
    }

    public Rotation2d getFLAbsoluteRotation2d() {
        return Rotation2d.fromDegrees(FL_cancoder.getAbsolutePosition());
    }

    protected void drive() {
        setModuleStates(Axes.Drive_ForwardBackward.getAxis(),
                        Axes.Drive_LeftRight.getAxis(),
                        Axes.Drive_Rotation.getAxis(), false);
    }

    protected void creep() {
        setModuleStatesCreep(Axes.Drive_ForwardBackward.getAxis(),
                             Axes.Drive_LeftRight.getAxis(),
                             Axes.Drive_Rotation.getAxis(), false);
    }

    public SwerveDrivetrainModel getDrivetrainModel() {
        return dt;
    }

    protected void characterizeDrive() {
        if (getStateFirstRunThrough()) {
            characterizeDriveInit();
        }
        characterizeDrive((double) getSequenceRequiring().getTimeSinceStartOfPhase() / 1000.0 * Drive.Config.DriveCharacterization.QUASIASTIC_VOLTAGE);
    }

    public void updatePoseWithVision() {
        if (Robot.limelight.getBotPose() != null && !Buttons.DTM.getButton()) {
            dt.setKnownPose(Robot.limelight.getBotPose());
        }
    }

    public void generatePathToEndPoseOnTheFly(Pose2d gridPosition) {
        this.setPathPlannerFollower(new PathPlannerFollower(getPose(),
                                                            getSpeed(),
                                                            gridPosition,
                                                            gridPosition.getRotation(),
                                                            0.0,
                                                            AUTO.kMaxSpeedMetersPerSecond,
                                                            AUTO.kMaxAccelerationMetersPerSecondSquared),
                                    false);
    }

    public ChassisSpeeds getSpeed() {
        return Drive.Calculated.KINEMATICS.toChassisSpeeds(getSwerveModuleStates());
    }

    @Override
    public void neutral() {
        // TODO Auto-generated method stub
        setModuleStates(new ChassisSpeeds(0.0, 0.0, 0.0), true);
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        setModuleStates(new ChassisSpeeds(0.0, 0.0, 0.0), true);
        return true;
    }
}