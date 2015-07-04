
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prasannav
 */

public class Percolation {
   
   public static double SCALEFACTOR =  (0.075);
   public static double pointerSize = (0.050);
   public static int window_width = 500;
   public static int window_height = 500;
   int[][] model; // original matrix
   int _N; // to store size of Matrix N*N
   int virtualTop;
   int virtualBottom;
   WeightedQuickUnionUF _qf;
   // Public Api
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
       if(N<0){ throw new IndexOutOfBoundsException("N should be > 0"); }
        
       _N = N;
       model = new int[N][N];
       _qf = new WeightedQuickUnionUF((N * N) + 2);
       virtualTop = N*N;
       virtualBottom = virtualTop+1;
       init(); 
   }
   
    void init()
    { // o(N2) matrix initalization 
        for(int i=0;i<_N;i++)
            for(int j=0;j<_N;j++)            
                model[i][j] = 0; //model[i][j] = pos(i, j);            
        prepareToDraw(); 
        print(model, _N);
    }
   
   public boolean open(int i, int j)          // open site (row i, column j) if it is not open already
   {   /* --------- Hint ---- about open a new site
       So, now a site is successfully opened. 
       CheckForPossible Adjacent Sites
       Filter out Adjacent open sites , out of closed ones.
       Connect it with adjacent Sites
       */       
       checkBoundsAndThrow(i);
       checkBoundsAndThrow(j);       
       if(!isOpen(i, j))
       {  
        model[i][j] = 1; // To Open a site        
        adjacentSitesPredictor(i,j); 
        draw(i, j,(model[i][j] == 1) ? true : false);
        return true;
       }
       else
       {return false;}              
   }
   
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
       return model[i][j] == 1; // If a site is open, it is suposed to be 1
   }
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
      boolean status = false;
       /* This full site checking module , 
       picks up a single open site, 
       and check for the adjacent (left, right,up, down) opened sites,
       if(there are adjacent opened sites) && if( this site is connected with an another open adjacent site)
       Then it is said to be a full site   
        */
       checkBoundsAndThrow(i);
       checkBoundsAndThrow(j);
       
         if(_qf.connected(virtualTop, pos(i,j)))
         {
             status = true;
         }
   return status; 
   }
   public boolean percolates()             // does the system percolate?
   {       
       return _qf.connected(virtualTop, virtualBottom);
   }

   // test client (optional)
   public static void main(String[] args )
   {
       Stopwatch watch = new Stopwatch();
       Percolation p = new Percolation(StdIn.readInt());
       WeightedQuickUnionUF uf = new WeightedQuickUnionUF(p._N * p._N);
       p.openSitesRandomly();
       
       StdOut.println(watch.elapsedTime());
   }
   
   // util methods 
   
   // Operational Utils Methods
   int pos(int i, int j)
   {
       return ((_N*i - _N+j) + _N);
   }
   
   void checkBoundsAndThrow(int i) 
   {
           if (i < 0 || i >= _N) throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (_N-1));   
   }
   
   void openSitesRandomly()
   {      
       int opensitesCount=0;
       int i=0;
       boolean openAnotherSite = true;
       while(openAnotherSite){
         int a=RandomPos();
         int b=RandomPos();
         
         if(open(a,b))
            {
                opensitesCount++;
                sleepBaby(300);
                if(isFull(a, b))
                   {drawIsFull(a,b);}
                sleepBaby(1000);
                
                if(percolates() == true)
                   {   
                   openAnotherSite = false;
                   StdOut.println("Iterations "+i +" of "+ (_N*_N));
                   StdOut.println("Percolateing with opensites ="+ opensitesCount);
                   StdOut.println("Percolation Thresold is "+ (((double)opensitesCount) / ((double)(_N*_N))));
                                   bruteForceDraw(_N);
                   break;
                   }
            }           
         i++;
       }    
   }
   
   
     
   void adjacentSitesPredictor(int i, int j)
   {   
   // given a site , this module can predict adjacent possible sites and help u with possible sites
   // Give a site, there are fout possible sites at most
    int _i = i;
    int _j = j;
    // right possible
   if(j+1 < _N)
   {       _i = i;       _j = j+1;
    if(isOpen(_i, _j)){
       unionAdjacentOpenedSiteWithThisNewOpenSite(i, j, _i, _j);
       
    }
   } 
    // left possible
   if(j-1 >= 0)
   {       _i = i;       _j = j-1;
   if(isOpen(_i, _j)){
      unionAdjacentOpenedSiteWithThisNewOpenSite(i, j, _i, _j);      
   }
   }
   // down possible
   if(i+1 < _N)
   {       _i = i+1;       _j = j;
   if(isOpen(_i, _j)){
       unionAdjacentOpenedSiteWithThisNewOpenSite(i, j, _i, _j);
   }
   }
       // up possible   
   if(i-1 >= 0)
   {       _i = i-1;       _j = j;
   if(isOpen(_i, _j)){
       unionAdjacentOpenedSiteWithThisNewOpenSite(i, j, _i, _j);      
   }
   }
   
   if(i == 0){
               unionVirtualTopOrBottom(pos(i,j), virtualTop); 
   }
   
   if(i == _N-1 )
   {
               unionVirtualTopOrBottom(pos(i,j), virtualBottom);
               
   }

   
}
 
   void unionVirtualTopOrBottom(int x, int y){
       if(y == virtualBottom){
          //   StdOut.println("V_Bottom");
       }else 
       { 
           //StdOut.println("V_Top");  // potentially dangerous comment
       }
       _qf.union(x, y);
    if(_qf.connected(x, y))
    { 
       // StdOut.println("Result of vtop/btm  is true for "+x+","+y);
    }
    }
   
   void unionAdjacentOpenedSiteWithThisNewOpenSite(int i, int j, int p, int q){
       // i,j is this site, where p,q is adj site
       //_qf.union(model[i][j], model[p][q]);
       _qf.union(pos(i, j), pos(p, q));
   
      if(_qf.connected(pos(i, j), pos(p, q))){
       //StdOut.println("Result true for "+pos(i,j)+","+pos(p,q));
        }
   }
   
   void isFullHelper(int i, int j, int p, int q)
   {
       drawIsFull(p,q);
   }
   
   
   int RandomPos()
   {
       return StdRandom.uniform(_N);
   }
   
   void sleepBaby(int seconds){
   try {     Thread.sleep(seconds);      }
   catch (InterruptedException ex) { Logger.getLogger(Percolation.class.getName()).log(Level.SEVERE, null, ex);}  
   }
   
   // Visual related methods
   
   void bruteForceDraw(int size){
     
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {             
               if(isFull(i, j)){
                   drawIsFull(i, j);
                  
               }
               else if(isOpen(i, j)){
                   draw(i, j, true);
               }else
               {
                   draw(i, j, false);                   
               }
           }
       }
   
   }
   void print(int[][] matrix, int size)
   {
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {             
               draw(i, j,(matrix[i][j] == 1) ? true : false);
           }
       }
   } 
   
   void draw(int x, int y, boolean isOpended)
   {    
    double _x = (double)x;
    double _y = (double)x;
    _x = (x * SCALEFACTOR) + 0.05;
    _y = (y * SCALEFACTOR) + 0.05;
    
        if(isOpended)
            StdDraw.setPenColor(Color.WHITE);
        else
            StdDraw.setPenColor(Color.black);
       
    //StdDraw.point(_x, _y);
    StdDraw.point(_y, _x);
   }
   
    void drawIsFull(int x, int y)
   {    
    double _x = (double)x;
    double _y = (double)x;
    _x = (x * SCALEFACTOR) + 0.05;
    _y = (y * SCALEFACTOR) + 0.05;
    StdDraw.setPenColor(Color.BLUE);       
    //StdDraw.point(_x, _y);
    StdDraw.point(_y, _x);
   }
   
   
   void prepareToDraw(){
       StdDraw.setCanvasSize(window_width, window_height);
       StdDraw.setPenRadius(pointerSize);
       StdDraw.clear(Color.GRAY);
   }
   
  
   

   // To Remove
   void clearAndRedraw() // Not needed, right now
   {
       StdDraw.clear();      
       prepareToDraw();
       print(model, _N);
   }
    
}


