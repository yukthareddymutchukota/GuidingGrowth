// EMPLOYEE CLASS REPRESENTS INDIVIDUAL WORKERS IN THE SIMULATION
class Employee {
    // EMPLOYEE ATTRIBUTES
    float productivity, satisfaction;
    float moneyNeed;
    color empColor;
    int x, y;
    int salary;

    // CONSTRUCTOR: INITIALIZE EMPLOYEE WITH GIVEN PARAMETERS
    Employee(float initialProductivity, float initialSatisfaction, float moneyNeed, color initialColor, int x, int y) {
        this.productivity = initialProductivity;
        this.satisfaction = initialSatisfaction;
        this.moneyNeed = moneyNeed;
        this.empColor = initialColor;
        this.x = x;
        this.y = y;
        this.salary = employeeSalary;
    }

    // RESPOND TO MANAGEMENT DECISIONS AND MARKET CONDITIONS
    void respondToManagement(StockMarket market) {
        // ADJUST PRODUCTIVITY BASED ON MONITORING TIME
        productivity += map(monitoringTime, 0, 10, 0, 0.2);

        // ADJUST BASED ON COLLABORATION WILLINGNESS
        productivity += map(collaborationWillingness, 0, 10, 0, 0.2);
        satisfaction += map(collaborationWillingness, 0, 10, 0, 0.3);

        // ADJUST BASED ON DEADLINE STRICTNESS
        productivity += map(deadlineStrictness, 0, 10, 0, 0.2);
        satisfaction -= map(deadlineStrictness, 0, 10, 0, 0.3);

        // CALCULATE SALARY EFFECT ON PRODUCTIVITY AND SATISFACTION
        float salaryEffect = map(employeeSalary, 30000, 70000, -0.2, 0.2);
        productivity += salaryEffect * (1 + moneyNeed);
        satisfaction += salaryEffect * (1 + moneyNeed);

        // UPDATE EMPLOYEE SALARY
        this.salary = employeeSalary;

        // ADJUST BASED ON STOCK MARKET TRENDS
        if (market.trend.equals("Rising")) {
            productivity += 0.1;
            satisfaction += 0.1;
        } else if (market.trend.equals("Declining")) {
            productivity -= 0.1;
            satisfaction -= 0.1;
        }

        // IF SATISFACTION IS BELOW 4, DECREASE PRODUCTIVITY
        if (satisfaction < 4) {
            productivity -= map(4 - satisfaction, 0, 4, 0, 0.5);
        }

        // ENSURE PRODUCTIVITY AND SATISFACTION STAY WITHIN BOUNDS
        productivity = constrain(productivity, 0, 10);
        satisfaction = constrain(satisfaction, 0, 10);
    }

    // DISPLAY EMPLOYEE ON THE GRID
    void display() {
        // CALCULATE COLOR BASED ON PRODUCTIVITY AND SATISFACTION
        float redValue = map(satisfaction, 0, 10, 255, 0);
        float greenValue = map(productivity, 0, 10, 0, 255);
        empColor = color(redValue, greenValue, 0);

        // DRAW EMPLOYEE AS A CIRCLE
        fill(empColor);
        ellipse(x, y, 40, 40);
    }
}