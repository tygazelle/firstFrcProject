package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.trigon.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(int setPoint) {
        return new StartEndCommand(
                () -> {
                    RobotContainer.ARM.SetTargerPostion(setPoint);
                },
                RobotContainer.ARM::stop,
                RobotContainer.ARM
        );
    }
}
