class stock{
int x;
int y;
int numPoints;
int graphWidth;
int graphHeight;
stock(int x, int y, int n ,int w, int h){
this.x = x;
this.y = y;
this.numPoints = n;
this.graphWidth = w;
this.graphHeight = h;
float[] values = new float[numPoints];
}
void update(){
  
for (int i = 1; i < numPoints; i++) {
    this.values[i - 1] = this.values[i];
}


}
void display(){}
//square(this.x,)

}
