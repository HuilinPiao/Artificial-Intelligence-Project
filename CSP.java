import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSP {
    static ArrayList<Variable> var = new ArrayList<Variable>();
    //    static ArrayList<Variable> assigned = new ArrayList<Variable>();
    static ArrayList<Variable> unassigned = new ArrayList<Variable>();
    static String type;


    public ArrayList<Variable> backtracking() {
        int idealSize;
        if (type.equals("1")) {
            idealSize = 7;
        } else {
            idealSize = 15;
        }

        if (Assignment.assigned.size() == idealSize) {
            return Assignment.assigned;
        }

        if(type.equals("1")){
            unassigned.get(0).assignment = unassigned.get(0).colors.get(0);
            if (Constraints.unaryConstraint(unassigned.get(0))) {
                Assignment.assigned.add(unassigned.get(0));
                unassigned.remove(0);
                backtracking();

            } else {
                unassigned.get(0).colors.remove(0);
                backtracking();
            }
        }
        else {

            unassigned.get(0).assign_time = unassigned.get(0).domain.get(0);

            if(Constraints.binaryConstraint(unassigned.get(0))) {
                Assignment.assigned.add(unassigned.get(0));
                unassigned.remove(0);
                backtracking();

            } else {
                unassigned.get(0).domain.remove(0);
                backtracking();
		    }
        }
        return Assignment.assigned;

    }

    public static void main(String[] args) throws FileNotFoundException {
        type = args[0];
        ArrayList<String> domain = new ArrayList<String>();
        if(type.equals("1")) {
            CSP m = new CSP();
		//reading files
		File file = new File(args[1]);
		Scanner scr = new Scanner(file);

		int lineCount = 0 ;
		//iterate the file and build up the variable, domain and constrains.
		while(scr.hasNext()) {
			//keep track of number of lines read from the input file
			lineCount++;
			String nextSen = scr.nextLine();
			// first line is the variables
			if(lineCount==2) {
				String[]names = nextSen.split(" ");
				for (String i:names){
					Variable newState = new Variable(i);
					m.var.add(newState);

				}
			}
			//second to eighth line is the neighboring states
			else if(lineCount>2&&lineCount<=9){
				String[]n = nextSen.split(" ");
				ArrayList<Variable>neigh = new ArrayList<Variable>();
				for(int i=1;i<n.length;i++) {
					for (Variable j:m.var) {
						if(j.name.equals(n[i])) {
							neigh.add(j);
						}
					}
				}
				for (Variable i:m.var) {
					if(i.name.equals(n[0])) {
						i.neighbor = neigh;
					}
				}
			}
			else if(lineCount==10) {
				String[] colors = nextSen.split(" ");
				for (String i:colors) {
					domain.add(i);
				}
			}
			else {
				String[]temp = nextSen.split(" ");
				if(temp[1].equals("=")) {
					for(Variable s:m.var) {
						if(s.name.equals(temp[0])) {
							s.colors.clear();
							s.colors.add(temp[2]);
						}
					}
				}
				else if(temp[1].equals("!=")) {
					for(Variable s:m.var) {
						if(s.name.equals(temp[0])) {
							s.colors.remove(temp[2]);
						}
					}
				}
			}

		}
		m.unassigned = m.var;
		m.backtracking();
            for (Variable i:Assignment.assigned) {
			System.out.println(i.name+" "+i.assignment);
		}

	}
        else {
            File file = new File(args[1]);
            Scanner scr = new Scanner(file);
            CSP j = new CSP();
            int lineCount = 0;
            while (scr.hasNext()) {
                //keep track of number of lines read from the input file
                lineCount++;
                String nextSen = scr.nextLine();
                // first line is the variables
                if (lineCount == 2) {
                    String[] names = nextSen.split(" ");
                    for (String i : names) {
                        Variable newJobs = new Variable(i);
                        j.var.add(newJobs);

                    }
                }
                //second to eighth line is the neighboring states
                else if (lineCount > 2 && lineCount <= 17) {
                    String[] n = nextSen.split(" ");
                    for (Variable i : j.var) {
                        if (i.name.equals(n[0])) {
                            i.complete_time = Integer.parseInt(n[1]);
                        }
                    }

                } else if (lineCount == 18) {
                    int time = Integer.parseInt(nextSen);
                    for (Variable job : j.var) {
                        for (int i = 0; i <= time; i++) {
                            job.domain.add(i);
                        }
                    }
                } else {
                    String[] temp = nextSen.split(" ");
                    if (temp[1].equals("before")) {
                        for (Variable s : j.var) {
                            if (s.name.equals(temp[0])) {
                                for (Variable k : j.var) {
                                    if (k.name.equals(temp[2])) {
                                        s.before_constrain.add(k);
                                        k.after_constrain.add(s);
                                    }
                                }
                            }
                        }
                    } else if (temp[1].equals("disjoint")) {
                        for (Variable s : j.var) {
                            if (s.name.equals(temp[0])) {
                                for (Variable k : j.var) {
                                    if (k.name.equals(temp[2])) {
                                        s.disjoint_constrain.add(k);
                                        k.disjoint_constrain.add(s);
                                    }
                                }
                            }
                        }
                    }
                }

            }

            j.unassigned = j.var;
            j.backtracking();
            for (Variable i : Assignment.assigned) {
                System.out.println(i.name + " " + i.assign_time);
            }
        }
    }
}
