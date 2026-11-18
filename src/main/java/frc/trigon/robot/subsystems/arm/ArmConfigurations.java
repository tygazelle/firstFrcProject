package frc.trigon.robot.subsystems.arm;

public enum ArmConfigurations {
    open(120),
    closed(0),
    halfOpen(60);

    final int setPoint;

    ArmConfigurations(int setPoint) {
        this.setPoint = setPoint;
    }

}
