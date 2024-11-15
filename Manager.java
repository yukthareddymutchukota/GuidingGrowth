class Manager {
    String managerType; //Manager type based on chosen settings
    int monitoringTime; //Time spent monitoring employees
    int deadlineStrictness; //Level of deadline strictness (1-10 scale)
    int collaborationWillingness; //Willingness to collaborate with employees (1-10 scale)
    int employeeSalary; //Salary given for employees
    boolean isPaused; //Pause simulation
    boolean isReset; //Reset simulation

    // Constructor
    Manager(String mt, int emt, int ds, int cw, int es) {
        this.managerType = mt;
        this.monitoringTime = emt;
        this.deadlineStrictness = ds;
        this.collaborationWillingness = cw;
        this.employeeSalary = es;
        this.isPaused = false;
        this.isReset = false;
    }

    // Method to pause the simulation
    void pauseSimulation() {
        isPaused = true;
    }

    // Method to reset the simulation
    void resetSimulation() {
        isReset = true;
    }

  
    // Display manager type
    void displayManagerType() {
        if (monitoringTime < 3 && deadlineStrictness < 3 && collaborationWillingness > 6) {
          managerType = "Laissez-Faire";
        }
        else if (monitoringTime > 6 && deadlineStrictness > 6 && collaborationWillingness < 3) {
          managerType = "Authortive";
        }
        else if (monitoringTime == 5 && deadlineStrictness == 5 && collaborationWillingness ==5) {
          managerType = "Autocratic";
        }
        else {
          managerType = "Unique Manager!";
        }
        println("Manager Type: " + managerType);
    }
}
