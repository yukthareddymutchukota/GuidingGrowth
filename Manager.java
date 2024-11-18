// MANAGER CLASS REPRESENTS THE COMPANY'S MANAGEMENT STYLE
class Manager {
    // MANAGER ATTRIBUTES
    String managerType;
    boolean isPaused;
    boolean isReset;

    // CONSTRUCTOR: INITIALIZE MANAGER WITH GIVEN TYPE
    Manager(String managerType) {
        this.managerType = managerType;
        this.isPaused = false;
        this.isReset = false;
    }

    // UPDATE MANAGER TYPE BASED ON CURRENT MANAGEMENT SETTINGS
    void updateManagerStats() {
        // DETERMINE MANAGER TYPE BASED ON MONITORING, DEADLINE, AND COLLABORATION SETTINGS
        if (monitoringTime < 3 && deadlineStrictness < 3 && collaborationWillingness > 6) {
            managerType = "Laissez-Faire";
        } else if (monitoringTime > 6 && deadlineStrictness > 6 && collaborationWillingness < 3) {
            managerType = "Authoritative";
        } else if (monitoringTime == 5 && deadlineStrictness == 5 && collaborationWillingness == 5) {
            managerType = "Balanced";
        } else {
            managerType = "Unique";
        }
    }

    // METHOD TO PAUSE THE SIMULATION
    void pauseSimulation() {
        isPaused = true;
    }

    // METHOD TO RESET THE SIMULATION
    void resetSimulation() {
        isReset = true;
    }

    // METHOD TO RESUME THE SIMULATION
    void resumeSimulation() {
        isPaused = false;
        isReset = false;
    }
}