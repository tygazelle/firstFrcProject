package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.robot.subsystems.MotorSubsystem;
import frc.trigon.robot.subsystems.wheel.WheelConstatnts;

import javax.swing.text.Position;
import java.beans.Encoder;

import static frc.trigon.robot.subsystems.arm.ArmConstants.MOTOR;

public class Arm extends MotorSubsystem {
    private final PIDController PID_ARM = ArmConstants.PID_ARM;
    private final CANcoderEncoder ENCODER_ARM = ArmConstants.ENCODER_ARM;
    private final TalonFXMotor motor = MOTOR;

    @Override
    public void stop() {
        motor.stopMotor();
    }

    public void SetTargerPostion(int setPoint) {
        motor.setPosition(PID_ARM.calculate(ENCODER_ARM.getSignal(CANcoderSignal.POSITION),setPoint));
    }
}
