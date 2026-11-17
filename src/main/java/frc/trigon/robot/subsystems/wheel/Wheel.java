package frc.trigon.robot.subsystems.wheel;

import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.units.measure.Voltage;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXMotor;
import frc.trigon.lib.hardware.phoenix6.talonfx.TalonFXSignal;
import frc.trigon.robot.subsystems.MotorSubsystem;

public class Wheel extends MotorSubsystem {
    static final TalonFXMotor MOTOR = WheelConstatnts.MOTOR;

    private static final VoltageOut VOLTAGE_REQUEST = new VoltageOut(0).withEnableFOC(WheelConstatnts.FOC_ENABLED);

    private static void setVoltage(double voltage) {
        MOTOR.setControl(VOLTAGE_REQUEST.withOutput(voltage));
        MOTOR.registerSignal(TalonFXSignal.MOTOR_VOLTAGE, voltage);
    }

    private static double getVoltage() {
        return MOTOR.getSignal(TalonFXSignal.MOTOR_VOLTAGE);
    }

    public void collect(){
        setVoltage(-6.0);
    }

    public void eject(){
        setVoltage(6.0);
    }

    public void stop(){
        setVoltage(0);
    }
}





