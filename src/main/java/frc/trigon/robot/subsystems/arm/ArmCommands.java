package frc.trigon.robot.subsystems.arm;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.trigon.robot.RobotContainer;

public class ArmCommands {

    public static Command getSetTargetAngleCommand(double setPoint) {
        return new StartEndCommand(
                () -> {RobotContainer.ARM.GoToSetPostion(setPoint);},
                RobotContainer.ARM::stop,
                RobotContainer.ARM
        );
    }
}
