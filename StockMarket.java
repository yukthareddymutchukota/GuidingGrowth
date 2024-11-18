class StockMarket {
    String trend; // Current trend of the stock market (e.g., "Stable", "Rising", "Declining")
    float fluctuationRate; // Rate at which the market changes
    float currentIndex; // Current stock index value (used for visualization)
    float[] values; // Array to store recent stock index points for graphing
    int numPoints; // Number of points displayed on the graph
    int x, y; // Position for drawing the graph
    int graphWidth, graphHeight; // Dimensions of the graph

    // Constructor
    StockMarket(String initialTrend, float initialFluctuationRate) {
        this.trend = initialTrend;
        this.fluctuationRate = initialFluctuationRate;
        this.currentIndex = 1000; // Starting index value
        this.numPoints = 50; // Number of points in the graph
        this.x = 400; // Example x-position on screen
        this.y = 300; // Example y-position on screen
        this.graphWidth = 300;
        this.graphHeight = 100;
        this.values = new float[numPoints];
        initializeGraph();
    }

    // Method to initialize graph points
    void initializeGraph() {
        for (int i = 0; i < numPoints; i++) {
            values[i] = currentIndex; // Start with a consistent baseline
        }
    }

    // Method to update the stock market trend and graph
    void update() {
        // Adjust current index based on trend
        switch (trend) {
            case "Rising":
                currentIndex += fluctuationRate * random(5, 10);
                break;
            case "Declining":
                currentIndex -= fluctuationRate * random(5, 10);
                break;
            case "Stable":
                currentIndex += random(-2, 2);
                break;
        }

        // Keep index within a reasonable range
        currentIndex = constrain(currentIndex, 800, 1200);

        // Shift values for the graph and add the new point
        for (int i = 1; i < numPoints; i++) {
            values[i - 1] = values[i];
        }
        values[numPoints - 1] = currentIndex;
    }

    // Method to display the stock market graph
    void display() {
        stroke(0);
        noFill();
        rect(x, y, graphWidth, graphHeight); // Draw graph boundary

        stroke(0, 100, 255); // Blue line for graph
        for (int i = 1; i < numPoints; i++) {
            float x1 = x + (i - 1) * (graphWidth / (numPoints - 1));
            float y1 = y + graphHeight - map(values[i - 1], 800, 1200, 0, graphHeight);
            float x2 = x + i * (graphWidth / (numPoints - 1));
            float y2 = y + graphHeight - map(values[i], 800, 1200, 0, graphHeight);
            line(x1, y1, x2, y2);
        }

        // Display the current trend and index value
        fill(0);
        textSize(12);
        text("Trend: " + trend, x + 10, y - 10);
        text("Current Index: " + int(currentIndex), x + 10, y + graphHeight + 20);
    }

    // Method to change the trend based on events
    void setTrend(String newTrend) {
        this.trend = newTrend;
    }

    // Impact on employees based on market condition
    void affectEmployees(ArrayList<Employee> employees) {
        for (Employee e : employees) {
            if (trend.equals("Rising")) {
                e.productivity += 0.5; // Slight boost
                e.satisfaction += 0.3; // Slight morale boost
            } else if (trend.equals("Declining")) {
                e.productivity -= 0.5; // Slight decrease
                e.satisfaction -= 0.3; // Slight morale drop
            }
            // Keep values within range
            e.productivity = constrain(e.productivity, 0, 10);
            e.satisfaction = constrain(e.satisfaction, 0, 10);
            e.updateColor(); // Update color based on new productivity
        }
    }

    // Method to notify the manager when the trend changes significantly
    void notifyManager(Manager manager) {
        if (trend.equals("Rising")) {
            println("Stock market rising: Manager may ease up on supervision.");
            manager.adjustStrategy(this);
        } else if (trend.equals("Declining")) {
            println("Stock market declining: Manager may tighten control.");
            manager.adjustStrategy(this);
        }
    }
}