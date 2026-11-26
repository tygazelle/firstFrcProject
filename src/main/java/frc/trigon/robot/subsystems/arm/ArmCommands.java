package frc.trigon.robot.subsystems.arm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.trigon.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(Rotation2d targetAngle) {
        return new StartEndCommand(
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                RobotContainer.ARM::stop,
                RobotContainer.ARM
        );
    }

    public static Command getsetTargetStateCommand(ArmConstants.ArmState targetState) {
        return new StartEndCommand(
                () -> RobotContainer.ARM.setTargetAngle(targetState.targetAngle),
                RobotContainer.ARM::stop,
                RobotContainer.ARM
        );
    }
}
