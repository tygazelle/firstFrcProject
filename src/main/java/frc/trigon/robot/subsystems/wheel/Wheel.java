package frc.trigon.robot.subsystems.wheel;

import com.ctre.phoenix6.controls.VoltageOut;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;
import frc.trigon.robot.subsystems.MotorSubsystem;

public class Wheel extends MotorSubsystem {
    private final TalonFXMotor motor = WheelConstatnts.MOTOR;
    private final VoltageOut voltageOut = new VoltageOut(0).withEnableFOC(WheelConstatnts.FOC_ENABLED);

    private void setVoltage(double voltage) {
        motor.setControl(voltageOut.withOutput(voltage));
    }

    private double getVoltage() {
        return motor.getSignal(TalonFXSignal.MOTOR_VOLTAGE);
    }

    public void collect(){
        setVoltage(-6.0);
    }

    public void eject(){
        setVoltage(6.0);
    }

    @Override
    public void stop(){
        motor.stopMotor();
    }
}