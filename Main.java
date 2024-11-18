// IMPORT G4P LIBRARY FOR GUI CONTROLS
import g4p_controls.*;

// GLOBAL VARIABLES
ArrayList<Employee> employees;  
Manager manager;              
StockMarket stockMarket;      
int frameCounter = 0;

// NEW GLOBAL VARIABLES FOR GUI CONTROL
int monitoringTime = 5;
int collaborationWillingness = 5;
int deadlineStrictness = 5;
int employeeSalary = 50000;

void setup() {
    // SET THE CANVAS SIZE
    size(1000, 600);
    // SET BACKGROUND COLOR
    background(#a6bed4);

    // INITIALIZE MANAGER WITH PRESET PARAMETERS
    manager = new Manager("Balanced");

    // INITIALIZE STOCK MARKET WITH PRESET TREND AND FLUCTUATIONS
    stockMarket = new StockMarket("Stable", 0.4);  // INCREASED FLUCTUATION RATE FOR MORE DYNAMIC DISPLAY

    // CREATE EMPLOYEES WITH PRESET VALUES
    employees = new ArrayList<Employee>();
    int gridSize = 50;
    int startX = (width - gridSize * 10) / 2;
    int startY = (height - gridSize * 10) / 2;
    
    // CREATE 100 EMPLOYEES
    for (int i = 0; i < 100; i++) {
        float initialProductivity = random(3, 7);
        float initialSatisfaction = random(3, 7);
        float moneyNeed = random(0, 1);

        int x = startX + (i % 10) * gridSize + gridSize / 2;
        int y = startY + (i / 10) * gridSize + gridSize / 2;

        color initialColor = color(0, 255, 0);

        Employee e = new Employee(initialProductivity, initialSatisfaction, moneyNeed, initialColor, x, y);
        employees.add(e);
    }
    
    // CREATE GUI ELEMENTS
    createGUI();
}

void draw() {
    // REFRESH BACKGROUND
    background(#a6bed4);

    // DRAW OFFICE GRID
    drawGrid();

    // UPDATE COUNTER
    frameCounter++;
    // UPDATE EVERY 30 FRAMES
    if (frameCounter % 30 == 0) {
        // UPDATE STOCK MARKET
        stockMarket.update();

        // UPDATE EACH EMPLOYEE
        for (Employee e : employees) {
            e.respondToManagement(stockMarket);
        }
    }

    // DISPLAY EACH EMPLOYEE
    for (Employee e : employees) {
        e.display();
    }

    // DISPLAY STOCK MARKET
    stockMarket.display();
    // UPDATE MANAGER STATS
    manager.updateManagerStats();
    // DRAW COMPANY STATS PANEL
    drawCompanyStatsPanel();
}

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

void drawCompanyStatsPanel() {
    // DRAW PANEL BACKGROUND
    fill(#052c50);
    noStroke();
    rect(0, 0, 215, height);

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
    text("Company Revenue: $" + nf(calculateCompanyRevenue(), 0, 2), 10, 140);
    text("Avg Employee Satisfaction: " + nf(calculateAverageSatisfaction(), 1, 2), 10, 160);
    text("Avg Employee Productivity: " + nf(calculateAverageProductivity(), 1, 2), 10, 180);
}

// CALCULATE COMPANY REVENUE
float calculateCompanyRevenue() {
    float revenue = 0;
    for (Employee e : employees) {
        revenue += e.productivity * 1000;
    }
    return revenue;
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