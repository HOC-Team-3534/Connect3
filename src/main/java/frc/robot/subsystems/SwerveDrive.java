package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants.DRIVE;
import frc.robot.Constants.DRIVE.Calculated;
import frc.robot.Constants.DRIVE.Config;
import frc.robot.Constants.DRIVE.Known;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveDrivetrainModel;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveModule;
import frc.robot.RobotContainer.Axes;

public class SwerveDrive extends BaseDriveSubsystem<SwerveDriveState> {
    final static WPI_TalonFX FL_drive = new WPI_TalonFX(1);
    final static WPI_TalonFX FL_steer = new WPI_TalonFX(3);
    final static CANCoder FL_cancoder = new CANCoder(2);
    final static SwerveModule fl = new SwerveModule(Known.SDS_MODULE_CONFIGURATION,
                                                    Config.SWERVE_MODULE_TUNINGS, FL_drive,
                                                    FL_steer, FL_cancoder,
                                                    Rotation2d.fromDegrees(1.32));
    final static WPI_TalonFX FR_drive = new WPI_TalonFX(4);
    final static WPI_TalonFX FR_steer = new WPI_TalonFX(6);
    final static CANCoder FR_cancoder = new CANCoder(5);
    final static SwerveModule fr = new SwerveModule(Known.SDS_MODULE_CONFIGURATION,
                                                    Config.SWERVE_MODULE_TUNINGS, FR_drive,
                                                    FR_steer, FR_cancoder,
                                                    Rotation2d.fromDegrees(11.25));
    final static WPI_TalonFX BL_drive = new WPI_TalonFX(7);
    final static WPI_TalonFX BL_steer = new WPI_TalonFX(9);
    final static CANCoder BL_cancoder = new CANCoder(8);
    final static SwerveModule bl = new SwerveModule(Known.SDS_MODULE_CONFIGURATION,
                                                    Config.SWERVE_MODULE_TUNINGS, BL_drive,
                                                    BL_steer, BL_cancoder,
                                                    Rotation2d.fromDegrees(1.93));
    final static WPI_TalonFX BR_drive = new WPI_TalonFX(10);
    final static WPI_TalonFX BR_steer = new WPI_TalonFX(12);
    final static CANCoder BR_cancoder = new CANCoder(11);
    final static SwerveModule br = new SwerveModule(Known.SDS_MODULE_CONFIGURATION,
                                                    Config.SWERVE_MODULE_TUNINGS, BR_drive,
                                                    BR_steer, BR_cancoder,
                                                    Rotation2d.fromDegrees(3.96));
    final static WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Config.PIGEON2_ID);
    final static SwerveDrivetrainModel dt = new SwerveDrivetrainModel(fl, fr, bl, br, pigeon2,
                                                                      Calculated.KINEMATICS,
                                                                      Config.MODEL_CONFIGURATION);
    static PIDController limelightPID = new PIDController(0.185, 0.0, 0.0);

    public SwerveDrive() {
        super(dt, Calculated.KINEMATICS, SwerveDriveState.NEUTRAL);
        // TODO Auto-generated constructor stub
    }

    protected void drive() {
        setModuleStates(Axes.Drive_ForwardBackward.getAxis(), Axes.Drive_LeftRight.getAxis(), Axes.Drive_Rotation.getAxis());
    }

    protected void creep() {
        setModuleStatesCreep(Axes.Drive_ForwardBackward.getAxis(), Axes.Drive_LeftRight.getAxis(), Axes.Drive_Rotation.getAxis());
    }

    public SwerveDrivetrainModel getDrivetrainModel() {
        return dt;
    }

    protected void characterizeSteer() {
        if (getStateFirstRunThrough()) {
            characterizeSteerInit();
        }
        characterizeSteer(getSequenceRequiring().getTimeSinceStartOfPhase() * DRIVE.Config.SteerCharacterization.QUASIASTIC_VOLTAGE);
    }

    protected void characterizeDrive() {
        if (getStateFirstRunThrough()) {
            characterizeDriveInit();
        }
        characterizeDrive(getSequenceRequiring().getTimeSinceStartOfPhase() * DRIVE.Config.DriveCharacterization.QUASIASTIC_VOLTAGE);
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