package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.SwerveHelper;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Drive.Calculated;
import frc.robot.Constants.Drive.Config;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveDrivetrainModel;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SwerveModule;
import frc.robot.RobotContainer.Axes;

public class SwerveDrive extends BaseDriveSubsystem<SwerveDriveState> {
    final static boolean loadedConstants = SwerveHelper.loadSwerveConstants();
    final static WPI_TalonFX FL_drive = new WPI_TalonFX(1);
    final static WPI_TalonFX FL_steer = new WPI_TalonFX(3);
    final static CANCoder FL_cancoder = new CANCoder(2);
    final static SwerveModule fl = new SwerveModule(FL_drive, FL_steer,
                                                    FL_cancoder,
                                                    Rotation2d.fromDegrees(1.76));
    final static WPI_TalonFX FR_drive = new WPI_TalonFX(4);
    final static WPI_TalonFX FR_steer = new WPI_TalonFX(6);
    final static CANCoder FR_cancoder = new CANCoder(5);
    final static SwerveModule fr = new SwerveModule(FR_drive, FR_steer,
                                                    FR_cancoder,
                                                    Rotation2d.fromDegrees(13.09));
    final static WPI_TalonFX BL_drive = new WPI_TalonFX(7);
    final static WPI_TalonFX BL_steer = new WPI_TalonFX(9);
    final static CANCoder BL_cancoder = new CANCoder(8);
    final static SwerveModule bl = new SwerveModule(BL_drive, BL_steer,
                                                    BL_cancoder,
                                                    Rotation2d.fromDegrees(2.46));
    final static WPI_TalonFX BR_drive = new WPI_TalonFX(10);
    final static WPI_TalonFX BR_steer = new WPI_TalonFX(12);
    final static CANCoder BR_cancoder = new CANCoder(11);
    final static SwerveModule br = new SwerveModule(BR_drive, BR_steer,
                                                    BR_cancoder,
                                                    Rotation2d.fromDegrees(4.92));
    final static WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Config.PIGEON2_ID);
    final static SwerveDrivetrainModel dt = new SwerveDrivetrainModel(fl, fr,
                                                                      bl, br,
                                                                      pigeon2);
    static PIDController limelightPID = new PIDController(0.185, 0.0, 0.0);

    public SwerveDrive() {
        super(dt, Calculated.KINEMATICS, SwerveDriveState.NEUTRAL);
        // TODO Auto-generated constructor stub
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