/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import frc.lib.controllers.SpectrumAndNotButton;
import frc.lib.controllers.SpectrumAxisButton;
import frc.lib.controllers.SpectrumIOButton;
import frc.lib.controllers.SpectrumOrButton;
import frc.lib.controllers.SpectrumTwoButton;
import frc.lib.controllers.SpectrumXboxController;
import frc.lib.controllers.SpectrumAxisButton.ThresholdType;
import frc.lib.controllers.SpectrumXboxController.XboxAxis;
import frc.lib.util.Debugger;
import frc.lib.util.SpectrumLogger;
import frc.lib.util.SpectrumPreferences;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.PathFollower;
import frc.robot.subsystems.PhotonLEDs;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.VisionLL;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.cargo.IntakeCargo;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.button.*;

import frc.robot.commands.Climb;
import frc.robot.commands.ClimberKicker;
//import frc.robot.commands.auto.FollowPath;
//import frc.robot.commands.auto.LvlOneTwoRocketLeft;
//import frc.robot.commands.auto.LvlOneTwoRocketRight;
//import frc.robot.commands.auto.SmartMotionDrive;
import frc.robot.commands.cargo.*;
import frc.robot.commands.drive.AutoHatchIntake;
//import frc.robot.commands.drive.AutoTurn;
import frc.robot.commands.drive.BrakeMode;
import frc.robot.commands.drive.Drive;
//import frc.robot.commands.drive.LLDrive;
import frc.robot.commands.elevator.CheckIfZero;
import frc.robot.commands.elevator.ElevatorZero;
import frc.robot.commands.elevator.ManualElevator;
import frc.robot.commands.elevator.MMElevator;
import frc.robot.commands.elevator.SimpleElevatorGoToPos;
import frc.robot.commands.hatch.*;
import frc.robot.subsystems.Elevator;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public static CargoMech cargoMech = new CargoMech();
  public static Drivetrain drive = new Drivetrain();
  public static Pneumatics pneumatics = new Pneumatics();
  public static Climber climber = new Climber();
  public static Elevator elevator = new Elevator();
  public static Hatch hatch = new Hatch();
  public static VisionLL visionLL = new VisionLL();
  public static PhotonLEDs photon = new PhotonLEDs();
  public static DriverStation DS;
  public static PathFollower pathFollower = new PathFollower();

  public static SpectrumLogger logger = SpectrumLogger.getInstance();
  public static SpectrumPreferences prefs = SpectrumPreferences.getInstance();

  public static PowerDistributionPanel PDP = new PowerDistributionPanel();

  public static SpectrumXboxController driverController = new SpectrumXboxController(0, .17, .05);
  public static SpectrumXboxController opController = new SpectrumXboxController(1, .15, .15);
  public static SpectrumAxisButton leftTriggerButton;
  public static SpectrumAxisButton rightTriggerButton;
  //public static SpectrumAxisButton rightTriggerCanelIntake;
  public static SpectrumAxisButton leftStickIn;
  public static SpectrumAxisButton leftStickCargoShip;
  public SpectrumOrButton DriverLeftDpad;
  public SpectrumOrButton DriverRightDpad;

  // Add Debug flags
  // You can have a flag for each subsystem, etc
  public static final String _controls = "CONTROL";
  public static final String _general = "GENERAL";
  public static final String _auton = "AUTON";
  public static final String _commands = "COMMAND";
  public static final String _drive = "DRIVE";

  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    LiveWindow.setEnabled(false);
		LiveWindow.disableAllTelemetry();
		DS = DriverStation.getInstance();
		initDebugger(); //Init Debugger
		//SpectrumLogger.getInstance().intialize();  //setup files for logging
		printInfo("Start robotInit()");
		//HW.oi = new OI();
		Dashboard.intializeDashboard();
    visionLL.initialize();
    SpectrumLogger.getInstance().finalize();  //Finalize the logging items
    
    drive.setDefaultCommand(new Drive(drive));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * inst antiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Driver Buttons
    //A button is aim with camera inside drive() command
    //driverController.yButton.whileHeld(new Climb());
    //driverController.selectButton.whenPressed(new ClimberKicker());
    driverController.bButton.whileHeld(new AutoHatchIntake(elevator, hatch, cargoMech, driverController, drive));
    //driverController.xButton.whileHeld(new LLDrive());
    new SpectrumAxisButton(driverController, XboxAxis.RIGHT_X, .3, ThresholdType.DEADBAND).whileHeld(new BrakeMode(drive));// Go to brake mode when doing one side turn thing

    //driverController.startButton.whenPressed(new SmartMotionDrive(-7.85, 7.85));

    
    DriverLeftDpad = new SpectrumOrButton(driverController.Dpad.Left, new SpectrumOrButton(driverController.Dpad.UpLeft, driverController.Dpad.DownLeft));
    DriverRightDpad = new SpectrumOrButton(driverController.Dpad.Right, new SpectrumOrButton(driverController.Dpad.UpRight, driverController.Dpad.DownRight));
    //DriverLeftDpad.whenPressed(new AutoTurn(70, DriverLeftDpad));
    //DriverRightDpad.whenPressed(new AutoTurn(-70, DriverRightDpad));

    //Operator Buttons

    //Cargo and Hatch Controls
    rightTriggerButton = new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.RIGHT_TRIGGER, .5, ThresholdType.GREATER_THAN);
    Button intakeBtn = new SpectrumAndNotButton(rightTriggerButton, opController.Dpad.Left);
    IntakeCargo in = new IntakeCargo(intakeBtn, cargoMech, elevator);
    intakeBtn.whenPressed(in);

    leftTriggerButton = new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.LEFT_TRIGGER, .5, ThresholdType.GREATER_THAN);
    new SpectrumAndNotButton(leftTriggerButton, opController.Dpad.Left).whileHeld(new FireCargo(cargoMech));

    new SpectrumAndNotButton(opController.leftBumper, opController.Dpad.Left).whenPressed(new MMElevator((int)prefs.getNumber("C: ElevatorHeight", 2000), elevator));

    SpectrumOrButton leftDpad = new SpectrumOrButton(opController.Dpad.Left, new SpectrumOrButton(opController.Dpad.UpLeft, opController.Dpad.DownLeft));
    new SpectrumTwoButton(leftDpad, opController.rightBumper).whileHeld(new TiltDown(cargoMech));
    new SpectrumTwoButton(leftDpad, opController.leftBumper).whileHeld(new HatchRelease(hatch));
    new SpectrumTwoButton(leftDpad, leftTriggerButton).whileHeld(new HatchFire(hatch, cargoMech));
    new SpectrumTwoButton(leftDpad, rightTriggerButton).whileHeld(new HatchReady(hatch, cargoMech));

    leftStickIn = new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.LEFT_Y, -.25, ThresholdType.LESS_THAN);
    leftStickIn.whileHeld(new RollerBottomOn(1.0, cargoMech));
    leftStickIn.whileHeld(new RollerTopOn(1.0, cargoMech));

    leftStickCargoShip = new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.LEFT_Y, .25, ThresholdType.GREATER_THAN);
    leftStickCargoShip.whileHeld(new CargoShipDrop(cargoMech));

    new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.RIGHT_X, -.5, ThresholdType.LESS_THAN).whileHeld(new BullDozer(cargoMech));

    //Elevator Controls
    opController.startButton.whileHeld(new ElevatorZero(elevator));
    SpectrumIOButton cargoButton = new SpectrumIOButton(cargoMech.CargoSW);
    SpectrumOrButton rightDpad =  new SpectrumOrButton(opController.Dpad.Right, new SpectrumOrButton(opController.Dpad.UpRight, opController.Dpad.DownRight));
    SpectrumOrButton  cargoOverRideable = new SpectrumOrButton(cargoButton, rightDpad);
    opController.aButton.whenPressed(new CheckIfZero(elevator));
    new SpectrumTwoButton(opController.aButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posCargoL1, elevator));
    new SpectrumTwoButton(opController.xButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posCargoL2, elevator));
    new SpectrumTwoButton(opController.yButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posCargoL3, elevator));
    new SpectrumTwoButton(opController.bButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posCargoShip, elevator));
    new SpectrumAndNotButton(opController.aButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posDownLimit, elevator));
    new SpectrumAndNotButton(opController.bButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posHatchL2, elevator));
    new SpectrumAndNotButton(opController.xButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posHatchL2, elevator));
    new SpectrumAndNotButton(opController.yButton, cargoOverRideable).whenPressed(new MMElevator(Elevator.posHatchL3, elevator));

    new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.RIGHT_Y, -.15, ThresholdType.LESS_THAN).whileHeld(new ManualElevator(elevator));
    new SpectrumAxisButton(opController, SpectrumXboxController.XboxAxis.RIGHT_Y, .15, ThresholdType.GREATER_THAN).whileHeld(new ManualElevator(elevator));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  private static void initDebugger(){
    Debugger.setLevel(Debugger.debug2); //Set the initial Debugger Level
    Debugger.flagOn(_general); //Set all the flags on, comment out ones you want off
    Debugger.flagOn(_controls);
    Debugger.flagOn(_auton);
    Debugger.flagOn(_commands);
    Debugger.flagOn(_drive);
  }

  public static void printDebug(String msg){
    Debugger.println(msg, _general, Debugger.debug2);
  }
  
  public static void printInfo(String msg){
    Debugger.println(msg, _general, Debugger.info3);
  }
  
  public static void printWarning(String msg) {
    Debugger.println(msg, _general, Debugger.warning4);
  }
}
