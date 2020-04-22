class Main {
  public static void main(String[] args) {
    double [] dataArray1 = {1,1,1,3,2,3,7,0,1,3,-2,17};
    double [] dataArray2 = {0,1,2,4,2,1,0,-2,3,0,1,-1};
    double [] dataArray3 = {3,1,5,1,0,0,3,1,4,0,1,0,7,5,2,0,0,1};
    Matrix a = new Matrix(3, 6, dataArray3, Matrix.ROW_MAJOR_ORDER);
    /*System.out.println(a.toString());
    Matrix abReduced = a.solveByGaussJordan(0.00001);
    System.out.println(abReduced.toString());*/

    double [] valores = {9,8,4,2,5,2};
    double [] row     = {0,1,2,2,3,4};
    double [] column  = {4,1,0,2,5,2};

    Matrix.matrisDispersa(valores,row,column);
  }
}