package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.robot.subsystems.MotorSubsystem;
public class Arm extends MotorSubsystem {
    private final PIDController pidArm = ArmConstants.PID_ARM;
    private final CANcoderEncoder encoderArm = ArmConstants.ENCODER_ARM;
    private final TalonFXMotor motor = ArmConstants.MOTOR;

    @Override
    public void stop() {
        motor.stopMotor();
    }

    public double getEncoderArmPostionDouble(){return encoderArm.getSignal(CANcoderSignal.POSITION);}

    public double getPidArmCaculationDouble(){return pidArm.calculate(getEncoderArmPostionDouble());}

    public void setTargetAngle(Rotation2d targetAngle) {
        motor.setPosition(pidArm.calculate(getEncoderArmPostionDouble(),targetAngle.getRotations()));
    }
}
