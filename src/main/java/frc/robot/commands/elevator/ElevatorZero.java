package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;

/**
 * Zero the Extension
 */
public class ElevatorZero extends CommandBase {
	private final Elevator m_Elevator;
	double s;
	boolean alreadyDown = false;
	public ElevatorZero(Elevator elevator) {
		m_Elevator = elevator;
		// Use requires() here to declare subsystem dependencies
		addRequirements(elevator);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		m_Elevator.softLimitsOn(false);
		
		//Get Zero Speed from the prefrences
		s = -1 * RobotContainer.prefs.getNumber("E: Zero Speed", 0.4);

		if (m_Elevator.getBottomLimitSW()){
			alreadyDown = m_Elevator.getBottomLimitSW();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		m_Elevator.setOpenLoop(s);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return m_Elevator.getBottomLimitSW();
	}

	// Called once after isFinished returns true
	public void end() {
		//We actually reached our bottom limit switch, set to zero
		if (m_Elevator.getBottomLimitSW()){
			m_Elevator.zeroPosition();
			m_Elevator.setTargetPosition(0);
		}
		m_Elevator.setOpenLoop(0.0);
		m_Elevator.softLimitsOn(true);
		if (!alreadyDown){
			m_Elevator.logEvent("Elevator Zero");
			m_Elevator.setMotionMagicParams();
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end();
	}
}
