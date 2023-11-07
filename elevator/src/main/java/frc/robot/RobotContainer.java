// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.constants.GlobalConstants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.GamepadDrive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Drivetrain;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProxyCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;



/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	public static RobotContainer instance;
	private final HashMap<String, Command> eventMap = new HashMap<>();
	private final Drivetrain m_drivetrainSubsystem = new Drivetrain();
	private final LogitechController m_controller = new LogitechController(ControllerConstants.DRIVER_CONTROLLER_PORT);

	private PowerDistribution m_PowerDistribution = new PowerDistribution(PCM_ID, ModuleType.kRev);
	private PneumaticsControlModule m_pneumaticsHub = new PneumaticsControlModule(PNEUMATICS_HUB);

	public static RobotContainer getInstance(){
		return instance;
	}


	/**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
		instance = this;
        // Set up the default command for the drivetrain.
        // The controls are for field-oriented driving:
        // Left stick Y axis -> forward and backwards movement
        // Left stick X axis -> left and right movement
        // Right stick X axis -> rotation
        m_drivetrainSubsystem.setDefaultCommand(new GamepadDrive(m_drivetrainSubsystem, m_controller));
		
		// Configure the button bindings
        configureButtonBindings();
		m_pneumaticsHub.enableCompressorDigital();
	

		LiveWindow.disableAllTelemetry();
    }

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
	
		//new Trigger(m_controller::getXButton)

		//new Trigger(m_controller::getAButton)

		//new Trigger(m_controller::getBButton)

		//new Trigger(m_controller::getYButton)


	}

	public boolean isRedAlliance() {
		return DriverStation.getAlliance() == Alliance.Red;
	}

	public Alliance getAlliance(){
		return DriverStation.getAlliance();
	}

	public boolean isDisabled() {
		return DriverStation.isDisabled();
	}

	public boolean isAutonomous() {
		return DriverStation.isAutonomous();
	}

	public boolean isTeleop() {
		return DriverStation.isTeleop();
	}

	public Drivetrain getDrivetrain() {
		return m_drivetrainSubsystem;
	}

	public static HashMap<String, Command> getEventMap() {
		return getInstance().eventMap;
	}

	public LogitechController getcontroller() {
		return m_controller;
	}

	public PowerDistribution getPowerDistribution() {
		return m_PowerDistribution;
	}

	public static PowerDistribution getPowerDistributionInstance() {
		return getInstance().m_PowerDistribution;
	}

	public void setPowerDistribution(PowerDistribution m_PowerDistribution) {
		this.m_PowerDistribution = m_PowerDistribution;
	}

	public static double voltageToPercentOutput(double voltage) {
		return MathUtil.clamp(voltage/Math.min(12, getPowerDistributionInstance().getVoltage()), -1, 1);
	}

}