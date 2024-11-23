// IMPORT G4P LIBRARY FOR GUI CONTROLS
import g4p_controls.*;

// GLOBAL VARIABLES
ArrayList<Employee> employees;  
Manager manager;              
StockMarket stockMarket;      
int frameCounter = 0;

// GUI CONTROL VARIABLES
int monitoringTime = 5;
int collaborationWillingness = 5;
int deadlineStrictness = 5;
int employeeSalary = 50000;

// BUSINESS NAME AND SIMULATION STATE VARIABLES
String businessName = "Guiding Growth";
boolean isPaused;
boolean isReset;

// COMPANY REVENUE AND MARKET CONDITION VARIABLES
float companyRevenue = 0;
float marketCondition = 1.0;
float cashReserve = 1000000; // STARTUP CAPITAL

void setup() {
    // SET THE CANVAS SIZE
    size(1000, 600);
    // SET BACKGROUND COLOR
    background(#a6bed4);

    // INITIALIZE MANAGER WITH PRESET PARAMETERS
    manager = new Manager("Balanced");

    // INITIALIZE STOCK MARKET WITH PRESET TREND AND FLUCTUATIONS
    stockMarket = new StockMarket("Stable", 0.5);

    // CREATE EMPLOYEES WITH PRESET VALUES
    employees = new ArrayList<Employee>();
    int gridSize = 50;
    int startX = (width - gridSize * 10) / 2;
    int startY = (height - gridSize * 10) / 2;
    
    // CREATE 100 EMPLOYEES
    for (int i = 0; i < 100; i++) {
        // INITIALIZE EMPLOYEE ATTRIBUTES
        float initialProductivity = random(5, 10); // INCREASED INITIAL PRODUCTIVITY
        float initialSatisfaction = random(5, 10);
        float moneyNeed = random(0, 1);

        // CALCULATE EMPLOYEE POSITION IN THE GRID
        int x = startX + (i % 10) * gridSize + gridSize / 2;
        int y = startY + (i / 10) * gridSize + gridSize / 2;

        // SET INITIAL EMPLOYEE COLOR
        color initialColor = color(0, 255, 0);

        // CREATE AND ADD EMPLOYEE TO THE LIST
        Employee e = new Employee(initialProductivity, initialSatisfaction, moneyNeed, initialColor, x, y);
        employees.add(e);
    }
    
    // CREATE GUI ELEMENTS
    createGUI();
}

void draw() {
    // CHECK IF SIMULATION IS RUNNING AND NOT RESET
    if (!isPaused && !isReset) {
        // REFRESH BACKGROUND
        background(#a6bed4);

        // DRAW BUSINESS NAME
        drawBusinessName();

        // DRAW OFFICE GRID
        drawGrid();

        // UPDATE FRAME COUNTER
        frameCounter++;
        // PERFORM UPDATES EVERY 30 FRAMES
        if (frameCounter % 30 == 0) {
            // UPDATE STOCK MARKET
            stockMarket.update();

            // UPDATE EACH EMPLOYEE
            updateEmployees();

            // UPDATE COMPANY REVENUE
            updateCompanyRevenue();

            // UPDATE MANAGER STATS
            manager.updateManagerStats();
        }

        // DISPLAY EACH EMPLOYEE
        for (Employee e : employees) {
            e.display();
        }

        // DISPLAY STOCK MARKET
        stockMarket.display();

        // DRAW COMPANY STATS PANEL
        drawCompanyStatsPanel();
    } 
    // CHECK IF SIMULATION NEEDS RESETTING
    else if (isReset) {
        // RESET SIMULATION LOGIC
        resetSimulation();
        isReset = false;
    }
}

void updateEmployees() {
    // ITERATE THROUGH EACH EMPLOYEE
    for (int i = 0; i < employees.size(); i++) {
        Employee e = employees.get(i);
        float avgNeighborProductivity = 0;
        float avgNeighborSatisfaction = 0;
        int neighborCount = 0;

        // DEFINE NEIGHBOR CHECKING OFFSETS
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        // CHECK NEIGHBORS IN A GRID LAYOUT
        for (int j = 0; j < 8; j++) {
            int ni = i + dx[j] + dy[j] * 10; // ASSUMING 10X10 GRID
            if (ni >= 0 && ni < employees.size()) {
                Employee neighbor = employees.get(ni);
                avgNeighborProductivity += neighbor.productivity;
                avgNeighborSatisfaction += neighbor.satisfaction;
                neighborCount++;
            }
        }

        // CALCULATE AVERAGE NEIGHBOR ATTRIBUTES
        if (neighborCount > 0) {
            avgNeighborProductivity /= neighborCount;
            avgNeighborSatisfaction /= neighborCount;

            // APPLY PEER INFLUENCE
            e.productivity += (avgNeighborProductivity - e.productivity) * 0.01;
            e.satisfaction += (avgNeighborSatisfaction - e.satisfaction) * 0.01;
        }

        // UPDATE EMPLOYEE STATE BASED ON MANAGEMENT
        e.respondToManagement(stockMarket);
    }
}

void updateCompanyRevenue() {
    // INITIALIZE REVENUE VARIABLES
    float grossRevenue = 0;
    float totalSalaries = 0;
    float miscCosts = 0;
    
    // CALCULATE REVENUE AND SALARIES BASED ON EMPLOYEES
    for (Employee e : employees) {
        grossRevenue += e.productivity * 3000; // INCREASED TO $3000 PER PRODUCTIVITY POINT
        totalSalaries += e.salary;
    }
    
    // CALCULATE MISCELLANEOUS COSTS (15% OF GROSS REVENUE)
    miscCosts = grossRevenue * 0.15;
    
    // ADJUST REVENUE BASED ON STOCK MARKET TRENDS
    float marketEffect = 1.0;
    if (stockMarket.trend.equals("Rising")) {
        marketEffect = 1.05; // 5% INCREASE
    } else if (stockMarket.trend.equals("Declining")) {
        marketEffect = 0.95; // 5% DECREASE
    }
    
    // APPLY MANAGEMENT EFFECTIVENESS FACTOR
    float managementFactor = map(manager.managementEffectiveness, 0.5, 2.0, 0.9, 1.1);

    // SLOWLY ADJUST MARKET CONDITION
    marketCondition += random(-0.01, 0.01);
    marketCondition = constrain(marketCondition, 0.8, 1.2);

    // APPLY FINAL MARKET EFFECTS TO GROSS REVENUE
    grossRevenue *= marketEffect * marketCondition * managementFactor;

    // ENSURE MINIMUM REVENUE TO COVER COSTS
    float minimumRevenue = totalSalaries * 1.2; // 20% ABOVE TOTAL SALARIES
    grossRevenue = max(grossRevenue, minimumRevenue);

    // CALCULATE NET REVENUE
    float netRevenue = grossRevenue - totalSalaries - miscCosts;

    // UPDATE COMPANY CASH RESERVE
    cashReserve += netRevenue;

    // UPDATE COMPANY REVENUE WITH NET PROFIT
    companyRevenue = netRevenue;
}

// DRAW BUSINESS NAME
void drawBusinessName() {
    // SET TEXT PROPERTIES AND DRAW BUSINESS NAME
    fill(0);
    textAlign(CENTER, TOP);
    textSize(24);
    text(businessName, width/2, 10);
}

// DRAW OFFICE GRID
void drawGrid() {
    // SET STROKE COLOR AND WEIGHT
    stroke(0);
    strokeWeight(2);
    int gridSize = 50;
    int startX = (width - gridSize * 10) / 2;
    int startY = (height - gridSize * 10) / 2;
    
    // DRAW 10x10 GRID
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            fill(255);
            rect(startX + i * gridSize, startY + j * gridSize, gridSize, gridSize);
        }
    }
}

