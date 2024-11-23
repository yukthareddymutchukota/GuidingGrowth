// DEFINE STOCK MARKET CLASS
class StockMarket {
    // STOCK MARKET ATTRIBUTES
    String trend;                     // CURRENT TREND ("Rising", "Declining", "Stable")
    float fluctuationRate;            // MAXIMUM RANDOM FLUCTUATION RATE
    float[] values;                   // ARRAY TO STORE STOCK MARKET VALUES
    int numPoints = 100;              // TOTAL NUMBER OF POINTS FOR GRAPH
    float[] trendValues = new float[10]; // ARRAY FOR MOVING AVERAGE CALCULATION
    int trendIndex = 0;               // INDEX TO TRACK TREND VALUES ARRAY

    // CONSTRUCTOR: INITIALIZE STOCK MARKET WITH GIVEN TREND AND FLUCTUATION RATE
    StockMarket(String trend, float fluctuationRate) {
        this.trend = trend;                      // SET INITIAL TREND
        this.fluctuationRate = fluctuationRate;  // SET FLUCTUATION RATE

        // INITIALIZE STOCK VALUES ARRAY WITH DEFAULT STARTING VALUE
        values = new float[numPoints];
        for (int i = 0; i < numPoints; i++) {
            values[i] = 50; // START STOCK VALUES AT MIDPOINT (50)
        }

        // INITIALIZE TREND VALUES ARRAY WITH DEFAULT STARTING VALUE
        for (int i = 0; i < trendValues.length; i++) {
            trendValues[i] = 50; // START TREND VALUES AT MIDPOINT (50)
        }
    }

    // UPDATE STOCK MARKET VALUES
    void update() {
        // SHIFT ALL VALUES LEFT TO MAKE ROOM FOR NEW VALUE
        for (int i = 0; i < numPoints - 1; i++) {
            values[i] = values[i + 1]; // SHIFT VALUES ONE INDEX LEFT
        }

        // GENERATE NEW VALUE BASED ON RANDOM FLUCTUATION
        float change = random(-fluctuationRate, fluctuationRate); 
        values[numPoints - 1] = constrain(values[numPoints - 1] + change, 0, 100); 
        // ADD FLUCTUATION AND CONSTRAIN VALUE TO [0, 100]

        // UPDATE TREND VALUES ARRAY
        trendValues[trendIndex] = values[numPoints - 1]; // STORE LATEST VALUE IN TREND VALUES
        trendIndex = (trendIndex + 1) % trendValues.length; // CYCLE TREND INDEX

        // CALCULATE MOVING AVERAGE OF TREND VALUES
        float avgTrend = 0; // INITIALIZE SUM OF TREND VALUES
        for (float v : trendValues) {
            avgTrend += v; // SUM UP ALL TREND VALUES
        }
        avgTrend /= trendValues.length; // DIVIDE BY NUMBER OF TREND VALUES

        // DETERMINE TREND BASED ON MOVING AVERAGE AND LAST VALUE
        if (avgTrend > values[numPoints - 1] + 5) {
            trend = "Rising"; // TREND IS RISING IF AVERAGE IS SIGNIFICANTLY HIGHER
        } else if (avgTrend < values[numPoints - 1] - 5) {
            trend = "Declining"; // TREND IS DECLINING IF AVERAGE IS SIGNIFICANTLY LOWER
        } else {
            trend = "Stable"; // OTHERWISE, TREND IS STABLE
        }
    }

    // DISPLAY STOCK MARKET GRAPH
    void display() {
        // DRAW GRAPH BACKGROUND
        fill(255); // WHITE BACKGROUND
        rect(width - 220, 10, 210, 110); // DRAW RECTANGLE FOR GRAPH AREA

        // DRAW GRAPH LINE
        stroke(0);  // BLACK LINE COLOR
        noFill();   // NO FILL INSIDE THE GRAPH
        beginShape(); // BEGIN DRAWING SHAPE
        for (int i = 0; i < numPoints; i++) {
            float x = map(i, 0, numPoints - 1, width - 210, width - 20); 
            // MAP INDEX TO X POSITION
            float y = map(values[i], 0, 100, 110, 20); 
            // MAP VALUE TO Y POSITION (HIGHER VALUES CLOSER TO TOP)
            vertex(x, y); // ADD VERTEX TO SHAPE
        }
        endShape(); // FINISH SHAPE

        // DISPLAY CURRENT TREND TEXT
        fill(0); // BLACK TEXT
        textSize(14); // TEXT SIZE
        text("Stock Market: " + trend, width - 118, 140); 
        // DISPLAY TREND BELOW GRAPH
    }
}