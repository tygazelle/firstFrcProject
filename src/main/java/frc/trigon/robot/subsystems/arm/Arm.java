package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderEncoder;
import frc.trigon.lib.hardware.phoenix6.cancoder.CANcoderSignal;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.robot.subsystems.MotorSubsystem;
public class Arm extends MotorSubsystem {
    private final PIDController pid = ArmConstants.PID;
    private final CANcoderEncoder encoder = ArmConstants.ENCODER;
    private final TalonFXMotor motor = ArmConstants.MOTOR;

    @Override
    public void stop() {
        motor.stopMotor();
    }

    public double getEncoderArmPostionDouble(){
        return encoder.getSignal(CANcoderSignal.POSITION);
    }

    public double getPidArmCaculationDouble(){
        return pid.calculate(getEncoderArmPostionDouble());
    }

    public void setTargetAngle(Rotation2d targetAngle) {
        motor.setPosition(pid.calculate(getEncoderArmPostionDouble(),targetAngle.getRotations()));
    }
}
