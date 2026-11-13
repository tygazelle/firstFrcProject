package frc.trigon.robot.subsystems.wheel;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import com.ctre.phoenix6.controls.VoltageOut;

public class WheelConstatnts {
    public static final int MOTOR_ID = 0;
    public static final String MOTOR_NAME = "IntakeMotor";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID,MOTOR_NAME);

    public static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    public static final double GEAR_RATIO = 1.0;

    public static final VoltageOut VOLTAGE_REQUEST = new VoltageOut(0);

    static{
        final TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        MOTOR.applyConfiguration(config);
    }

}
