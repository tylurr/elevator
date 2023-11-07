package frc.robot.subsystems;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.constants.CANConstants;

/**
 * Add your docs here.
 */
public class Drivetrain extends SubsystemBase {
  private TalonSRX leftMotor1;
  private TalonSRX leftMotor2;
  private TalonSRX rightMotor1;
  private TalonSRX rightMotor2;

  private MotorControllerGroup rightMotors;
  private MotorControllerGroup leftMotors;

  private DifferentialDrive mainDrive;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Drivetrain(){
    this.leftMotor1 = new TalonSRX(CANConstants.LEFT_MOTOR_1);
    this.leftMotor2 = new TalonSRX(CANConstants.LEFT_MOTOR_2);
    this.rightMotor1 = new TalonSRX(CANConstants.RIGHT_MOTOR_1);
    this.rightMotor2 = new TalonSRX(CANConstants.RIGHT_MOTOR_2);


    this.leftMotors = new MotorControllerGroup(this.leftMotor1, this.leftMotor2);
    this.rightMotors = new MotorControllerGroup(this.rightMotor1, this.rightMotor2);

    
    this.mainDrive = new DifferentialDrive(leftMotors, rightMotors);
    this.mainDrive.setDeadband(0.1);





  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new GamepadDrive());
   
  }

  public void arcDrive(double throttle, double rotation){

    this.mainDrive.arcadeDrive(throttle, rotation);
  
  }
  
}
