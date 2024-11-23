// DEFINE MANAGER CLASS
class Manager {
    // MANAGER ATTRIBUTES
    String managerType;               // CURRENT TYPE OF MANAGER (E.G., BALANCED, AUTHORITATIVE)
    float managementEffectiveness = 1.0; // MANAGER'S EFFECTIVENESS MULTIPLIER (DEFAULT 1.0)
    String previousManagerType = ""; // PREVIOUS MANAGER TYPE FOR EFFECTIVENESS TRACKING

    // CONSTRUCTOR: INITIALIZE MANAGER WITH GIVEN TYPE
    Manager(String managerType) {
        this.managerType = managerType; // SET INITIAL MANAGER TYPE
    }

    // UPDATE MANAGER TYPE AND STATS BASED ON CURRENT SETTINGS
    void updateManagerStats() {
        int totalScore = monitoringTime + collaborationWillingness + deadlineStrictness; 
        // CALCULATE TOTAL SCORE BASED ON MANAGEMENT METRICS

        // DETERMINE MANAGER TYPE BASED ON SCORES AND THRESHOLDS
        if (totalScore <= 9) { 
            managerType = "Laissez-Faire"; // MINIMAL INVOLVEMENT IN MANAGEMENT
        } 
        else if (totalScore >= 24) {
            managerType = "Authoritative"; // HIGHLY STRICT MANAGEMENT STYLE
        } 
        else if (monitoringTime >= 4 && monitoringTime <= 6 &&
                 collaborationWillingness >= 4 && collaborationWillingness <= 6 &&
                 deadlineStrictness >= 4 && deadlineStrictness <= 6) {
            managerType = "Balanced"; // WELL-BALANCED MANAGEMENT STYLE
        } 
        else if (collaborationWillingness > 7) {
            managerType = "Collaborative"; // EMPHASIS ON TEAMWORK AND COOPERATION
        } 
        else if (deadlineStrictness > 7) {
            managerType = "Deadline-Driven"; // STRONG FOCUS ON MEETING DEADLINES
        } 
        else if (monitoringTime > 7) {
            managerType = "Micromanager"; // OVERLY FOCUSED ON CLOSE SUPERVISION
        } 
        else {
            managerType = "Unique"; // MANAGEMENT STYLE THAT DOESN'T FIT THE OTHER CATEGORIES
        }

        // UPDATE MANAGEMENT EFFECTIVENESS
        if (managerType.equals(previousManagerType)) { 
            // INCREASE EFFECTIVENESS IF MANAGER TYPE IS CONSISTENT
            managementEffectiveness = min(2.0, managementEffectiveness * 1.05); 
        } else {
            // DECREASE EFFECTIVENESS IF MANAGER TYPE CHANGES
            managementEffectiveness = max(0.5, managementEffectiveness * 0.95); 
        }

        previousManagerType = managerType; // STORE CURRENT TYPE AS PREVIOUS FOR NEXT UPDATE
    }

    // METHOD TO PAUSE THE SIMULATION
    void pauseSimulation() {
        isPaused = true; // SET GLOBAL PAUSE FLAG TO TRUE
    }

    // METHOD TO RESUME THE SIMULATION
    void resumeSimulation() {
        isPaused = false; // SET GLOBAL PAUSE FLAG TO FALSE
    }

    // METHOD TO RESET THE SIMULATION
    void resetSimulation() {
        isReset = true; // SET GLOBAL RESET FLAG TO TRUE
    }
}