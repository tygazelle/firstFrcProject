package frc.trigon.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;

public class ArmConstants {
    static final PIDController PID_ARM = new PIDController(1.5, 0.00001, 2);

    private static int CAN_ENCODER_ARM_ID = 0;
    private static String  CAN_ENCODER_ARM_NAME = "arm";
    static final CANcoderEncoder ENCODER_ARM = new CANcoderEncoder(CAN_ENCODER_ARM_ID,CAN_ENCODER_ARM_NAME);

    private static final int MOTOR_ID = 1;
    private static final String MOTOR_NAME = "Arm";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID, MOTOR_NAME);

    static final boolean FOC_ENABLED = true;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.0;

    static {
        final TalonFXConfiguration MOTOR_CONFIG = new TalonFXConfiguration();

        MOTOR_CONFIG.Audio.BeepOnBoot = false;
        MOTOR_CONFIG.Audio.BeepOnConfig = false;

        MOTOR_CONFIG.MotorOutput.Inverted = INVERTED_VALUE;
        MOTOR_CONFIG.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        MOTOR_CONFIG.Feedback.SensorToMechanismRatio = GEAR_RATIO;

        MOTOR.applyConfiguration(MOTOR_CONFIG);
        MOTOR.registerSignal(TalonFXSignal.MOTOR_VOLTAGE, 100);
        MOTOR.registerSignal(TalonFXSignal.STATOR_CURRENT, 100);

        final CANcoderConfiguration ENCODER_CONFIG = new CANcoderConfiguration();

        ENCODER_ARM.registerSignal(CANcoderSignal.POSITION, 100);
    }

}
