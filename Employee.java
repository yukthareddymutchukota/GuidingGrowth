class Employee {
    float productivity; // Productivity level (0-10 scale)
    int salary; // Salary amount
    float satisfaction; // Satisfaction level (0-10 scale)
    int x, y; // Position on the screen
    color displayColor; // Color representing productivity
    boolean isCollaborating; // If the employee is currently collaborating

    // Constructor
    // Constructor with color parameter added
    Employee(float initialProductivity, int initialSalary, float initialSatisfaction, color displayColor, int x, int y) {
        this.productivity = initialProductivity;
        this.salary = initialSalary;
        this.satisfaction = initialSatisfaction;
        this.x = x;
        this.y = y;
        this.displayColor = displayColor; // Initialize with the given color
        this.isCollaborating = false;
        updateColor(); // Initialize color based on productivity
    }


    // Method to display the employee on the screen
    void display() {
        fill(this.displayColor);
        circle(this.x, this.y, 10); // Display as a circle with a size of 10
    }

    // Method to update productivity and satisfaction based on management changes
    void updateStats(int managerMonitoringTime, int deadlineStrictness, int collaborationLevel) {
        // Satisfaction affected by collaboration level and monitoring time
        this.satisfaction = collaborationLevel * 1.2 - (managerMonitoringTime + deadlineStrictness) * 0.5;
        this.satisfaction = constrain(this.satisfaction, 0, 10); // Keep within range
        
        // Productivity is influenced by salary and satisfaction
        this.productivity = (salary / 5000) + (satisfaction * 0.5) - (deadlineStrictness * 0.3);
        this.productivity = constrain(this.productivity, 0, 10); // Keep within range
        
        updateColor(); // Update color based on new productivity
    }

    // Method to update the display color based on productivity levels
    void updateColor() {
        if (this.productivity >= 8) {
            this.displayColor = color(0, 255, 0); // Green for high productivity
        } else if (this.productivity >= 5) {
            this.displayColor = color(255, 165, 0); // Orange for moderate productivity
        } else {
            this.displayColor = color(255, 0, 0); // Red for low productivity
        }
    }

    // Method to simulate collaboration, boosting productivity temporarily
    void collaborate() {
        this.isCollaborating = true;
        this.satisfaction += 1.5; // Temporary boost in satisfaction
        this.productivity += 1.0; // Temporary boost in productivity
        this.satisfaction = constrain(this.satisfaction, 0, 10);
        this.productivity = constrain(this.productivity, 0, 10);
    }

    // Method to handle consequences of a management change
    void handleConsequences(int managerMonitoringTime, int deadlineStrictness) {
        updateStats(managerMonitoringTime, deadlineStrictness, manager.collaborationWillingness);
    }

    void respondToManagement(Manager manager, StockMarket stockMarket) {
        // Adjust productivity and satisfaction based on management parameters and market conditions
        float baseProductivity = (manager.monitoringTime + manager.deadlineStrictness) / 2;
        float collaborationBoost = (manager.collaborationWillingness > 6) ? 1.5 : 1.0;

        if (stockMarket.trend.equals("Rising")) {
            productivity += collaborationBoost; // Positive market influence
            satisfaction += 0.5; // Slight morale increase
        } else if (stockMarket.trend.equals("Declining")) {
            productivity -= 1; // Drop due to negative market
            satisfaction -= 0.5; // Morale decline
        }

        // Recalculate and constrain productivity and satisfaction
        productivity = constrain(productivity, 0, 10);
        satisfaction = constrain(satisfaction, 0, 10);
        updateColor(); // Ensure the color matches new productivity
    }
}