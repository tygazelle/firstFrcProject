package frc.trigon.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;

public class ArmConstants {
    private static final int
            MOTOR_ID = 9,
            ENCODER_ID = 9;
    private static final String
            MOTOR_NAME = "ArmMotor",
            ENCODER_NAME = "ArmEncoder";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID, MOTOR_NAME);
    static final CANcoderEncoder ENCODER_ARM = new CANcoderEncoder(ENCODER_ID, ENCODER_NAME);
    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.0;
    static final double
            KP = 1.5,
            KI = 0.000001,
            KD = 5;
    static final PIDController PID_ARM = new PIDController(KP, KI, KD);
    static final boolean FOC_ENABLED = true;

    static {
        configureMotor();
        configureEncoder();
    }

    public static void configureMotor(){
        final TalonFXConfiguration Config = new TalonFXConfiguration();

        Config.Audio.BeepOnBoot = false;
        Config.Audio.BeepOnConfig = false;

        Config.MotorOutput.Inverted = INVERTED_VALUE;
        Config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        Config.Feedback.SensorToMechanismRatio = GEAR_RATIO;

        MOTOR.applyConfiguration(Config);
        MOTOR.registerSignal(TalonFXSignal.MOTOR_VOLTAGE, 100);
        MOTOR.registerSignal(TalonFXSignal.STATOR_CURRENT, 100);
    }

    public static void configureEncoder(){
        final CANcoderConfiguration Config = new CANcoderConfiguration();

        Config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        Config.MagnetSensor.MagnetOffset = 0.44675;
        Config.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;

        ENCODER_ARM.applyConfiguration(Config);
        ENCODER_ARM.setSimulationInputsFromTalonFX(MOTOR);
        MOTOR.registerSignal(TalonFXSignal.MOTOR_VOLTAGE,100);
        ENCODER_ARM.registerSignal(CANcoderSignal.POSITION,100);
    }

    public enum ARM_STATE {
        OPEN(Rotation2d.fromDegrees(120)),
        CLOSED(Rotation2d.fromDegrees(0)),
        HALF_OPEN(Rotation2d.fromDegrees(60)),;

        final Rotation2d targetAngle;
        ARM_STATE(Rotation2d targetAngle) {
            this.targetAngle = targetAngle;
        }
    }
}
