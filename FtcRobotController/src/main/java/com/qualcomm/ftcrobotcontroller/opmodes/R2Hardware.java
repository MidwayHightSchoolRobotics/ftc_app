package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

/**
 * Created by Braxton on 10/2/2015.
 */
public class R2Hardware extends OpMode
{


    public ArrayList<String> warnings;

    private DcMotor motorDriveLeft;
    private DcMotor motorDriveRight;


    public R2Hardware()
    {

    }

    @Override
    public void init()
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

    }

    @Override
    public void loop()
    {

    }

    @Override
    public void stop()
    {

    }


}
