/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
//subsystems
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Climb;
//commands
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ManualTurretAim;
import frc.robot.commands.IntakePowerCell;
import frc.robot.commands.ManualClimbControl;
import frc.robot.commands.setClimbBrakeDisabled;
import frc.robot.commands.setClimbBrakeEnabled;
import frc.robot.commands.ExpelPowerCell;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  //define controllers
  XboxController driveController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  XboxController fightStick = new XboxController(Constants.FIGHTSTICK_PORT);

  //define subsystems
  Drivetrain m_drivetrain = new Drivetrain();
  Turret m_turret = new Turret();
  Intake m_intake = new Intake();
  Climb m_climb = new Climb();
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    // Configure the button bindings
    configureButtonBindings();

    //set default commands
    m_drivetrain.setDefaultCommand(
      new DefaultDrive(
        m_drivetrain,
        () -> getFwdAxis(), 
        () -> getTurnAxis()));

    m_turret.setDefaultCommand(
      new ManualTurretAim(
        m_turret,
        () -> getManualTurretAxis()));

    m_climb.setDefaultCommand(
      new ManualClimbControl(
        m_climb, 
        () -> getClimbControl()));


  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //manual expel intake with fightstick a
    new JoystickButton(fightStick, Constants.A_BUTTON_PORT)
      .whileHeld(new ExpelPowerCell(m_intake, () -> Constants.INTAKE_POWER));
    //intake, top feed, bottom feed with fightstick x
    new JoystickButton(fightStick, Constants.X_BUTTON_PORT)
      .whileHeld(new IntakePowerCell(m_intake, () -> Constants.INTAKE_POWER));
      //.alongWith();

    //new JoystickButton(fightStick, Constants.LB_BUTTON_PORT)
    // new JoystickButton(fightStick, fightStick.getTriggerAxis(Hand.kRight))
    //   .whenActive(new setClimbBrakeDisabled(m_climb));
    new JoystickButton(fightStick, Constants.RB_BUTTON_PORT)
      .whenPressed(new setClimbBrakeEnabled(m_climb));
  }

    //fightstickLBButton.whileHeld(new LimelightTurretAim(m_turret));
  

  private double getFwdAxis() {
    return driveController.getRawAxis(1);
  }

  private double getTurnAxis() {
    return driveController.getRawAxis(4);
  }

  private double getManualTurretAxis() {
    return fightStick.getRawAxis(0);
  }

  private double getClimbControl() {
    if (fightStick.getYButton()) {
      return 0.5;
    }
    else if (fightStick.getBButton()) {
      return -0.5;
    }
    else {
      return 0;
    }
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
}
