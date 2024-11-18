class Manager {
    String managerType; // Type of manager determined by chosen settings
    int monitoringTime; // Time spent monitoring employees (scale 1-10)
    int deadlineStrictness; // Deadline enforcement level (scale 1-10)
    int collaborationWillingness; // Willingness to collaborate with employees (scale 1-10)
    int baseSalary; // Base salary set for employees
    boolean isPaused; // Pause simulation flag
    boolean isReset; // Reset simulation flag

    // Constructor
    Manager(String managerType, int monitoringTime, int deadlineStrictness, int collaborationWillingness, int baseSalary) {
        this.managerType = managerType;
        this.monitoringTime = monitoringTime;
        this.deadlineStrictness = deadlineStrictness;
        this.collaborationWillingness = collaborationWillingness;
        this.baseSalary = baseSalary;
        this.isPaused = false;
        this.isReset = false;
    }

  // Method to adjust management strategies based on stock market trend
    void adjustStrategy(StockMarket stockMarket) {
        if (stockMarket.trend.equals("Rising")) {
            collaborationWillingness = constrain(collaborationWillingness + 1, 0, 10);
            println("Manager becomes more open to collaboration due to a strong market.");
        } else if (stockMarket.trend.equals("Declining")) {
            monitoringTime = constrain(monitoringTime + 1, 0, 10);
            deadlineStrictness = constrain(deadlineStrictness + 1, 0, 10);
            println("Manager increases monitoring and enforces stricter deadlines due to market decline.");
        }
    }

    // Method to display manager stats on screen
    void displayManagerStats() {
        fill(0);
        textSize(14);
        text("Manager Type: " + managerType, 10, 20);
        text("Monitoring Time: " + monitoringTime, 10, 40);
        text("Deadline Strictness: " + deadlineStrictness, 10, 60);
        text("Collaboration Willingness: " + collaborationWillingness, 10, 80);
        text("Base Salary: $" + baseSalary, 10, 100);
    }

    // Method to determine manager type based on parameter settings
    void determineManagerType() {
        if (monitoringTime < 3 && deadlineStrictness < 3 && collaborationWillingness > 6) {
            managerType = "Laissez-Faire";
        } else if (monitoringTime > 6 && deadlineStrictness > 6 && collaborationWillingness < 3) {
            managerType = "Authoritative";
        } else if (monitoringTime == 5 && deadlineStrictness == 5 && collaborationWillingness == 5) {
            managerType = "Autocratic";
        } else {
            managerType = "Adaptive Manager";
        }
    }

    // Display manager stats and adjustments
    void displayManagerStatsInConsole() {
        println("Manager Type: " + managerType);
        println("Monitoring Time: " + monitoringTime);
        println("Deadline Strictness: " + deadlineStrictness);
        println("Collaboration Willingness: " + collaborationWillingness);
        println("Employee Salary: $" + baseSalary);
    }

    // Method to adjust employee productivity based on management style
    void manageEmployees(ArrayList<Employee> employees) {
        for (Employee e : employees) {
            e.updateStats(monitoringTime, deadlineStrictness, collaborationWillingness);

            // Special case: collaborate if collaborationWillingness is high
            if (collaborationWillingness > 7) {
                e.collaborate();
            }
        }
    }

    // Method to pause the simulation
    void pauseSimulation() {
        isPaused = true;
    }

    // Method to resume the simulation
    void resumeSimulation() {
        isPaused = false;
    }

    // Method to reset the simulation
    void resetSimulation() {
        isReset = true;
        // Reset parameters if necessary
    }

    // Adjust manager settings (could be tied to UI controls)
    void adjustMonitoringTime(int newTime) {
        this.monitoringTime = constrain(newTime, 1, 10);
        determineManagerType(); // Re-evaluate manager type
    }

    void adjustDeadlineStrictness(int newStrictness) {
        this.deadlineStrictness = constrain(newStrictness, 1, 10);
        determineManagerType();
    }

    void adjustCollaborationWillingness(int newWillingness) {
        this.collaborationWillingness = constrain(newWillingness, 1, 10);
        determineManagerType();
    }

    void adjustBaseSalary(int newSalary) {
        this.baseSalary = max(30000, newSalary); // Ensure salary is reasonable
    }
}