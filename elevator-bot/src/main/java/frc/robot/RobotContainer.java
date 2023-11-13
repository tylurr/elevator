// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DefaultElevatorCommand;
import frc.robot.subsystems.Elevator;

/** Add your docs here. */
public class RobotContainer {
  private final LogitechController m_controller = new LogitechController(0);
  private final Elevator m_elevator = new Elevator();

  public RobotContainer() {
    configureButtonBindings();
   
  }

  public void configureButtonBindings() {
    m_elevator.setDefaultCommand(new DefaultElevatorCommand(m_elevator, m_controller::getLeftY));

  }

}
