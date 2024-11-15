// GLOBAL VARIABLES
ArrayList < Employee > employees;  
Manager manager;              
StockMarket stockMarket;      
//hey
void setup () {
  size(800, 600);
  background(255);
  
  // INITIALIZING MANAGER WITH HOW LONG THEY LAST --> STILL NEED TO CODE
  manager = new Manager(/);
  
  // INITIALIZING STOCKMARKET
  stockMarket = new StockMarket( "Stable" , 0.5);
  // Initial trend is "Stable"
  // fluctuations = 0.5 (rate of change)

  // CREATING EMPLOYEERS
  employees = new ArrayList < Employee > ();
  for ( int i = 0; i < 10; i++ ) {
    // ADDING 10 EMPLOYEES
    float initialProductivity = random ( 0, 11 );
    float initialSatisfaction = random ( 0, 11 );
    int initialSalary = 50000;                  

    Employee e = new Employee ( initialProductivity, initialSalary, initialSatisfaction );
    employees.add ( e );
  }
}

void draw () {
  // CLEARS THE SCREEN EACH FRFAME
  background ( 255 );
  
  // DISPLAYS MANAGER
  manager.displayManagerStats();

  // UPDATES AND DISPLAYS STOCK MARKET
  stockMarket.update();
  stockMarket.display();

  }
}
