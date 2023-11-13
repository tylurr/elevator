// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class Elevator extends SubsystemBase { 
    private CANSparkMax leftMotor = new CANSparkMax(5,MotorType.kBrushless);
    private CANSparkMax rightMotor = new CANSparkMax(6,MotorType.kBrushless);
    public Elevator() {
        super();
        leftMotor.setInverted(true);
        rightMotor.setInverted(false);

    }

    public void move(double value) {
        leftMotor.set(6*value);
        rightMotor.set(6*value);
     }

}
