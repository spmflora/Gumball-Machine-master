package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean finish = false;
        String m = "";
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            m = "Quarter Ejected Successfully";
            state = NO_QUARTER;
            finish = true;
        }
        else if(state.equalsIgnoreCase(NO_QUARTER)){
            m = "There is no quarter to eject";
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            m = "There is no quarter to eject because the machine is sold out";
        }
        else if(state.equalsIgnoreCase(SOLD)){
            m = "Please do not try to shortchange the machine";
        }
        return new TransitionResult(finish, m, state, count);
    }

    @Override
    public TransitionResult turnCrank() {
        boolean finish = false;
        String m = "";
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            m = "Turning Crank";
            state = SOLD;
            finish = true;
        }
        else if(state.equalsIgnoreCase(NO_QUARTER)){
            m = "There is no quarter to turn the crank";
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            m = "There is no quarter to crank because the machine is sold out";
        }
        else if(state.equalsIgnoreCase(SOLD)){
            m = "Please do not try to shortchange the machine";
        }
        return new TransitionResult(finish, m, state, count);
    }

    public TransitionResult dispense(){
        boolean finish = false;
        String m = "";
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            m = "Please turn the crank to continue";
        }
        else if(state.equalsIgnoreCase(NO_QUARTER)){
            m = "Please insert a quarter then turn the crank to continue";
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            m = "There is no ball to dispense because the machine is sold out";
        }
        else if(state.equalsIgnoreCase(SOLD)){
            m = "dispensing ball";
            releaseBall();
            state = NO_QUARTER;
            finish = true;
        }
        return new TransitionResult(finish, m, state, count);
    }
    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }


}
