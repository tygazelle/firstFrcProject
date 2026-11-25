package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.robot.subsystems.MotorSubsystem;

import static frc.trigon.robot.subsystems.arm.ArmConstants.MOTOR;

public class Arm extends MotorSubsystem {
    private final PIDController pidArm = ArmConstants.PID_ARM;
    private final CANcoderEncoder encoderArm = ArmConstants.ENCODER_ARM;
    private final TalonFXMotor motor = MOTOR;

    @Override
    public void stop() {
        motor.stopMotor();
    }

    public void SetTargetAngle(Rotation2d targetAngle) {
        motor.setPosition(pidArm.calculate(encoderArm.getSignal(CANcoderSignal.POSITION),targetAngle.getRotations()));
    }
}
