// STOCKMARKET CLASS SIMULATES MARKET CONDITIONS
class StockMarket {
    // STOCK MARKET ATTRIBUTES
    String trend;
    float fluctuationRate;
    float[] values;
    int numPoints = 100;

    // CONSTRUCTOR: INITIALIZE STOCK MARKET WITH GIVEN TREND AND FLUCTUATION RATE
    StockMarket(String trend, float fluctuationRate) {
        this.trend = trend;
        this.fluctuationRate = fluctuationRate;
        // INITIALIZE STOCK VALUES ARRAY
        values = new float[numPoints];
        for (int i = 0; i < numPoints; i++) {
            values[i] = 50;
        }
    }

    // UPDATE STOCK MARKET VALUES
    void update() {
        // SHIFT ALL VALUES TO THE LEFT
        for (int i = 0; i < numPoints - 1; i++) {
            values[i] = values[i + 1];
        }
        
        // GENERATE NEW VALUE WITH RANDOM FLUCTUATION
        float change = random(-fluctuationRate, fluctuationRate);
        values[numPoints - 1] = constrain(values[numPoints - 1] + change, 0, 100);

        // DETERMINE MARKET TREND BASED ON LATEST CHANGE
        if (change > 0) {
            trend = "Rising";
        } else if (change < 0) {
            trend = "Declining";
        } else {
            trend = "Stable";
        }
    }

    // DISPLAY STOCK MARKET GRAPH
    void display() {
        // DRAW GRAPH BACKGROUND
        fill(255);
        rect(width - 220, 10, 210, 110);

        // DRAW GRAPH LINE
        stroke(0);
        noFill();
        beginShape();
        for (int i = 0; i < numPoints; i++) {
            float x = map(i, 0, numPoints - 1, width - 210, width - 20);
            float y = map(values[i], 0, 100, 110, 20);
            vertex(x, y);
        }
        endShape();

        // DISPLAY CURRENT TREND
        fill(0);
        textSize(14);
        text("Stock Market: " + trend, width - 210, 140);
    }
}