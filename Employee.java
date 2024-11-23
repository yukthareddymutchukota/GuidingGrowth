// DEFINE EMPLOYEE CLASS
class Employee {
    // EMPLOYEE ATTRIBUTES
    float productivity, satisfaction; // EMPLOYEE PRODUCTIVITY AND SATISFACTION LEVELS
    float moneyNeed;                  // EMPLOYEE FINANCIAL NEED
    color empColor;                   // EMPLOYEE COLOR REPRESENTATION
    int x, y;                         // EMPLOYEE POSITION IN GRID
    int salary;                       // EMPLOYEE SALARY
    float baseProductivity = 0.0;     // BASELINE PRODUCTIVITY LEVEL
    float productivityMomentum = 0;   // MOMENTUM FOR PRODUCTIVITY CHANGES
    float satisfactionMomentum = 0;   // MOMENTUM FOR SATISFACTION CHANGES
    float stressLevel;                // EMPLOYEE STRESS LEVEL
    float motivationLevel;            // EMPLOYEE MOTIVATION LEVEL

    // CONSTRUCTOR: INITIALIZE EMPLOYEE WITH GIVEN PARAMETERS
    Employee(float initialProductivity, float initialSatisfaction, float moneyNeed, color initialColor, int x, int y) {
        // SET PRODUCTIVITY AND SATISFACTION LEVELS
        this.productivity = initialProductivity;
        this.satisfaction = initialSatisfaction;
        // SET FINANCIAL NEED
        this.moneyNeed = moneyNeed;
        // SET INITIAL COLOR
        this.empColor = initialColor;
        // SET POSITION IN GRID
        this.x = x;
        this.y = y;
        // SET INITIAL SALARY
        this.salary = employeeSalary;
    }

    // RESPOND TO MANAGEMENT DECISIONS AND MARKET CONDITIONS
    void respondToManagement(StockMarket market) {
        float productivityChange = 0; // CHANGE IN PRODUCTIVITY
        float satisfactionChange = 0; // CHANGE IN SATISFACTION

        // RESPONSE TO MONITORING TIME
        productivityChange += (monitoringTime * 0.1) - 0.5;
        satisfactionChange += 0.5 - (monitoringTime * 0.1);
        

        // RESPONSE TO COLLABORATION WILLINGNESS
        productivityChange += (collaborationWillingness * 0.1) - 0.2;
        satisfactionChange += (collaborationWillingness * 0.1) - 0.3;
        

        // RESPONSE TO DEADLINE STRICTNESS
        productivityChange += (deadlineStrictness * 0.1) - 0.5;
        satisfactionChange += 0.5 - (deadlineStrictness * 0.1);


        // RESPONSE TO SALARY
        float salaryEffect = map(employeeSalary, 30000, 90000, -0.3, 0.3);
        productivityChange += salaryEffect * (1 + moneyNeed);
        satisfactionChange += salaryEffect * (1 + moneyNeed);

        // APPLY CHANGES WITH MOMENTUM
        productivityMomentum += (productivityChange - productivityMomentum) * 0.1;
        satisfactionMomentum += (satisfactionChange - satisfactionMomentum) * 0.1;

        // UPDATE PRODUCTIVITY AND SATISFACTION
        productivity += productivityMomentum;
        satisfaction += satisfactionMomentum;

        // FACTOR IN STRESS AND MOTIVATION
        productivity = constrain(productivity, baseProductivity, 10);
        satisfaction = constrain(satisfaction, 0, 10);

        // UPDATE EMPLOYEE SALARY
        this.salary = employeeSalary;

        // UPDATE COLOR BASED ON PRODUCTIVITY
        updateColor();
    }

    // DISPLAY EMPLOYEE ON THE GRID
    void display() {
        // SET EMPLOYEE COLOR
        fill(empColor);
        // DRAW EMPLOYEE AS A CIRCLE
        ellipse(x, y, 40, 40);
    }

    // UPDATE EMPLOYEE COLOR BASED ON PRODUCTIVITY
    void updateColor() {
        // CALCULATE RED VALUE BASED ON LOW PRODUCTIVITY
        float redValue = map(10 - productivity, 0, 10, 0, 255);
        // CALCULATE GREEN VALUE BASED ON HIGH PRODUCTIVITY
        float greenValue = map(productivity, 0, 10, 0, 255);
        // SET EMPLOYEE COLOR
        empColor = color(redValue, greenValue, 0);
    }
}