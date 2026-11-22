package frc.trigon.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;

public class ArmConstants {
    private static final int
            MOTOR_ID = 1,
            CAN_ENCODER_ARM_ID = 0;
    private static final String
            MOTOR_NAME = "Arm",
            CAN_ENCODER_ARM_NAME = "ArmEncoder";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID, MOTOR_NAME);
    static final CANcoderEncoder ENCODER_ARM = new CANcoderEncoder(CAN_ENCODER_ARM_ID, CAN_ENCODER_ARM_NAME);
    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.0;
    static final double
            P = 1.5,
            I = 0.000001
            ,D = 5;
    static final PIDController PID_ARM = new PIDController(P, I, D);
    static final boolean FOC_ENABLED = true;

    static {
        configureMotor();
        configureEncoder();
    }

    public static void configureMotor(){

        final TalonFXConfiguration motorConfig = new TalonFXConfiguration();

        motorConfig.Audio.BeepOnBoot = false;
        motorConfig.Audio.BeepOnConfig = false;

        motorConfig.MotorOutput.Inverted = INVERTED_VALUE;
        motorConfig.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        motorConfig.Feedback.SensorToMechanismRatio = GEAR_RATIO;

        MOTOR.applyConfiguration(motorConfig);
        MOTOR.registerSignal(TalonFXSignal.MOTOR_VOLTAGE, 100);
        MOTOR.registerSignal(TalonFXSignal.STATOR_CURRENT, 100);
    }

    public static void configureEncoder(){
        final CANcoderConfiguration encoderConfig = new CANcoderConfiguration();

        encoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        encoderConfig.MagnetSensor.MagnetOffset = 0.44675;
        encoderConfig.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;

        ENCODER_ARM.applyConfiguration(encoderConfig);
        ENCODER_ARM.setSimulationInputsFromTalonFX(MOTOR);
    }

    public enum ARM_STATE {
        open(Rotation2d.fromDegrees(120)),
        closed(Rotation2d.fromDegrees(0)),
        halfOpen(Rotation2d.fromDegrees(60)),;

        final Rotation2d targetAngle;
        ARM_STATE(Rotation2d targetAngle) {
            this.targetAngle = targetAngle;
        }
    }
}
