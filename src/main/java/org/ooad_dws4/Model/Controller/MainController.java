package org.ooad_dws4.Model.Controller;

import org.ooad_dws4.Model.Common.Message;

public class MainController {

    public MainController() {
    }

    public void linkObjects(IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    private IOBridge ioBridge;

    public void inputEvent(int event) {
        // TODO implement here
        System.out.println("inputEvent() has been called from MainController : BUTTON NUMBER = "+event);

//    <TimeKeeping Mode>

//    Use Case 1. Change Mode           : button 1
//        this.ioBridge.outputEvent(new Message(11, "updateView",
//                new HashMap<String, String>() {{
//                    put("0","   ");
//                    put("1","3+ 253");
//                    put("3","01|3000");
//                    put("4","  TIMER   ");
//                }}));

//    Use Case 2. Activate Modes        : button 1/2/3/4/5
//        this.ioBridge.outputEvent(new Message(11, "updateView",
//                new HashMap<String, String>() {{
//                    put("0","   ");
//                    put("1","3+ 253");
//                    put("3","01|3000");
//                    put("4","  TIMER   ");
//                }}));
//    Use Case 6. Timekeeping           : no button             blink
//    Use Case 7. Change Date and Time  : button 1/2/3/4/5      blink
//    Use Case 8. Watch D-Day           : no button
//    Use Case 9. Change D-Day Index    : button 3/4
//    Use Case 28. Toggle Sound         : button 8
        this.ioBridge.outputEvent(new Message(10, "toggleMute", null));
//    Use Case 29. Watch Timekeeping    : no button
//    Use Case 30. Time Refresh         : no button

//    <D-Day Mode>
//    Use Case 3. Change D-Day Index    : button 3/4
//    Use Case 5. Toggle D-Day          : button 2
//    Use Case 4. Set Date of D-Day     : button 1/2/3/4/5      blink

//    <Timer Mode>
//    Use Case 10. Start Timer          : button 3
//    Use Case 11. Set Timer Time       : button 1/2/3/4/5      blink
//    Use Case 12. Pause Timer          : button 3
//    Use Case 13. Resume Timer         : button 3
//    Use Case 14. Reset Timer          : button 2

//    <Buzzing : Timer & Alarm>
//    Use Case 15. Start Buzzing        : no button
//    Use Case 16. Stop Buzzing         : all button
//    Use Case 20. Stop Buzzing         : all button
//    Use Case 21. Start Buzzing        : no button

//    <Alarm Mode>
//    Use Case 17. Change Alarm Index   : button 3/4
//    Use Case 18. Toggle Alarm         : button 2
//    Use Case 19. Set Alarm Time       : button 1/2/3/4/5

//    <Stopwatch Mode>
//    Use Case 22. Start Stopwatch      : button 3
//    Use Case 23. Pause Stopwatch      : button 3
//    Use Case 24. Reset Stopwatch      : button 2
//    Use Case 25. Resume Stopwatch     : button 3

//    <World Time Mode>
//    Use Case 26. Change City Index    : button 3/4
//    Use Case 27. Choose City          : button 2

    }

    public void defaultScreenTimerReset() {
        // TODO implement here
    }

    public void stopBuzzer() {
        // TODO implement here
    }

}