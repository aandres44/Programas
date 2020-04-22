public class MatrixDispersa {

    public static double encontrarValor(double[] value, double[] row, double[] column, int i, int j) {
        
        int f = 0;
        boolean found = false;

        while(!found && f < row.length){
            if(row[f] == i){
                found == true;
            } else {
                f++;
            }
        }
        
        if (found && column[f] == j) {
            return value[f];
        } else {
            return 0;
        }
    
    }

    public static void sustituirValor(double[] value, double[] row, double[] column, int i, int j, double valor, epsilon) {
        
        int f = 0;
        boolean found = false;

        while(!found && f < row.length){
            if(row[f] == i){
                found == true;
            } else {
                f++;
            }
        }
        
        if (found && column[f] == j) {
            value[f];
        } else {
            0;
        }

        /*Posibilidades: 
        1 que valor sea 0 y no este en los arreglos el valor anterior; no se hace nada
        2 valor sea 0 y haya un valor en los arreglos; se borra de los arreglos y se cambia el tamano creando nuevos arreglos y pasando todo
        3 valor es dif de 0 y valor anterior es 0; se cambian los arreglos y se agrega el valor en el orden correspondiente
        4 valor dif de 0 y valor anterior dif de 0; simplemente se cambia el valor correspondiente
        */
        
        //Caso 1: 0, 0
        if(Math.abs(valor) < epsilon && Math.abs(encontrarValor(value, row, column, i, j)) < epsilon) {

        } else if(Math.abs(valor) < epsilon && Math.abs(encontrarValor(value, row, column, i, j)) > epsilon) { //Caso 2: 0, #

        } else if(Math.abs(valor) > epsilon && Math.abs(encontrarValor(value, row, column, i, j)) < epsilon) { //Caso 3: #, 0

        } else { //Caso 4: #, #
            value[f] = valor;
        }

        
    
    }

    public static void matrisDispersa(double [] value, double [] row, double [] column, epsilon) {

    int f = 0; //iterador de los arreglos

    int p = row[row.length - 1];


    for (int j = 0; j <= p; j++) {
      System.out.print("\n");
      for (int i = 0; i <= p; i++) {
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

    //Matrix abReduced = this.clone();
    for(int i = 0; i < p; i++)
    {
      if(Math.abs(encontrarValor(value, row, column, i, i)) < epsilon)
      {
        for(int k = i + 1; k < m; k++)
        {
          if(Math.abs(encontrarValor(value, row, column, k, i)) > epsilon)//k i
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
}