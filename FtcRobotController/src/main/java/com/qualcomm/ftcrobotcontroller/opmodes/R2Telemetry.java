package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Braxton on 10/2/2015.
 */

/**
 *  Telemetry KEY
 *   00 - Important Message or Error Message
 *   01 - Left Drive Motor power and encoder count
 *   02 - Right Drive Motor power and encoder count
 *
 *
 *
 *
 */
public class R2Telemetry extends R2Hardware {

    public R2Telemetry()
    {

    }

    public void updateTelemetry()
    {
        if(!warnings.isEmpty())
        {
            String combined = "";
            for(String warning : warnings)
            {
                combined = (combined+ ", " + warning);
            }
            telemetry.addData("00",combined);
            telemetry.addData("01", "Test Message!");
            warnings.clear();
        }




    }


}
