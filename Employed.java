int employeeSize = 10;

class Employee{
int productivity;
int salery;
int satisfaction;
int x;
int y;
color colour;

Employee(int p, int s,int sa,color c,int x, int y){
this.productivity = p;
this.salery = s;
this.satisfaction = sa;
this.colour = c;
//////maybe
this.x = x;
this.y = y;
}

void display(){
color(this.colour);
circle(this.x,this.y,employeeSize);
}

void Consequinces(){
  //////////// colours
  if(this.productivity == 1){this.colour = color(255,0,0);}
  if(this.productivity == 2){this.colour = color(200,50,0);}
  if(this.productivity == 3){this.colour = color(200,100,0);}
  if(this.productivity == 4){this.colour = color(50,150,0);}
  if(this.productivity == 5){this.colour = color(0,255,0);}
  if(this.productivity == 6){this.colour = color(0,200,50);}
  if(this.productivity == 7){this.colour = color(0,150,100);}
  if(this.productivity == 8){this.colour = color(0,0,255);}
  if(this.productivity == 9){this.colour = color(100,50,170);}
  if(this.productivity == 10){this.colour = color(255,0,255);}
  //////////////
  this.satisfaction =  manager.willingnessToCooperate - ((manager.moniterTime + manager.deadlineStrictness)/2)/2;
  this.productivity = 1 (manager.moniterTime + manager.deadlineStrictness)/2 ;
  
}
}