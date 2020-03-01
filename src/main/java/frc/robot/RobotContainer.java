/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ManualTurretAim;
import frc.robot.subsystems.ExampleSubsystem;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //define the robot's subsystems
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //define the robot's commands
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //define controllers
  XboxController driveController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  XboxController fightStick = new XboxController(Constants.FIGHTSTICK_PORT);

  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //define subsystems
    Drivetrain m_Drivetrain = new Drivetrain();
    Turret m_Turret = new Turret();
    
    // Configure the button bindings
    configureButtonBindings();

    //set default commands
    m_Drivetrain.setDefaultCommand(
      new DefaultDrive(
        m_Drivetrain,
        () -> getFwdAxis(), 
        () -> getTurnAxis()));
    m_Turret.setDefaultCommand(
      new ManualTurretAim(
        m_Turret,
        () -> getManualTurretAxis()));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  private double getFwdAxis() {
    return driveController.getRawAxis(1);
  }

  private double getTurnAxis() {
    return driveController.getRawAxis(4);
  }

  private double getManualTurretAxis() {
    return fightStick.getRawAxis(0);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
