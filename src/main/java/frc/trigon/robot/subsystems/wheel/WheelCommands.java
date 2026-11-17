package frc.trigon.robot.subsystems.wheel;
import edu.wpi.first.wpilibj2.command.*;

public class WheelCommands {
    public static Command startCollectEndStop(Wheel wheel) {
        return new StartEndCommand(
                () -> {
                    wheel.collect();
                },
                () -> {
                    wheel.stop();
                },
                wheel
        );
    }

    public static Command startEjectEndStop(Wheel wheel) {
        return new StartEndCommand(
                () -> {
                    wheel.eject();
                },
                () -> {
                    wheel.stop();
                },
                wheel
        );
    }
    
    public static Command collectThenWaitThenEjectThenStop(Wheel wheel) {
        return new SequentialCommandGroup(
                startCollectEndStop(wheel).withTimeout(3.00),
                startEjectEndStop(wheel).withTimeout(3.00),
                new InstantCommand(() -> wheel.stop())
        );
    }


}