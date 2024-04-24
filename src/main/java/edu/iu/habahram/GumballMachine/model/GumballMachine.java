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
        if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        }
        else{
            message = "Please crank the machine";
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
        else {
            m = "There is no Quarter to eject";
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
        else {
            m = "You need to pay first";
        }
        return new TransitionResult(finish, m, state, count);
    }

    public TransitionResult dispense(){
        boolean finish = false;
        String m = "";
        if(state.equalsIgnoreCase(SOLD)){
            m = "dispensing ball";
            releaseBall();
            state = NO_QUARTER;
            finish = true;
        }
        else{
            m = "You need to crank first";
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