// DRAW COMPANY STATS PANEL
void drawCompanyStatsPanel() {
    // DRAW PANEL BACKGROUND
    fill(#052c50);
    noStroke();
    rect(0, 0, 220, height);

    // SET TEXT COLOR AND SIZE
    fill(255);
    textSize(18);
    textAlign(LEFT, TOP);
    
    // DRAW PANEL TITLE
    text("Company Statistics:", 10, 10);

    // DRAW COMPANY STATS
    textSize(14);
    text("Manager Type: " + manager.managerType, 10, 40);
    text("Monitoring Time: " + monitoringTime, 10, 60);
    text("Collaboration Willingness: " + collaborationWillingness, 10, 80);
    text("Deadline Strictness: " + deadlineStrictness, 10, 100);
    text("Employee Salary: $" + employeeSalary, 10, 120);
    text("Net Profit: $" + nf(companyRevenue, 0, 2), 10, 140);
    text("Cash Reserve: $" + nf(cashReserve, 0, 2), 10, 160);
    text("Avg Employee Satisfaction: " + nf(calculateAverageSatisfaction(), 1, 2), 10, 180);
    text("Avg Employee Productivity: " + nf(calculateAverageProductivity(), 1, 2), 10, 200);
}

// RESET SIMULATION
void resetSimulation() {
    frameCounter = 0;
    monitoringTime = 5;
    collaborationWillingness = 5;
    deadlineStrictness = 5;
    employeeSalary = 50000;
    companyRevenue = 0;
    marketCondition = 1.0;
    cashReserve = 1000000; // RESET CASH RESERVE
    
    for (Employee e : employees) {
        e.productivity = random(5, 10); // RESET TO HIGHER INITIAL PRODUCTIVITY
        e.satisfaction = random(5, 10);
        e.stressLevel = random(0, 5);
        e.motivationLevel = random(0, 5);
    }

    stockMarket = new StockMarket("Stable", 0.5);

    manager = new Manager("Balanced");
    manager.updateManagerStats();
}

// CALCULATE AVERAGE EMPLOYEE SATISFACTION
float calculateAverageSatisfaction() {
    float totalSatisfaction = 0;
    for (Employee e : employees) {
        totalSatisfaction += e.satisfaction;
    }
    
    return totalSatisfaction / employees.size();
}

// CALCULATE AVERAGE EMPLOYEE PRODUCTIVITY
float calculateAverageProductivity() {
    float totalProductivity = 0;
    for (Employee e : employees) {
        totalProductivity += e.productivity;
    }
    
    return totalProductivity / employees.size();
}