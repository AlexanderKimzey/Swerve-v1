/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private double deadZone = 0.2;
  private Controller controller = Controller.getInstance();
  private Mech mech = Mech.getInstance();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    }
    
  

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    final double leftJoystickRadius = Math.atan2(controller.getXLeftDriver(), controller.getYLeftDriver());
    final int leftJoystickAngle = (int) Math.toDegrees(leftJoystickRadius);

    SmartDashboard.putNumber("left joystick angle", leftJoystickAngle);
    SmartDashboard.putNumber("LX", controller.getXLeftDriver());
    SmartDashboard.putNumber("LY", controller.getYLeftDriver());
    SmartDashboard.putNumber("RX", controller.getXRightDriver());
    SmartDashboard.putNumber("RY", controller.getYRightDriver());
    SmartDashboard.putNumber("Mech 1 Position", mech.getMechOnePosition());

    if((controller.getYLeftDriver() > deadZone) || (controller.getYLeftDriver() < -deadZone) || (controller.getXLeftDriver() > deadZone) || controller.getXLeftDriver() < -deadZone){
      mech.setmechOnePosition(leftJoystickAngle);
      mech.setmechTwoPosition(leftJoystickAngle);
      mech.setmechThreePosition(leftJoystickAngle);
      mech.setmechFourPosition(leftJoystickAngle);
    }else{
      
    }

    if(controller.getXRightDriver() > deadZone || controller.getXRightDriver() < -deadZone){
      mech.mechOneControl(controller.getXRightDriver() * 100);
    }else{
      mech.mechOneControl(0);
    }

    if(controller.getBackButtonDriver()){
      mech.zero();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
}
