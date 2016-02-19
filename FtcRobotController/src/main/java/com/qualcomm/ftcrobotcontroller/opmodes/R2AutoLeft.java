package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

/**
 * Created by BraxtonL_Williams on 1/4/2016.
 */
public class R2AutoLeft extends LinearOpMode {
    private DcMotor motorDriveLeft;
    private DcMotor motorDriveRight;

    public Servo servoHook;

    public Servo servoMen;

    public Servo servoLock;

    public LightSensor lightSensor;

    public ArrayList<String> warnings = new ArrayList<String>(20);



    public R2AutoLeft()

    {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    } // PushBotAuto

    @Override
    public void runOpMode()
    {
        try
        {
            motorDriveLeft = hardwareMap.dcMotor.get("driveLeft");

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'driveLeft'");
            DbgLog.msg(e.getLocalizedMessage());
            motorDriveLeft = null;
        }

        try
        {
            motorDriveRight = hardwareMap.dcMotor.get("driveRight");
            motorDriveRight.setDirection(DcMotor.Direction.REVERSE);

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'driveRight'");
            DbgLog.msg(e.getLocalizedMessage());
            motorDriveRight = null;
        }

        try
        {
            servoHook = hardwareMap.servo.get("hook");
        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'hook'");
            DbgLog.msg(e.getLocalizedMessage());
            servoHook = null;
        }

        try
        {
            servoMen = hardwareMap.servo.get("men");
        }
        catch (Exception e)
        {
            warnings.add("failed to map 'men'");
            DbgLog.msg(e.getLocalizedMessage());
            servoMen = null;
        }

        try
        {
            servoLock = hardwareMap.servo.get("lock");
        }
        catch (Exception e)
        {
            warnings.add("failed to map 'lock'");
            DbgLog.msg(e.getLocalizedMessage());
            servoLock = null;
        }
        try
        {
            lightSensor = hardwareMap.lightSensor.get("light");
        }
        catch (Exception e)
        {
            warnings.add("failed to map 'light'");
            DbgLog.msg(e.getLocalizedMessage());
            lightSensor = null;
        }
        lightSensor.enableLed(true);

        try {

            servoMen.setPosition(0.95);
        }
        catch (Exception e)
        {
            DbgLog.msg(e.getLocalizedMessage());
        }

        try {

            servoLock.setPosition(0);
        }
        catch (Exception e)
        {
            DbgLog.msg(e.getLocalizedMessage());
        }

        try {
            waitForStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set_drive_power(1.0,1.0);
        try {
            sleep(4400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set_drive_power(0,0);
        set_drive_power(0.8,-0.2);

        try {
            sleep(2700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        set_drive_power(-0.5,-0.5);

        try {
            sleep(1240);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set_drive_power(0,0);

        /*
        set_drive_power(0,0);

        set_drive_power(0.3,0.6);
        while(lightSensor.getLightDetected()>0.6)
        {

        }

        set_drive_power(0.6,0.3);
        while(lightSensor.getLightDetected()>0.6)
        {

        }
        set_drive_power(0,0);
        */
        servoMen.setPosition(0);



    }

    void set_drive_power (double p_left_power, double p_right_power)

    {
        if (motorDriveLeft != null)
        {
            motorDriveLeft.setPower(p_left_power);
        }
        if (motorDriveRight != null)
        {
            motorDriveRight.setPower(p_right_power);
        }

    } // set_drive_power




}
