public class Matrix
{
  public static int ROW_MAJOR_ORDER = 1;
  public static int COLUMN_MAJOR_ORDER = 2;
  private int m;
  private int n;
  private double [][] data;

  public Matrix(int m, int n)
  {
    this.m = m;
    this.n = n;
    this.data = this.createMatrix(m, n);
  }

public Matrix(int m, int n, double [] dataArray, int mode)
  {
    this.m = m;
    this.n = n;
    this.data = this.createMatrix(m, n);
    int k = 0;
    if(mode == Matrix.ROW_MAJOR_ORDER)
    {
       for(int i = 0; i < m; i++)
      {
        for(int j = 0; j < n; j++)
        {
            this.data[i][j] = dataArray[k];
            k++;
        }
      }
    }
    else{
       for(int j = 0; j < n; j++)
      {
        for(int i = 0; i < m; i++)
        {
            this.data[i][j] = dataArray[k];
            k++;
        }
      }
    }

  }

  public String toString()
  {
    String msg = "";
     for(int i = 0; i < m; i++)
      {
        for(int j = 0; j < n; j++)
        {
            msg = msg + this.data[i][j] + " ";
        }
        msg = msg + "\n";
      }
      return msg;
  }


  private double [][] createMatrix(int m, int n)
  {
    double [][] temp = new double[m][n];
    for(int i = 0; i < m; i++)
    {
      for(int j = 0; j < n; j++)
      {
          temp[i][j] = 0.0;
      }
    }
    return temp;
  }

  public Matrix clone()
  {
    Matrix a = new Matrix(this.m, this.n);
    for(int i = 0; i < this.m; i++)
    {
      for(int j = 0; j < this.n; j++)
      {
        a.data[i][j] = this.data[i][j];
      }
    }
    return a;
  }

  public Matrix solveByGaussJordan(double epsilon)
  {
    Matrix abReduced = this.clone();
    for(int i = 0; i < this.m; i++)
    {
      if(Math.abs(abReduced.data[i][i]) < epsilon)
      {
        for(int k = i + 1; k < m; k++)
        {
          if(Math.abs(abReduced.data[k][i]) > epsilon)
          {
            // Intercambiar renglones va aquí: renglón k con i
            for(int j = 0; j < n; j++)
            {
              double temp = abReduced.data[k][j];
              abReduced.data[k][j] = abReduced.data[i][j];
              abReduced.data[i][j] = temp;
            }
            break;
          }
        }
        if(Math.abs(abReduced.data[i][i]) < epsilon)
        {
          //// Sistema de ecuaciones es o indeterminado o inconsistente
        }
      }
      double piv = abReduced.data[i][i];
      for(int j = 0; j < n; j++)
      {
        abReduced.data[i][j] = abReduced.data[i][j] / piv;
      }
      // Hacer ceros toda la columna del pivote
      for(int k = 0; k < m; k++)
      {
        if(k != i)
        {
          double factor = -abReduced.data[k][i];
          for(int j = 0; j < n; j++)
          {
            abReduced.data[k][j] = abReduced.data[i][j] * factor + abReduced.data[k][j]; 
          }
        }
      }
    }
    return abReduced;
  }

  /*public Matrix inversaByGaussJ(double epsilon) {
    this.solveByGaussJordan()
  }*/

  public static void matrisDispersa(double [] value, double [] row, double [] column) {

    int f = 0; //iterador de los arreglos

    double maxC = 0;

    for (int k = 0; k < column.length; k++) {

      if (column[k] > maxC) {
          maxC = column[k];
      }
      
    }

    for (int j = 0; j <= row[row.length - 1]; j++) {
      System.out.print("\n");
      for (int i = 0; i <= maxC; i++) {
        if (column[f] == i && row[f] == j) {
          System.out.print((int) value[f++] + " ");
        } else {
          System.out.print(0  + " ");
        }
        if (f == column.length) {
          f--;
        }
      }
    }


  }

}