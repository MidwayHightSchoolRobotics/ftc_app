package com.qualcomm.ftcrobotcontroller.opmodes;

import java.util.Objects;

/**
 * Created by BraxtonL_Williams on 1/4/2016.
 */
public class R2AutoPrimary extends R2Telemetry{

    public R2AutoPrimary ()

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


    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //

    } // start

    @Override public void loop ()

    {
       if(this.time <=2)
       {
           set_drive_power(1.0f,1.0f);
       }
        else
       {
           set_drive_power(0,0);
       }



    }


}
