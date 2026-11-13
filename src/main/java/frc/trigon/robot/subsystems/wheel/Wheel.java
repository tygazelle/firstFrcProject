package frc.trigon.robot.subsystems.wheel;

import frc.trigon.robot.subsystems.MotorSubsystem;

import static frc.trigon.robot.subsystems.wheel.WheelConstatnts.MOTOR;
import static frc.trigon.robot.subsystems.wheel.WheelConstatnts.VOLTAGE_REQUEST;


public class Wheel extends MotorSubsystem {
    public static void collect(){
        MOTOR.setControl(VOLTAGE_REQUEST.withOutput(-6.0));
    }

    public static void eject(){
        MOTOR.setControl(VOLTAGE_REQUEST.withOutput(6.0));
    }

    public static void stop(){
        MOTOR.setControl(VOLTAGE_REQUEST.withOutput(0));
    }
}
