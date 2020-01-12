package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.CargoMech;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class FinishIntake extends ParallelCommandGroup {
  /**
   * Add your docs here.
   */
  public FinishIntake(CargoMech cargoMech) {
    addRequirements(cargoMech);
    addCommands(
      new RollerTopOn(1.0, cargoMech),
      new RollerBottomOn(1.0, cargoMech)

    );
    //this.addParallel(new RollerTopOn(1.0),1);
    //addParallel(new RollerBottomOn(1.0),1);
  }
  protected void intialize(){
    withTimeout(1);
  }

  public boolean isFinished() {
    return CommandScheduler.getInstance().timeSinceScheduled(this) >= 1;
  }

  protected void end(){

  }
}
