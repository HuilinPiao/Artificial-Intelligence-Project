import java.util.ArrayList;

public class Constraints {

    public static boolean unaryConstraint(Variable s) {
                for (Variable i : s.neighbor) {
                    for (Variable j : Assignment.assigned) {
                        if (i.equals(j)) {
                            if (i.assignment.equals(s.assignment)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
        }

    //binary consistent
    public static boolean binaryConstraint(Variable j) {
        for (Variable i:j.before_constrain) {
            for(Variable job:Assignment.assigned) {
                if(i.name.equals(job.name)) {
                    if(j.assign_time+j.complete_time>job.assign_time) {
                        return false;
                    }
                }
            }
        }
        for(Variable i:j.disjoint_constrain) {
            for(Variable job:Assignment.assigned) {
                if(i.name.equals(job.name)) {
                    if(j.assign_time>=job.assign_time&&j.assign_time<job.assign_time+job.complete_time||
                            j.assign_time<=job.assign_time&&j.assign_time+j.complete_time>job.assign_time) {
                        return false;
                    }

                }
            }
        }
        for(Variable i:j.after_constrain) {
            for(Variable job:Assignment.assigned) {
                if(i.name.equals(job.name)) {
                    if(j.assign_time<job.assign_time+job.complete_time) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
