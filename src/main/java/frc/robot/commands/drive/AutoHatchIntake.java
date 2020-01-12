package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.controllers.SpectrumXboxController;
import frc.lib.drivers.Photon.Animation;
import frc.lib.drivers.Photon.Color;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.RumbleController;
import frc.robot.commands.cargo.TiltUp;
import frc.robot.commands.elevator.MMElevator;
import frc.robot.commands.hatch.HatchHold;
import frc.robot.commands.hatch.HatchReady;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;

public class AutoHatchIntake extends SequentialCommandGroup {
    boolean hasTarget = false;

    public AutoHatchIntake(Elevator elevator, Hatch hatch, CargoMech cargoMech, SpectrumXboxController controller, Drivetrain drivetrain) {
        addCommands(
            new AutoHatchIntake1(elevator, hatch, cargoMech),
            new AutoHatchIntake2(controller),
            new AutoHatchIntake3(hatch),
            new AutoHatchIntake4(cargoMech),
            new DriveSpeed(-.5, drivetrain)
        );
        //this.addParallel(new MMElevator(Elevator.posDownLimit));
        //this.addParallel(new HatchReady());
        //this.addSequential(new LLDrive());
        //this.addParallel(new RumbleController(OI.driverController, .75));
        //this.addSequential(new TimedCommand(.2));
        //this.addParallel(new HatchHold());
        //this.addSequential(new TimedCommand(.15));
        //this.addParallel(new TiltUp());
        //this.addSequential(new TimedCommand(.15));
        //this.addSequential(new DriveSpeed(-.5),.5);
        ///////////////////////////////////////////////////this.setInterruptible(false);
    }

    @Override
    public void initialize() {
        if (RobotContainer.visionLL.getLimelightHasValidTarget()){
            hasTarget = true;
        } else{
            hasTarget = false;
        }
    }

    public void execute(){
        RobotContainer.photon.addAnimation("AutoLoad", Animation.SIREN, Color.YELLOW, Color.WHITE, 101, 1);
    }

    public boolean isFinished(){
        return !hasTarget || super.isFinished();
    }
}
