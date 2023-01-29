package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.statebasedcontroller.subsystem.general.swervedrive.swervelib.SDSModuleConfiguration;

public final class Constants {
    public static RobotType ROBOTTYPE = RobotType.CBOT;
    public static final int PIGEON_2 = 19; // FIXME Set Pigeon ID
    public static double LOOP_PERIOD_MILLIS = 20;

    public enum RobotType {
        CBOT,
        PBOT
    }

    public static final class Drive {
        public static final class Known {
            /**
             * The left-to-right distance between the drivetrain wheels
             * <p>
             * Should be measured from center to center.
             */
            public static final double TRACKWIDTH_METERS = 0.578; // FIXME
            /**
             * The front-to-back distance between the drivetrain wheels.
             * <p>
             * Should be measured from center to center.
             */
            public static final double WHEELBASE_METERS = 0.578; // FIXME
            public static final SDSModuleConfiguration SDS_MODULE_CONFIGURATION = SDSModuleConfiguration.SDSMK4(SDSModuleConfiguration.driveGearRatios.SDSMK4_L2);
            public static final double MAX_DRIVE_MOTOR_RPM = 6380.0; // Falcon500
                                                                     // max rpm
        }

        public static final class Calculated {
            public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(new Translation2d(Drive.Known.TRACKWIDTH_METERS / 2.0,
                                                                                                               Drive.Known.WHEELBASE_METERS / 2.0),
                                                                                             new Translation2d(Drive.Known.TRACKWIDTH_METERS / 2.0,
                                                                                                               -Drive.Known.WHEELBASE_METERS / 2.0),
                                                                                             new Translation2d(-Drive.Known.TRACKWIDTH_METERS / 2.0,
                                                                                                               Drive.Known.WHEELBASE_METERS / 2.0),
                                                                                             new Translation2d(-Drive.Known.TRACKWIDTH_METERS / 2.0,
                                                                                                               -Drive.Known.WHEELBASE_METERS / 2.0));
            // Drivetrain Performance Mechanical limits
            static public final double MAX_FWD_REV_SPEED_MPS_EST = Known.MAX_DRIVE_MOTOR_RPM / 60.0 / Known.SDS_MODULE_CONFIGURATION.driveGearRatio * Known.SDS_MODULE_CONFIGURATION.wheelCircumference;
            static public final double MAX_ROTATE_SPEED_RAD_PER_SEC_EST = MAX_FWD_REV_SPEED_MPS_EST / Math.hypot(Known.TRACKWIDTH_METERS / 2.0,
                                                                                                                 Known.WHEELBASE_METERS / 2.0);
            static public final double MAX_ROTATE_SPEED_RAD_PER_SEC_MOTOR_EST = Known.MAX_DRIVE_MOTOR_RPM / 60 / Known.SDS_MODULE_CONFIGURATION.angleGearRatio * 2 * Math.PI;
        }

        public static final class Config {
            public static final int PIGEON2_ID = 13;

            public static final class DriveCharacterization {
                public static final double QUASIASTIC_VOLTAGE = 0.75; // voltage
                                                                      // per
                                                                      // second
                                                                      // increase
                public static final double QUASIASTIC_DURATION = 10.0; // duration
                                                                       // of
                                                                       // test
                                                                       // seconds
            }
        }

        public static final class ROBOT {
            public static final double QUIESCENT_CURRENT_DRAW_A = 2.0; // Misc
                                                                       // electronics
            public static final double BATTERY_NOMINAL_VOLTAGE = 13.2; // Nicely
                                                                       // charged
                                                                       // battery
            // 40mOhm - average batter + cabling
            public static final double BATTERY_NOMINAL_RESISTANCE = 0.040;
            public static final double MAX_VOLTAGE = 12.0; // Maximum Voltage
                                                           // sent
                                                           // to a motor
                                                           // controller
        }

        public static final class AUTO {
            public static final double kMaxSpeedMetersPerSecond = 2;
            public static final double kMaxAccelerationMetersPerSecondSquared = 3;
            public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
            public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
            public static final double TRAJECTORYXkP = 10;
            public static final double TRAJECTORYYkP = 10;
            public static final double THETACONTROLLERkP = 10;
            // Constraint for the motion profilied robot angle controller
            public static final TrapezoidProfile.Constraints THETACONTROLLERCONSTRAINTS = new TrapezoidProfile.Constraints(kMaxAngularSpeedRadiansPerSecond,
                                                                                                                           kMaxAngularSpeedRadiansPerSecondSquared);
        }
    }
}