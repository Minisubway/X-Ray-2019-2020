package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class Drive extends CommandBase {
  private final Drivetrain m_Drivetrain;
  public Drive(Drivetrain drivetrain){
    m_Drivetrain = drivetrain;
    // Use requires() here to declare subsystem dependencies
    addRequirements(drivetrain);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_Drivetrain.logEvent("DRIVE");
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
    double turn = RobotContainer.driverController.leftStick.getX();
    double throttle = RobotContainer.driverController.triggers.getTwist();

    //m_Drivetrain.print("aBut: " + OI.driverController.aButton.get() + " valid: " + Robot.vision.getLimelightHasValidTarget());
    //If we are in single side steering, drive normally or by vision
    if (Math.abs(RobotContainer.driverController.rightStick.getX()) < .2){
      //If we are in vision mode use it to steer, if not drive normally, also check that elevator isn't blocking vision
      if (RobotContainer.driverController.aButton.get() && RobotContainer.visionLL.getLimelightHasValidTarget() && !RobotContainer.elevator.blockingVision()){
        m_Drivetrain.visionDrive(throttle);
      } else if(Math.abs(turn) < 0.05 && Math.abs(throttle) > 0.05) {
        m_Drivetrain.driveStraight(throttle);
      }  else {      
        m_Drivetrain.DriverArcadeDrive(throttle, turn);
      } 
    }
    else {//Single side steering and put us in brake mode for it.
      double l = Math.max(RobotContainer.driverController.rightStick.getX(), 0);
      double r = Math.max((-1 * RobotContainer.driverController.rightStick.getX()), 0);
      m_Drivetrain.tankDrive(l * .9 , r * .9);
    }
    //If we aren't arcing one side, drive with throttle and turn values
    
    //m_Drivetrain.printDebug("Turn: " + turn + " Throttle: " + throttle);
    //m_Drivetrain.printDebug("LeftOut: " + m_Drivetrain.leftFrontMotor.get() + " RightOut: " + m_Drivetrain.rightFrontMotor.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
  }
}
