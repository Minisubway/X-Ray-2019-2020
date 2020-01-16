package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.util.Debugger;
import frc.lib.util.SpectrumLogger;
import frc.robot.commands.elevator.MMElevator;

public class Autonomous {

	public static void init() {
        //CommandScheduler.getInstance().cancelAll();
		//Scheduler.getInstance().enable();
		new MMElevator(RobotContainer.elevator).schedule(); //Force a new hold position at the begining of telop, should keep it at the right position.
        RobotContainer.visionLL.initialize();
		Debugger.println("Auto Init");
		SpectrumLogger.getInstance().addEvent("System", "Auto Init Complete");
	}

	// Periodic method called roughly once every 20ms
	public static void periodic() {
	}

	public static void cancel() {
		CommandScheduler.getInstance().cancelAll();
		SpectrumLogger.getInstance().addEvent("System", "Auto Complete");
		RobotContainer.pneumatics.compressor.start();
	}

    public static void printDebug(String msg){
    	Debugger.println(msg, Robot._auton, Debugger.debug2);
    }
    
    public static void printInfo(String msg){
    	Debugger.println(msg, Robot._auton, Debugger.info3);
    }
    
    public static void printWarning(String msg) {
    	Debugger.println(msg, Robot._auton, Debugger.warning4);
    }
}
