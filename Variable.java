import java.util.ArrayList;

public class Variable {
    String name;
        int assign_time;
        int complete_time;
        ArrayList<Variable> before_constrain;
        ArrayList<Variable> disjoint_constrain;
        ArrayList<Integer> domain;
        ArrayList<Variable> after_constrain;

        //storing the states that are next to this state
        ArrayList<Variable> neighbor;
        ArrayList<String> colors;
        String assignment = "";

    public Variable(String name) {
        this.name = name;
        if(CSP.type.equals("1")) {
            neighbor = new ArrayList<Variable>();
            colors = new ArrayList<String>();
            colors.add("r");
            colors.add("g");
            colors.add("b");
        }
        else {
            assign_time = 0;
            complete_time = 0;
            domain = new ArrayList<Integer>();
            before_constrain = new ArrayList<Variable>();
            disjoint_constrain = new ArrayList<Variable>();
            after_constrain = new ArrayList<Variable>();
        }

    }
}
