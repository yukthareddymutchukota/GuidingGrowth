class stock{
int x;
int y;
int numPoints;
int graphExtent;
float[] values;
stock(int x, int y, int n ,int w){
this.x = x;
this.y = y;
this.numPoints = n;
this.graphExtent = w;
float[] values = new float[numPoints];
}
void initialize(){
  int middle = graphExtent / 2;
  this.values[0] = middle;
  for (int i = 1; i < numPoints; i++) {
  this.values[i] = random(values[i - 1]-1,values[i - 1]+1);
  }
}
void update(){
  
for (int i = 1; i < numPoints; i++) {
    this.values[i - 1] = this.values[i];
}
/////// change this to have it change systematicall!!!!!!!
this.values[numPoints - 1] += random(-2, 2);
///////
this.values[numPoints - 1] = constrain(values[numPoints - 1], this.y , this.graphExtent);

}
void display(){
square(this.x,this.y, this.graphExtent);
 for (int i = 0; i < numPoints; i++) {
    line(i - 1,values[i -1],i,values[i]);
  }
}
}