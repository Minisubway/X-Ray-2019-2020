package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.lib.drivers.Photon.Animation;
import frc.lib.drivers.Photon.Color;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.elevator.ElevatorCargoIntakeControl;
import frc.robot.commands.elevator.MMElevator;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Elevator;

public class IntakeCargo extends ParallelCommandGroup {
  private final Elevator m_Elevator;
  private final CargoMech m_CargoMech;
  /**
   * Add your docs here.
   */

   int buttonDebounce;
   Button intakeButton;
  public IntakeCargo(Button btn, CargoMech cargoMech, Elevator elevator) {
    m_Elevator = elevator;
    m_CargoMech = cargoMech;
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    addRequirements(cargoMech, elevator);
    addCommands(
      new IntakeDown(cargoMech),
      new TiltDown(cargoMech),
      new RollerBottomOn(1.0, cargoMech),
      new RollerTopOn(1.0, cargoMech),
      new ElevatorCargoIntakeControl(elevator)
    );
    this.intakeButton = btn;
  }
  protected void intialize(){
    RobotContainer.cargoMech.logEvent("Intaking Cargo");
    buttonDebounce = -1;
    if (RobotContainer.cargoMech.isIntakeComplete()){
      this.cancel();
    }
  }

  public boolean isFinished() {
    //If no longer holding intake button stop intaking
    if (!intakeButton.get()){
      return true;
    }
    
    if (buttonDebounce == -1){
      if (RobotContainer.cargoMech.isIntakeComplete()){
        return true;
      }
      buttonDebounce = 0;
    } 
    //Super easy button debounce / moving avaerage
    if (RobotContainer.cargoMech.isIntakeComplete()){
      buttonDebounce++;
    } else if (buttonDebounce > 0){ //only decrement if greater than 0
      buttonDebounce--;
    }

    if (buttonDebounce > 10){ //Only autostop the command if the button has been pressed for 10 cycles
      RobotContainer.photon.addAnimation("IntakeCargoFinished", Animation.BLINK_DUAL, Color.ORANGE, Color.WHITE, 75, 10);
      return true;
    }
    // if (Robot.cargoMech.isIntakeComplete()){
    //   return true;
    // }
    return false;
  }

  protected void end(){
    new MMElevator(Elevator.posDownLimit, m_Elevator).schedule();
    if (buttonDebounce < 10){
      new FinishIntake(m_CargoMech).schedule();
    }
  }
}
