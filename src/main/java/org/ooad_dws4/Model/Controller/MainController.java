package org.ooad_dws4.Model.Controller;

public class MainController {
    int repeat2 = 0;
    int repeat4 = 0;

    public MainController() {
    }

    public void linkObjects(IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    private IOBridge ioBridge;

    public void inputEvent(int event) {
//        System.out.println("inputEvent() has been called from MainController : BUTTON NUMBER = "+event);

//    <TimeKeeping Mode>
// ------------------------------------- Scenario 1 -------------------------------------
//    Use Case 1. Change Mode           : button 1
//        if(event==1){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0","MON");
//                        put("1","3+ 253");
//                        put("3","06|2042");
//                        put("4","2020-05-04");
//                    }}));
//        }

// ------------------------------------- Scenario 2 -------------------------------------
//    Use Case 2. Activate Modes        : button 1/2/3/4/5/6
//        if(event==6){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0"," ON");
//                        put("1","   4/4");
//                        put("3"," MODE  ");
//                        put("4","  D_DAY   ");
//                    }}));
//        }
//        if(event==2){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0","OFF");
//                        put("1","   3/4");
//                    }}));
//        }
//        if(event==1){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0","MON");
//                        put("1","3+ 253");
//                        put("3","06|2042");
//                        put("4","2020-05-04");
//                    }}));
//        }

//    Use Case 6. Timekeeping           : no button             blink

// ------------------------------------- Scenario 3 -------------------------------------
//    Use Case 7. Change Date and Time  : button 1/2/3/4/5      blink
//        if(event==5){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0","EDT");
//                        put("blink","3");
//                    }}));
//        }
//        else if(event==2 && repeat2==0) {
//            repeat2++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("blink", "4");
//                    }}));
//        }
//        else if(event==4 && repeat4==0) {
//            repeat4++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("3", "06|2142");
//                    }}));
//        }
//        else if(event==4 && repeat4==1) {
//            repeat4++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("3", "06|2242");
//                    }}));
//        }
//        else if(event==3){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("3","06|2142");
//                    }}));
//        }
//        else if(event==2 && repeat2==1){
//            repeat2++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("blink","5");
//                    }}));
//        }
//        else if(event==2 && repeat2==2){
//            repeat2++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("blink","0");
//                    }}));
//        }
//        else if(event==2 && repeat2==3){
//            repeat2++;
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("blink","1");
//                    }}));
//        }
//        else if(event==1){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0", "   ");
//                        put("blink", null);
//                    }}));
//        }

//    Use Case 8. Watch D-Day           : no button

// ------------------------------------- Scenario 4 -------------------------------------
//    Use Case 9. Change D-Day Index    : button 3/4
//        if(event==3) {
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("1", "2-  35");
//                    }}));
//        }
//    Use Case 28. Toggle Sound         : button 8
//        if(event==8){
//            this.ioBridge.outputEvent(new Message(10, "toggleMute",
//                    null));
//        }

//    Use Case 29. Watch Timekeeping    : no button

//    Use Case 30. Time Refresh         : no button

//    <D-Day Mode>
// ------------------------------------- Scenario 5 -------------------------------------
//    Use Case 3. Change D-Day Index    : button 3/4
//        if(event==4){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0", "OFF");
//                        put("1", "4+  34");
//                        put("3", "D-DAY 4");
//                        put("4", "2020-05-02");
//                    }}));
//        }

//    Use Case 5. Toggle D-Day          : button 2
//        if(event==2){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0", " ON");
//                    }}));
//        }

// ------------------------------------- Scenario 6 -------------------------------------
//    Use Case 4. Set Date of D-Day     : button 1/2/3/4/5      blink
//        if(event==5){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("0", "EDT");
//                        put("blink", "0");
//                    }}));
//        }
//        if(event==2){
//            this.ioBridge.outputEvent(new Message(11, "updateView",
//                    new HashMap<String, String>() {{
//                        put("blink", "1");
//                    }}));
//        }

//    <Timer Mode>
//    Use Case 10. Start Timer          : button 3
//    Use Case 11. Set Timer Time       : button 1/2/3/4/5      blink
//    Use Case 12. Pause Timer          : button 3
//    Use Case 13. Resume Timer         : button 3
//    Use Case 14. Reset Timer          : button 2

//    <Buzzing : Timer & Alarm>
//    Use Case 15. Start Buzzing        : no button
//        if(event==4){
//            this.ioBridge.outputEvent(new Message(11, "buzzRinging", null));
//        }
//    Use Case 16. Stop Buzzing         : all button
//        if(event==3){
//            this.ioBridge.outputEvent(new Message(11, "buzzOff", null));
//        }
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