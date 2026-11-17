package frc.trigon.robot.subsystems.wheel;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import com.ctre.phoenix6.controls.VoltageOut;

public class WheelConstatnts {
    private static final int MOTOR_ID = 0;
    private static final String MOTOR_NAME = "IntakeMotor";
    static final TalonFXMotor MOTOR = new TalonFXMotor(MOTOR_ID,MOTOR_NAME);

    static final boolean FOC_ENABLED = true;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.0;

    static{
        final TalonFXConfiguration config = new TalonFXConfiguration();

        config.Audio.BeepOnBoot = false;
        config.Audio.BeepOnConfig = false;

        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;

        MOTOR.applyConfiguration(config);
    }

}
