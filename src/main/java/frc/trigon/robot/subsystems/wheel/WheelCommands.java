package frc.trigon.robot.subsystems.wheel;
import edu.wpi.first.wpilibj2.command.*;
import frc.trigon.robot.RobotContainer;

public class WheelCommands {
    public static Command getCollectCommand() {
        return new StartEndCommand(
                RobotContainer.WHEEL::collect,
                RobotContainer.WHEEL::stop,
                RobotContainer.WHEEL
        );
    }

    public static Command getEjectsCommand() {
        return new StartEndCommand(
                RobotContainer.WHEEL::eject,
                RobotContainer.WHEEL::stop,
                RobotContainer.WHEEL
        );
    }
    
    public static Command getCollectThenEjectCommand() {
        return new SequentialCommandGroup(
                getCollectCommand().withTimeout(3.00),
                getEjectsCommand().withTimeout(3.00)
        );
    }
}