package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.drivers.Photon;
import frc.lib.drivers.Photon.Animation;
import frc.lib.drivers.Photon.Color;
import frc.robot.commands.RumbleOff;
import frc.robot.commands.drive.CoastMode;
import frc.robot.commands.drive.DriveSpeed;

public class Disabled {
    
    public static void init() {
        Command a = new RumbleOff(RobotContainer.driverController, RobotContainer.opController);
        new DriveSpeed(0.0, RobotContainer.drive).schedule();
        a.schedule();
        //CommandScheduler.getInstance().cancelAll();
        a.cancel();
         //Do things if connected to FMS only, so when telop starts during a match but not during testing
        if (DriverStation.getInstance().isFMSAttached() || SmartDashboard.getBoolean("Drive/BRAKEMODE", true)){
        } else{
            new CoastMode(RobotContainer.drive).schedule();
        }    
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
        CommandScheduler.getInstance().run();
        Dashboard.dashboardFlash(); //Only flash when disabled, one less thing to send during telop
        /*if (HW.oi.isDriverButtonPushed()){
            Robot.photon.addAnimation("DriverButton", Animation.CYLON, Photon.Color.BLUE, Color.WHITE, 50, 5);
        }

        if (HW.oi.isOperatorButtonPushed()){
            Robot.photon.addAnimation("OperatorButton", Animation.CYLON, Color.RED, Color.WHITE, 51, 5);
        } */
    }
}
