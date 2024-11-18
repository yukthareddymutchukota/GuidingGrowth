// GLOBAL VARIABLES
ArrayList<Employee> employees;  
Manager manager;              
StockMarket stockMarket;      

void setup() {
    size(800, 600);
    background(255);

    // INITIALIZING MANAGER WITH PRESET PARAMETERS
    manager = new Manager("Balanced", 5, 5, 5, 50000);

    // INITIALIZING STOCK MARKET WITH PRESET TREND AND FLUCTUATIONS
    stockMarket = new StockMarket("Stable", 0.5);

    // CREATING EMPLOYEES WITH PRESET VALUES
    employees = new ArrayList<Employee>();
    for (int i = 0; i < 10; i++) {
        // ADDING 10 EMPLOYEES WITH INITIAL PARAMETERS
        float initialProductivity = random(3, 7); // Mid-range productivity
        float initialSatisfaction = random(3, 7); // Mid-range satisfaction
        int initialSalary = 50000; // Base salary

        // Positioning employees in a grid layout
        int x = (i % 5) * 150 + 50; // Horizontal spacing
        int y = (i / 5) * 150 + 50; // Vertical spacing

        color initialColor = color(0, 255, 0); // Default color for productive employees

        // Create and add the new employeeEmployee e = new Employee(initialProductivity, initialSalary, initialSatisfaction, initialColor, x, y);
        Employee e = new Employee(initialProductivity, initialSalary, initialSatisfaction, initialColor, x, y);
        employees.add(e);
    }
}

void draw() {
    // CLEARS THE SCREEN EACH FRAME
    background(255);

    // DISPLAYS MANAGER STATUS AND PARAMETERS
    manager.displayManagerStats();

    // UPDATES AND DISPLAYS THE STOCK MARKET GRAPH
    stockMarket.update();
    stockMarket.display();

    // UPDATES AND DISPLAYS EACH EMPLOYEE
    for (Employee e : employees) {
        e.respondToManagement(manager, stockMarket); // Adjust productivity and satisfaction
        e.display(); // Draw each employee on the screen
    }
}