package frc.trigon.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;

public class ArmConstants {
    static final double P = 1.5;
    static final double I = 0.000001;
    static final double D = 2;
    static final PIDController PID_ARM = new PIDController(P, I, D);

    private static final int CAN_ENCODER_ARM_ID = 0;
    private static final String  CAN_ENCODER_ARM_NAME = "arm";
    static final CANcoderEncoder ENCODER_ARM = new CANcoderEncoder(CAN_ENCODER_ARM_ID,CAN_ENCODER_ARM_NAME);

    private static final int MOTOR_ID = 1;
    private static final String MOTOR_NAME = "Arm";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID, MOTOR_NAME);

    static final boolean FOC_ENABLED = true;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.0;

    public enum ArmState {
        open(120),
        closed(0),
        halfOpen(60);

        final int targetAngle;

        ArmState(int targetAngle) {
            this.targetAngle = targetAngle;
        }
    }

    public static void configure_motor(){
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

    public static void configure_encoder(){
        final CANcoderConfiguration encoderConfig = new CANcoderConfiguration();

        encoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        encoderConfig.MagnetSensor.MagnetOffset = 0.44675;
        encoderConfig.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;

        ENCODER_ARM.applyConfiguration(encoderConfig);
        ENCODER_ARM.setSimulationInputsFromTalonFX(MOTOR);
    }

    static {
        configure_motor();
        configure_encoder();
    }
}
