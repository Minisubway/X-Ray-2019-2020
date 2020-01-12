package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drive.BrakeMode;
import frc.robot.commands.elevator.MMElevator;


/**
 * The Driver Control period of the competition
 */
public class Teleop {
	
    public static void init() {
        CommandScheduler.getInstance().cancelAll();
        //new MMElevator(RobotContainer.elevator).schedule(); //Force a new hold position at the begining of telop, should keep it at the right position.
        RobotContainer.visionLL.initialize();

        //Do things if connected to FMS only, so when telop starts during a match but not during testing
        if (DriverStation.getInstance().isFMSAttached()){
        }
        new BrakeMode(RobotContainer.drive).schedule();
    }

    public static void periodic() {
        CommandScheduler.getInstance().run();
    }

    public static void cancel() {
        CommandScheduler.getInstance().cancelAll();
    }
}
